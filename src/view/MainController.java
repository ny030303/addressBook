package view;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import application.Main;
import domain.DbVO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import util.DBUtil;

public class MainController {
	@FXML
	public Button addBtn;
	
	@FXML
	private VBox dataList;
	
	private ArrayList<DbVO> list = new ArrayList<>();
	
	
	@FXML
	public void initialize() {
		Main.app.MainController = this;
//		list = FXCollections.observableArrayList();
//		topScoreList.setItems(list);
//		App.app.game = new Game(gameCanvas, nextBlockCanvas, scoreLabel, list);
		reloadData();
	}
	
	public void reloadData() {
		list.clear(); //리스트를 전부 지우고 새로 가져오기
		Connection con = DBUtil.getConnection(); //DB연결 가져오기
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM `project1`";
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				list.add(makeDbVO(rs));
			}
			
			makeFXML();
			
		} catch (Exception e) {
			System.out.println("데이터베이스 쿼리중 오류 발생");
			e.printStackTrace();
		} finally {
			DBUtil.close(pstmt);
			DBUtil.close(con);
		}
	}
	
	private DbVO makeDbVO(ResultSet rs) throws Exception {
		DbVO temp = new DbVO();
		
		temp.setNumber(rs.getInt("number"));
		temp.setUsername(rs.getString("username"));
		temp.setAddressname(rs.getString("addressname"));
		temp.setIspublic(rs.getString("ispublic"));
		temp.setAdddate(rs.getString("adddate"));
		return temp;
	}
	
	
	public void makeFXML() throws Exception {
		dataList.getChildren().clear();
		for(DbVO item : this.list) {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/DataLayout.fxml"));
			AnchorPane root = (AnchorPane)loader.load();
			
			DataController ic = loader.getController();
			
			int number = item.getNumber();
			String addressname = item.getAddressname();
			String ispublic = item.getIspublic();
			String username = item.getUsername();
			String date = item.getAdddate();
			ic.setData(number, addressname, ispublic, username, date);
			dataList.getChildren().add(root);
		}
	}
//	
//	public void keypressHandle(KeyEvent e) {
//		if( e.getCode() == KeyCode.ENTER ) {
//			search();
//		}
//	}
	
	public void insertBtnEvent() {
		Main.app.PageController.writeBtnName("등록");
		Main.app.openPopup();
	}
}
