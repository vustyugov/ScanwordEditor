package my.program.root.controller;

import javafx.beans.property.*;
import javafx.collections.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import my.program.root.ScanwordEditorApp;

public class ScanwordsWordListOverviewController {
	private class WordView {
		private StringProperty word;
		private StringProperty scanwordName;
		private StringProperty existingInDic;
		private StringProperty repeat;
		
		public WordView(String word, String sName, String eIDic, String repeat) {
			this.word = new SimpleStringProperty(word);
			this.scanwordName = new SimpleStringProperty(sName);
			this.existingInDic = new SimpleStringProperty(eIDic);
			this.repeat = new SimpleStringProperty(repeat);
		}
		
		public void setWord(String word) {
			this.word.set(word);
		}
		
		public String getWord() {
			return this.word.get();
		}
		
		public StringProperty wordProperty() {
			return word;
		}
		
		public void setScanwordName(String sName) {
			this.scanwordName.set(sName);
		}
		
		public String getScanwordName() {
			return this.scanwordName.get();
		}
		
		public StringProperty scanwordNameProperty() {
			return scanwordName;
		}
		
		public void setExistingInDic(String eIDic) {
			this.existingInDic.set(eIDic);
		}
		
		public String getExistingInDic() {
			return existingInDic.get();
		}

		public StringProperty existingInDic() {
			return this.existingInDic;
		}
		
		public void setRepeate( String repeat) {
			this.repeat.set(repeat);
		}
		
		public String getRepeat() {
			return repeat.get();
		}
		
		public StringProperty repeat() {
			return repeat;
		}
	}
	
	@FXML
	private AnchorPane wordListView;
	
	@FXML
	private TableView<WordView> wordsTable;
	
	@FXML
	private TableColumn<WordView, String> wordTableColumn;
	
	@FXML
	private TableColumn<WordView, String> scanwordTableColumn;
	
	@FXML
	private TableColumn<WordView, String> dictionaryTableColumn;
	
	@FXML
	private TableColumn<WordView, String> repeatTableColumn;
	
	private Stage stage;
	private ScanwordEditorApp mainApp;
	
	@FXML
	private void initialize() {
		wordTableColumn.setCellValueFactory(cellData -> cellData.getValue().wordProperty());
		scanwordTableColumn.setCellValueFactory(cellData -> cellData.getValue().scanwordNameProperty());
		dictionaryTableColumn.setCellValueFactory(cellData -> cellData.getValue().existingInDic());
		repeatTableColumn.setCellValueFactory(cellData -> cellData.getValue().repeat());
	}
	
	public void setListStage(Stage stage) {
		this.stage = stage;
	}
	
	public void setMainApp(ScanwordEditorApp mainApp) {
		this.mainApp = mainApp;
		ObservableList<WordView> list = FXCollections.observableArrayList();
		for(String[] element: mainApp.getBlockWordList()) {
			list.add(new WordView(element[0],element[1], element[2], element[3]));
		}
		wordsTable.setItems(list);
	}
	
	@FXML
	private void handleUpdate() {
		wordListView.setOnKeyPressed((KeyEvent event) -> {
			switch(event.getCode()) {
			case F5:
				ObservableList<WordView> list = FXCollections.observableArrayList();
				for(String[] element: mainApp.getBlockWordList()) {
					list.add(new WordView(element[0],element[1], element[2], element[3]));
				}
				wordsTable.setItems(list);
			default:
				break;
			}
		});
	}	
}