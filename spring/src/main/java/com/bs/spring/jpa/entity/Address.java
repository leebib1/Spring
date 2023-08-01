package com.bs.spring.jpa.entity;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Embeddable
public class Address {
	
	private String zipcode;
	private String statement;
	private String detailAdress;
	
	
}

