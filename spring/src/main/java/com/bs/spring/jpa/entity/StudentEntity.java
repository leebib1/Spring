package com.bs.spring.jpa.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class StudentEntity {
	@Id
	private long studentNo;
	private String studentName;
	private int grade;
	private int classNumber;
	
	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE},orphanRemoval = true)
	//영속성 전이
	@JoinColumn(name="mylocker") //nullable : 필수 관계 설정 ->inner join한다. 기본적으로는 left outer join
	private LockerEntity mylocker;
	
	//n:n 관계를 설정했을 때 DB에서는 join테이블이 생성되어야 한다.
	//@ManyToMany(cascade = {CascadeType.PERSIST})
	//JOIN 테이블에 대한 설정 가능 ->join테이블에 대한 Entity를 따로 만들지 않고 @JoinTable 어노테이션으로 만들 수 있다.
	//어노테이션으로 만들어진 JOIN테이블은 다른 컬럼을 추가하거나 제약 조건을 추가하기에 적합하지 않기 때문에 보통 OneToMany를 사용해서 Entity를 하나 더 만들어서 사용한다.
	//joinColumns : 현재 Entity의 PK가 저장되는 컬럼
	//inverseJoinColumns : 반대측 Entity의 PK가 저장될 컬럼
//	@JoinTable(name="student_clubs",joinColumns =@JoinColumn(name="student_no",nullable = false),
//		inverseJoinColumns =@JoinColumn(name="club_no") )
//	private List<Club> clubs;
	@OneToMany(mappedBy="student")
	private List<StudentClubs> clubs;
}
