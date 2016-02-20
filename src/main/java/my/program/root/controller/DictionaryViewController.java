package my.program.root.controller;

import java.util.List;
import java.util.LinkedList;
import javafx.collections.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.input.*;
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
				List<String> words = dic.findWordsByTemplateAndCathegory(pattern, cath);
				unusedListView.getItems().addAll(FXCollections.observableList(words));
			default:
				break;
			}
		});
	}
	
	public void setDictionary(Dictionary dic) {
		this.dic = dic;
	}
	
	public void setScanword(Block block) {
		this.block = block;
	}
	
	public void setCathegories(List<String> caths) {
		cathegories = new LinkedList<String>();
		cathegories.add("все");
		if(caths != null) {
			cathegories.addAll(caths);
		}
		comboBox.getItems().addAll(cathegories);
	}
}