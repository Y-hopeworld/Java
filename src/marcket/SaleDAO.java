package marcket;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import mybatis.SqlMapConfig;

public class SaleDAO {

	// 판매관련 클래스 DAO
	SqlSessionFactory sqlSessionFactory = SqlMapConfig.getSqlSession();
	SqlSession sqlSession ;
	int result;
	List<SaleDTO> list;
	
	int cnt=0;
	int price=0; 
	
	public int insertSale(HashMap<String, Object> map) {
		sqlSession = sqlSessionFactory.openSession(true);
			
			try {
					result = sqlSession.insert("sale.insertSale",map);
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				sqlSession.close();
			}
			return result;
			
	}
	
	// 일일 판매량 출력하는 함수
	public void dashBoard() {
			sqlSession = sqlSessionFactory.openSession();
		
			try {
					list = sqlSession.selectList("dashBoard");
					int i = 0;
					System.out.println("번호 \t 제품명 \t 판매수량 \t 가격");
					
					for (SaleDTO line : list) {
						System.out.print((i+1)+"\t");
						System.out.print(line.getSname() + "\t");
						System.out.print(line.getCnt() + "\t");
						System.out.println(line.getTprice() + "\t");
						cnt += list.get(i).getCnt();
						price += list.get(i).getTprice();
						i+=1;
					}
						
					System.out.println("오늘 판매한 제품은  총"+ list.size()+"종류, 총"+cnt+"개, 판매액"+ price+ "원입니다.");
						
						
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				sqlSession.close();
			}
			
	}
					
	
}
					
					
					
					
					
					
				
				
	
	
	
	
	
	
