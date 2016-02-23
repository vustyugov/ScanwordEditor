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
import my.program.root.model.ScanwordEditorModel;
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
	
	@FXML
	private Tab unusedTab;
	
	@FXML
	private Tab usedTab;
	
	@FXML
	private Tab incorrectTab;
	
	private List<String> cathegories;
	private Dictionary dic;
	private Block block;
	private ScanwordEditorModel app;
	
	@FXML
	private void initialize() {
	}
	
	@FXML
	private void findHandler() {
		textField.setOnKeyPressed((KeyEvent event) -> {
			switch(event.getCode()) {
			case ENTER:
				unusedListView.getItems().clear();
				String pattern = textField.getText();
				String cath = comboBox.getSelectionModel().getSelectedItem();
				unusedListView.getItems().addAll(FXCollections.observableList(dic.findWordsByTemplateAndCathegory(pattern, cath)));
				usedListView.getItems().addAll(FXCollections.observableArrayList(block.findWordsByTemplate(pattern)));
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
			app.loadScanwordsFile(block, selectedFile);
		}
		else {
			
		}
	}
	
	public void setDictionary(Dictionary dic) {
		this.dic = dic;
		cathegories = new LinkedList<String>();
		cathegories.add("все");
		if(dic.getCathegories() != null) {
			cathegories.addAll(dic.getCathegories());
		}
		comboBox.getItems().addAll(cathegories);
	}
	
	public void setScanword(Block block) {
		this.block = block;
	}
	
	public void setMainApp(ScanwordEditorModel app) {
		this.app = app;
	}
}