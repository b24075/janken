package oit.is.z3398.kaizi.janken.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class JankenController {
  @GetMapping("/janken")
  public String janken() {
    return "janken.html";
  }
}
