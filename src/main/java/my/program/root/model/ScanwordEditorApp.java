package my.program.root.model;

import java.io.*;
import java.util.List;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import my.program.root.controller.DictionaryViewController;
import my.program.root.model.dao.impl.DaoFactory;
import my.program.root.model.dictionary.*;
import my.program.root.model.scanword.Block;
import my.program.root.model.scanword.Scanword;
import my.program.root.model.util.ScanwordUtil;

public class ScanwordEditorApp extends Application {
	private Stage primaryStage;
	private AnchorPane rootLayout;
	
	@Override
	public void start(Stage primaryPane) throws Exception {
		this.primaryStage = primaryPane;
		this.primaryStage.setTitle("ScanwordEditor");
		
		showDictionaryView();
	}
		
	public void loadScanwordsFile(Block block, File file){
		DaoFactory.getScanwordDao().getConnetction(file.getAbsolutePath());
		List<Scanword> list = DaoFactory.getScanwordDao().readAll();
		block.setScanwords(list);
	}

	public void showDictionaryView() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(ScanwordEditorApp.class.getResource("view/DictionaryView.fxml"));
			rootLayout = (AnchorPane) loader.load();
			
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
			
			DictionaryViewController controller = loader.getController();
			controller.setMainApp(this);
			Block block = new Block("test", "23/2/2016 4:08:50");
			controller.setScanword(block);
			controller.setDictionary(Dictionary.getInstance());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Stage getPrimaryStage() {
		return this.primaryStage;
	}
	
	public static void main(String args) {
		ScanwordUtil.loadDictionaryFromDB(StorageType.SQLite, Dictionary.getInstance());
		launch(args);
	}
}