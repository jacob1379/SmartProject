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
	private String sBusinessNum;
	private String sPassword;
	private String sName;
	private LocalDate sBirth;
	private Integer sPhone;
	private String sEmail;
	private Integer sStoreNum;
	private LocalDate sJoinday;
	private String sRole;
	private Integer sLoginFailCnt;
	private Boolean sEnabled;
	private String sCheckcode;
	private sLevel sLevel;

	public void sAddJoinInfo(String checkcode, String encodePassword, sLevel sLevels) {
		this.sCheckcode = checkcode;
		this.sPassword = encodePassword;
		this.sLevel = sLevels;
	}

	public Read toDto() {
		return SsellerDto.Read.builder()
				.sId(sId).sName(sName)
				.sEmail(sEmail)
				.sBirth(sBirth)
				.sJoinday(sJoinday)
				.slevel(sLevel)
				.build();
	}
}
