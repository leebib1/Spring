package com.bs.spring.memo.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.bs.spring.memo.model.dto.Memo;
@Repository
public class MemoDaoImpl implements MemoDao {

	@Override
	public List<Memo> selectMemoList(SqlSession session) {
		// TODO Auto-generated method stub
		return session.selectList("memo.selectMemoList");
	}

	@Override
	public int insertMemo(SqlSession session, Map param) {
		// TODO Auto-generated method stub
		return session.insert("memo.insertMemo",param);
	}

}
