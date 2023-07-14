package com.bs.spring.memo.service;

import java.util.List;
import java.util.Map;

import com.bs.spring.memo.model.dto.Memo;

public interface MemoService {
	List<Memo> selectMemoList();
	
	int insertMemo(Map param);
}
