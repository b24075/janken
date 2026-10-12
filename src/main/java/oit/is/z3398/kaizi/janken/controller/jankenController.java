package oit.is.z3398.kaizi.janken.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import oit.is.z3398.kaizi.janken.model.Janken;

@Controller
public class jankenController {
  @GetMapping("/janken")
  public String janken() {
    return "janken.html";
  }

  @PostMapping("/janken")
  public String janken(@RequestParam String name, ModelMap model) {

    model.addAttribute("name", name);
    return "janken.html";
  }

  @GetMapping("/jankengame")
  public String jankengame(@RequestParam String hand, ModelMap model) {
    Janken janken = new Janken(hand);
    model.addAttribute("playerHand", janken.getPlayerHand()); // あなたの手
    model.addAttribute("cpuHand", janken.getCpuHand()); // 相手の手
    model.addAttribute("result", janken.getResult()); // 結果
    return "janken.html";
  }

}
