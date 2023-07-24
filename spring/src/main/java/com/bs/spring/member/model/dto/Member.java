package com.bs.spring.member.model.dto;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Member implements UserDetails{
	@NotEmpty(message = "아이디는 반드시 입력하세요.")
	@Size(min=4, message = "아이디는 4글자이상 입력하세요.")
	private String userId;
	@Pattern(regexp="(?=.*?[a-z])(?=.*?[A-Z])(?=.*?[~!@#$%^&*()])[a-zA-Z~!@#$%^&*()0-9]{8,}",
			message = "영대소문자, 특수기호를 포함하여 8글자 이상 입력하세요.")
	private String password;
	private String name;
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
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		//로그인한 사용자의 권한을 설정하는 메소드
		List<GrantedAuthority> auth=new ArrayList();
		auth.add(new SimpleGrantedAuthority("ROLL_USER")); //로그인하면 ROLL_USER권한이 부여된다.
		if(userId.equals("admin")) {
			auth.add(new SimpleGrantedAuthority("ROLL_ADMIN")); //관리자 계정이면 관리자 권한을 추가
		}
		return auth;
	}
	
	//security가 username을 사용하고 있기 때문에 필드에 username을 구분할 값이 아닌 것으로 사용하는 것은 좋지 않다.
	@Override
	public String getUsername() {
		//인증할 아이디 값을 반환하는 메소드
		return this.userId;
	}
	
	//권한 유효 여부
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	
	//계정이 잠겼는지
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
}
