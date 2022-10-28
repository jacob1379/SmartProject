package com.example.demo.seller.dto;




import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.example.demo.seller.entity.Sseller;
import com.example.demo.seller.entity.sLevel;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor(access=AccessLevel.PRIVATE)
public class SsellerDto {
	
	@Data
	@Builder
	public static class Join {
		@Pattern(regexp="^[A-Z0-9]{8,10}$", message = "아이디는 대문자와 숫자 8~10자입니다")
		@NotEmpty(message="아이디는 필수 입력입니다")
		private String sId;
		
		@NotNull(message="사업자번호는 필수 입력입니다")
		private String sBusinessNum;
		
		@NotEmpty(message="비밀번호는 필수 입력입니다")
		private String sPassword;
		
		@NotEmpty(message="이름은 필수 입력입니다")
		private String sName;
		
		@NotNull(message="생일은 필수 입력입니다")
		private LocalDate sBirth;
		
		@NotNull(message="연락처는 필수 입력입니다")
		private Integer sPhone;
		
		@Email
		@NotEmpty(message="이메일은 필수 입력입니다")
		private String sEmail;
				
		public Sseller toEntity() {
			return Sseller.builder()
					.sId(sId)
					.sBusinessNum(sBusinessNum)
					.sPassword(sPassword)
					.sName(sName)
					.sBirth(sBirth)
					.sPhone(sPhone)
					.sEmail(sEmail)
					.build();
		}
	}
	
	@Data
	@Builder
	public static class Read {
		private String sId;
		private String sBusinessNum;
		private String sName;
		private LocalDate sBirth;
		private String sEmail;
		private LocalDate sJoinday;
		private Long days;
		private Integer sStoreNum;
		private sLevel slevel;
	}
	
	@Data
	@Builder
	public static class Update {
		private String sId;
		private String sPassword;
		private String sName;
		private Integer sPhone;
		private String sEmail;
		
		public Sseller toEntity() {
			return Sseller.builder()
					.sId(sId)
					.sPassword(sPassword)
					.sName(sName)
					.sPhone(sPhone)
					.sEmail(sEmail)
					.build();
		}
		
	}
	
	/*
	@Data
	public static class Update {
		private String sEmail;
	}
	*/
	@Data
	public static class sbNumCheck {
		@NotEmpty(message="사업자번호는 필수 입력입니다.")
		private String sBusinessNum;
	}
	
	@Data
	public static class sIdCheck {
		@Pattern(regexp="^[A-Z0-9]{8,10}$", message = "아이디는 대문자와 숫자 8~10자입니다")
		@NotEmpty(message="아이디는 필수 입력입니다")
		private String sId;
	}
	
	@Data
	public static class sEmailCheck {
		@Email
		@NotEmpty(message="이메일은 필수 입력입니다")
		private String email;
	}
	
	@Data
	public static class findIdByEmail {
		@NotEmpty(message="이메일은 필수 입력입니다")
		@Email(message="잘못된 이메일 형식입니다")
		private String sEmail;
	}
	
	@Data
	public static class stPassword {
		@NotEmpty(message="아이디는 필수 입력입니다")
		private String sId;
		
		@Email
		@NotEmpty(message="이메일은 필수 입력입니다")
		private String sEmail;
	}
	
	@Data
	public static class scPassword {
		@NotEmpty(message="이메일은 필수 입력입니다")
		private String sPassword;
		
		@NotEmpty(message="새 비밀번호는 필수 입력입니다")
		private String newPssword;
	}
	
}
