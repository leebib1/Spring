package com.bs.spring.board.service;

import java.util.List;
import java.util.Map;

import com.bs.spring.board.model.dto.Attachment;
import com.bs.spring.board.model.dto.Board;

public interface BoardService {
	List<Board> selectBoardList(Map<String,Object> param);
	
	Board selectBoardContent(int no);
	
	int insertBoard(Board b, Attachment a);
	
	int selectBoardCount();
}
