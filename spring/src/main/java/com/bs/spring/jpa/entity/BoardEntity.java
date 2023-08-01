package com.bs.spring.jpa.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = {"boardWriter"})
@Entity
@SequenceGenerator(name="seq_boardno",sequenceName="seq_boardno", initialValue = 1, allocationSize = 1)
public class BoardEntity {
	@Id
	@GeneratedValue(generator="seq_boardno", strategy=GenerationType.SEQUENCE)
	private long boardNo;
	private String boardTitle;
	private String boardContent;
	@ManyToOne
	@JoinColumn(name="member_no")
	private JpaMember boardWriter;
	private Date writeDate;
}
