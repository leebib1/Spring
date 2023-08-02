package com.bs.spring.jpa.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
//@Table(uniqueConstraints = {@UniqueConstraint(name="uq_student_club",columnNames = {"student_no","club_no"})})
@SequenceGenerator(name="seq_studentclubno",sequenceName="seq_studentclubno", initialValue = 1, allocationSize = 1)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@IdClass(StudentClubsId.class) //복합키를 줄때 해당 식별자 클래스를 만들어준다.
public class StudentClubs {
//	@Id
//	@GeneratedValue(generator="seq_studentclubno", strategy = GenerationType.SEQUENCE)
//	private Long studentClubsNo;
	@Id
	@ManyToOne
	@JoinColumn(name="student_no")
	private StudentEntity student;
	@Id
	@ManyToOne
	@JoinColumn(name="club_no")
	private Club club;
	@Temporal(TemporalType.DATE)
	private Date enrollDate;
}
