package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import service.MemberService;
import service.ProdService;
import util.ScanUtil;
import util.View;
import view.Print;

public class MainController extends Print {

	static public Map<String, Object> sessionStorage = new HashMap<>();
	
	MemberService memberService = MemberService.getInstance();
	ProdService prodService = ProdService.getInstance();
	
	boolean debug = false;
	
	public static void main(String[] args) {
		new MainController().start();
	}

	private void start() {
		View view = View.HOME;
		while (true) {
			switch (view) {
			case HOME:
				view = home();
				break;
			case LOGIN:
				view = login();
				break;
			case SIGN:
				view = sign();
				break;
			case PROD_MANAGEMENT:
				view = management();
				break;
			case PROD_LIST:
				view = list();
				break;
			case PROD_DETAILE:
				view = detaile();
				break;
			case PROD_ADD:
				view = add();
				break;
			case PROD_DELETE:
				view = delete();
				break;
			case PROD_UPDATE:
				view = update();
				break;
			default:
				break;
			}
		}
	}

	
	private View update() {
		if (debug) System.out.println("# 상품 변경 후 전체리스트 이동");
		
		List<Map<String, Object>> list = prodService.list();
		
		for (Map<String, Object> map : list) {
			System.out.println(map);
		}
		
		int prodNo = ScanUtil.nextInt("상품 번호 :");
		String name = ScanUtil.nextLine("상품명 : ");
		String type = ScanUtil.nextLine("타입 : ");
		int price = ScanUtil.nextInt("가격 : ");
		
		List<Object> param = new ArrayList();
		param.add(name);
		param.add(type);
		param.add(price);
		param.add(prodNo);
		
		prodService.update(param);
	
		List<Map<String, Object>> list1 = prodService.list();
		
		for (Map<String, Object> map : list1) {
			System.out.println(map);
		}
		
		return View.PROD_LIST;
	}

	private View delete() {	// 삭제  안됨
		if (debug) System.out.println("# 상품 삭제 후 전체리스트 이동");
		
		List<Map<String, Object>> list = prodService.list();
		
		for (Map<String, Object> map : list) {
			System.out.println(map);
		}
		
		int prodNo = ScanUtil.nextInt("상품 번호 :");
		List<Object> param = new ArrayList();
		param.add(prodNo);
		
		int result = prodService.delete(param);
		if (result == 0) {
			System.out.println("삭제를 실패했습니다.");
			return View.PROD_DELETE;
		}
		
		List<Map<String, Object>> list1 = prodService.list();
		
		for (Map<String, Object> map : list1) {
			System.out.println(map);
		}
		
		return View.PROD_LIST;
	}

	private View add() {
		if (debug) System.out.println("# 상품 추가 후 전체리스트 이동");
		
		List<Object> param = new ArrayList();
		String name = ScanUtil.nextLine("상품명 : ");
		String type = ScanUtil.nextLine("타입 : ");
		int price = ScanUtil.nextInt("가격 : ");
		
		param.add(name);
		param.add(type);
		param.add(price);
		
		prodService.insert(param);
		
		List<Map<String, Object>> list = prodService.list();
		
		for (Map<String, Object> map : list) {
			System.out.println(map);
		}
		
		return View.PROD_LIST;
	}

	private View detaile() {
		if (debug) System.out.println("============ 상품 상세 보기 ============ ");
		printDetaile();	
		
		int sel = ScanUtil.menu();
		if (sel == 1) return View.PROD_DELETE;
		else if (sel == 2) return View.PROD_UPDATE;
		else if (sel == 3) return View.PROD_LIST;
		
		else return View.HOME;
	}

	private View list() {
		if (debug) System.out.println("============ 상품 전체 리스트 ============ ");
		printList();
		
		int sel = ScanUtil.menu();
		if (sel == 1) {
			List<Map<String, Object>> list = prodService.list();
			for (Map<String, Object> map : list) {
				System.out.println(map);
			}
			return View.PROD_DETAILE;
		}
		else if (sel == 2) return View.PROD_MANAGEMENT;
		
		else return View.HOME;
	}

	private View management() {
		if (debug) System.out.println("============ 상품 관리 게시판 ============ ");
		printManagement();
		
		int sel = ScanUtil.menu();
		if (sel == 1) return View.PROD_LIST;
		else if (sel == 2) return View.PROD_ADD;
		
		else return View.HOME;
	}

	private View sign() {
		System.out.println("============ 회원가입 ============ ");

		List<Object> param = new ArrayList();
		String id = ScanUtil.nextLine("ID: ");
		String pw = ScanUtil.nextLine("PW: ");
		String name = ScanUtil.nextLine("NAME: ");
		
		param.add(id);
		param.add(pw);
		param.add(name);
		param.add(2);
		
		memberService.sign(param, 2);
		
		System.out.println("============ 회원가입 성공 ============ ");
		
		return View.LOGIN;
	}
	
	private View login() {
		if (debug) System.out.println("============ 1. 로그인 ============ ");
		if (debug) printLogin();
		
		String id = ScanUtil.nextLine("ID: ");
		String pw = ScanUtil.nextLine("PW: ");
		
		List<Object> param = new ArrayList();
		param.add(id);
		param.add(pw);
		param.add(2);
		
		boolean loginChk = memberService.login(param, 2);
		if (!loginChk) {
			System.out.println("로그인 실패");
			return View.SIGN;
		}
	
		else return View.PROD_MANAGEMENT;
	}

	private View home() {
		if (debug) System.out.println("============ 홈 ============");
		
		printHome();
		
		int sel = ScanUtil.menu();
		if (sel == 1) return View.LOGIN;
		else if (sel == 2) return View.SIGN;
		
		else return View.HOME;
	}

}
