package cs317.project.mhw.ui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class Main extends Application 
{
    public static void main(String[] args) 
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception 
    {
    	
    	BorderPane bpMenu = new BorderPane();
	 	
	 	Label lblTitle = new Label("Monster Hunter World : Player Support Project");
	 	VBox vbTitle = new VBox(lblTitle);
	 	vbTitle.setAlignment(Pos.CENTER);
	 	vbTitle.setPadding(new Insets(10, 0, 10, 0));
	 	vbTitle.setId("label-vbox");
	 	
	 	lblTitle.setTextAlignment(TextAlignment.CENTER);
	 	lblTitle.setWrapText(true);
	 	lblTitle.setId("bold-label");
	 	
	 	GridPane gpThings = new GridPane();
		VBox vbButtons = new VBox(3);

        Button btItem = new Button("Items");
        GridPane.setConstraints(btItem, 1, 0);
        btItem.setMaxWidth(Double.MAX_VALUE);
        ImageView item = new ImageView(new Image("cs317/project/mhw/ui/item.png", 100, 100, true, false));
        btItem.setGraphic(item);
        
        Button btWpns = new Button("Weapons");
        GridPane.setConstraints(btWpns, 1, 1);
        btWpns.setMaxWidth(Double.MAX_VALUE);
        
        Button btArmr = new Button("Armor");
        GridPane.setConstraints(btArmr, 1, 2);
        btArmr.setMaxWidth(Double.MAX_VALUE);
        
        Button btMons = new Button("Monsters");
        GridPane.setConstraints(btMons, 1, 3);
        btMons.setMaxWidth(Double.MAX_VALUE);
        
        Button btQsts = new Button("Quests");
        GridPane.setConstraints(btQsts, 1, 4);
        btQsts.setMaxWidth(Double.MAX_VALUE);
        
        vbButtons.getChildren().addAll(btItem, btWpns, btArmr, btMons, btQsts);
        
	 	bpMenu.setTop(vbTitle);
	 	bpMenu.setLeft(vbButtons);
  
        Scene sceneHomePage = new Scene(bpMenu, 600, 600);
        primaryStage.setMinWidth(200);
        sceneHomePage.getStylesheets().add(Main.class.getResource("Viper.css").toExternalForm());
        primaryStage.setTitle("MHW:PSP");
        primaryStage.setScene(sceneHomePage);
        primaryStage.show();
    }
}

/*
  		GridPane gpHomePage = new GridPane();
		VBox vbLinks = new VBox(0);
        vbLinks.setMinWidth(20);
        vbLinks.setMinHeight(200);

        GridPane.setConstraints(lblTitle, 0, 0);

        Button btItem = new Button("Items");
        GridPane.setConstraints(btItem, 0, 1);
        
        Button btWpns = new Button("Weapons");
        GridPane.setConstraints(btWpns, 0, 2);
        
        Button btArmr = new Button("Armor");
        GridPane.setConstraints(btArmr, 0, 3);
        
        Button btMons = new Button("Monsters");
        GridPane.setConstraints(btMons, 0, 4);
        
        Button btQsts = new Button("Quests");
        GridPane.setConstraints(btQsts, 0, 5);
        
        btItem.setId("ass-balls");
        vbLinks.getChildren().addAll(lblTitle, btItem, btWpns, btArmr, btMons, btQsts);
        gpHomePage.setGridLinesVisible(true);
        gpHomePage.add(vbLinks, 0, 0);
        GridPane.setRowSpan(vbLinks, 6);

*/