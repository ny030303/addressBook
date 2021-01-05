package view;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import application.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import util.DBUtil;

public class PageController {
	@FXML
	private TextField addressname;
	
	@FXML
	private TextField ispublic;
	
	@FXML
	private TextField username;
	
	@FXML
	private Label lblError;
	
	@FXML
	private Label tempLabel;

	@FXML
	private Button pageBtn;
	
	private Stage me;
	
	@FXML
	public void initialize() {
		Main.app.PageController = this;
	}
	
	public void setDialogStage(Stage me) {
		this.me = me;
	}
	
	public void clearTexts() {
		addressname.setText("");
		ispublic.setText("");
		username.setText("");
		lblError.setText("");
	}
	
	public void writeTexts(String tempLabel, String addressname, String ispublic, String username) {
		this.tempLabel.setText(tempLabel);
		this.addressname.setText(addressname);
		this.ispublic.setText(ispublic);
		this.username.setText(username);
		this.lblError.setText("");
	}
	
	public void writeBtnName(String name) {
		this.pageBtn.setText(name);
	}
	
	public void insert() {
		if(addressname.getText().isEmpty() || ispublic.getText().isEmpty() ||  username.getText().isEmpty()) {
			lblError.setText("비어있는 곳이 있는지 살펴보시기 바랍니다.");
			return;
		}
		System.out.println(this.pageBtn.getText() );
		if( this.pageBtn.getText() == "등록") {
			Connection con = DBUtil.getConnection(); //DB연결 가져오기
			PreparedStatement pstmt = null;
			String sql = "INSERT INTO project1 ( `addressname`, `ispublic`, `username`, `adddate`) "
					+ "VALUES (?,?,?,now())";
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, addressname.getText());
				pstmt.setString(2, ispublic.getText());
				pstmt.setString(3, username.getText());
//				pstmt.setDate(4,  Date.valueOf( LocalDate.now() ));
				pstmt.executeUpdate();
			} catch (Exception e) {
				System.out.println("데이터베이스 쿼리중 오류 발생");
				e.printStackTrace();
			} finally {
				DBUtil.close(pstmt);
				DBUtil.close(con);
			}
			
			
		} else if(this.pageBtn.getText() == "수정") {
			Connection con = DBUtil.getConnection(); //DB연결 가져오기
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = "UPDATE `project1` SET `addressname`= ?,`ispublic`= ?,`username`= ?,`adddate`= now() WHERE number = ?";
			try {
				pstmt = con.prepareStatement(sql);
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, this.addressname.getText());
				pstmt.setString(2, this.ispublic.getText());
				pstmt.setString(3, this.username.getText());
				pstmt.setString(4, this.tempLabel.getText());
				
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
		
		clearTexts();
		Main.app.MainController.reloadData();
		this.me.close();
		
	}
}
