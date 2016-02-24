package my.program.root.controller;

import java.util.List;
import java.io.File;
import java.util.LinkedList;
import javafx.collections.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import my.program.root.ScanwordEditorApp;
import my.program.root.model.dictionary.Dictionary;
import my.program.root.model.scanword.Block;

public class DictionaryViewController {
	@FXML
	private ComboBox<String> comboBox;
	
	@FXML
	private TextField textField;
	
	@FXML
	private ListView<String> unusedListView;
	
	@FXML
	private ListView<String> usedListView;
	
	@FXML
	private ListView<String> incorrectListView;
		
	private List<String> cathegories;
	private Dictionary dic;
	private Block block;
	private ScanwordEditorApp app;
	
	@FXML
	private void initialize() {
	}
	
	@FXML
	private void findHandler() {
		textField.setOnKeyPressed((KeyEvent event) -> {
			switch(event.getCode()) {
			case ENTER:
				unusedListView.getItems().clear();
				usedListView.getItems().clear();
				incorrectListView.getItems().clear();
				String pattern = textField.getText();
				String cath = comboBox.getSelectionModel().getSelectedItem();
				List<String> incorrectList = dic.findWordsByTemplateAndCathegory(
															pattern, "запрещенные");
				List<String> usedList = block.findWordsByTemplateAndCathegory(
						Dictionary.getInstance(), pattern, cath);
				List<String> unusedList = dic.findWordsByTemplateAndCathegory(pattern, cath);
				
				unusedList.removeAll(incorrectList);
				unusedList.removeAll(usedList);

				unusedListView.getItems().addAll(
						FXCollections.observableList(unusedList));

				usedListView.getItems().addAll(
						FXCollections.observableList(usedList));
				
				incorrectListView.getItems().addAll(
						FXCollections.observableList(incorrectList));
			default:
				break;
			}
		});
	}
	
	@FXML
	private void loadHandle() {
		FileChooser fChooser = new FileChooser();
		fChooser.setTitle("Select files");
		fChooser.getExtensionFilters().addAll(new ExtensionFilter("Excel files","*.xlsx"));
		File selectedFile = fChooser.showOpenDialog(null);
		if(selectedFile != null) {
			textField.setEditable(false);
			comboBox.setDisable(true);
			app.loadScanwordsFile(block, selectedFile);
			textField.setEditable(true);
			comboBox.setDisable(false);
		}
		else {
			
		}
	}
	
	public void setDictionary(Dictionary dic) {
		this.dic = dic;
		cathegories = new LinkedList<String>();
		cathegories.add("все");
		cathegories.addAll(dic.getCathegories());
		comboBox.getItems().addAll(cathegories);
	}
	
	public void setScanword(Block block) {
		this.block = block;
	}
	
	public void setMainApp(ScanwordEditorApp app) {
		this.app = app;
	}
}