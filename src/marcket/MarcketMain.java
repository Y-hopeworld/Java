package marcket;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class MarcketMain {
	
	// 내부저장소
	// 관리자 계정 ID와 PW 선언
	// 포스기 안에 내부 로그인 만들기
	String id = "admin";
	String pw = "1234";
			
	public static void main(String[] args) {

				Scanner sc = new Scanner(System.in);
				List<ProductDTO> list;
				ProductDAO pDao = new ProductDAO();
				MarcketMain mm = new MarcketMain();
				SaleDAO sDao = new SaleDAO();
				Boolean flag = false;
				int code = 0;
				String pname;
				String keyword;
				
				
				// 프로그램 시작
				String userid = "";
				String userpw = "";
				
				System.out.println("☺︎☺︎☺︎☺︎☺︎☺︎☺︎☺︎☺︎☺︎☺︎☺︎☺︎☺︎☺︎☺︎☺︎☺︎☺︎☺︎☺︎☺︎☺︎☺︎☺︎☺︎☺︎☺︎☺︎☺︎☺︎☺︎☺︎☺︎☺︎☺︎☺︎☺︎☺︎☺︎");
				System.out.println("☺︎☺︎ Marcket System Ver 1.0 ☺︎☺︎ ");
				
				/*// 로그인 체크
				do {
					System.out.println("☺︎☺︎[MSG] Please login to use.");
					System.out.print("☺︎☺︎ ID >>");
					userid = sc.nextLine();
					System.out.print("☺︎☺︎ pw >>");
					userpw = sc.nextLine();
				}while(mm.login(userid, userpw));
				*/
				// 로그인 성공 & 업무시작 코드 시작
				
			while(true) {	
				System.out.println("☺︎☺︎☺︎☺︎☺︎☺︎☺︎☺︎☺︎☺︎☺︎☺︎☺︎☺︎☺︎☺︎☺︎☺︎☺︎☺︎☺︎☺︎☺︎☺︎☺︎☺︎☺︎☺︎☺︎☺︎☺︎☺︎☺︎☺︎☺︎☺︎☺︎☺︎☺︎☺︎");
				System.out.println("☺︎☺︎ 1. 제품 판매  	  ☺︎☺︎ ");
				System.out.println("☺︎☺︎ 2. 제품 등록&추가   ☺︎☺︎ ");
				System.out.println("☺︎☺︎ 3. 제품  수정	  ☺︎☺︎ ");
				System.out.println("☺︎☺︎ 4. 제품  삭제	  ☺︎☺︎ ");
				System.out.println("☺︎☺︎ 5. 제품  조회	  ☺︎☺︎ ");
				System.out.println("☺︎☺︎ 6. 제품  검색	  ☺︎☺︎ ");
				System.out.println("☺︎☺︎ 7. 일일 매출현황     ☺︎☺︎ ");
				System.out.println("☺︎☺︎ 8. 프로그램 종료     ☺︎☺︎ ");
				System.out.println("☺︎☺︎☺︎☺︎☺︎☺︎☺︎☺︎☺︎☺︎☺︎☺︎☺︎☺︎☺︎☺︎☺︎☺︎☺︎☺︎☺︎☺︎☺︎☺︎☺︎☺︎☺︎☺︎☺︎☺︎☺︎☺︎☺︎☺︎☺︎☺︎☺︎☺︎☺︎☺︎");
				
				while(true) {
				System.out.print("☺︎☺︎ Code >>");
				code = sc.nextInt();
				
			
					if(code >= 1 && code <= 8) {
						
						break;
					}else {
						System.out.println("번호를 잘못 입력했습니다. 다시 입력해주세요.");
						continue;
					}
					
			}
						// 1. 제품판매
						//  판매된 제품이름 , 수량을 - , 가격을 매출로 빼는 작업
						if(code == 1) { 
							System.out.println("구매하고 싶은 제품의 번호와 수량을 입력해주세요.");
							
							//재고액이 있는 제품  
							list = pDao.selectUsePdt();
							int cnt = 0;
							int tprice = 0;
							String sname= "";
				
						while(true) {
							System.out.print("구매번호 >>");
							int buyCode = sc.nextInt();
							
							System.out.print("구매할 수량>>");
							cnt = sc.nextInt();
				
							//판매하려는 제품명
							sname = list.get(buyCode-1).getPname();
							
							// 사용자가 구매하려는 제품 1개 가격
							int price = list.get(buyCode-1).getPrice();
							
							// 총가격 = 1개가격 x 구매수량
							tprice = price * cnt;
							
							// 현재제품 재고량
							int nowCnt = list.get(buyCode-1).getCnt();
							
							if(nowCnt >= cnt) {
									break;
							}else {
								System.out.println("[Msg].");
							
							}
							
						}
							
							
							
							//tbl_sale  판매한 기록을 저장(판매 제품명, 수량, 총가격)
							HashMap<String, Object> map = new HashMap<>();
							map.put("sname", sname);
							map.put("cnt", cnt);
							map.put("tprice", tprice);
							int result = sDao.insertSale(map);
							
							if(result>0) {
									// tbl_product에서 재고를 마이너스
									pDao.cntminusPdt(sname,cnt);
							}else {
								System.out.println("☺︎☺︎[MSG] Login denied.");
								
							}
							
				
						//2. 제품등록 & 추가
						}else if(code == 2) {
							System.out.println("제품이름을 입력해주세요");
							System.out.print("제품이름>>");
							sc.nextLine();
							pname =sc.nextLine();
							
							if(pDao.pdtAlready(pname)) {
								// 기존에 등록된 제품임으로 추가(UPDATE)기능
								// 입고수량 입력 받아서 UPDATE
								System.out.println("입고 할 제품의 이름과 수량을 입력해주세요.");
								
								System.out.print("제품 이름 >>");
								sc.nextLine();
								pname = sc.nextLine();
								
								System.out.print("제품 수량 >>");
								int cnt = sc.nextInt();
								
								pDao.cntPlusPdt(pname,cnt);
								
								
							}else {
								// 최초 등록된 제품임으로 등록(INSERT)기능
								// 제조회사, 가격, 입고수량 입력 받아서 INSERT
								System.out.println("새로운 제품을 등록해주세요.");
								System.out.print("제품 이름 >>");
								pname = sc.nextLine();
							  
								System.out.print("제품 회사 >>");
								String company = sc.nextLine();

								System.out.print("제품 가격 >>");
								int price = sc.nextInt();
								
								System.out.print("제품 수량 >>");
								int cnt = sc.nextInt();
								
								
								pDao.insertPdt(pname, company, price, cnt);
								
							}
						
						
						// 3. 제품수정
						}else if(code == 3) {
							System.out.println("수정할 제품의 이름을 입력해주세요.");
							System.out.println("제품의 이름 >>");
							sc.nextLine();
							pname = sc.nextLine();
							
							
							
							System.out.print("수정) 회사이름>>");
							String company = sc.nextLine();
							
							System.out.print("수정)가격 >>");
							int price = sc.nextInt();
							
							System.out.print("수정)수량 >>");
							int cnt = sc.nextInt();
							
							
							
							pDao.updatePdt(pname,company,price,cnt);
							
							
						// 4. 제품 삭제	
						}else if(code == 4) {
							System.out.println("삭제할 제품 이름을 입력해주세요.");
							System.out.print("제품 이름 >>");
							sc.nextLine();
							 pname = sc.nextLine();
							
							pDao.deletePdt(pname);
							
							
						// 5. 제품 조회	
						}else if(code == 5) {
							System.out.println("편의점에 판매하는 제품 목록을 조회합니다.");
							pDao.selectPdt();
							
						// 6. 제품검색	
						}else if(code == 6) {
							System.out.println("검색 할 단어를 입력해주세요.");
							System.out.print("검색 >>");
							sc.nextLine();
							keyword = sc.nextLine();
							
							// 검색할 단어 여부
							int check = pDao.searCheck(keyword);
							
							
							if(check == 1) {
									pDao.searchPdt(keyword);
							} else {
								System.out.println(keyword + "가 검색 되지않습니다");
							}
							
						// 7. 일일 매출현황	
						}else if(code == 7) {
							
							System.out.println("일일 매출 현황입니다.");
							sDao.dashBoard();
							
							
						// 8.프로그램 종료
						}else if(code == 8) {
							System.out.println(" [Msg] Exit the program");
							System.exit(0);
						}
										
					
			}	
	
	
	}	
	
	
	

		
	
	// 로그인 체크 기능
	
	// 1. 
	public boolean login(String userid, String userpw ) {
		Boolean flag = true ; // 로그인 유무 판별 (true : 실패 , false :성공)
		
		if(userid.equals(id) && userpw.equals(pw)) {
			// 로그인 성공
			flag = false;
			System.out.println("☺︎☺︎ 환영합니다 관리자님");
		}  else {
			System.out.println("☺︎☺︎[MSG] Login denied.");
		}
		
		return flag;
	}
	
	
	
	}
					
			
			
			
	
	
