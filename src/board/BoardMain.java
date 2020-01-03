package board;
/*
 * 로그인 기능 추가
 * 	Session = yes of no 
 * 	Userid = "로그인 유저 id"
 * 
 *  DB → yes 면 로그인 ok no면 id or pw 틀렸습니다
 * 1)등록 → 로그인 유무 → 로그인 등록(작성자 = 로그인유저)
 * 		→ 로그인을 하셔야만 글을 등록 
 * 		→ 로그인 이동  
 *  
 */
import java.util.Scanner;

public class BoardMain {

	static String session = "NO"; // 로그인유무(YES : LOGIN OK)
	static String userid = "";	// 로그인 유저의 ID값	
	
	
	public static void main(String[] args) {

			Scanner sc = new Scanner(System.in);
			
			// 게시글 관련기능
			BoardDAO bDao = new BoardDAO();
			//회원관련 기능
			MemberDAO mDao = new MemberDAO();
			
			// 사용자가 선택한 프로그램 번호
			int code = 0;
			
		while(true) {
			System.out.println("■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■");
			System.out.println("■□■□■□■□■□■□■□■□■□■□■□ Weverse 게시판 ■□■□■□■□■□■□■□■□■□■□■□■");
		
			System.out.println("■□ 0. 로그인");
			System.out.println("■□ 1. 게시글 등록");
			System.out.println("■□ 2. 게시글 수정");
			System.out.println("■□ 3. 게시글 삭제");
			System.out.println("■□ 4. 게시글 조회");
			System.out.println("■□ 5. 게시글 검색");
			System.out.println("■□ 6. 게시글 정렬");
			System.out.println("■□ 7. 상세게시글");
			System.out.println("■□ 8. 만든이");
			System.out.println("■□ 9. 로그아웃");
			System.out.println("■□ 10. 프로그램 종료");
			
			//로그인 성공 했을 때만 나올 수 있게 
			if(BoardMain.session.equals("YES")) {
				System.out.println("■□  "+ BoardMain.userid + "님의 재방문을 환영합니다"); }
			System.out.println("■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■");
			
			while(true) {
				System.out.print("번호 >>");
				 code = sc.nextInt();
				
				// 1~9까지 번호 입력 한경우 & 1~9 외의 번호를 입력한 경우
					if(code >= 0 && code <= 10) {
						break;
					}else {
						System.out.println("잘못된 번호입니다. 다시 입력해주세요.");
						continue;
					}
			}  // break 나오는 곳
				
				
			
			
				// 0. 로그인
				if(code ==0) {
						System.out.println("로그인 할 ID와 PW를 입력해주세요");
						System.out.print("ID >>");
						sc.nextLine();
						String id = sc.nextLine();
						
						System.out.print("PW >>");
						String pw = sc.nextLine();
					
						mDao.login(id, pw);
							
						
				// 1. 게시글 등록(제목,내용,작성자)
				}else if(code == 1) {
					
					//로그인 유무 확인
					if(BoardMain.session.equals("NO")) {
						System.out.println("로그인이 필요한 기능입니다. ");
						System.out.println("로그인해주세요.");
						continue;
					}
					// YES 일 때 게시글 등록 가능
					System.out.print("게시글 제목 >>");
					sc.nextLine();
					String title =sc.nextLine();
					
					System.out.print("내용>>");
					String content = sc.nextLine();
					
					
					String writer = BoardMain.userid;
					
					BoardDTO bDto = new BoardDTO(title, content, writer);
					
					bDao.insertBoard(title, content, writer);
					
					
				 // 2. 수정	
				}else if(code == 2) {
					if(BoardMain.session.equals("NO")) {
						System.out.println("로그인이 필요한 기능입니다. 로그인을 해주세요");
						continue;
					} 
			

						System.out.print("수정하고자 하는 게시물 번호>>");
						int bno = sc.nextInt();
						
						String writer = mDao.checkid(bno);
				
						
							if(writer.equals(userid)) {
								System.out.print("제목>>");
								sc.nextLine();
								String title = sc.nextLine();
								
								System.out.print("내용>>");
								String content = sc.nextLine();
								
								bDao.updateBoard(bno, title, content, writer);
							}else {
								System.out.println("잘못된 경로");
							}
						

				// 3. 삭제	
				}else if(code == 3) {
					if(BoardMain.session.equals("NO")) {
						System.out.println("로그인이 필요한 기능입니다. 로그인을 해주세요");
						continue;
					} 
			
					
						System.out.println("삭제할 게시물의 번호를 입력하세요.");
						System.out.print("번호>>");
						int bno = sc.nextInt();
						
						String writer = mDao.checkid(bno);
						
							if(writer.equals(userid)) {
									bDao.deleteBoard(bno);
								
							}else {
								System.out.println("잘못된 경로");
							}
							
					
				// 4. 조회	
				}else if(code == 4) {
					System.out.println("전체 게시물을 조회합니다.");
					
					bDao.selectBoard();
					
				// 5. 검색	
				}else if(code == 5) {
					System.out.println("검색 할 내용을 입력해주세요.");
					System.out.print("검색 >>");
					sc.nextLine();
					String keyword = sc.nextLine();
					
					bDao.searchBoard(keyword);
					
					
					
				// 6. 정렬	
				}else if(code == 6) {
					System.out.println("조회 순으로 정렬해주세요.");
					bDao.boardSortBoard();
					
				// 7. 상세게시글	
				}else if(code == 7) {
					System.out.println("선택한 게시글을 조회합니다");
					System.out.print("번호 >>");
					int bno = sc.nextInt();
				
					bDao.viewBoard(bno);
				// 8. 만든이
				}else if(code == 8) {
						System.out.println("▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨");
					 	System.out.println("▨▨ Name : Board Program ");
					 	System.out.println("▨▨ Version : 1.7");
					 	System.out.println("▨▨ Use : JAVA,ORACLE,MYBATIS");
					 	System.out.println("▨▨ Date : 20.01.01");
					 	System.out.println("▨▨ by 1Verse");
					 	System.out.println("▨▨ Y.hopeworld@gmail.com");
					
				}else if(code ==9) {
					if(BoardMain.session.equals("YES")) { // 로그인 된 상태
						
						System.out.println(BoardMain.userid + "님이 로그아웃 되었습니다.");
						BoardMain.session="NO";
						BoardMain.userid = "";
					}else {
						System.out.println("로그인이 필요한 기능입니다.");
					}
				}
				
				
				// 10. 프로그램종료	
				else if(code == 10) {
					
					System.out.println("프로그램이 종료 되었습니다.");
					System.exit(0);
					
					
				}
				
				
				
		
		
		
		
		
		
		
		
	    
		
		
		
		
		
		}

	
	}







}
