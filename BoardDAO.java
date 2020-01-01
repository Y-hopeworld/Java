package problem.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;



public class BoardDAO {

	Connection conn;
	PreparedStatement pstmt;
	 // sql문을 만들 때 사용
	// preparedstatement 사용시 statement에서는 변수 값을 넣었던 것과 달리 변수 값 대신 ? 사용
	
	ResultSet rs;
 	// SELECT 쿼리 실행시 executeQuery() 메서드를 사용함
	// 출처:https://sumin172.tistory.com/74
	
	ArrayList<BoardDTO> list = new ArrayList<>();

	int result; 
	
	BoardDTO bDto;
	
 	//1.게시물 등록
	public void boardInsert(BoardDTO bDto) {
 		
 		try {
 			
 			// DBMAnager의 메서드인 getConnection 호출
 			conn = DBManager.getConnection();
 			
 			// 쿼리 만들기
 			String sql = " INSERT INTO tbl_board(bno,title,content,writer) " 
 							+ " VALUES(seq_board.NEXTVAL,?,?,?) ";
 			
 			pstmt = conn.prepareStatement(sql);
 			// PreparedStatement = DBManager.getConnection.prepareStatement(INSERT INTO tbl_board(bno,title,content,writer) " 
			//																+ " VALUES(seq_board.NEXTVAL,?,?,?) 
 			
 			// 쿼리문에서 ?에 데이터 넣기
 			pstmt.setString(1, bDto.getTitle());
 			pstmt.setString(2, bDto.getContent());
 			pstmt.setString(3, bDto.getWriter());
 			
 			//만든 쿼리 날리기
 			// executeUpdate : INSERT, UPDATE 같은 DDL이나 DML 실행할 때 사용
 			
 			int result = pstmt.executeUpdate();
 			
 			if(result > 0) {
 				System.out.println("▨▨ " + bDto.getTitle() + " 의 게시물이 등록되었습니다");
 			}else {
 				System.out.println("게시물등록에 실패 하였습니다. 관리자에게 문의해주세요");
 			}
 					
 			
 			
 			
			
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			
		}
 		
 		
 		
 	}
 
 	//2.게시물수정
 	public void boardUpdate(BoardDTO bDto) {
 	
 		
 		try {
	 			// DBManager의 메서드인 getConnection는  쿼리를 날릴 수 있는 Statement을 실행 할 수 있다
	 			// 그 실행 할 수 있는 것을 conn에 저장
	 			conn = DBManager.getConnection();
	 			
	 			// sql문 만들기
	 			String sql = " UPDATE tbl_board " 
	 						 + " SET title = ?, " 
	 						 + " content = ?, " 
	 						 + " writer = ? " 
	 						 + "WHERE bno = ? ";
	 			// 쿼리문만들기
	 			// 쿼리문을 만드는 두가지 방법 
	 			// 1. createStatment() : sql에 모든 값을 다 넣어야 사용가능
	 			// 2. prepareStatement() : sql을 만든 후 값이 동적으로 바뀌는 것을 ? 으로 바꾸고 , ?에 원하는 값을 넣는 방법
	 			pstmt = conn.prepareStatement(sql);
	 			
	 			// 쿼리문을 만들 때 ?에 데이터를 넣는 방법
	 			pstmt.setString(1,bDto.getTitle());
	 			// bDto에 있는 내용을 getter를 통해 데이터를 꺼내 넣는다.
	 			
	 			pstmt.setString(2,bDto.getContent());
	 			pstmt.setString(3,bDto.getWriter());
	 			pstmt.setInt(4, bDto.getBno());
	 			
	 			//만든 쿼리를 날리기
	 			// 쿼리 날리는 방법
	 			// 1) executeQuery : resultSet를 만드는 sql문을 사용하고, 주로 SELECT문을 수행할 떄 사용
	 			// 2) executeUpdate: INSERT, UPDATE 같은 DDL이나 DML을 실행할 떄 사용
	 			// DDL데이터 조작어(테이블 안 데이터변형) : INSERT, UPDATE, DELETE
	 			// DDL데이터 정의어(테이블같은 데이터구조 정의) : CREATE , ALTER, DROP
	 			
	 			int result = pstmt.executeUpdate();
	 						// DBManager.getConnection().prepareStatement(sql).executeUpdate();
	 			
	 			if(result > 0) {
	 				System.out.println(bDto.getTitle()+"의 게시물이 수정 되었습니다.");
	 				
	 			}else {
	 				System.out.println("게시물 수정에 실패했습니다. 관리자에게 문의하세요.");
	 				
	 			}
	 			
 			} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
		}
 		
 		
	}
 	
 	//3.게시물삭제
 	public void boardDelete(int bno) {
 		
 		try {
 				// 쿼리를 날릴 수 있는 Statement 실행문 불러오기
 				conn = DBManager.getConnection();
 				
 				// sql문 만들기
 				String sql = "DELETE tbl_board " 
 							+ "WHERE bno = ?";
 				
 				// ?에 들어갈 값을 넣을 수 있게 만들어주기
 				pstmt = conn.prepareStatement(sql);
 						
 				// ?에 들어갈 값 생성
 				pstmt.setInt(1, bno);

 				int result = pstmt.executeUpdate();
 				
 				if(result>0) {
 					  System.out.println("▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨");
 					  System.out.println("게시물 "+ bno + "이 삭제 되었습니다.");
 				}else {
 					 System.out.println("게시물삭제에 실패했습니다. 관리자에게 문의해주세요.");
 				}
 				
 			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
				try {
						conn.close();
				} catch (Exception e2) {
					
				}
			
		}
 		
 		
 	}
 	
 	
 	
 	//4.게시물 조회
 	public void boardSelect() {
 	
 		try {
 		
 			// conn에 쿼리를 날리는 statement가 작성 가능하게 만들어짐
 	 		conn = DBManager.getConnection();
 	 		
 	 		//sql문 만들기
 	 		String sql = " SELECT * FROM tbl_board " 
 	 					 + " ORDER BY bno DESC ";
 	 		
 //PreparedStatement pstmt = DBManager.getConnection().prepareStatement(" SELECT * FROM tbl_board " 
//																		+ " ORDER BY bno DESC ";
// PreparedStatement는 변수 값을 넣었던 것과 달리 변수 값 대신 ? 사용
// 2. prepareStatement(): sql을 만든 후 값이 동적으로 바뀌는 것을 ? 바꾸고, ?에 원하는 값을 넣는 방법 	 		
 	 		pstmt = conn.prepareStatement(sql);
 			
 	 		//쿼리 날리기 : ResultSet은 SELECT 쿼리 실행시 executeQuery를 사용
 	 		ResultSet rs = pstmt.executeQuery();
 	 		// 결과 : DB에게 가서 데이터를 가지고 리턴해서 rs에 저장 
 	 		
 	 		list.clear();
 	 		// 리스트에 담긴 내용을 지우고
 	 		// 다시 리스트에 담아라
 	 		// ResultSet의 메서드 next() : 다음행으로 실행위치를 이동 한 후 한 레코드(row)를 가리킴
 	 		while(rs.next()) {
 	 			// 각 데이터를 변수에 저장
 	 			int bno = rs.getInt("bno");
 	 			String title = rs.getString("title");
 	 			String content = rs.getString("content");
 	 			String writer = rs.getString("writer");
 	 			Date regdate = rs.getDate("regdate");
 	 			
 	 			//저장한 변수를 하나의 줄로 만들기
 	 			BoardDTO bDto = new BoardDTO(bno, title, content, writer, regdate);
 	 			
 	 			// 저장된 하나의 줄들을 리스트에 담아줌
 	 			list.add(bDto);
 	 			
 	 	    }
 	 		
 	 		//출력
 	 		// for each(향상된 for문) :오직 배열의 값만 사용 할 수 있고, 수정 할 수는 없다
 	 		
 	 		for (BoardDTO line : list) {
				
 	 			System.out.println(line.toString());
 	 			
			}
 	 		
 	 		
			
		} catch (Exception e) {
			// 에러메세지의 발생 근원지를 찾아서 단계별로 에러를 출력
			e.printStackTrace();
		}
 		
 			
 		
 	}
 	
 	//5.게시물검색
 	public void boardSearch(String search) {
 		try {
	 				conn = DBManager.getConnection();
	 				
	 				String sql =" SELECT * FROM tbl_board " 
	 							+" WHERE title LIKE ? OR " 
	 							+ "content LIKE ?";
	 				
	 				pstmt = conn.prepareStatement(sql);
	 				
	 				pstmt.setString(1, "%"+search+"%");
	 				pstmt.setString(2, "%"+search+"%");
	 				
	 				
	 				ResultSet rs = pstmt.executeQuery();
	 				
	 				list.clear();
	 				
	 				while(rs.next()) {
	 					// 변수 하나하나에 데이터 담기
	 					int bno = rs.getInt("bno");
	 					String title = rs.getString("title");
	 					String content = rs.getString("content");
	 					String writer = rs.getString("writer");
	 					Date regdate = rs.getDate("regdate");
	 					
	 					// 변수에 담겨진 데이터를 하나로 묶어주기
	 					BoardDTO bDto = new BoardDTO(bno, title, content, writer, regdate);
	 					
	 					//리스트에 한줄 한줄 담아주기
	 					list.add(bDto);
	 					
	 			     }
	 				System.out.println("▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨");
	 				System.out.println("▨▨\t"+ search + "로 검색한 결과 총 "+ list.size() + "건이 검색 되었습니다");
	 				
	 				// rs에 담긴 DB데이터를 list에 넣어서 출력
	 				printQuery(list);
	 				
	 				
 		} catch (Exception e) {
			e.printStackTrace();
		} 
 		
 		
 	}
 	
 	//6.정렬
 	public void boardBoardSort() {
 		try {
 			conn = DBManager.getConnection();
 			
 			String sql = "SELECT * FROM tbl_board " 
 					   + "ORDER BY viewcnt DESC";
 			
 			pstmt = conn.prepareStatement(sql);
 			
 			ResultSet rs = pstmt.executeQuery();
		
 			while(rs.next()) {
 				// 변수 하나하나에 데이터 담기
 				 int bno = rs.getInt("bno");
 				 String  title = rs.getString("title");
 				 String content = rs.getString("content");
 				 String writer = rs.getString("writer"); 
 				 int viewcnt = rs.getInt("viewcnt");
 				 Date regdate = rs.getDate("regdate");
 				 
 				// 변수에 담긴 내용을 한줄로 담기
 				 BoardDTO bDto = new BoardDTO(bno, title, content, writer, viewcnt, regdate);
 						 
 				// 한줄로 담긴 내용들을 리스트로 만들기 
 				list.add(bDto); 
 			}
 			printQuery(list);
 			
		} catch (Exception e) {
		
		} finally {
			
		}
 		
 		
 	}
 	
 	//7.상세게시글
 	public void boardView(int bno) {
 		//상세게시글 조회수 +1 증가
 		
 		int result = viewCntPlus(bno);
	 		if(!(result>0)) {
	 			System.out.println("▨▨ 조회수 증가 실패! 관리자에게 문의하세요");
	 		    return; // 실패하면 메서드 종료
	 		}
	 		
 		
 		try {
	 			// statement를 작성 해줄 메서드 호출
	 			conn = DBManager.getConnection();
	 			
	 			//sql문 작성
	 			String sql = " SELECT * FROM tbl_board " 
	 						+ "WHERE bno = ?";
	 			
	 			// ?에 값을 넣을 수 있도록 만들어주기
	 			pstmt = conn.prepareStatement(sql);
	 			
	 			// ?에 값 넣어주기
	 			pstmt.setInt(1, bno);
	 			
	 			// DB으 ㅣ데이터를 받아 rs에 담기
	 			ResultSet rs = pstmt.executeQuery();
	 			
	 			list.clear();
	 			
	 			while(rs.next()) {
	 				// 변수에 하나하나 값 담기
	 				 bno = rs.getInt("bno");
	 				 String  title = rs.getString("title");
	 				 String content= rs.getString("content");
	 				 String writer= rs.getString("writer");
	 				 int viewcnt = rs.getInt("viewcnt");
	 				 Date regdate = rs.getDate("regdate");
	 						
	 			     
	 				  bDto = new BoardDTO(bno, title, content, writer, viewcnt, regdate);
	 				 
	 			} 
	 			
	 	 		System.out.println("▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨");
	 			System.out.println("▨▨ 게시글 번호       | " + bno );
	 			System.out.println("▨▨ 게시글 제목       | " + bDto.getTitle() );
	 			System.out.println("▨▨ 게시글 내용       | " + bDto.getContent() );
	 			System.out.println("▨▨ 게시글 작성자    | " + bDto.getWriter() );
	 			System.out.println("▨▨ 게시글 조회수    | " + bDto.getViewcnt() );
	 			System.out.println("▨▨ 게시글 작성일자 | " + bDto.getRegdate() );
	 			System.out.println("▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨");
	 			
	 			
 		} catch (Exception e) {
			// TODO: handle exception
 		  }finally {
			
		   }
 	
 	}
 	
 	
 	
 	// 조회수를 증가 해주는 메서드
 	public int viewCntPlus(int bno) {
 		
 		try {
	 			conn = DBManager.getConnection();
	 			String sql =" UPDATE tbl_board " 
	 					   + " SET viewcnt = viewcnt + 1 " 
	 					   + " WHERE bno = ?";
	 			
	 			pstmt = conn.prepareStatement(sql);
	 			
	 			pstmt.setInt(1, bno);
	 			
	 			result = pstmt.executeUpdate();
	 			
	 			

 		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
		} return result;
 		
 	}
 	
 	


 	// 조회 된 결과를 출력하는 함수
 	public void printQuery(ArrayList<BoardDTO> list) {
 		
 		
 		System.out.println("▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨");
		System.out.println("번호 \t 제목  \t \t 내용 \t	 작성자 	  조회수	 작성일자  ");
		System.out.println("▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨");
		
		// 검색한 결과를 출력
 			for (BoardDTO line : list) {
 				System.out.println(line.toString());
 			}
	
 		
 	}
 	
 	

}
