package oit.is.z3398.kaizi.janken.controller;

import java.security.Principal;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import oit.is.z3398.kaizi.janken.model.Janken;
import oit.is.z3398.kaizi.janken.model.UserMapper;
import oit.is.z3398.kaizi.janken.model.Entry;
import org.springframework.ui.ModelMap;
import oit.is.z3398.kaizi.janken.model.User;

@Controller
public class JankenController {
  @Autowired
  private Entry entry;

  @Autowired
  private UserMapper userMapper;

  @GetMapping("/janken")
  public String janken(Principal prin, ModelMap model) {
    String loginUser = prin.getName();
    this.entry.addUser(loginUser);
    model.addAttribute("entry", this.entry);

    ArrayList<User> users = userMapper.selectAllByUserName();

    model.addAttribute("users", users);

    return "janken.html";
  }
}
