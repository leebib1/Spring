package com.bs.spring.jpa.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Club {
	@Id
	private long clubNo;
	private String clubName;
	private String location;
	//양방향 관계이기 때문에 주종관계를 설정해야 한다. ->mappedBy 이용
//	@ManyToMany(mappedBy="clubs")
//	private List<StudentEntity> student;
	@OneToMany(mappedBy="club")
	private List<StudentClubs> students;
}
