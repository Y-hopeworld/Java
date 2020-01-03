package problem;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;



import mybatis.SqlMapConfig;

public class BankDAO {
	
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	ArrayList<BankDTO> list = new ArrayList<>();


	//MyBatis 세팅값 호출
	// Session을 생성하는 공장을 만드는 과정									
	SqlSessionFactory sqlSessionFactory = SqlMapConfig.getSqlSession();
	//						→ 클래스 메서드 : sqlSessionFactory을 만들어줘
	
	// mapper에 접근하기 위한 SqlSession
	SqlSession sqlSession;
	
	List<BankDTO> list2;
	int result;
	
	//1. 신규 계좌 개설
	public void insertBank(String bname, String pw) {
		
		// openSession(true) → Auto Commit이 된다( 자동 commit )
		sqlSession = sqlSessionFactory.openSession(true);
		
			try {
					BankDTO bDto = new BankDTO(bname, pw);
					result = sqlSession.insert("insertBank",bDto);
					
					// select와 다르게 insert는 commit을 해줘야한다, 하지만 위에 openSession에 true를 넣어주면 Auto Commit 해줌
					//sqlSession.commit();
						if(result > 0) {
							System.out.println("▦▦ "+bname + "님의 신규계좌가 등록 되었습니다.");
						} else {
							System.out.println("▦▦ 계좌개설에 실패하였습니다. 관리자에게 문의해주세요.");
						}
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				sqlSession.close();
			}
		
	}
	
	//2. 입금
	public void updateBank(int bno, int money) {
		
		//마이바티스에서 일하게 만드는 것
		sqlSession = sqlSessionFactory.openSession(true);
		
		
			try {
				// <> 은 레퍼런스 타입(객체자료형)만 가능
				//       프리미티브타입(기본자료형)
				// 	 	기본 자료형을 객체 자료형으로 바꿔주는 Integer
					HashMap <String, Integer> map = new HashMap<>();
					map.put("bno", bno);
					map.put("money", money);
					//동적쿼리
					map.put("flag", 1);
					
					
					result = sqlSession.update("changeMoney",map);
					
					if(result > 0) {
						System.out.println("▦▦ "+ bno + " 에 " + money +"가 입금되었습니다.");
						System.out.println("▦▦ 현재잔액은 "+balanceMoney(bno) + "입니다");
					}else {
						System.out.println("▦▦ 입금에 실패했습니다. 관리자에게 문의해주세요.");
					}
					
					
					
			} catch (Exception e) {
					e.printStackTrace();
			}finally {
				sqlSession.close();
			}
	}
	
	// 3. 출금
	public void minusMoney(int bno, int money) {
		
		sqlSession = sqlSessionFactory.openSession(true);
		HashMap<String, Integer> map = new HashMap<>();
		map.put("bno", bno);
		map.put("money", money);
		map.put("flag", 0);
		
		
			try {
					result = sqlSession.update("changeMoney",map);
						if(result > 0 ) {
							System.out.println("▦▦ 출금되었습니다.");
							System.out.println("▦▦ 현재 계좌잔액은" + balanceMoney(bno)+"입니다");
						} else {
							System.out.println("▦▦ 출금에 실패했습니다. 관리자에게 문의해주세요.");
						}
						
					
					
			} catch (Exception e) {
				e.printStackTrace();
			
			} finally {
				sqlSession.close();
			}
			
	}
	
	
	
	//계좌전체 조회
	public void selectBank() {
		
		// sqlSessionFactory.openSession()을 통해 sqlSession 만들어달라고 욫엉
		sqlSession = sqlSessionFactory.openSession();
		
		try {
			//sqlSession에게 일을 시킬거야
			// 조회 결과가 한 건 : selectone
			//		      두 건 이상 : selectList
			// INSERT : insertList , UPDATE : updateList
			list2 = sqlSession.selectList("selBank");
			
			
			for (BankDTO line : list2) {
				System.out.println(line.toString());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//반납해야함  다른애들이 써야하니까
			sqlSession.close();
		}
				
		
	}
	
	
/*public void BankInsert(BankDTO bDto) {
		
		//DB에 있는 자료를 자바로 옮김
		try {
			
			//DBManger에 있는 드라이버 로드 및 커넥션을 conn에 저장
			conn = DBManager.getConnection();
			
			//sql문 입력
			String sql = " INSERT INTO tbl_bank(bno,bname,pw) " + 
						 " VALUES(seq_bank.NEXTVAL,?,?)";
			
			// pstmt에 conn과 sql을 연결한 결과물을 저장
			pstmt = conn.prepareStatement(sql);
			// sql문 만드는 방법
			pstmt.setString(1, bDto.getBname());
			pstmt.setString(2, bDto.getPw());
			
			int result = pstmt.executeUpdate();
			
			if(result > 0) {
				System.out.println("▦▦"+ bDto.getBname()+"님의 계좌가 등록되었습니다.");
			}else {
				System.out.println("▦▦ 계좌등록에 실패하였습니다. 관리자에게 문의하세요.");
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
		}
		
	}*/
			
			
			
			
	
	// 4.계좌 조회
	/*public void BankSelect() {
		
		try {
			// conn에 DB에 있는 드라이브,커넥션 연결
			conn = DBManager.getConnection();
			// sql문작성
			String sql = "SELECT * FROM tbl_bank " + 
					     "ORDER BY bno DESC";
			
			// sql문을 만들어주세요
			pstmt = conn.prepareStatement(sql);
			
			// DB에 가서 데이터를 가지고 리턴하여 rs에 넣어주기
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				// 하나씩 자료를 넣어줌
				int bno = rs.getInt("bno"); 
				String bname = rs.getString("bname");
				String pw = rs.getString("pw");
				int money = rs.getInt("money");
				Date regdate = rs.getDate("regdate");
				
				// 하나씩 저장된 데이터를 모아 bDto에 저장
				BankDTO bDto = new BankDTO(bno, bname, pw, money, regdate);
				
				// 하나로 모은 데이터를 list에 저장
				list.add(bDto);
				
			}
			System.out.println("▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦");
			System.out.println("▦▦ 번호  \t  이름 \t 비밀번호 \t 계좌금액 \t 개설일자");	
			
			for (BankDTO line : list) {
				System.out.println(line.toString());
				
			}
				
			
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			
		}
		
		
	}
	*/
	
	// 5.계좌조회 (1건)
	public void selectAccount(int bno, String pw) {
		// 일할 sqlSession을 만들어줘
		sqlSession = sqlSessionFactory.openSession();
				
		try {
				// 한건만 조회니까 selsectOne
				// 계좌번호와 비밀번호를 넣어주어야 하기 때문에 자료를 넣는데 1건에 2개의 자료를 넣어야하기 때문에 DTO,List,Array,HashMap을 씀
				BankDTO bDto = new BankDTO(bno, pw);
				// 
				bDto = sqlSession.selectOne("selectAccount",bDto);
				
				// 계좌번호와 패스워드 일치 여부
					if(bDto == null) {
						System.out.println("▦▦ 존재하지 않는 계좌번호이거나 비밀번호가 틀렸습니다.");
						return;
					}else {
						//SelectOne → DTO
						//SelectList  List or DTO
						System.out.println("▦▦ "+ bno + "계좌의 총금액은 "+ bDto.getMoney()+"입니다.");
						
						
					}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
				
		
		
		
	}
	
	
	
	// 5. 계좌검색 
	public void BankSearch(String keyword) {
		try {
			conn = DBManager.getConnection();
			
			String sql = "SELECT * "+
						 "FROM tbl_bank " + 
						 "WHERE bname LIKE ? ";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, "%"+ keyword +"%");
			
			//rs에 pstmt에 있는 sql 데이터를 가져가다 저장
			rs = pstmt.executeQuery();
			
			
			list.clear();
			while(rs.next()) {
				// rs는 DB데이터이므로 값을 꺼내기
				 int bno = rs.getInt("bno");
				 String bname = rs.getString("bname");
				 String pw = rs.getString("pw"); 
				 int money = rs.getInt("money");
				 Date regdate = rs.getDate("regdate");
				 
				 // 꺼낸 데이터를 하나로 통합
				 BankDTO bDto = new BankDTO(bno, bname, pw, money, regdate);
				 
				 //하나로 통합한 데이터를 list에 저장
				 list.add(bDto);
			}

			System.out.println("▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦");
			System.out.println("번호\t 이름 \t 비밀번호  \t 잔액   \t 계좌개설일");
			
			for (BankDTO line : list) {
				System.out.println(line.toString());
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
		}
		
		
		}
	
		
	//  계좌해지
	public void deleteAccount(int bno, String pw) {
		
		sqlSession = sqlSessionFactory.openSession(true);
		
		// Hashmap = key 와  value로 
		// key , value
		//"bno",bno
		//"pw",pw
		
		HashMap<String, Object> map = new HashMap<>();
		map.put("bno", bno);
		map.put("pw", pw);
		
		
		try {
			// 0 또는 1로 값이 나옴
			result = sqlSession.delete("deleteAccount",map);
			
			if(result > 0) {
				System.out.println(bno + "계좌를 삭제했습니다.");
			}else {
				System.out.println("계좌해지에 실패했습니다.");
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			sqlSession.close();
		}
		
		
		
	}


	// 계좌잔액조회
	public int balanceMoney(int bno) {
		sqlSession = sqlSessionFactory.openSession();
		int money = 0;
		
		try {
			
			money = sqlSession.selectOne("balanceMoney", bno);
			
			System.out.println(">>>>"+money);
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}finally {
			sqlSession.close();
		}
			return money;
	}
	
	// 아이디와 비밀번호가 맞는지 체크 
	public boolean checkUser(int bno, String pw) {
		
		boolean flag = false;
		sqlSession = sqlSessionFactory.openSession();
		
		// 두건 이상의 자료를 넣으니까 object 넣어줌
		HashMap<String, Object> map = new HashMap<>();
		map.put("bno", bno);
		map.put("pw", pw);
		
		
			 try {
				 result = sqlSession.selectOne("checkUser",map);
					 if(result >0) {
						 flag = true;
					 }
				
			} catch (Exception e) {
					e.printStackTrace();
			}finally {
				sqlSession.close();
			}
		
		
			
			 
			 return flag;
		
		
	}
	
	
}


