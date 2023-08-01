package com.bs.spring.jpa.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.bs.spring.jpa.common.Level;
import com.bs.spring.jpa.common.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//일반 POJO 클래스를 Entity로 등록
@Entity // (name="")
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
//@Table(name = "memberjpa")
@SequenceGenerator(name = "seq_jpamemberno", sequenceName = "seq_jpamemberno", initialValue = 1, allocationSize = 1)
public class JpaMember {
	@Id // entity를 구분하는 식별자 DB의 PK가된다.
	@GeneratedValue(generator = "seq_jpamemberno", strategy = GenerationType.SEQUENCE)
	@Column(name = "member_no")
	private Long memberNo;
	@Column(name = "member_id", unique = true, nullable = false, length = 20)
	private String memberId;
	@Column(name = "member_pwd", unique = true, nullable = false, length = 20)
	private String memberPwd;
	// BigDecimal타입에 사용하는 숫자설정
	// precistion : 전체자리수
	// scale : 소수점자리수
	@Column(precision = 10, scale = 3)
	private BigDecimal age;
	@Column(columnDefinition = "number default 100.0")
	private double height;
	// EnumType을 이용해서 처리하기
	@Column(name = "member_role")
	@Enumerated(EnumType.STRING) // 문자열자체를 저장
	private Role role;

	@Column(name = "member_level")
	@Enumerated(EnumType.ORDINAL) // 문자열과 연결되어있는 숫자값 저장
	private Level level;

	// 날짜타입에 대해 설정하기
	@Temporal(TemporalType.DATE)
	private Date birthDay;
	@Temporal(TemporalType.TIMESTAMP)
	private Date startDate;

	// lob타입설정하기
	@Lob
	private String info;// varchar2
	@Lob
	private byte[] dataSample;

	// DB컬럼 대상에서 제외하기
	@Transient
	private String tempData;

	@Embedded
	private Address addr;
	@Transient
	private String target;
	
	@OneToMany(mappedBy="boardWriter",fetch=FetchType.LAZY) //, fetch=FetchType.EAGER)
	//아무런 설정 없이 실행하면 자동으로 join 테이블을 만든다.
	//서브 클래스에 mappedBy 속성을 추가해주면 join 테이블을 생성하지 않음. ->JoinColumn이 없는 쪽에 설정
	//지연 로딩 처리 : fetch=FetchType.LAZY
	//필요한 시점에만 데이터를 가져올 수 있게 프록시 객체(대체제)를 먼저 불러오고 실제 데이터를 사용할 때 가져오게 만드는 기능
	//fetch=FetchType.EAGER : proxy 객체를 사용하지 않고 무조건 전체 데이터를 불러오기 위해 select문을 실행한다.
	private List<BoardEntity> boards;
	

}
