package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/*
 * LPROD 테이브에 새로운 데이터 추가하기
 * 
 * lprod_gu와 lprod_nm은 직접 입력받아 처리하고,
 * lprod_id는 현재의 lprodid들 중 제일 큰값보다 1 증가된 값으로 한다.
 * (기타사항: lprod_gu도 중복되는지 검사한다.)
 */
public class T04_JdbcTest {
	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		
		try {
			// 1. 드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 2. DB에 접속(Connection객체 생성)
			String ur1 = "jdbc:oracle:thin:@localhost:1521/xe";
			String userId = "PSH";
			String password = "java";
			
			
			conn = DriverManager.getConnection(ur1, userId, password);
			
			
			stmt = conn.createStatement();
			
			Scanner s = new Scanner(System.in);
			System.out.print("상품번호를 입력해주세요 > ");
			String gu = s.nextLine();
			
			System.out.print("상품종류를 입력해주세요 > ");
			String nm = s.nextLine();
			
			String sql = " insert into lprod " + " (lprod_id, lprod_gu, lprod_nm) " + " values(101, gu, nm) ";
			int cnt = stmt.executeUpdate(sql);
			System.out.println("첫번째 반환값 : " + cnt);
			
		}catch(ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패!!!");
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			// 6. 종료 (사용ㄷ했던 자원을 모두 반납한다.)
			if(stmt != null) try {stmt.close();}catch(SQLException e) {}
			if(conn != null) try {conn.close();}catch(SQLException e) {}
		}
	}
}
