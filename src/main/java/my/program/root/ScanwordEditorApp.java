package my.program.root;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import my.program.root.controller.DictionaryViewController;
import my.program.root.controller.ScanwordsWordListOverviewController;
import my.program.root.model.StorageType;
import my.program.root.model.dao.api.ScanwordDao;
import my.program.root.model.dao.impl.DaoFactory;
import my.program.root.model.dictionary.*;
import my.program.root.model.scanword.*;
import my.program.root.model.util.ScanwordUtil;

public class ScanwordEditorApp extends Application {
	private Stage primaryStage;
	private AnchorPane rootLayout;
	private Block block;
	
	@Override
	public void start(Stage primaryPane) throws Exception {
		this.primaryStage = primaryPane;
		this.primaryStage.setTitle("ScanwordEditor");
		Dictionary.getInstance();
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
			
			
			Stage listStage = new Stage();
			listStage.setTitle("Words");
			listStage.initModality(Modality.WINDOW_MODAL);
			listStage.initOwner(primaryStage);
			
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
			
			DictionaryViewController controller = loader.getController();
			controller.setMainApp(this);
			controller.setDictionary(Dictionary.getInstance());
//			DateFormat format = new SimpleDateFormat("dd/mm/yyy HH:mm:ss");
//			Calendar cal = Calendar.getInstance();
//			Date date = cal.getTime();
//			String time = format.format(date);
			block = new Block("test", "22/12/1223 12:12:12");
			controller.setScanword(block);			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private List<String> getRepeatedWords(List<String> list) {
		Set<String> result = new HashSet<String> ();
		List<String> tmp = new ArrayList<String>(list);
		for(int index = 0; index < tmp.size(); index++) {
			for(int nIndex = index+1; nIndex < tmp.size(); nIndex++) {
				if(list.get(index).equals(list.get(nIndex))) {
					result.add(list.get(index));
					break;
				}
			}
		}
			
		return new LinkedList<String>(result);
	}
	
	public List<String[]> getBlockWordList() {
		List<String[]> list = new LinkedList<String[]>();
		String[] listElement = null;
		List<String> repeatedWords = this.getRepeatedWords(block.getWordsList());
		for(Scanword scanword: block.getScanword()) {
			for(String word: scanword.getWords()) {
				listElement = new String[4];
				listElement[0] = word;
				listElement[1] = scanword.getName();
				if(Dictionary.getInstance().containsWord(word)) {
					listElement[2] = "true";
				} else {
					listElement[2] = "false";
				}
				if(repeatedWords.contains(word)) {
					listElement[3] = "true";
				} else {
					listElement[3] = "false";
				}
				list.add(listElement);
			}
		}
		return list;
	}
	
	public void showScanwordsWordListOverview() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(ScanwordEditorApp.class.getResource("view/ScanwordsWordListOverview.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			
			Stage listStage = new Stage();
			listStage.setTitle("Words");
//			listStage.initModality(Modality.WINDOW_MODAL);
//			listStage.initOwner(primaryStage);
			
			Scene scene = new Scene(page);
			listStage.setScene(scene);
			
			
			ScanwordsWordListOverviewController controller = loader.getController();
			controller.setListStage(listStage);
			controller.setMainApp(this);
			
			listStage.show();
		} catch(IOException e) {
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