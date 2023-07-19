package com.bs.spring.memo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bs.spring.memo.model.dto.Memo;
import com.bs.spring.memo.service.MemoService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/memo")
@Slf4j
public class MemoController {
	//@Autowired
	private MemoService service;
	
	public MemoController(MemoService service) {
		this.service=service;
	}
	@RequestMapping("/memoList.do")
	public String memoList(Model model) {
		List<Memo> list=service.selectMemoList();
		model.addAttribute("memoList",list);
		return "memo/memoList";
	}
	
	@PostMapping("/insertMemo.do")
	public String insertMemo(@RequestParam Map param, Model m) {
		int result=service.insertMemo(param);
		if(result==0) {
			m.addAttribute("msg","등록 실패");
			m.addAttribute("loc","/");
			return "common/msg";
		}
		return "redirect:/memo/memoList.do";
	}
}
