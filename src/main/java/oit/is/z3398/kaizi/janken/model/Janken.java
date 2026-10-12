package oit.is.z3398.kaizi.janken.model;

public class Janken {

  private String playerHand; // 1. あなたの手
  private String cpuHand; // 2. 相手の手
  private String result; // 3. 結果

  public Janken(String playerHand) {
    // 1. クリックした手を「あなたの手」にする
    this.playerHand = playerHand;

    // 2. CPUの手はグーで固定
    this.cpuHand = "グー";

    // 3. 勝ち・負け・あいこを判定する
    if (playerHand.equals(cpuHand)) {
      this.result = "あいこ";
    } else if (playerHand.equals("パー")) {
      this.result = "勝ち"; // パー vs グー
    } else {
      this.result = "負け"; // チョキ vs グー
    }
  }

  public String getPlayerHand() {
    return playerHand;
  }

  public String getCpuHand() {
    return cpuHand;
  }

  public String getResult() {
    return result;
  }

}
