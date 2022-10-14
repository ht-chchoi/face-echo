package com.ht.faceecho.controller;

import com.ht.faceecho.data.Member;
import com.ht.faceecho.service.EchoService;
import com.ht.faceecho.ui.DefaultUI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/ht/api")
public class EchoController {
  @Autowired
  private DefaultUI defaultUI;
  @Autowired
  private EchoService echoService;

  private final SimpleDateFormat resDateFormat = new SimpleDateFormat("yyyyMMdd HH:mm:ss");

  @PostMapping("/get_household_members")
  public Object getHouseholdMembers(@RequestBody final Map<String, String> reqBody) {
    this.defaultUI.appendLog("POST /ht/api/get_household_members  req >>  ", reqBody);
    Map<String, Object> res = new HashMap<>();
    res.put("members", this.echoService.getMembers("member", reqBody));
    res.put("status", "200");
    this.defaultUI.appendLog("res >>  ", res);
    return res;
  }

  @PostMapping("/get_household_visitors")
  public Object getHouseholdVisitors(@RequestBody final Map<String, String> reqBody) {
    this.defaultUI.appendLog("POST /ht/api/get_household_visitors  req >>  ", reqBody);
    Map<String, Object> res = new HashMap<>();
    res.put("members", this.echoService.getMembers("visitor", reqBody));
    res.put("status", "200");
    this.defaultUI.appendLog("res >>  ", res);
    return res;
  }

  @PostMapping("/reg_member")
  public Object registerMember(@RequestBody final Member member) {
    member.setImg("{마스킹}");
    this.defaultUI.appendLog("POST /ht/api/reg_member  req >>  ", member);
    Map<String, Object> res = new HashMap<>();
    res.put("datetime", this.resDateFormat.format(new Date()));
    res.put("fid", this.echoService.registerMember("member", false, member));
    res.put("status", "200");
    this.defaultUI.appendLog("res >>  ", res);
    return res;
  }

  @PostMapping("/reg_member_confirm")
  public Object registerMemberConfirm(@RequestBody final Member member) {
    member.setImg("{마스킹}");
    this.defaultUI.appendLog("POST /ht/api/reg_member_confirm  req >>  ", member);
    Map<String, Object> res = new HashMap<>();
    res.put("datetime", this.resDateFormat.format(new Date()));
    res.put("fid", this.echoService.registerMember("member", true, member));
    res.put("status", "200");
    this.defaultUI.appendLog("res >>  ", res);
    return res;
  }

  @PostMapping("/reg_visitor")
  public Object registerVisitor(@RequestBody final Member member) {
    member.setImg("{마스킹}");
    this.defaultUI.appendLog("POST /ht/api/reg_visitor  req >>  ", member);
    Map<String, Object> res = new HashMap<>();
    res.put("datetime", this.resDateFormat.format(new Date()));
    res.put("fid", this.echoService.registerMember("visitor", false, member));
    res.put("status", "200");
    this.defaultUI.appendLog("res >>  ", res);
    return res;
  }

  @PostMapping("/reg_visitor_confirm")
  public Object registerVisitorConfirm(@RequestBody final Member member) {
    member.setImg("{마스킹}");
    this.defaultUI.appendLog("POST /ht/api/reg_visitor_confirm  req >>  ", member);
    Map<String, Object> res = new HashMap<>();
    res.put("datetime", this.resDateFormat.format(new Date()));
    res.put("fid", this.echoService.registerMember("visitor", true, member));
    res.put("status", "200");
    this.defaultUI.appendLog("res >>  ", res);
    return res;
  }

  @PostMapping("/reg_confirm")
  public Object registerConfirm(@RequestBody final Map<String, String> reqBody) {
    this.defaultUI.appendLog("POST /ht/api/reg_confirm  req >>  ", reqBody);
    String result = this.echoService.updateConfirm(true, reqBody);
    Map<String, Object> res = new HashMap<>();
    res.put("msg", result);
    res.put("status", result.equals("confirmed") ? "200" : "400");
    this.defaultUI.appendLog("res >>  ", res);
    return res;
  }

  @PostMapping("/reg_cancel")
  public Object registerCancel(@RequestBody final Map<String, String> reqBody) {
    this.defaultUI.appendLog("POST /ht/api/reg_cancel  req >>  ", reqBody);
    String result = this.echoService.updateConfirm(false, reqBody);
    Map<String, Object> res = new HashMap<>();
    res.put("msg", result);
    res.put("status", result.equals("registration cancled") ? "200" : "400");
    this.defaultUI.appendLog("res >>  ", res);
    return res;
  }

  @PostMapping("/del_member")
  public Object deleteMember(@RequestBody final Map<String, String> reqBody) {
    this.defaultUI.appendLog("POST /ht/api/del_member  req >>  ", reqBody);
    String result = this.echoService.deleteMember(reqBody);
    Map<String, Object> res = new HashMap<>();
    res.put("msg", result);
    res.put("status", result.equals("deleted") ? "200" : "400");
    this.defaultUI.appendLog("res >>  ", res);
    return res;
  }
}
