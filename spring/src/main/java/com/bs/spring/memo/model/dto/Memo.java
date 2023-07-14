package com.bs.spring.memo.model.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Memo {
	private int memoNo;
	private String memo;
	private String password;
	private Date memoDate;
}
