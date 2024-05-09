package dao;

import java.util.List;
import java.util.Map;

import util.JDBCUtil;

public class MemberDao {
	private static MemberDao instance;

	private MemberDao() {

	}

	public static MemberDao getInstance() {
		if (instance == null) {
			instance = new MemberDao();
		}
		return instance;
	}
	
	JDBCUtil jdbc = JDBCUtil.getInstance();
	
	public void sign (List<Object> param) {
		String sql = " INSERT INTO JAVA_MEMBER\r\n" + 
					 "VALUES((SELECT NVL(MAX(MEM_NO),0) + 1 FROM JAVA_MEMBER), "
					 + " ?, ?, ?, 'N', ?)";
		
		jdbc.selectOne(sql, param);
		
	}
	
	
	public Map<String, Object> login(List<Object> param) {
		String sql = "SELECT *\r\n" + 
					 "FROM JAVA_MEMBER\r\n" + 
					 "WHERE ID = ?\r\n" + 
					 "AND PASS = ?\r\n" + 
					 "AND DELYN = 'N'\r\n" + 
					 "AND ROLE = ?";
		return jdbc.selectOne(sql, param);
		
	}
}
