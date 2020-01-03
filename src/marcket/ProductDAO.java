package marcket;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import mybatis.SqlMapConfig;

public class ProductDAO {

		SqlSessionFactory sqlSessionFactory = SqlMapConfig.getSqlSession();
		
		SqlSession sqlSession;
		int result;
		List<ProductDTO> list;
		Boolean flag = false; // default값은 false
		
		// 제품 등록 & 추가 기능 작동시 기존에 등록된 제품인지 최초 입고 제품인지 판별
		public boolean pdtAlready(String name) {
			sqlSession = sqlSessionFactory.openSession();
				try {
					// DB에 자료 한건만 뜨니까 selectOne
					result = sqlSession.selectOne("pdt.already",name);
					// 결과: mapper로 가서 실행후 DB에서 자료를 가지고 리턴해서 옴
					if(result>0) {
						flag = true;
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}finally {
					sqlSession.close();
				}
				return flag;
		}
		

		// 2-1 기존제품 추가
		public void cntPlusPdt(String pname, int cnt) {
				sqlSession = sqlSessionFactory.openSession(true);
				
				try {
						HashMap<String,Object> map = new HashMap<>();
						map.put("pname", pname);
						map.put("cnt", cnt);
			
						
						result = sqlSession.update("cntPlusPdt",map);
						
						
						if(result > 0) {
							System.out.println(pname + "의 제품이" + cnt +"개 추가 되었습니다." );
						}else {
							System.out.println("제품 추가가 실패 했습니다 ");
						}
						
						
						
					
				} catch (Exception e) {
					e.printStackTrace();
				}finally {
					sqlSession.close();
				}
			
		}
		
		// 2-2 새 제품 등록
		public void insertPdt(String pname, String company, int price, int cnt) {
			sqlSession = sqlSessionFactory.openSession(true);
				try {
						HashMap<String,Object> map = new HashMap<>();
						map.put("pname", pname);
						map.put("company", company);
						map.put("price", price);
						map.put("cnt", cnt);
						
						result = sqlSession.insert("insertPdt",map);
						
						if(result > 0) {
							System.out.println(pname + "의 제품이 등록 되었습니다.");
						}else {
							System.out.println("제품 등록에 실패하였습니다. 관리자에게 문의해주세요.");
						}
						
					
				} catch (Exception e) {
						e.printStackTrace();
				}finally {
					sqlSession.close();
				}
				
			
		}
		
	
		//3. 제품수정
		public void updatePdt(String pname, String company,int price,int cnt) {
				sqlSession = sqlSessionFactory.openSession(true);
					try {
							HashMap<String, Object> map = new HashMap<>();
							map.put("pname", pname);
							map.put("company", company);
							map.put("price", price);
							map.put("cnt", cnt);
							
							result = sqlSession.update("updatePdt",map);						
						 	
							if(result > 0) {
								System.out.println(pname + "의 제품 정보가 수정되었습니다.");
							}else {
								System.out.println("제품 수정을 실패했습니다. 관리자에게 문의해주세요.");
							}
						
					} catch (Exception e) {
							e.printStackTrace();
					}finally {
						sqlSession.close();
					}
					
				
			
		}
		
		//4.제품삭제
		public void deletePdt(String pname) {
				sqlSession = sqlSessionFactory.openSession(true);
				
					try {
							result = sqlSession.delete("deletePdt",pname);
							
							if(result > 0) {
								System.out.println("제품 " + pname + "를 삭제했습니다.");
							} else {
								System.out.println("제품 삭제에 실패했습니다. 관리자에게 문의해주세요.");
							}
						
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						sqlSession.close();
					}
				
		}
		
		//5.제품조회
		public void selectPdt() {
			sqlSession= sqlSessionFactory.openSession();
				try {
					
						list = sqlSession.selectList("selectPdt");
					
						for (ProductDTO line : list) {
							
							System.out.println("제품번호 \t  제품 이름 \t 회사 \t 가격 \t 수량 \t 재고일자");
							
							System.out.println(line.toString());
						}
						
						
				} catch (Exception e) {
					e.printStackTrace();
				}finally {
					sqlSession.close();
				}
				
			
		}
		
		//6.제품검색
		public void searchPdt(String keyword) {
					sqlSession = sqlSessionFactory.openSession(true);
						try {
								list = sqlSession.selectList("searchPdt",keyword);
								
								for (ProductDTO line : list) {
									System.out.println(line.toString());
								}
							
						} catch (Exception e) {
								e.printStackTrace();
						}finally {
							sqlSession.close();
						}
				
			
			
		}
		
		//7. 일일매출 현황
		public void salesPdt() {}

		
		//검색 유무기능
		public int searCheck(String keyword) {
			
			sqlSession = sqlSessionFactory.openSession();
				try {
						result = sqlSession.selectOne("searCheck",keyword);
						
					
						
				} catch (Exception e) {
					e.printStackTrace();
				}finally {
					sqlSession.close();
				}
				
				
				return result;
		}
		
		
		
}
