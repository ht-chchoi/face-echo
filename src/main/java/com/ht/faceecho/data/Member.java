package com.ht.faceecho.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Member {
  private String fid;
  private String name;
  private String dong;
  private String ho;
  private String birthDate;
  private String phone;
  private Integer approveYN;
  private String createDate;

  private String img;
  private String requestId;
  private String approveId;

  private String startDate;
  private String endDate;
  private String info;
}
