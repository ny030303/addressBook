package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import domain.DbVO;
import util.DBUtil;

public class MainApp {
	public static void main(String[] args) {
		DBUtil db = new DBUtil();
		
		
	}
	
	public void reloadTopScore() {
		Connection con = DBUtil.getConnection(); //DB연결 가져오기
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM tetris ORDER BY `score` DESC LIMIT 0, 10";
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
//				list.add(makeTempVO(rs));
			}
			
		} catch (Exception e) {
			System.out.println("데이터베이스 쿼리중 오류 발생");
			e.printStackTrace();
		} finally {
			DBUtil.close(pstmt);
			DBUtil.close(con);
		}
	}
	private DbVO makeTempVO(ResultSet rs) throws Exception{
		DbVO temp = new DbVO();
		temp.setAddressname(rs.getString("addressname"));
		temp.setIspublic(rs.getString("ispublic"));
		temp.setUsername(rs.getString("username"));
		return temp;
	}
}
