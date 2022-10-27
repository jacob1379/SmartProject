package com.example.demo.seller.entity;

import java.time.*;

import com.example.demo.seller.dto.SsellerDto;
import com.example.demo.seller.dto.SsellerDto.Read;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Sseller {
	private String sId;
	private String sPassword;
	private String sName;
	private String sEmail;
	private LocalDate sBirth;
	private LocalDate sJoinday;
	private String sProfile;
	private String sRole;
	private Integer sLoginFailCnt;
	private Boolean sEnabled;
	private String sCheckcode;
	private Integer sBuyCount;
	private Integer sBuyMoney;
	private sLevel sLevels;
	private Integer sPhone;
	private Integer sStoreNum;
	private String sBusinessNum;

	public void sAddJoinInfo(String profileName, String checkcode, String encodePassword, sLevel sLevels) {
		this.sProfile = profileName;
		this.sCheckcode = checkcode;
		this.sPassword = encodePassword;
		this.sLevels = sLevels;
	}

	public Read toDto() {
		return SsellerDto.Read.builder()
				.sId(sId)
				.sName(sName)
				.sEmail(sEmail)
				.sBirth(sBirth)
				.sJoinday(sJoinday)
				.slevels(sLevels)
				.build();
	}
}
