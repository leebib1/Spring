package com.bs.spring.jpa.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
	@JoinColumn(name="mylocker", nullable=false) //nullable : 필수 관계 설정 ->inner join한다. 기본적으로는 left outer join
	private LockerEntity mylocker;
}
