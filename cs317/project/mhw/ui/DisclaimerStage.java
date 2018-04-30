package cs317.project.mhw.ui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * 
 * @author Tyler Procko
 * @date January-May 2018
 * 
 * JavaFX class that displays a simple stage when activated.
 *
 */

public class DisclaimerStage 
{
	private Stage disclaimerStage = new Stage();
	private Scene disclaimerScene;
	private ScrollPane root = new ScrollPane();
	private VBox vbLabel = new VBox();
	private Label lblText;
	
	/**
	 * Default constructor.
	 */
	public DisclaimerStage()
	{
		// Set up scene and stage
		disclaimerScene = new Scene(root, 300, 300);
		disclaimerStage.initModality(Modality.APPLICATION_MODAL);
		disclaimerStage.setScene(disclaimerScene);
		disclaimerStage.setResizable(false);
		disclaimerStage.setTitle("MHW:PSP - Planned Features and Known Bugs");
		disclaimerScene.getStylesheets().add(ProgramHUD.class.getResource("style.css").toExternalForm());
		disclaimerStage.show();
		
		// Set up the label of planned features and bugs
		lblText = new Label("Planned Features:\n--Armor Builder: the ability to save your created armor sets, view and update them.\n"
				+ "--Buttons in HUD will change color and stay changed while on their respective page.\n"
				+ "--More content to be added for every item type, primarily in the form of descriptions and (possibly) specific images.\n\n"
				+ "Known Bugs:\n--Scrollpanes resize slightly when clicked. It means nothing functionally, but it can be an annoying visual bug.\n"
				+ "--Tableviews often throw null pointer exceptions. Nothing fatal occurs, but the problem exists. The problem stems from "
				+ "selecting something in a tableview, then updating the tableview through a selection in a combobox. There is no known fix "
				+ "at this time.\n--Kinsects should not be allowed in the Armor Builder...");
		vbLabel.getChildren().add(lblText);	
		root.setContent(vbLabel);
		
		// FX settings
		lblText.setTextAlignment(TextAlignment.JUSTIFY);
		lblText.setPadding(new Insets(0, 10, 0, 10));
		lblText.setWrapText(true);
		root.setFitToWidth(true);
	}
}