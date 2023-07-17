package com.bs.spring.board.controller;

import static com.bs.spring.common.PageFactory.getPage;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bs.spring.board.model.dto.Attachment;
import com.bs.spring.board.model.dto.Board;
import com.bs.spring.board.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	private BoardService service;
	
	@RequestMapping("/boardList.do")
	public String boardList(Model model, HttpServletRequest request,
			@RequestParam(value="cPage",defaultValue="1") int cPage) {
		int numPerpage=10;
		List<Board> boards=service.selectBoardList(Map.of("cPage",cPage,"numPerpage",numPerpage));
		model.addAttribute("boards",boards);
		
		int totalData=service.selectBoardCount();
		model.addAttribute("pageBar",getPage(cPage, numPerpage, totalData, request.getRequestURI()));
		model.addAttribute("totalData",totalData);
		return "board/boardList";
	}
	
	@RequestMapping("/boardWrite.do")
	public String boardWrite() {
		return "board/boardInsert";
	}
	
	@PostMapping("/boardInsert.do")
	public String boardInsert(Model model, Board b, Attachment a) {
		int result=service.insertBoard(b,a);
		if(result!=2) {
			model.addAttribute("msg","등록 실패");
			model.addAttribute("loc","/");
			return "common/msg";
		}
		return "redirect:/board/boardList.do";
	}
	
	@RequestMapping("/boardContent.do")
	public String boardContent(Model model, int no) {
		model.addAttribute("board",service.selectBoardContent(no));
		return "board/boardContent";
	}
}
