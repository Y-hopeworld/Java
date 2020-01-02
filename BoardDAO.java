package board;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import mybatis.SqlMapConfig;



public class BoardDAO {
	
	// Mybatis 세팅값 호출
	// Session을 생성
	
	// 1. sqlSessionFactroy 만들기
	SqlSessionFactory sqlSessionFactory = SqlMapConfig.getSqlSession();
	
	
	// mapper에 접근하기 위한 SqlSession
	SqlSession sqlSession;
	
	int result;
	List<BoardDTO> list;
	
	
		// 1. 게시글등록
		public void insertBoard(String title, String content, String writer) {
			
			// mapper에 접근하기 위한  Sqlsession
			sqlSession = sqlSessionFactory.openSession(true);
			
			
				try {
						HashMap<String,Object> map = new HashMap<>();
						map.put("title", title);
						map.put("content", content);
						map.put("writer", writer);
						
						result = sqlSession.insert("insertBoard",map);
						
							if(result > 0) {
								System.out.println(title + "의 게시물이 등록되었습니다.");
							} else {
								System.out.println("게시물 등록에 실패했습니다. 관리자에게 문의해주세요.");
							}
						
					
				} catch (Exception e) {
						e.printStackTrace();
				}finally {
					sqlSession.close();
				}
			
			
		}
		
		// 2. 게시글 수정
		public void updateBoard(int bno, String title, String content, String writer) {
				// sqlSession을 실행 할 수 있도록 만들기 (true를 넣어줘야 Auto commit이 됌)
				sqlSession = sqlSessionFactory.openSession(true);
				
				try {	
						HashMap<String,Object> map = new HashMap<>();
						map.put("bno", bno);
						map.put("title", title);
						map.put("content", content);
						map.put("writer", writer);
						
						result = sqlSession.update("updateBoard",map);
						
						if(result > 0) {
							System.out.println(title + "의 게시물이 수정되었습니다.");
						} else {
							System.out.println("게시물 수정에 실패했습니다. 관리자에게 문의하세요");
						}
					
					
				} catch (Exception e) {
						e.printStackTrace();
				} finally {
					sqlSession.close();
				}
			
			
			
		}
		
		
		//3. 게시글 삭제
		public void deleteBoard(int bno) {
			
				sqlSession = sqlSessionFactory.openSession(true);
				
					try {
						
						HashMap<String,Integer> map = new HashMap<>();
						map.put("bno", bno);
						
						result = sqlSession.delete("deleteBoard", map);
						
						if(result>0) {
							System.out.println(bno + "의 게시물이 삭제 되었습니다.");
						}else {
							System.out.println("게시물 삭제에 실패했습니다. 관리자에게 문의하세요.");
						}
							
					} catch (Exception e) {
							e.printStackTrace();
					} finally {
						sqlSession.close();
					}
				
			
			
			
		}
		
		//4. 게시글 조회
		public void selectBoard() {
				sqlSession = sqlSessionFactory.openSession();
				 
				
					try {
						// sqlSession을 실행
						// 조회 결과가 한건인 경우 : selectone
						// 조회결과가  두건이상인경우: selectList
						
						 list = sqlSession.selectList("selectBoard");
						 
						 for (BoardDTO line : list) {
							System.out.println(line.toString());
						}
					 
					 
					
					} catch (Exception e) {
						e.printStackTrace();
					}	finally {
						sqlSession.close();
					}
						
			
			
			
		}
		
		//5. 게시글검색
		public void searchBoard(String keyword) {
			
			sqlSession = sqlSessionFactory.openSession();
				
				try {
					sqlSession = sqlSessionFactory.openSession();
					
					list = sqlSession.selectList("searchBoard",keyword);
					
					for (BoardDTO line : list) {
						System.out.println(line.toString());
					}
					
					
					
				} catch (Exception e) {
						e.printStackTrace();
				}finally {
					sqlSession.close();
				}
				
			
		}
		
		// 6. 정렬
		public void boardSortBoard() {
				// sqlSession을 실행 
				sqlSession = sqlSessionFactory.openSession();
					try {
						list = sqlSession.selectList("boardSortBoard");
						
						for (BoardDTO line : list) {
							
							System.out.println(line.toString());
							
						}
						
					} catch (Exception e) {
							e.printStackTrace();
					}finally {
						sqlSession.close();
					}
				
				
				
			
			
			
		}
		
		//7. 상세게시글
		public void viewBoard(int bno) {
			
			
			
			sqlSession = sqlSessionFactory.openSession();
			
				try {
					
					
					list = sqlSession.selectList("viewBoard", bno);
					
					viewCnt(bno);
					
					for (BoardDTO line : list) {
						System.out.println(line.toString());
					}
						
					
				} catch (Exception e) {
						e.printStackTrace();
				}finally {
						sqlSession.close();
				}
				
				
		}
		
		
		
		// 조회순 올려주는 메서드
		public void viewCnt(int bno) {
				
				sqlSession = sqlSessionFactory.openSession(true);
				
				try {
						
						result = sqlSession.update("viewCnt",bno);
					
					
					
				} catch (Exception e) {
						e.printStackTrace();
				} finally {
					sqlSession.close();
				}
				
				
				
			
			
		}
		
		
		
	

}

