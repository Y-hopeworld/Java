package board;

import java.util.HashMap;

import javax.security.auth.login.Configuration;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import mybatis.SqlMapConfig;

public class MemberDAO {
		SqlSessionFactory sqlSessionFactory = SqlMapConfig.getSqlSession();
		SqlSession sqlSession;
		
		int result;
		
		// 로그인 기능
		public void login(String id , String pw) {
			
			sqlSession = sqlSessionFactory.openSession();
			
			// 여러 매개변수를 받아 올 때는 HashMap이나 DTO을 통해서 받아옴
			HashMap<String, String> map = new HashMap<>();
			map.put("id", id);
			map.put("pw", pw);
			
			
				try {
					// 데이터가 한건 
					// 데이터 받아 올 떄 0 또는 1로 받아옴
						int flag =sqlSession.selectOne("member.login",map);
						
					if(flag > 0) { // 로그인 성공
						System.out.println("♡♥♡♥♡♥♡♥♡♥♡♥♡♥♡♥♡♥♡♥♡♥♡♥♡♥♡♥♡♥♡♥♡♥♡♥♡♥♡♥♡♥♡♥♡♥♡♥♡♥♡♥♡♥♡♥♡♥♡♥");
						System.out.println("♡♥반갑습니다 "+ id + "님 ♡♥");
						System.out.println("♡♥♡♥♡♥♡♥♡♥♡♥♡♥♡♥♡♥♡♥♡♥♡♥♡♥♡♥♡♥♡♥♡♥♡♥♡♥♡♥♡♥♡♥♡♥♡♥♡♥♡♥♡♥♡♥♡♥♡♥");
						BoardMain.session= "YES";
						BoardMain.userid = id;
						
						
					}else { // 로그인 실패
						System.out.println("ID 또는 PW가 틀렸습니다. 확인해주세요");
						return;
						
					}
					
				} catch (Exception e) {
						e.printStackTrace();
				} finally {
					sqlSession.close();
				}
			
			
		}
		
		// 로그아웃 기능
		public void logout() {}
		
		
		
		public String checkid(int bno) {
			
			sqlSession = sqlSessionFactory.openSession();
			return sqlSession.selectOne("checkid",bno) ;
			
		}
		
		
		//회원가입
		//회원수정
		//회원삭제
		//회원검색
		//회원조회
		
		
}
