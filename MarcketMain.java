package marcket;

import java.util.Scanner;

public class MarcketMain {
	
	// 내부저장소
	// 관리자 계정 ID와 PW 선언
	// 포스기 안에 내부 로그인 만들기
	String id = "admin";
	String pw = "1234";
			
	public static void main(String[] args) {

				ProductDAO pDao = new ProductDAO();
				SaleDAO sDao = new SaleDAO();
				Scanner sc = new Scanner(System.in);
				MarcketMain mm = new MarcketMain();
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
							
							System.out.println("현재 재고가 있는 제품목록입니다.");
							pDao.zeroPdt();
							
							
							System.out.print("판매할 제품번호 >>");
							int pno = sc.nextInt();
							
							System.out.println("판매할 제품 이름>>");
							sc.nextLine();
							String sname = sc.nextLine();
							
							System.out.print("수량>>");
							int cnt = sc.nextInt();
							
							System.out.println("가격>>");
							int price = sc.nextInt();
							
							int tprice = (cnt * price);
						
							//재고액 빼기
							pDao.salePdt(pno, cnt);
							
							//매출 + 리스트담기
							sDao.pdtList(sname, cnt, tprice);
							
							
										
							
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
							
							int check = pDao.searCheck(keyword);
							
							
							if(check == 1) {
									pDao.searchPdt(keyword);
							} else {
								System.out.println("\""+keyword +"\""+ "의  제품이 검색 되지 않습니다.");
							}
							
						// 7. 일일 매출현황	
						}else if(code == 7) {
						
								System.out.println("매출현황");
								sDao.selectSales();
								
								
						// 8.프로그램 종료
						}else if(code == 8) {
							System.out.println(" [Msg] Exit the program");
							System.exit(0);
						}
										
					
			}	
	
	
	}	
	
	
	

		
	
	// 로그인 체크 기능
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
					
			
			
			
	
	