package com.bs.spring.jpa.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@SequenceGenerator(name = "seq_lockerno",sequenceName = "seq_lockerno",initialValue = 1,allocationSize = 1)
public class LockerEntity {
	@Id
	@GeneratedValue(generator = "seq_lockerno", strategy = GenerationType.SEQUENCE)
	private long lockerNo;
	private String lockerPosition;
	private String lockerColor;
	
	//@OneToOne(mappedBy="mylocker",cascade=CascadeType.REMOVE)
	//locker가 삭제되면 studentNo도 삭제
//	private long studentNo;
}
