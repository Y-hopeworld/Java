package problem.board;

import java.util.Scanner;

import problem.BOOK.BookDAO;

// 게시판 만들기
// 게시글 등록,수정,삭제,조회,검색,정렬,상세게시글,만든이,프로그램종료


public class BoardMain {

	public static void main(String[] args) {
	
		Scanner sc = new Scanner(System.in);

		int code= 0;
		
		while(true) {
		System.out.println("▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨");
		System.out.println("▨▨ 더블디 게시판 ");
		System.out.println("▨▨ 1. 게시글 등록");
		System.out.println("▨▨ 2. 게시글 수정");
		System.out.println("▨▨ 3. 게시글 삭제");
		System.out.println("▨▨ 4. 게시글 조회");
		System.out.println("▨▨ 5. 게시글 검색");
		System.out.println("▨▨ 6. 게시글 정렬");
		System.out.println("▨▨ 7. 상세게시글");
		System.out.println("▨▨ 8. 만든이");
		System.out.println("▨▨ 9. 프로그램 종료");
		System.out.println("▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨");
		
		
		
		while(true) {
			System.out.print("▨▨ 번호>>");
			code = sc.nextInt();
			
			
			if(code >= 1 && code <= 9) {
				break;
			} else {
				System.out.println("잘못된 번호 입력입니다. 1~9번 중에 다시 입력해주세요.");
				continue;
			}
			
		} 
		
			BoardDAO bDao = new BoardDAO();
		  // 1. 게시물 등록
		  if(code == 1) { 
			  System.out.println("▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨");
			  System.out.print("▨▨ 1.게시물번호 >>");
			  int bno = sc.nextInt();
			  sc.nextLine();
			  
			  System.out.print("▨▨ 2.게시물제목 >>");
			  String title = sc.nextLine();
			  
			  System.out.print("▨▨ 3.내용 >>");
			  String content =sc.nextLine();
			  
			  System.out.print("▨▨ 4. 작성자>");
			  String writer = sc.nextLine();
			  
			  // 게시물등록에 담을 내용을 BoardDTO에  
			  BoardDTO bDto = new BoardDTO(bno, title, content, writer);
			  bDao.boardInsert(bDto);
			  
			  
		 // 2. 게시물 수정  : 바꾸고자 하는 게시물의 번호
		  } else if(code == 2) {
		
			  System.out.println("▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨");
			  System.out.print("▨▨ 수정하고자 하는 게시물의번호 >>");
			  int bno = sc.nextInt();
			  sc.nextLine();
			  
			  System.out.print("▨▨ 게시물제목 >>");
			  String title = sc.nextLine();
			  
			  System.out.print("▨▨ 게시물내용 >>");
			  String content =sc.nextLine();
			  
			  System.out.print("▨▨ 작성자 >>");
			  String writer =sc.nextLine();
			  
			
			  BoardDTO bDto = new BoardDTO(bno, title, content, writer);
			  
			  bDao.boardUpdate(bDto);
			  
			  
			  
     	  // 3. 게시물 삭제
		  }else if(code == 3) {
			  System.out.println("▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨");
			  System.out.println("▨▨삭제하고자 하는 게시물의 번호를 입력하세요");
			  System.out.print("▨▨ 번호 >>");
			  int bno = sc.nextInt();
			 
			  bDao.boardDelete(bno);
			  
			  
		  // 4. 게시물 조회
		  }else if(code == 4) {
			  System.out.println("▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨");
			  System.out.println("▨▨ 현재 등록된 게시물을 조회합니다.");
			  System.out.println("▨▨번호 \t  제목 \t \t 내용 \t 작성자 \t 작성일자 ");
			  bDao.boardSelect();
			  
			  
		  // 5. 게시물 검색
		  }else if(code == 5) {
			  System.out.println("▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨");
			  System.out.println("▨▨ 검색 하려는 내용을 입력하세요>>");
			  System.out.print("▨▨ 검색 >>");
			  sc.nextLine();
			  String search = sc.nextLine();
			  
			  bDao.boardSearch(search);
			  
			  
			  
		  // 6. 게사물 정렬
		  }else if(code == 6) {
			  System.out.println("▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨");
			  System.out.println("▨▨ 조회 순으로 게시물을 조회 해주세요>>");
			  
			  bDao.boardBoardSort();
			  
			  
	      // 7. 상세게시글
		  }else if(code == 7) {
			  
			  
			  System.out.println("▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨");
			  System.out.println("▨▨ 보고싶은 게시글의 번호를 입력하세요 >>");
			  System.out.print("▨▨ 번호 >>");
			  int bno = sc.nextInt();
			  
			  bDao.boardView(bno);
			  
		  // 8. 만든이
		  }else if(code == 8) {
			  System.out.println("▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨");
			  System.out.println("▨▨ Name : Board Program ");
			  System.out.println("▨▨ Version : 1.7");
			  System.out.println("▨▨ Use : JAVA,ORACLE");
			  System.out.println("▨▨ Date : 19.12.28");
			  System.out.println("▨▨ by 1Verse");
			  System.out.println("▨▨ Y.hopeworld@gmail.com");
			  
			  
		  // 9.프로그램 종료  
		  }else if(code == 9) {
			  System.out.println("▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨");
			  System.out.println("▨▨프로그램이 종료되었습니다.");
			  System.exit(0);
			  
		  }
		  
		
	
		
		
	}

	}
}
