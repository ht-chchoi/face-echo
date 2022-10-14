package com.ht.faceecho.service;

import com.ht.faceecho.data.Member;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EchoService {
  private final SimpleDateFormat createDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");

  private final Map<String, Member> memberMap = new HashMap<>();
  private final Map<String, Member> visitorMap = new HashMap<>();

  public Object getMembers(final String type, final Map<String, String> reqBody) {
    switch (type) {
      case "member":
        return this.memberMap.values().stream()
            .filter(member -> reqBody.get("dong").equals(member.getDong()) && reqBody.get("ho").equals(member.getHo()))
            .collect(Collectors.toList());
      case "visitor":
        return this.visitorMap.values().stream()
            .filter(member -> reqBody.get("dong").equals(member.getDong()) && reqBody.get("ho").equals(member.getHo()))
            .collect(Collectors.toList());
      default:
        throw new RuntimeException("memberType not exist");
    }

  }

  public Object registerMember(final String type, final boolean isConfirm, final Member member) {
    member.setImg(null);
    member.setFid(UUID.randomUUID().toString());
    member.setCreateDate(this.createDateFormat.format(new Date()));
    if (isConfirm) {
      member.setApproveYN(1);
      member.setApproveId(member.getRequestId());
    } else {
      member.setApproveYN(0);
    }
    switch (type) {
      case "member":
        this.memberMap.put(member.getFid(), member);
        return member.getFid();
      case "visitor":
        this.visitorMap.put(member.getFid(), member);
        return member.getFid();
      default:
        throw new RuntimeException("memberType not exist");
    }
  }

  public String updateConfirm(final boolean isConfirm, final Map<String, String> reqBody) {
    Member member = null;
    if (this.memberMap.containsKey(reqBody.get("fid"))) {
      member = this.memberMap.get(reqBody.get("fid"));
    }
    if (this.visitorMap.containsKey(reqBody.get("fid"))) {
      member = this.visitorMap.get(reqBody.get("fid"));
    }

    if (member == null) {
      return "id not found";
    }

    if (isConfirm) {
      member.setApproveYN(1);
      member.setApproveId(reqBody.get("approveId"));
      return "confirmed";
    } else {
      member.setApproveYN(0);
      member.setApproveId(reqBody.get("approveId"));
      return "registration cancled";
    }
  }

  public String deleteMember(final Map<String, String> reqBody) {
    boolean isExist = false;
    if (this.memberMap.containsKey(reqBody.get("fid"))) {
      this.memberMap.remove(reqBody.get("fid"));
      isExist = true;
    }
    if (this.visitorMap.containsKey(reqBody.get("fid"))) {
      this.visitorMap.remove(reqBody.get("fid"));
      isExist = true;
    }

    if (isExist) {
      return "deleted";
    }
    return "id not found";
  }
}
