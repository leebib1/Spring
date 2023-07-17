package com.bs.spring.member.model.dto;

import java.sql.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Member {
	@NotEmpty(message = "아이디는 반드시 입력하세요.")
	@Size(min=4, message = "아이디는 4글자이상 입력하세요.")
	private String userId;
	@Pattern(regexp="(?=.*?[a-z])(?=.*?[A-Z])(?=.*?[~!@#$%^&*()])[a-zA-Z~!@#$%^&*()0-9]{8,}",
			message = "영대소문자, 특수기호를 포함하여 8글자 이상 입력하세요.")
	private String password;
	private String userName;
	private String gender;
	@Min(value=14, message = "14살 이상만 가입할 수 있습니다.") @Max(150)
	private int age;
	@Email
	private String email;
	@NotEmpty
	private String phone;
	private String address;
	private String[] hobby;
	private Date enrolldate;
}
