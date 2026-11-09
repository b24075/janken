package oit.is.z3398.kaizi.janken.controller;

import java.security.Principal;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestParam;
import oit.is.z3398.kaizi.janken.model.*;

@Controller
public class JankenController {
  @Autowired
  private Entry entry;

  @Autowired
  private UserMapper userMapper;

  @Autowired
  private MatchMapper matchMapper;

  @GetMapping("/janken")
  public String janken(Principal prin, ModelMap model) {
    String loginUser = prin.getName();
    this.entry.addUser(loginUser);
    model.addAttribute("entry", this.entry);

    ArrayList<User> users = userMapper.selectAllByUserName();
    model.addAttribute("users", users);

    ArrayList<Match> matches = matchMapper.selectAllMatches();
    model.addAttribute("matches", matches);

    return "janken.html";
  }

  @GetMapping("/match")
  public String moveMatch(@RequestParam int id, ModelMap model) {
    User user = userMapper.selectById(id);
    model.addAttribute("user", user);
    return "match.html";
}

  @GetMapping("/fight")
  public String fight(@RequestParam String hand, @RequestParam int id,
      Principal prin, ModelMap model) {
    // 自分のユーザー情報(ログイン名から取得)
    User me = userMapper.selectByName(prin.getName());

    // CPU の手(課題では固定でよいので Gu に固定)
    String cpuHand = "Gu";

    // matches テーブルに登録
    Match match = new Match();
    match.setUser1(me.getId());
    match.setUser2(id);
    match.setUser1Hand(hand);
    match.setUser2Hand(cpuHand);
    matchMapper.insertMatch(match);

    // 勝敗判定
    String result = judge(hand, cpuHand);

    // match.html に表示する値
    model.addAttribute("myHand", hand);
    model.addAttribute("cpuHand", cpuHand);
    model.addAttribute("result", result);

    // 相手の情報も入れ直す(/match と同じ名前で入れる)
    model.addAttribute("user", userMapper.selectById(id));

    return "match.html";
  }

  private String judge(String my, String cpu) {
    if (my.equals(cpu)) {
      return "Draw";
    }
    if ((my.equals("Gu") && cpu.equals("Choki")) ||
        (my.equals("Choki") && cpu.equals("Pa")) ||
        (my.equals("Pa") && cpu.equals("Gu"))) {
      return "You Win!";
    }
    return "You Lose";
  }

}
