package com.bs.spring.memo.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bs.spring.memo.dao.MemoDao;
import com.bs.spring.memo.model.dto.Memo;
@Service
public class MemoServiceImpl implements MemoService {
	@Autowired
	private SqlSession session;
	@Autowired
	private MemoDao dao;
	@Override
	public List<Memo> selectMemoList() {
		return dao.selectMemoList(session);
	}
	@Override
	public int insertMemo(Map param) {
		return dao.insertMemo(session, param);
	}
	
	
}
