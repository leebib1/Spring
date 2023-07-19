package com.bs.spring.common;

public class PageFactory {
	public static String getPage(int cPage,int numPerpage, int totalData, String url) {
		//매개변수로 페이지바 만들기
		StringBuffer pageBar=new StringBuffer();
		pageBar.append("<ul class='pagination justify-content pagination-sm'>");
		int totalPage=(int)(Math.ceil((double)totalData/numPerpage));
		int pageBarSize=5;
		int pageNo=((cPage-1)/pageBarSize)*pageBarSize+1;
		int pageEnd=pageNo+pageBarSize-1;
		if(pageNo==1) {
			pageBar.append("<li class='page-item disabled'><a class='page-link' href='#'>이전</a></li>");
		}else {
			pageBar.append("<li class='page-item'>");
			pageBar.append("<a class='page-link' href='javascript:fn_paging("+(pageNo-1)+")'>이전</a></li>");
		}
		
		while(!(pageNo>pageEnd||pageNo>totalPage)) {
			if(cPage==pageNo) {
				pageBar.append("<li class='page-item disabled'>");
				pageBar.append("<a class='page-link' href='#'>"+pageNo+"</a></li>");
			}else {
				pageBar.append("<li class='page-item'>");
				pageBar.append("<a class='page-link' href='javascript:fn_paging("+(pageNo)+")'>"+pageNo+"</a></li>");
			}
			pageNo++;
		}
		
		if(pageNo>totalPage) {
			pageBar.append("<li class='page-item disabled'><a class='page-link' href='#'>다음</a></li>");
		}else {
			pageBar.append("<li class='page-item'>");
			pageBar.append("<a class='page-link' href='javascript:fn_paging("+(pageNo+1)+")'>다음</a></li>");
		}
		pageBar.append("</ul>");
		
		//스크립트
		pageBar.append("<script>");
		pageBar.append("function fn_paging(no){");
		pageBar.append("location.assign('"+url+"?cPage='+no);}");
		pageBar.append("</script>");
		return new String(pageBar);
	}
}
