package com.example.demo.consumer.dto;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.consumer.entity.Consumer;
import com.example.demo.consumer.entity.Levels;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access=AccessLevel.PRIVATE)
public class ConsumerDto {
	
	@Data
	public static class IdCheck {
		@Pattern(regexp="^[A-Z0-9]{8,10}", message="아이디는 대문자와 숫자 8~12자입니다")
		@NotEmpty(message="아이디는 필수입력입니다")
		private String cId;
	}
	
	@Data
	public static class NicknameCheck {
		@NotEmpty(message="닉네임은 필수입력입니다")
		private String cNickname;
	}
	
	@Data
	public static class EmailCheck {
		@Email //이메일 형식에 대한 유효성을 체크할 때
		@NotEmpty(message="이메일은 필수입력입니다")
		private String cEmail;
	}
	
	@Data
	@Builder
	public static class Join {
		@Pattern(regexp="^[A-Z0-9]{8,10}", message="아이디는 대문자와 숫자 8~10자입니다.")
		@NotEmpty(message="아이디는 필수 입력입니다.")
		private String cId;
		@NotEmpty(message="비밀번호는 필수 입력입니다.")
		private String cPassword;
		@NotEmpty(message="닉네임은 필수 입력입니다.")
		private String cNickname;
		@NotEmpty(message="이름은 필수 입력입니다.")
		private String cName;
		@NotEmpty(message="생일은 필수 입력입니다.")
		private LocalDate cBirthday;
		@NotNull(message="연락처는 필수 입력입니다.")
		private String cPhone;
		@Email
		@NotEmpty(message="이메일은 필수 입력입니다.")
		private String cEmail;
		private MultipartFile cProfile;
		
		public Consumer toEntity() {
			return Consumer.builder().cId(cId).cPassword(cPassword).cNickname(cNickname).cName(cName)
					.cBirthday(cBirthday).cPhone(cPhone).cEmail(cEmail).build();
		}
	}
	
	@Data
	public static class FindId {
		@Email(message="잘못된 이메일 형식입니다")
		@NotEmpty(message="이메일은 필수입력입니다")
		private String cEmail;
	}
	
	@Data
	public static class FindPassword {
		@Pattern(regexp="^[A-Z0-9]{8,10}$", message="아이디는 대문자와 숫자 8~12자입니다")
		@NotEmpty(message="아이디는 필수입력입니다")
		private String cId;
		@NotEmpty(message="이메일은 필수입력입니다")
		private String cEmail;
	}

	@Data
	public static class OutputFind {
		private String cId;
		private String cPassword;
		private String cEmail;
	}
	
	@Data
	public static class ChangePassword {
		@NotEmpty(message="비밀번호는 필수입력입니다")
		private String password;
		
		@NotEmpty(message="새 비밀번호는 필수입력입니다")
		private String newPassword;
	}
	
	@Data
	@Builder
	public static class Read {
		private String cId;
		private String cNickname;
		private String cName;
		private LocalDate cBirthday;
		private LocalDate cJoinday;
		private Long cDays;
		private String cPhone;
		private String cEmail;
		private String cProfile;
		private Integer cBuyCount;
		private Integer cBuyMoney;
		private Levels cLevel;
	}
	
	@Data
	@Builder
	public static class Update {
		private String cPassword;
		private String cNickname;
		private String cPhone;
		private String cEmail;
		private MultipartFile cProfile;
		
		public Consumer toEntity2() {
			return Consumer.builder().cPassword(cPassword).cNickname(cNickname).cPhone(cPhone).cEmail(cEmail).build();
		}
	} 
}
