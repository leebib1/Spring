package com.bs.spring.jpa.entity;

import java.io.Serializable;

import lombok.Data;

//Id 식별자 클래스
//필수 제약 조건들
//1. 기본 생성자
//2. 클래스가 public으로 선언되어야 한다.
//3. Serializable interface가 구현되어야 한다.
//4. 동등성 비교를 위해 equals, hashCode가 오버라이딩 되어있어야 한다.
@Data
public class StudentClubsId implements Serializable{
	
	private Long student; //식별자로 사용할 자료형과 이름을 맞춰주면 된다.
	private Long club;
}
