package cs317.project.mhw.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

/**
 * 
 * @author Tyler Procko
 * @date January-May 2018
 * 
 * Parent class to all of the Search<...> classes. Provides a lot
 * of generalized JavaFX setup.
 * 
 * TODO generalize searchClick in subclasses
 *
 */
public class SearchPARENT 
{
	protected TextField tfSearch = new TextField();
	protected Button btSearch = new Button("Search");
	protected HBox hbSearch = new HBox(10, tfSearch, btSearch);
	protected ComboBox<String> cbSearchBy = new ComboBox<>();
	protected VBox vbSearch = new VBox(10, hbSearch, cbSearchBy);
	protected BorderPane root = new BorderPane();

	protected String tfValue;
	private String regex = "[a-z A-Z 0-9 . , -]";
	
	/**
	 * Method used by subclasses to setup their JavaFX. Takes in a string type,
	 * which is put into the textfield.
	 * 
	 * @param type
	 */
	protected void setup(String type)
	{
		tfValue = "Search for a specific " + type + "...";
		tfSearch.setPromptText(tfValue);
		// Enable enter key acting like search button click
		tfSearch.addEventFilter(KeyEvent.KEY_PRESSED, e ->
		{
			if(e.getCode() == KeyCode.ENTER)
			{
				btSearch.fire();
			}
		});
		
		// Disable SQL injection
		checkText();
		
		HBox.setHgrow(tfSearch, Priority.ALWAYS);
		btSearch.setDisable(true);
		BorderPane.setMargin(vbSearch, new Insets(10, 10, 10, 10));
		vbSearch.setAlignment(Pos.CENTER);
		root.setTop(vbSearch);
	}

	/**
	 * Important method. Disables SQL injection by only allowing a specified
	 * regex of characters to be entered into the textfield for searching.
	 */
	private void checkText()
	{
		tfSearch.textProperty().addListener((var, oldText, newText) -> 
		{
			// As long as the textfield is not empty, continue
			if (!newText.isEmpty())
			{
				btSearch.setDisable(false);
				
				// Check the last entered character against the regex
				if (!(newText.substring(newText.length() - 1)).matches(regex))
				{
					tfSearch.setText(oldText);
				}
			}
			else
			{
				btSearch.setDisable(true);
			}
     	});
	}
	
	public BorderPane getRoot() {
		return root;
	}
}