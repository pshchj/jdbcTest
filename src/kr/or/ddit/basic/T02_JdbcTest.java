package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * Select 예제
 */
public class T02_JdbcTest {
/*
 * 문제1) 사용자로부터 lprod_id값을 입력받아 입력한 값보다 lprod_id가 큰 자료들을 출력하시오.
 * 
 * 문제2) lprod_id값을 2개 입력받아서 두 값 중 작은 값부터 큰값 사이의 자료를 출력하시오.
 */
	
	public static void main(String[] args) {
		// DB작업에 필요한 객체변수 선언
				Connection conn = null;
				Statement stmt = null;
				ResultSet rs = null;
				
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					
					String ur1 = "jdbc:oracle:thin:@localhost:1521/xe";
					String userId = "PSH";
					String password = "java";
					
					conn = DriverManager.getConnection(ur1, userId, password);
					
					stmt = conn.createStatement();
					
					String sql = "select * from lprod"; // 실행할 SQL문
					
					// SQL문이 select일 경우에는 excuteQuery()메서드 이용하고,
					//       insert, update, delete일 경우에는 executeUpdate()메서드 이용함.
					rs = stmt.executeQuery(sql);
				 	
					// 5. ResultSet객체에 저장되어 있는 자료를 반복문과 next()메서드를 이용하여
					//    차례로 읽어와 처리한다.
					System.out.println("실행한 쿼리문 : " + sql);
					System.out.println("=== 쿼리문 실행결과 ===");
					
					// rs.next() => ResultSet객체의 데이터를 가리키는 포인터를 다음 레코드로
					//              이동시키고, 그 곳에 자료가 있으면 true, 없으면 false를 반환한다.
					while(rs.next()) {
						Scanner s = new Scanner(System.in);
						System.out.print("id를 입력해주세요>");
						int num = Integer.parseInt(s.nextLine());
						if(rs.getInt("lprod_id") > num) {
							System.out.println("lprod_id : " + rs.getInt("lprod_id"));
							System.out.println("lprod_gu : " + rs.getString("lprod_gu"));
							System.out.println("lprod_nm : " + rs.getString("lprod_nm"));
							System.out.println("-------------------------------------");
						}
					}
					System.out.println("출력 끝...");
				}catch(SQLException e) {
					e.printStackTrace();
				}catch(ClassNotFoundException e) {
					e.printStackTrace();
				}finally {
					// 6. 종료 (사용ㄷ했던 자원을 모두 반납한다.)
					if(rs != null) try {rs.close();}catch(SQLException e) {}
					if(stmt != null) try {stmt.close();}catch(SQLException e) {}
					if(conn != null) try {conn.close();}catch(SQLException e) {}
				}
	}
}
