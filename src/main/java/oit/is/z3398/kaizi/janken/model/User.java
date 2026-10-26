package oit.is.z3398.kaizi.janken.model;

public class User {
  private int id;
  private String userName;

  /**
   * ユーザーIDを取得します。
   *
   */
  public int getId() {
    return id;
  }

  /**
   * ユーザーIDを設定します。
   *
   */
  public void setId(int id) {
    this.id = id;
  }

  /**
   * ユーザー名を取得します。
   *
   */
  public String getUserName() {
    return userName;
  }

  /**
   * ユーザー名を設定します。
   *
   */
  public void setUserName(String userName) {
    this.userName = userName;
  }

}
