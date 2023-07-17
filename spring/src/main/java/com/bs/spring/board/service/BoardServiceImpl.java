package com.bs.spring.board.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bs.spring.board.dao.BoardDao;
import com.bs.spring.board.model.dto.Attachment;
import com.bs.spring.board.model.dto.Board;
@Service
public class BoardServiceImpl implements BoardService {
	@Autowired
	private SqlSession session;
	
	@Autowired
	private BoardDao dao;
	
	@Override
	public List<Board> selectBoardList(Map<String,Object> param) {
		return dao.selectBoardList(session, param);
	}

	@Override
	public int insertBoard(Board b, Attachment a) {
		int result=dao.insertBoard(session, b);
		if(a!=null) {
			result+=dao.insertAttachment(session,a);
		}else {
			result+=1;
		}
		return result;
	}

	@Override
	public int selectBoardCount() {
		return dao.selectBoardCount(session);
	}

	@Override
	public Board selectBoardContent(int no) {
		return dao.selectBoardContent(session, no);
	}

}
