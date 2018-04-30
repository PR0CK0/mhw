package cs317.project.mhw.ui;

import cs317.project.mhw.db.Connect;
import cs317.project.mhw.web.WebCrawler;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 * 
 * @author Tyler Procko
 * @date January-May 2018
 * 
 * Start class for JavaFX application. Uses a CSS stylesheet, which
 * carries over to every other JavaFX root.
 *
 */

public class ProgramHUD extends Application
{	
	private static Connect sql;
	
	private Thread threadGrabNews;
	
	// Can declare these locally if persistent selection is desired
	private SearchItem searchItem;
	private SearchWeapon searchWeapon;
	private SearchArmor searchArmor;
	private SearchCharm searchCharm;
	private SearchPalicoEquipment searchPalicoEquipment;
	private SearchMonster searchMonster;
	private SearchQuest searchQuest;

	// Static armor builder so user selections persist
    private ArmorBuilder ab = new ArmorBuilder();
    private ArmorBuilderSetsPage abs;

	public static BorderPane root;
	private Scene scene;
	
	private Label lblTitle;
	private VBox vbTitle;
	
	private ImageView itemIcon, weaponIcon, armorIcon, palicoIcon, monsterIcon, questIcon, armorBuilderIcon;
	private Button btItems, btWeapons, btArmor, btPalico, btMonsters, btQuests, btArmorBuilder;
	private VBox vbButtons;
	
	private ImageView logo;
    private Label lblNewsHeader, lblNewsContent;
    private VBox vbCenterInner;
    private ScrollPane spCenter;
    private VBox vbCenter;
    
    private Label lblDisclaimer;
    private VBox vbDisclaimer;
	
	// TODO button status, will implement stack later so you can go back to previous pages
	//private SimpleStringProperty check = new SimpleStringProperty();

	/**
	 * Start method.
	 */
    @Override
    public void start(Stage primaryStage) throws Exception 
    {
    	/* ----- INITIALIZATION ----- */
    	// borderpanes are the greatest thing ever conceived
		root = new BorderPane();
		scene = new Scene(root, 720, 720);
		scene.getStylesheets().add(ProgramHUD.class.getResource("style.css").toExternalForm());
		primaryStage.setScene(scene);
	    primaryStage.setMinWidth(720);
	    primaryStage.setMinHeight(720);
	    primaryStage.setTitle("MHW:PSP");
	    primaryStage.getIcons().add(new Image("cs317/project/mhw/ui/img/icon.jpg"));
	    primaryStage.show();
	    
		/* ----- BORDERPANE TOP ----- */
	 	lblTitle = new Label("Monster Hunter World : Player Support Project");
	 	vbTitle = new VBox(lblTitle);
	 	lblTitle.setTextAlignment(TextAlignment.CENTER);
	 	lblTitle.setWrapText(true);
	 	lblTitle.setId("bold-label");
	 	vbTitle.setAlignment(Pos.CENTER);
	 	vbTitle.setPadding(new Insets(10, 0, 10, 0));
	 	vbTitle.setId("label-vbox"); 	
	 	
	 	/* ----- BORDERPANE LEFT ----- */
	 	itemIcon = new ImageView(new Image("cs317/project/mhw/ui/img/item.png", 50, 50, true, false));
	 	weaponIcon = new ImageView(new Image("cs317/project/mhw/ui/img/weapon.png", 50, 50, true, false));
	 	armorIcon = new ImageView(new Image("cs317/project/mhw/ui/img/armor.png", 50, 50, true, false));
	 	palicoIcon = new ImageView(new Image("cs317/project/mhw/ui/img/palico.png", 70, 70, true, false));
	 	monsterIcon = new ImageView(new Image("cs317/project/mhw/ui/img/monster.png", 50, 50, true, false));
	 	questIcon = new ImageView(new Image("cs317/project/mhw/ui/img/quest.png", 50, 50, true, false));
	 	armorBuilderIcon = new ImageView(new Image("cs317/project/mhw/ui/img/armrbldr.png", 100, 100, true, false));
	 	btItems = new Button("Items", itemIcon);
	 	btWeapons = new Button("Weapons", weaponIcon);
	 	btArmor = new Button("Armor &\nCharms", armorIcon);
	 	btPalico = new Button("Palico\nEquipment", palicoIcon);
	 	btMonsters = new Button("Monsters", monsterIcon);
	 	btQuests = new Button("Quests", questIcon);
	 	btArmorBuilder = new Button("Armor Set Builder", armorBuilderIcon);
		vbButtons = new VBox(5, btItems, btWeapons, btArmor, btPalico, btMonsters, btQuests, btArmorBuilder);
	    btItems.setMaxWidth(Double.MAX_VALUE);
	    btItems.setGraphicTextGap(10);
	    btWeapons.setMaxWidth(Double.MAX_VALUE);
	    btWeapons.setGraphicTextGap(10);
	    btArmor.setMaxWidth(Double.MAX_VALUE);
	    btArmor.setGraphicTextGap(10);
	    btPalico.setMaxWidth(Double.MAX_VALUE);
	    btPalico.setTextAlignment(TextAlignment.CENTER);
	    btPalico.setGraphicTextGap(10);
	    btMonsters.setMaxWidth(Double.MAX_VALUE);
	    btMonsters.setGraphicTextGap(10);
	    btQuests.setMaxWidth(Double.MAX_VALUE);
	    btQuests.setGraphicTextGap(10);
	    btArmorBuilder.setMaxWidth(Double.MAX_VALUE);
	    btArmorBuilder.setContentDisplay(ContentDisplay.BOTTOM);
	    VBox.setMargin(btArmorBuilder, new Insets(16, 0, 2, 0));
	    vbButtons.setAlignment(Pos.CENTER_LEFT);
	       
	    /* ----- BORDERPANE CENTER ----- */
	    logo = new ImageView(new Image("cs317/project/mhw/ui/img/logo.png", 520, 257, true, false));
	    lblNewsHeader = new Label("UPDATE NOTES:\n\n");
	    lblNewsContent = new Label("Loading news...");
	    vbCenterInner = new VBox(lblNewsHeader, lblNewsContent);
	    spCenter = new ScrollPane(vbCenterInner);
	    vbCenter = new VBox(20, logo, spCenter);
	    lblNewsHeader.setWrapText(true);
	    lblNewsHeader.setTextAlignment(TextAlignment.CENTER);
	    lblNewsHeader.setId("news-label");
	    lblNewsContent.setWrapText(true);
	    vbCenterInner.setAlignment(Pos.CENTER);
	    spCenter.setFitToWidth(true);
	    spCenter.setPadding(new Insets(0, 10, 0, 10));
	    vbCenter.setAlignment(Pos.TOP_CENTER);
	    BorderPane.setMargin(vbCenter, new Insets(10, 10, 10, 10));    
	    
	    /* ----- BORDERPANE BOTTOM ----- */
	    lblDisclaimer = new Label("All content \u00a9CAPCOM CO. Source code for this project written by "
	    		+ "Tyler Procko and Jordan James of ERAU, Daytona.");
	    vbDisclaimer = new VBox(lblDisclaimer);
	    lblDisclaimer.setTextAlignment(TextAlignment.CENTER);
	    lblDisclaimer.setWrapText(true);
	    lblDisclaimer.setId("tiny-label");
	    vbDisclaimer.setAlignment(Pos.CENTER);
	    
	    /* ----- BORDERPANE FINALIZATION ----- */
	 	root.setTop(vbTitle);
	 	root.setLeft(vbButtons);
	 	root.setCenter(vbCenter);
	 	root.setBottom(vbDisclaimer);
	 	
	 	// Start a thread utilizing the WebCrawler class, passing in the news content label
	 	threadGrabNews = new Thread(new WebCrawler(lblNewsContent));
	 	threadGrabNews.start();
	    
	 	// Return home on title click
	 	lblTitle.setOnMouseClicked(e -> 
	 	{
	 		if (!root.getCenter().equals(vbCenter))
	 		{
	 			root.setCenter(vbCenter);
	 		}
	 	});
	 	
	 	// Display disclaimer stage when disclaimer is blicked
	 	lblDisclaimer.setOnMouseClicked(e -> new DisclaimerStage());
	 	
	 	// Display a new item search page on item button click
	    btItems.setOnAction(e -> 
	    {
	    	searchItem = new SearchItem();
	    	root.setCenter(searchItem.getRoot());
	    });
	    
	    // Display a new weapon search page on weapon button click
	    btWeapons.setOnAction(e -> 
	    {
	    	searchWeapon = new SearchWeapon();
	    	root.setCenter(searchWeapon.getRoot());
	    });
	    
	    // Display intermediary screen for on armor button click
	    btArmor.setOnAction(e -> 
	    {
	    	searchArmor = new SearchArmor();
	    	searchCharm = new SearchCharm();
	    	
	    	// Set up a little selection pane for armor or charms
	    	selectArmorOrCharm();
	    });
	    
	    // Display a new palico equipment search page on palico equipment button click
	    btPalico.setOnAction(e -> 
	    {
	    	searchPalicoEquipment = new SearchPalicoEquipment();
	    	root.setCenter(searchPalicoEquipment.getRoot());
	    });
	    
	    // Display a new monsters search page on monsters button click
	    btMonsters.setOnAction(e -> 
	    {
	    	searchMonster = new SearchMonster();
	    	root.setCenter(searchMonster.getRoot());
	    });
	    
	    // Display a new quests search page on quests search click
	    btQuests.setOnAction(e -> 
	    {
	    	searchQuest = new SearchQuest();
	    	root.setCenter(searchQuest.getRoot());
	    });
	    
	    btArmorBuilder.setOnAction(e -> 
	    {
	    	abs = new ArmorBuilderSetsPage();

	    	// TODO
	    	// Set up a little selection pane for viewing current (saved) armor sets or creating a new one
	    	selectArmorBuilderType();
	    });
	}
    
    /**
     * Set up a selection pane for viewing armor or charms.
     */
    private void selectArmorOrCharm()
    {
    	Label lbl = new Label("What do you want to view?");
    	Button armor = new Button("Armor");
    	Button charms = new Button("Charms");
    	ImageView imgVw = new ImageView(new Image("cs317/project/mhw/ui/img/armor_anim.gif", 256, 120, true, false));
    	Label lblCat = new Label("Pick one, nya! Protect yourself from the m-m-monsters!");
    	VBox stuff = new VBox(10, lbl, armor, charms, imgVw, lblCat);
    	BorderPane select = new BorderPane(stuff);
    	stuff.setAlignment(Pos.CENTER);
    	VBox.setMargin(imgVw, new Insets(20, 0, 0, 0));
    	BorderPane.setMargin(stuff, new Insets(50, 50, 50, 50));
    	lbl.setId("news-label");
    	armor.setId("button-special");
    	charms.setId("button-special");
    	lblCat.setId("small-label");
    	stuff.setId("vbox-select");
    	
    	root.setCenter(select);
    	
    	armor.setOnAction(f -> root.setCenter(searchArmor.getRoot()));
    	charms.setOnAction(g -> root.setCenter(searchCharm.getRoot()));
    }
    
    /**
     * TODO
     * 
     * Set up the armor builder selection (create a new one, or view current ones).
     */
    private void selectArmorBuilderType()
    {
    	Label lbl = new Label("What do you want to do?");
    	Button view = new Button("View your armor sets");
    	Button newOne = new Button("Create a new armor set");
    	ImageView imgVw = new ImageView(new Image("cs317/project/mhw/ui/img/cat_anim.gif", 64, 64, true, false));
    	VBox stuff = new VBox(10, lbl, view, newOne, imgVw);
    	BorderPane select = new BorderPane(stuff);
    	VBox.setMargin(imgVw, new Insets(20, 0, 0, 0));
    	BorderPane.setMargin(stuff, new Insets(50, 50, 50, 50));
    	stuff.setAlignment(Pos.CENTER);
    	lbl.setId("news-label");
    	view.setId("button-special");
    	newOne.setId("button-special");
    	stuff.setId("vbox-select");
    	
    	root.setCenter(select);
    	
    	newOne.setOnAction(g -> root.setCenter(ab.getRoot()));
    	view.setOnAction(h -> root.setCenter(abs.getRoot()));
    }
    
    /**
     * Main!
     * 
     * @param args
     */
    public static void main(String[] args) 
    {
    	// Open persistent connection to database
    	sql = new Connect();

    	// Start the JavaFX application
    	launch(args);
    	
    	// If the application is closing, close the database connection
    	if (Platform.isImplicitExit())
    	{
    		sql.disconnect();
    	}
    }
}