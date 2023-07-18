package com.bs.spring.board.controller;

import static com.bs.spring.common.PageFactory.getPage;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.bs.spring.board.model.dto.Attachment;
import com.bs.spring.board.model.dto.Board;
import com.bs.spring.board.service.BoardService;
import com.bs.spring.member.model.dto.Member;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/board")
@Slf4j
public class BoardController {
	@Autowired
	private BoardService service;
	
	@RequestMapping("/boardList.do")
	public String boardList(Model model, HttpServletRequest request,
			@RequestParam(value="cPage",defaultValue="1") int cPage) {
		int numPerpage=10;
		List<Board> boards=service.selectBoardAll(Map.of("cPage",cPage,"numPerpage",numPerpage));
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
	
	@RequestMapping("/insertBoard.do")
	public String insertBoard(Board b, MultipartFile[] upFile, HttpSession session, Model m) {
		log.info("{}",b);
		log.info("{}",upFile);
		b.setBoardWriter((Member)session.getAttribute("loginMember"));
		//MultipartFile에서 제공하는 메소드를 이용해서 
		//파일을 저장할 수 있음 -> transferTo()메소드를 이용
		//절대경로 가져오기
		String path=session.getServletContext().getRealPath("/resources/upload/board/");
		//파일명에 대한 renamed규칙을 설정
		//직접리네임규칙을 만들어서 저장해보자.
		//yyyyMMdd_HHmmssSSS_랜덤값
//		List<Attachment> files=new ArrayList();
		if(upFile!=null) {
			for(MultipartFile mf:upFile) {
				if(!mf.isEmpty()) {
					String oriName=mf.getOriginalFilename();
					String ext=oriName.substring(oriName.lastIndexOf("."));
					Date today=new Date(System.currentTimeMillis());
					SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd_HHmmssSSS");
					int rdn=(int)(Math.random()*10000)+1;
					String rename=sdf.format(today)+"_"+rdn+ext;
					
					try {
						mf.transferTo(new File(path+rename));
					}catch(IOException e) {
						e.printStackTrace();
					}
					
					Attachment file=Attachment.builder()
							.originalFilename(oriName)
							.renamedFilename(rename)
							.build();
					
					b.getFile().add(file);
				}
			}
		}
		try {
			service.insertBoard(b);			
		}catch(RuntimeException e) {
			//입력 실패 시 업로드된 파일 삭제하기
			for(Attachment a:b.getFile()) {
				File delFile=new File(path+a.getRenamedFilename());
				delFile.delete();
			}
			m.addAttribute("msg","글쓰기 등록 실패! ");
			m.addAttribute("loc", "/board/boardWrite.do");
			return "common/msg";
		}
		
		
		
		return "redirect:/board/boardList.do";
	}
	
	@RequestMapping("/boardContent.do")
	public String selectBoardByNo(Model m,int no) {
		m.addAttribute("board",service.selectBoardById(no));
		return "board/boardContent";
	}
	
	@RequestMapping("/filedownload")
	public void fileDown(String oriname, String rename, OutputStream out, @RequestHeader(value="user-agent") String header,
			HttpSession session, HttpServletResponse res) {
		String path=session.getServletContext().getRealPath("/resources/upload/board/");
		File downloadFile=new File(path+rename);
		try(FileInputStream fis=new FileInputStream(downloadFile)) {
			BufferedInputStream bis=new BufferedInputStream(fis);
			BufferedOutputStream bos=new BufferedOutputStream(out);
			
			boolean isMS=header.contains("Trident")||header.contains("MSIE");
			//익스플로러 기준으로 8버전 전은 Trident가 들어가고 그 이후는 MSIE가 들어간다.
			String encodeRename="";
			if(isMS) {
				encodeRename=URLEncoder.encode(oriname,"UTF-8");
				encodeRename=encodeRename.replaceAll("\\+", "%20");
				//사용자 브라우저에 따라서 파일명이 한글인 경우 깨지지 않도록 인코딩
			}else {
				encodeRename=new String(oriname.getBytes("UTF-8"),"ISO-8859-1");
			}
			res.setContentType("application/octet-stream;charset=utf-8");
			res.setHeader("Content-Disposition", "attachment;filename=\""+encodeRename+"\"");
			
			int read=-1;
			while((read=bis.read())!=-1) {
				bos.write(read);
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
