package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bean.DBTestDTO;

public class DBTestDAO {
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String username = "c##java";
	private String password = "1234";
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public DBTestDAO() {
		try {
			Class.forName(driver);
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void getConnection() {
		try {
			conn = DriverManager.getConnection(url, username, password); 
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int insert(DBTestDTO dBTestDTO) {
		int su = 0;
		String sql ="INSERT INTO DBTEST VALUES(?,?,?,sysdate)";
		getConnection();
		
		try {
			pstmt = conn.prepareStatement(sql);// 생성
			
			//?에 데이터 대입
			pstmt.setString(1, dBTestDTO.getName());
			pstmt.setString(2, dBTestDTO.getAge());
			pstmt.setString(3, dBTestDTO.getHeight());
			
			su = pstmt.executeUpdate();//실행
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
				try {
					if(pstmt != null) pstmt.close();
					if(conn != null) conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return su;
	}

	public List<DBTestDTO> select() {
		List<DBTestDTO> list = new ArrayList<DBTestDTO>(); //부모 = 자식(다형성) 
		
		
		String sql = "select NAME,AGE,HEIGHT,TO_CHAR(LOGTIME,'yyyy.mm.dd') as LOGTIME from dbtest"; // 날짜 - yyyy.mm.dd 변환 LOGTIME AS 안쓰면 못찾는다.
																									
		
		getConnection();//접속
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();//실행.
			
			while(rs.next()) {
				DBTestDTO dbTestDTO = new DBTestDTO();
				dbTestDTO.setName( rs.getString("name"));
				dbTestDTO.setAge( rs.getString("age"));
				dbTestDTO.setHeight( rs.getString("height"));
				dbTestDTO.setLogtime( rs.getString("logtime"));
				
				list.add(dbTestDTO);
			}//while 현재 값이 없을때 까지 반복
			
		} catch (SQLException e) {
			e.printStackTrace();
			list = null;
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
		
		return list;
	}
}


//일반클래스
//- Object 상속
//- 반드시 new 생성해야 한다.