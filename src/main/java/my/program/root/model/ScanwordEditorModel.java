package my.program.root.model;

import java.io.*;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import my.program.root.controller.DictionaryViewController;
import my.program.root.model.dictionary.*;

public class ScanwordEditorModel extends Application {
	private Stage primaryStage;
	private AnchorPane rootLayout;
	
	@Override
	public void start(Stage primaryPane) throws Exception {
		this.primaryStage = primaryPane;
		this.primaryStage.setTitle("ScanwordEditor");
		
		showDictionaryView();
	}
		
	public void loadScanwordsFile(File file){
		
	}

	public void showDictionaryView() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(ScanwordEditorModel.class.getResource("view/DictionaryView.fxml"));
			rootLayout = (AnchorPane) loader.load();
			
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
			
			DictionaryViewController controller = loader.getController();
			controller.setMainApp(this);
			controller.setDictionary(Dictionary.getInstance());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Stage getPrimaryStage() {
		return this.primaryStage;
	}
}