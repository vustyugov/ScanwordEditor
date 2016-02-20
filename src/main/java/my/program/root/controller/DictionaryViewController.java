package my.program.root.controller;

import java.util.List;
import java.awt.KeyEventDispatcher;
import java.util.LinkedList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import my.program.root.ScanwordEditorApp;
import my.program.root.model.dictionary.Dictionary;

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
	
	private ScanwordEditorApp mainApp;
	private List<String> cathegories;
	private Dictionary dic;
	private String input;
	
	@FXML
	private void initialize() {
	}
	
	@FXML
	private void findHandler() {
		input = "";
		textField.setOnKeyPressed(new EventHandler<KeyEvent>() {

		    @Override
		    public void handle(KeyEvent key) {
		        if (key.getCode() != KeyCode.ENTER) {

		            if (key.getCode().isDigitKey() || key.getCode().isLetterKey()) {
		                String newChar = key.getText();
		                input = input.concat(newChar);
		            }

		        }
		        else {
		            System.out.println(input);
		        }

		    }
		});
		
		
//			switch (key.getCode()) {
//				case ENTER:
//					String word = textField.getText();
//					System.out.println("Press Enter");
//					String cathegory = comboBox.getSelectionModel().getSelectedItem();
//					List<String> words = Dictionary.getInstance().findWordsByTemplateAndCathegory(word, cathegory);
//					ObservableList<String> wordsToView = FXCollections.observableArrayList(words);
//					unusedListView.getItems().addAll(wordsToView);
//					break;
//				case SHIFT:
//					System.out.println("Press key shift");
//				default:
//					System.out.println("Press any key");
//					break;
//			}
	}
	
	public void setDictionary(Dictionary dic) {
		this.dic = dic;
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
