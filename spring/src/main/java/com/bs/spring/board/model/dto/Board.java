package com.bs.spring.board.model.dto;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import com.bs.spring.member.model.dto.Member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Board {
	private int boardNo;
	@NotEmpty(message = "제목은 비어있을 수 없습니다.")
	private String boardTitle;
	//private String boardWriter;
	private Member boardWriter;
	@NotEmpty(message="본문을 작성하세요.")
	private String boardContent;
	private Date boardDate;
	private int boardReadCount;
	//private Attachment file;
	private List<Attachment> file=new ArrayList();
}
