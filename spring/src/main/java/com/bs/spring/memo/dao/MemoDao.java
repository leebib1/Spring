package com.bs.spring.memo.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.bs.spring.memo.model.dto.Memo;

public interface MemoDao {
	List<Memo> selectMemoList(SqlSession session);
	
	int insertMemo(SqlSession session, Map param);
}
