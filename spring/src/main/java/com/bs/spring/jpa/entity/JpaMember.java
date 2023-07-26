package com.bs.spring.jpa.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//일반 POJO 클래스를 Entity로 등록
@Entity //(name="")
//Entity에 이름을 따로 설정하지 않으면 Entity의 이름은 클래스명이 된다.
//Entity는 기본 생성자가 필수로 있어야하고 final클래스(enum, interface, inner)는 사용할 수 없다. 
//필드에 final사용도 불가하다.
//테이블에 대한 설정 : 스키마 등록(오라클 제외), 카탈로그, 테이블레벨 제약조건, DB에 생성될 테이블명에 대해 설정 가능함(생략 시 클래스명으로 생성)
//@Table()
//@SequenceGenerator ->DB에서 사용할 sequence를 생성하는 어노테이션, Id값을 시퀀스 값으로 자동 부여할 때 사용한다.
//name : entity에서 사용할 시퀀스 이름
//squenceName : DB에서 사용하는 시퀀스 이름
//initValue : 시작값
//allocationSize : 증가값
//@TableGenerator : Id값을 중복 없이 발급하는 테이블 생성해서 Id를 부여하는 용도로 사용한다.
//@JsonIdentityInfo : Json을 이용해서 Entity객체를 가져올 때 테이블이 1:n, n:1 관계면 서로 참조하면서 무한 루프를 돌게 되는데 Id값을 주면 중복해서 가지고 오지 않는다. 
@Table(name="memberjpa")
@SequenceGenerator(name="seq_jpamemberno", sequenceName="seq_jpamemberno", initialValue = 1, allocationSize = 1)
public class JpaMember {
	@Id //entity를 구분하는 식별자 DB의 PK가된다.
	@GeneratedValue(generator = "seq_jpamemberno", strategy = GenerationType.SEQUENCE)
	private Long memberNo;
	private String memberId;
	private String memberPwd;
	private Integer age;
	private double height;
	
}
