package view;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import util.ScanUtil;

public class Print {
	
	public void printVar() {
		System.out.println("===================================");
		
	}
	
	public void printLn(int num) {
		for (int i = 0; i < num; i++) System.out.println();
	}
	

	public void printDetaile() {
		printVar();
		System.out.println("1. 상품 삭제");
		System.out.println("2. 상품 정보 변경");
		System.out.println("3. 상품 리스트");
		printLn(5);
		printVar();
	}
	
	
	public void printList() {
		printVar();
		System.out.println("1. 상품 상세 보기");
		System.out.println("2. 상품 관리 게시판");
		printLn(5);
		printVar();
	}
	
	
	public void printManagement() {
		printVar();
		System.out.println("1. 상품 전체 리스트 출력");
		System.out.println("2. 상품 추가");
		printLn(5);
		printVar();
	}
	
	
	public void printLogin() {
		printVar();
		System.out.println("# 로그인 시 상품 관리 게시판 이동");
		System.out.println("# 로그인 실패시 회원가입, 재로그인 시도 메뉴");
		printVar();
	}
	
	public void printHome() {
		printVar();
		System.out.println("1. 로그인");
		System.out.println("2. 회원가입");
		printLn(5);
		printVar();
	}
	
	
	public void prodListPrint(List<Map<String, Object>> prodList) {
		printVar();
//		for (Map<String,Object> map : prodList) {
//			BigDecimal prodNo = (BigDecimal) map.get("BOOK_NO");
//			String title = (String)map.get("TITLE");
//			String content = (String)map.get("CONTENT");
//			String pubdate = (String)map.get("PUBDATE");
//			System.out.println(bookNo + "\t" + title + "\t" + content + "\t" + pubdate);
//		}
//		
//		
		for (Map<String, Object> map : prodList) {
			System.out.println(map);
		}
		printVar();
	}
	
	
	
}
