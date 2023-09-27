package se01.ex01;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

//DB에 접근 (Access)해서 데이터 생성, 조회, 수정, 삭제 (CRED)
public class MemberDAO {
	
	private static final String driver = "oracle.jdbc.driver.OracleDriver";
	private static final String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	private static final String user = "scott";
	private static final String pwd = "12341234";
	
	Connection conn;
	Statement stmt;
	//조회하는 메서드
	
	List<MemberVO> listMember(){
		
		List<MemberVO> memberlist=new ArrayList<>();
		
		
		
		try {
			connDB();
			String query="select * from t_member";
			System.out.println("실행하고자마는 쿼리: " + query);
//			ResultSet rs=stmt.excuteQuery(query);
			ResultSet rs=stmt.executeQuery(query);
			
			while(rs.next()) {
				String id = rs.getString("id");
				String pwd = rs.getString("pwd");
				String name = rs.getString("name");
				String email = rs.getString("email");
				Date joinDate=rs.getDate("joinDate");
				System.out.println();
				//가져온 위의 데이터를 어딘가에 저장해서 웹에 나오게 해야하는데 
				//우리는 그 저장할 틈을 MemberCo로 만들어둔다.
				//우선 저장할 MemberVo 객체 생성
				
				MemberVO vo=new MemberVO();
				
				vo.setId(id);
				vo.setPwd(pwd);
				vo.setName(name);
				vo.setEmail(email);
				vo.setJoinDate(joinDate);
				
				memberlist.add(vo);	
			}
			
		} catch (SQLException e) {
			System.out.println("SQL관련 예외");
		}
		
		
		
		return memberlist;
	}

	//DB에 연결하는 메서드
	void connDB(){
		
		
		try {
			Class.forName(driver);
			System.out.println("오라클 드라이버 로딩 성공");
			
			//Driver :
			conn=DriverManager.getConnection(url, user, pwd);
			
			//DB에 SQL을 보내기 위한 객체 만들기
			stmt=conn.createStatement();
			
			
		} catch (Exception e) {
			System.out.println("오라클 드라이버 관련 예외");
		}
		
	}
	
}
