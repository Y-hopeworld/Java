package board;

import java.util.Scanner;

public class BoardMain {

	public static void main(String[] args) {

			Scanner sc = new Scanner(System.in);
			
			int code;
			
		while(true) {
			System.out.println("■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■");
			System.out.println("■□■□■□■□■□■□■□■□■□■□■□ Weverse 게시판 ■□■□■□■□■□■□■□■□■□■□■□■");
			System.out.println("■□ 1. 게시글 등록");
			System.out.println("■□ 2. 게시글 수정");
			System.out.println("■□ 3. 게시글 삭제");
			System.out.println("■□ 4. 게시글 조회");
			System.out.println("■□ 5. 게시글 검색");
			System.out.println("■□ 6. 게시글 정렬");
			System.out.println("■□ 7. 상세게시글");
			System.out.println("■□ 8. 만든이");
			System.out.println("■□ 9. 프로그램 종료");
			System.out.println("■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■");
			
			while(true) {
				System.out.print("번호 >>");
				 code = sc.nextInt();
				
				// 1~9까지 번호 입력 한경우 & 1~9 외의 번호를 입력한 경우
					if(code >= 1 && code <= 9) {
						break;
					}else {
						System.out.println("잘못된 번호입니다. 다시 입력해주세요.");
						continue;
					}
			}  // break 나오는 곳
				
				BoardDAO bDao = new BoardDAO();
			
			
				// 1. 게시글 등록(제목,내용,작성자)
				if(code == 1) {
					System.out.print("게시글 제목 >>");
					sc.nextLine();
					String title =sc.nextLine();
					
					System.out.print("내용>>");
					String content = sc.nextLine();
					
					System.out.print("작성자>>");
					String writer = sc.nextLine();
					
					BoardDTO bDto = new BoardDTO(title, content, writer);
					
					bDao.insertBoard(title, content, writer);
					
					
				 // 2. 수정	
				}else if(code == 2) {
					System.out.print("수정하고자 하는 게시물 번호>>");
					int bno = sc.nextInt();
					
					System.out.print("제목>>");
					sc.nextLine();
					String title = sc.nextLine();
					
					System.out.print("내용>>");
					String content = sc.nextLine();
					
					System.out.print("작성자>>");
					String writer = sc.nextLine();
					
					
					bDao.updateBoard(bno, title, content, writer);
					
				// 3. 삭제	
				}else if(code == 3) {
					System.out.println("삭제할 게시물의 번호를 입력하세요.");
					System.out.print("번호>>");
					int bno = sc.nextInt();
					
					bDao.deleteBoard(bno);
					
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
					
				// 9. 프로그램종료	
				}else if(code == 9) {
						System.out.println("■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■");
						System.out.println("■□■□■ 프로그램이 종료 되었습니다.");
						System.exit(0);
				
				
				}
		
		
		
		
		
		
		
		
	    
		
		
		
		
		
		}

	
	}







}
