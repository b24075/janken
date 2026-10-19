package oit.is.z3398.kaizi.janken.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import oit.is.z3398.kaizi.janken.model.Janken;
import oit.is.z3398.kaizi.janken.model.Entry;
import org.springframework.ui.ModelMap;

@Controller
public class JankenController {
  @Autowired
  private Entry entry;

  @GetMapping("/janken")
  public String janken(Principal prin, ModelMap model) {
    String loginUser = prin.getName();
    this.entry.addUser(loginUser);
    model.addAttribute("entry", this.entry);

    return "janken.html";
  }
}
