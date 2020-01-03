package problem;

import java.util.Scanner;

public class BankMain {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		BankDAO bDao = new BankDAO();
		int code = 0;

		
		while(true) {
			
		System.out.println("▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦");
		System.out.println("▦▦ HOPE 은행");
		System.out.println("▦▦ 1.계좌개설");
		System.out.println("▦▦ 2.입금");
		System.out.println("▦▦ 3.출금");
		System.out.println("▦▦ 4.고객조회");
		System.out.println("▦▦ 5.계좌조회");
		System.out.println("▦▦ 6.계좌해지");
		System.out.println("▦▦ 7.프로그램 종료");
		System.out.println("▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦");
		
		while(true) {
			System.out.print("▦▦ 번호 >>");
		    code = sc.nextInt();
		    if(code > 0 && code < 7) {
		    	break;
		    }else {
		    	System.out.println("▦▦ 1~6번까지의 번호를 다시 입력해주세요.");
		    	continue;
		    }
	    
	    }
		  //1. 프로그램 전체반복
	    //2. 1~6번까지 번호만 허용
	    //3. 계좌개설(INSERT)
	    //4. 계좌조회만들기(SELECT, 전체조회)
	    //5. 사용자 검색(이름)
	    //6. 프로그램 종료기능만들기
	    
		// 1. 계좌개설
		if(code == 1) {
			System.out.println("▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦");
			System.out.println("▦▦ 신규계좌를 개설합니다. 값을 입력해주세요");
			
			System.out.print("▦▦ 예금주>>");
			sc.nextLine();
			String bname = sc.nextLine();
			
	
			System.out.print("▦▦ 비밀번호>>");
			String pw = sc.nextLine();
			
			bDao.insertBank(bname,pw);
			
			
			
			
			
			
			
		
		// 2. 입금
		}else if(code == 2) {
			System.out.println("▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦");
			System.out.println("▦▦ 계좌에 입금하세요.");
			System.out.print("▦▦ 계좌번호 >>");
			int bno = sc.nextInt();
			
			System.out.print("▦▦ 입금금액 >>");
			int money = sc.nextInt();
		
			bDao.updateBank(bno, money);
			
			
		// 3. 출금	
		}else if(code == 3) {
			System.out.println("▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦");
			System.out.println("▦▦ 출금할 계좌번호와 암호를 입력해주세요.");
			System.out.print("▦▦ 계좌번호 >>");
			int bno = sc.nextInt();
			
			System.out.print("▦▦ 비밀번호 >>");
			sc.nextLine();
			String pw = sc.nextLine();
			
			
			
			
				if(bDao.checkUser(bno, pw)) {	// 체크 통과한 경우에만 값을 출금 할 수 있게 작성
					int balance = bDao.balanceMoney(bno);
					//잔액 가져오기
					System.out.println("▦▦ 잔액 "+balance);
					System.out.println("▦▦ 출금할 금액을 입력해주세요.");
					System.out.print("▦▦ 금액 >>");
					int money = sc.nextInt();
					
					
					// 잔액- 출금액 = 0
					// 잔액 > 출금액 = 잔액 - 출금액
					// 잔액 < 출금액 = 잔액이 부족 경고메세지
						if(balance < money) {
							System.out.println("잔액이 부족합니다. 다시 확인해주세요");
						}else { //출금
							
							bDao.minusMoney(bno, money);
							
						}
	
					
					
					
				} else { // 아닌경우
					System.out.println("존재하지않은 계좌번호이거나 비밀번호가 틀렸습니다.");
				}
				
			
	
			
		//4. 계좌조회
		}else if(code == 4) {
			System.out.println("▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦");
			System.out.println("▦▦ 등록된 고객님의 정보를 조회합니다.");
			
			bDao.selectBank();
			
			
			
		//5. 계좌조회	(1건)
		}else if(code == 5) {
			// 계좌번호, 패스워드
			System.out.println("▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦");
			System.out.println("▦▦ 계좌를 조회합니다. 계좌번호와 암호를 입력해주세요>>");
			sc.nextLine();
			System.out.print("▦▦ 계좌번호>>");
			int bno = sc.nextInt();
			System.out.print("▦▦ 패스워드>>");
			sc.nextLine();
			String pw = sc.nextLine();
			
			bDao.selectAccount(bno, pw);
			
			
			
		//6. 계좌해지
		}else if(code == 6) {
			System.out.println("▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦");
			System.out.println("▦▦ 계좌를 조회합니다. 계좌번호와 암호를 입력해주세요>>");
			System.out.print("▦▦ 계좌번호>>");
			int bno = sc.nextInt();
			System.out.print("▦▦ 비밀번호>>");
			sc.nextLine();
			String pw = sc.nextLine();
			
			bDao.deleteAccount(bno, pw);
			
			
		}else if(code ==7) {
			
		}
		
		
		}
		
		
		
		
		
	    
	    
		
		}
	    //1. 프로그램 전체반복
	    //2. 1~6번까지 번호만 허용
	    //3. 계좌개설(INSERT)
	    //4. 계좌조회만들기(SELECT, 전체조회)
	    //5. 사용자 검색(이름)
	    //6. 프로그램 종료기능만들기
	    
	    
	
	
	
	}
