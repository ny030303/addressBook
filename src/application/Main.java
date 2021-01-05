package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import view.MainController;
import view.PageController;


public class Main extends Application {
	
	public static Main app;
	private Stage popupStage;
	public PageController PageController;
	public MainController MainController;

	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			app = this;
			
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/MainLayout.fxml"));
			AnchorPane root = (AnchorPane) loader.load();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
			
			FXMLLoader scorePopupLoader = new FXMLLoader();
			scorePopupLoader.setLocation(getClass().getResource("/view/PageLayout.fxml"));
			
			popupStage = new Stage();
			popupStage.setTitle("기록");
			popupStage.initModality(Modality.WINDOW_MODAL);
			popupStage.initOwner(primaryStage);
			
			AnchorPane popup = scorePopupLoader.load();
			Scene popupScene = new Scene(popup);
			popupStage.setScene(popupScene);
			
			PageController = scorePopupLoader.getController();
			PageController.setDialogStage(popupStage);
			primaryStage.setScene(scene);
			primaryStage.show();
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void openPopup() {
//		popupController.setScore(score);
		popupStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
