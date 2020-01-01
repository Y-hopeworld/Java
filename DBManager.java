package problem.board;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBManager {

	// private : 외부에서 함부로 접근할 수 없게 숨겨주는 기능
	// static: 객체 생성 안 하고 사용 가능, 공유의 개념, 가비지 컬렉터에서 못 지움
	private static Connection conn;
	
	private final static String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private final static String URL = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
	private final static String USER = "java";
	private final static String PASS = "1234";
	
	
	//defalut 생성자
	private DBManager() {}
	
	// public : 해당 프로젝트 내에 있는 모든 클래스에서 사용가능
	// static : 객체사용없이 사용가능,공유의 개념,가비지 컬렉터 x
	public static Connection getConnection() {
		conn = null;
		
		if(conn == null) {
			try {
				
				// 1. 드라이버 생성 :드라이버 인터페이스를 구현한 클래스를 로딩
				// Class의 메서드인 forName을 호출해 oracle 에서 제공 되는 driver 클래스를 자바 JVM에 로딩
				Class.forName(DRIVER);
				// 결과 : 여러 driver 객체가 사용가능 하여 DriverManager를 사용할 수 있게 만듬

				//2. 연결 : 드라이버 매니저에게 connection 객체를 달라고 요청
				// DriverManger에 있는 static 메서드인 getConnection을 호출해 oracle에 연결하기 위한 커넥션 정보를 입력
				conn = DriverManager.getConnection(URL, USER, PASS);
				//결과: getConnection 수행결과로 connection 객체를 반환하는데, 이 객체를 통해 쿼리를 날리는 statement를 작성 할 수 있다 .
				
				
				
					
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			
		}
		
		return conn;
		
		
		
	}
	
	
	
	
}
