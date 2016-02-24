package my.program.root;

import java.io.*;
import java.util.List;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import my.program.root.controller.DictionaryViewController;
import my.program.root.model.StorageType;
import my.program.root.model.dao.api.ScanwordDao;
import my.program.root.model.dao.impl.DaoFactory;
import my.program.root.model.dictionary.*;
import my.program.root.model.scanword.*;
import my.program.root.model.util.ScanwordUtil;

public class ScanwordEditorApp extends Application {
	private Stage primaryStage;
	private AnchorPane rootLayout;
	
	@Override
	public void start(Stage primaryPane) throws Exception {
		this.primaryStage = primaryPane;
		this.primaryStage.setTitle("ScanwordEditor");
		ScanwordUtil.loadDictionaryFromDB(StorageType.SQLite, Dictionary.getInstance());
		showDictionaryView();
	}
		
	public void loadScanwordsFile(Block block, File file){
		ScanwordDao scanDao = DaoFactory.getScanwordDao();
		scanDao.getConnetction(file.getAbsolutePath());
		block.setFreeForReading(false);
		block.setScanwords(scanDao.readAll());
		block.setFreeForReading(true);
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
			controller.setDictionary(Dictionary.getInstance());
			Block block = new Block("test", "23/2/2016 4:08:50");
			controller.setScanword(block);			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Stage getPrimaryStage() {
		return this.primaryStage;
	}
	
	public static void main(String args) {
		launch(args);
	}
}