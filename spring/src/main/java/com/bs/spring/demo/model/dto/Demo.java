package com.bs.spring.demo.model.dto;

import java.sql.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Demo {
	private Long devNo;
	private String devName;
	private int devAge;
	private String devGender;
	private String devEmail;
	private String[] devLang;
	private List<String> devLang2;
	private Date birthDay;
}
