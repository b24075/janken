package oit.is.z3398.kaizi.janken.service;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import oit.is.z3398.kaizi.janken.model.Match;
import oit.is.z3398.kaizi.janken.model.MatchMapper;

@Service
public class AsyncKekka {
  private boolean dbUpdated = false;
  private final Logger logger = LoggerFactory.getLogger(AsyncKekka.class);

  @Autowired
  MatchMapper matchMapper;

  // 試合成立時に呼ぶ:matches に登録して更新フラグを立てる
  public void syncInsertMatch(Match match) {
    matchMapper.insertMatch(match);
    this.dbUpdated = true;
  }

  // 待機中のプレイヤーに結果を非同期で送る
  @Async
  public void asyncShowKekka(SseEmitter emitter, int userId) {
    try {
      while (true) {
        if (!dbUpdated) {
          TimeUnit.MILLISECONDS.sleep(500);
          continue;
        }
        Match match = matchMapper.selectActiveMatchByUser1(userId);
        if (match == null) { // 自分以外の試合の更新だった
          TimeUnit.MILLISECONDS.sleep(500);
          continue;
        }
        emitter.send(match); // 結果を送信(JSON に変換される)
        TimeUnit.MILLISECONDS.sleep(1000);
        dbUpdated = false;
        matchMapper.updateIsActiveFalse(match.getId()); // 表示済みにする
        break;
      }
    } catch (Exception e) {
      logger.warn("Exception:" + e.getClass().getName() + ":" + e.getMessage());
    } finally {
      emitter.complete();
    }
  }
}
