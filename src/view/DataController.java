package view;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import application.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import util.DBUtil;

public class DataController {
	@FXML
	private Label number;

	@FXML
	private Label addressname;
	
	@FXML
	private Label ispublic;
	
	@FXML
	private Label username;
	
	@FXML
	private Label date;
	
	@FXML
	private Button deletebtn;
	
	@FXML
	private Button updatebtn;
	
	public void setData(int number, String addressname, String ispublic, String username, String date) {
//		System.out.println(number + "," + addressname + "," + ispublic + "," + username + "," +  date);
		this.number.setText(number + "");
		this.addressname.setText(addressname);
		this.ispublic.setText(ispublic);
		this.username.setText(username);
		this.date.setText(date);
	}
	
	public void clickDeleteBtn() {
		
		System.out.println(this.number.getText());
		Connection con = DBUtil.getConnection(); //DB연결 가져오기
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "DELETE FROM `project1` WHERE number = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, this.number.getText());
			pstmt.executeUpdate();
		
			Main.app.MainController.reloadData();
			
		} catch (Exception e) {
			System.out.println("데이터베이스 쿼리중 오류 발생");
			e.printStackTrace();
		} finally {
			DBUtil.close(pstmt);
			DBUtil.close(con);
		}
	}
	
	public void clickUpdateBtn() {
		System.out.println("들어옴");
		Main.app.PageController.writeBtnName("수정");
		Main.app.PageController.writeTexts(this.number.getText(), this.addressname.getText(), this.ispublic.getText(), this.username.getText());
		System.out.println(this.number.getText());
		Main.app.openPopup();
		
	}
}