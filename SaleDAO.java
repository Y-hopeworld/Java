package marcket;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import mybatis.SqlMapContig;

public class SaleDAO {
	
	SqlSessionFactory sqlSessionFactory = SqlMapContig.getSqlSession();
	
	SqlSession sqlSession;
	
	List<SaleDTO> list;
	
	int result;
	
	
	
	
	// 1-1 제품계산
			public void pdtList(String sname, int cnt, int tprice) {
				
					sqlSession= sqlSessionFactory.openSession(true);
					
						try {	
								HashMap<String,Object> map = new HashMap<>();
								map.put("sname", sname);
								map.put("cnt", cnt);
								map.put("tprice", tprice);
								
								result = sqlSession.insert("pdtList",map);
								
								if(result>0) {
											System.out.println("총 가격은 " + tprice +"원 입니다.");
								}else {
										System.out.println("잘못된 경로입니다. 다시 입력해주세요");
								}
								
								
								
							
						} catch (Exception e) {
							e.printStackTrace();
						}finally {
							sqlSession.close();
						}
						
			}
			
			
			
			//7. 일일매출 현황
			public void selectSales() {
					
					sqlSession= sqlSessionFactory.openSession();
						
						try {
								list = sqlSession.selectList("selectSales");
							
								for (SaleDTO line : list) {
									
									System.out.println(line.toString());
									
								}
							
						} catch (Exception e) {
							e.printStackTrace();
						}finally {
							sqlSession.close();
						}
					
				
			}
			
			
	
	
}
