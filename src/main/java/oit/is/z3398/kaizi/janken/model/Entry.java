package oit.is.z3398.kaizi.janken.model;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

@Component
public class Entry {
  ArrayList<String> users = new ArrayList<>();
  int EntryNo = 1;

  public void addUser(String name) {
    // 同名のユーザが居たら何もせずにreturn
    for (String s : this.users) {
      if (s.equals(name)) {
        return;
      }
    }
    // 同名のユーザが居なかった場合はusersにnameを追加する
    this.users.add(name);
  }

  public ArrayList<String> getUsers() {
    return users;
  }

}
