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

}
