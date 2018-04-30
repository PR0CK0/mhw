package cs317.project.mhw.ui;

import cs317.project.mhw.db.WeaponList;
import cs317.project.mhw.item.Weapon;
import cs317.project.mhw.item.Weapon_Bow;
import cs317.project.mhw.item.Weapon_ChargeBlade;
import cs317.project.mhw.item.Weapon_DualBlades;
import cs317.project.mhw.item.Weapon_Greatsword;
import cs317.project.mhw.item.Weapon_Gunlance;
import cs317.project.mhw.item.Weapon_Hammer;
import cs317.project.mhw.item.Weapon_HeavyBowgun;
import cs317.project.mhw.item.Weapon_Huntinghorn;
import cs317.project.mhw.item.Weapon_InsectGlaive;
import cs317.project.mhw.item.Weapon_Kinsect;
import cs317.project.mhw.item.Weapon_Lance;
import cs317.project.mhw.item.Weapon_LightBowgun;
import cs317.project.mhw.item.Weapon_Longsword;
import cs317.project.mhw.item.Weapon_SwitchAxe;
import cs317.project.mhw.item.Weapon_SwordAndShield;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

/**
 * 
 * @author Tyler Procko
 * @date January-May 2018
 * 
 * Class to search and display weapons.
 *
 */
public class SearchWeapon extends SearchPARENT
{
	private WeaponList a = new WeaponList();
	public SimpleObjectProperty<Weapon> wpnStored = new SimpleObjectProperty<>(this, "wpnStored", new Weapon());
	
	private ItemPage page;
	private TableView<Weapon_Greatsword> greatswordTable = new TableView<Weapon_Greatsword>();
	private TableView<Weapon_Longsword> longswordTable = new TableView<Weapon_Longsword>();
	private TableView<Weapon_SwordAndShield> swordandshieldTable = new TableView<Weapon_SwordAndShield>();
	private TableView<Weapon_DualBlades> dualbladesTable = new TableView<Weapon_DualBlades>();
	private TableView<Weapon_SwitchAxe> switchaxeTable = new TableView<Weapon_SwitchAxe>();
	private TableView<Weapon_ChargeBlade> chargebladeTable = new TableView<Weapon_ChargeBlade>();
	private TableView<Weapon_Lance> lanceTable = new TableView<Weapon_Lance>();
	private TableView<Weapon_Gunlance> gunlanceTable = new TableView<Weapon_Gunlance>();
	private TableView<Weapon_InsectGlaive> insectglaiveTable = new TableView<Weapon_InsectGlaive>();
	private TableView<Weapon_Kinsect> kinsectTable = new TableView<Weapon_Kinsect>();
	private TableView<Weapon_Hammer> hammerTable = new TableView<Weapon_Hammer>();
	private TableView<Weapon_Huntinghorn> huntinghornTable = new TableView<Weapon_Huntinghorn>();
	private TableView<Weapon_LightBowgun> lightbowgunTable = new TableView<Weapon_LightBowgun>();
	private TableView<Weapon_HeavyBowgun> heavybowgunTable = new TableView<Weapon_HeavyBowgun>();
	private TableView<Weapon_Bow> bowTable = new TableView<Weapon_Bow>();
	
	private ComboBox<String> cbDisplayAll = new ComboBox<>();
	private HBox hbCbs = new HBox(10, cbDisplayAll, cbSearchBy);
	
	/**
	 * Default constructor. Used in ProgramHUD.
	 */
	public SearchWeapon()
	{
		constructorHelp(true);
	}
	
	/**
	 * Alternate constructor. Used in ArmorBuilder.
	 * @param normal
	 */
	public SearchWeapon(boolean normal)
	{
		constructorHelp(normal);
	}
	
	/**
	 * Called from the constructors. Sets up the majority of the JavaFX, especially on the tableviews.
	 * 
	 * @param normal
	 */
	private void constructorHelp(boolean normal)
	{
		vbSearch.getChildren().setAll(hbSearch, hbCbs);
		hbCbs.setAlignment(Pos.CENTER);
		
		// If called in ProgramHUD, call setupTableClicks() on a selected item in the tableview
		if (normal)
			setupTableClicks();
		// If called in ArmorBuilder call setupTableClicksForArmorBuilder() on a selcted item in the tableview
		else
			setupTableClicksForArmorBuilder();

		// Combobox setup
		cbDisplayAll.getItems().setAll("Display all Greatswords", "Display all Longswords", "Display all Sword and Shields", "Display all Dual Blades",
				"Display all Switch Axes", "Display all Charge Blades", "Display all Lances", "Display all Gunlances", 
				"Display all Insect Glaives", "Display all Kinsects", "Display all Hammers", "Display all Huntinghorns", 
				"Display all Light Bowguns", "Display all Heavy Bowguns", "Display all Bows");
		// Set default value to greatswords
		cbDisplayAll.setValue("Display all Greatswords");
		cbSearchBy.getItems().setAll("Search Greatswords", "Search Longswords", "Search Sword and Shields", "Search Dual Blades",
				"Search Switch Axes", "Search Charge Blades", "Search Lances", "Search Gunlances", 
				"Search Insect Glaives", "Search Kinsects", "Search Hammers", "Search Huntinghorns", 
				"Search Light Bowguns", "Search Heavy Bowguns", "Search Bows");
		cbSearchBy.setValue("What are you searching for...");
		
		// Setup tables
		setupGreatswordTable();
		setupLongswordTable();
		setupSwordAndShieldTable();
		setupDualBladesTable();
		setupSwitchAxeTable();
		setupChargeBladeTable();
		setupLanceTable();
		setupGunlanceTable();
		setupInsectGlaiveTable();
		setupKinsectTable();
		setupHammerTable();
		setupHuntinghornTable();
		setupLightBowgunTable();
		setupHeavyBowgunTable();
		setupBowTable();
		// Super call
	    setup("weapon");
	    
	    // Populate table and set root to initially display greatswords
		greatswordTable.setItems(a.getGreatswordList());
		root.setCenter(greatswordTable);
		
		// Initially disable the textfield when all items are displayed
		tfSearch.setDisable(true);
			
		// Display all of a type combobox funtionality
		cbDisplayAll.setOnAction(e -> 
		{
			cbSearchBy.setValue("What are you searching for...");
			tfSearch.setPromptText(tfValue);
			tfSearch.setText("");
			tfSearch.setDisable(true);
			
			if(cbDisplayAll.getValue().equalsIgnoreCase("display all greatswords"))
			{
				a.searchGreatsword("");
				greatswordTable.setItems(a.getGreatswordList());
				root.setCenter(greatswordTable);
			}
			else if (cbDisplayAll.getValue().equalsIgnoreCase("display all longswords"))
			{
				a.searchLongsword("");
				longswordTable.setItems(a.getLongswordList());
				root.setCenter(longswordTable);
			}
			else if (cbDisplayAll.getValue().equalsIgnoreCase("display all sword and shields"))
			{
				a.searchSwordAndShield("");
				swordandshieldTable.setItems(a.getSwordAndShieldList());
				root.setCenter(swordandshieldTable);
			}
			else if (cbDisplayAll.getValue().equalsIgnoreCase("display all dual blades"))
			{
				a.searchDualBlades("");
				dualbladesTable.setItems(a.getDualBladesList());
				root.setCenter(dualbladesTable);
			}
			else if (cbDisplayAll.getValue().equalsIgnoreCase("display all switch axes"))
			{
				a.searchSwitchAxe("");
				switchaxeTable.setItems(a.getSwitchAxeList());
				root.setCenter(switchaxeTable);
			}
			else if (cbDisplayAll.getValue().equalsIgnoreCase("display all charge blades"))
			{
				a.searchChargeBlade("");
				chargebladeTable.setItems(a.getChargeBladeList());
				root.setCenter(chargebladeTable);
			}
			else if (cbDisplayAll.getValue().equalsIgnoreCase("display all lances"))
			{
				a.searchLance("");
				lanceTable.setItems(a.getLanceList());
				root.setCenter(lanceTable);
			}
			else if (cbDisplayAll.getValue().equalsIgnoreCase("display all gunlances"))
			{
				a.searchGunlance("");
				gunlanceTable.setItems(a.getGunlanceList());
				root.setCenter(gunlanceTable);
			}
			else if (cbDisplayAll.getValue().equalsIgnoreCase("display all insect glaives"))
			{
				a.searchInsectGlaive("");
				insectglaiveTable.setItems(a.getInsectGlaiveList());
				root.setCenter(insectglaiveTable);
			}
			else if (cbDisplayAll.getValue().equalsIgnoreCase("display all kinsects"))
			{
				a.searchKinsect("");
				kinsectTable.setItems(a.getKinsectList());
				root.setCenter(kinsectTable);
			}
			else if (cbDisplayAll.getValue().equalsIgnoreCase("display all hammers"))
			{
				a.searchHammer("");
				hammerTable.setItems(a.getHammerList());
				root.setCenter(hammerTable);
			}
			else if (cbDisplayAll.getValue().equalsIgnoreCase("display all huntinghorns"))
			{
				a.searchHuntinghorn("");
				huntinghornTable.setItems(a.getHuntinghornList());
				root.setCenter(huntinghornTable);
			}
			else if (cbDisplayAll.getValue().equalsIgnoreCase("display all light bowguns"))
			{
				a.searchLightBowgun("");
				lightbowgunTable.setItems(a.getLightBowgunList());
				root.setCenter(lightbowgunTable);
			}
			else if (cbDisplayAll.getValue().equalsIgnoreCase("display all heavy bowguns"))
			{
				a.searchHeavyBowgun("");
				heavybowgunTable.setItems(a.getHeavyBowgunList());
				root.setCenter(heavybowgunTable);
			}
			else if (cbDisplayAll.getValue().equalsIgnoreCase("display all bows"))
			{
				a.searchBow("");
				bowTable.setItems(a.getBowList());
				root.setCenter(bowTable);
			}
		});
		
		// Search a type combobox functionality
		cbSearchBy.setOnAction(e -> 
		{
			if (cbSearchBy.getValue().equalsIgnoreCase("search greatswords"))
			{
				cbDisplayAll.setValue("Display all...");
				a.searchGreatsword("");
				greatswordTable.setItems(a.getGreatswordList());
				root.setCenter(greatswordTable);
				tfSearch.setDisable(false);
			}
			else if (cbSearchBy.getValue().equalsIgnoreCase("search longswords"))
			{
				cbDisplayAll.setValue("Display all...");
				a.searchLongsword("");
				longswordTable.setItems(a.getLongswordList());
				root.setCenter(longswordTable);
				tfSearch.setDisable(false);
			}
			else if (cbSearchBy.getValue().equalsIgnoreCase("search sword and shields"))
			{
				cbDisplayAll.setValue("Display all...");
				a.searchSwordAndShield("");
				swordandshieldTable.setItems(a.getSwordAndShieldList());
				root.setCenter(swordandshieldTable);
				tfSearch.setDisable(false);
			}
			else if (cbSearchBy.getValue().equalsIgnoreCase("search dual blades"))
			{
				cbDisplayAll.setValue("Display all...");
				a.searchDualBlades("");
				dualbladesTable.setItems(a.getDualBladesList());
				root.setCenter(dualbladesTable);
				tfSearch.setDisable(false);
			}
			else if (cbSearchBy.getValue().equalsIgnoreCase("search switch axes"))
			{
				cbDisplayAll.setValue("Display all...");
				a.searchSwitchAxe("");
				switchaxeTable.setItems(a.getSwitchAxeList());
				root.setCenter(switchaxeTable);
				tfSearch.setDisable(false);
			}
			else if (cbSearchBy.getValue().equalsIgnoreCase("search switch axes"))
			{
				cbDisplayAll.setValue("Display all...");
				a.searchChargeBlade("");
				chargebladeTable.setItems(a.getChargeBladeList());
				root.setCenter(chargebladeTable);
				tfSearch.setDisable(false);
			}
			else if (cbSearchBy.getValue().equalsIgnoreCase("search lances"))
			{
				cbDisplayAll.setValue("Display all...");
				a.searchLance("");
				lanceTable.setItems(a.getLanceList());
				root.setCenter(lanceTable);
				tfSearch.setDisable(false);
			}
			else if (cbSearchBy.getValue().equalsIgnoreCase("search gunlances"))
			{
				cbDisplayAll.setValue("Display all...");
				a.searchGunlance("");
				gunlanceTable.setItems(a.getGunlanceList());
				root.setCenter(gunlanceTable);
				tfSearch.setDisable(false);
			}
			else if (cbSearchBy.getValue().equalsIgnoreCase("search insect glaives"))
			{
				cbDisplayAll.setValue("Display all...");
				a.searchInsectGlaive("");
				insectglaiveTable.setItems(a.getInsectGlaiveList());
				root.setCenter(insectglaiveTable);
				tfSearch.setDisable(false);
			}
			else if (cbSearchBy.getValue().equalsIgnoreCase("search kinsects"))
			{
				cbDisplayAll.setValue("Display all...");
				a.searchKinsect("");
				kinsectTable.setItems(a.getKinsectList());
				root.setCenter(kinsectTable);
				tfSearch.setDisable(false);
			}
			else if (cbSearchBy.getValue().equalsIgnoreCase("search hammers"))
			{
				cbDisplayAll.setValue("Display all...");
				a.searchHammer("");
				hammerTable.setItems(a.getHammerList());
				root.setCenter(hammerTable);
				tfSearch.setDisable(false);
			}
			else if (cbSearchBy.getValue().equalsIgnoreCase("search huntinghorns"))
			{
				cbDisplayAll.setValue("Display all...");
				a.searchHuntinghorn("");
				huntinghornTable.setItems(a.getHuntinghornList());
				root.setCenter(huntinghornTable);
				tfSearch.setDisable(false);
			}
			else if (cbSearchBy.getValue().equalsIgnoreCase("search light bowguns"))
			{
				cbDisplayAll.setValue("Display all...");
				a.searchLightBowgun("");
				lightbowgunTable.setItems(a.getLightBowgunList());
				root.setCenter(lightbowgunTable);
				tfSearch.setDisable(false);
			}
			else if (cbSearchBy.getValue().equalsIgnoreCase("search heavy bowguns"))
			{
				cbDisplayAll.setValue("Display all...");
				a.searchHeavyBowgun("");
				heavybowgunTable.setItems(a.getHeavyBowgunList());
				root.setCenter(heavybowgunTable);
				tfSearch.setDisable(false);
			}
			else if (cbSearchBy.getValue().equalsIgnoreCase("search bows"))
			{
				cbDisplayAll.setValue("Display all...");
				a.searchBow("");
				bowTable.setItems(a.getBowList());
				root.setCenter(bowTable);
				tfSearch.setDisable(false);
			}
		});
		
		// Search button functionality
		btSearch.setOnAction(e -> searchClick());
	}
	
	/**
	 * Default weaponClick method, used when instance of this class
	 * is created through the default constructor. Displays a new
	 * item page for the selected item.
	 * 
	 * @param wpn
	 */
	private void weaponClick(Weapon wpn)
	{
		page = new ItemPage(wpn);
		ProgramHUD.root.setCenter(page.getRoot());
	}
	
	/**
	 * Alternate weaponClick method, used when instance of this class
	 * is created through the alternate constructor. Simply saves the
	 * selected item into a local variable.
	 * 
	 * @param wpn
	 */
	private void weaponClickForArmorBuilder(Weapon wpn)
	{
		wpnStored.setValue(wpn);
	}
	
	/**
	 * Allows searching by weapon name.
	 */
	public void searchClick()
	{
		if (cbSearchBy.getValue().equalsIgnoreCase("search greatswords"))
		{
			a.searchGreatsword(tfSearch.getText());
			greatswordTable.setItems(a.getGreatswordList());
		}
		else if (cbSearchBy.getValue().equalsIgnoreCase("search longswords"))
		{
			a.searchLongsword(tfSearch.getText());
			longswordTable.setItems(a.getLongswordList());
		}
		else if (cbSearchBy.getValue().equalsIgnoreCase("search sword and shields"))
		{
			a.searchSwordAndShield(tfSearch.getText());
			swordandshieldTable.setItems(a.getSwordAndShieldList());
		}
		else if (cbSearchBy.getValue().equalsIgnoreCase("search dual blades"))
		{
			a.searchDualBlades(tfSearch.getText());
			dualbladesTable.setItems(a.getDualBladesList());
		}
		else if (cbSearchBy.getValue().equalsIgnoreCase("search charge blades"))
		{
			a.searchChargeBlade(tfSearch.getText());
			chargebladeTable.setItems(a.getChargeBladeList());
		}
		else if (cbSearchBy.getValue().equalsIgnoreCase("search switch axes"))
		{
			a.searchSwitchAxe(tfSearch.getText());
			switchaxeTable.setItems(a.getSwitchAxeList());
		}
		else if (cbSearchBy.getValue().equalsIgnoreCase("search lances"))
		{
			a.searchLance(tfSearch.getText());
			lanceTable.setItems(a.getLanceList());
		}
		else if (cbSearchBy.getValue().equalsIgnoreCase("search gunlances"))
		{
			a.searchGunlance(tfSearch.getText());
			gunlanceTable.setItems(a.getGunlanceList());
		}
		else if (cbSearchBy.getValue().equalsIgnoreCase("search insect glaives"))
		{
			a.searchInsectGlaive(tfSearch.getText());
			insectglaiveTable.setItems(a.getInsectGlaiveList());
		}
		else if (cbSearchBy.getValue().equalsIgnoreCase("search kinsects"))
		{
			a.searchKinsect(tfSearch.getText());
			kinsectTable.setItems(a.getKinsectList());
		}
		else if (cbSearchBy.getValue().equalsIgnoreCase("search hammers"))
		{
			a.searchHammer(tfSearch.getText());
			hammerTable.setItems(a.getHammerList());
		}
		else if (cbSearchBy.getValue().equalsIgnoreCase("search huntinghorns"))
		{
			a.searchHuntinghorn(tfSearch.getText());
			huntinghornTable.setItems(a.getHuntinghornList());
		}
		else if (cbSearchBy.getValue().equalsIgnoreCase("search light bowguns"))
		{
			a.searchLightBowgun(tfSearch.getText());
			lightbowgunTable.setItems(a.getLightBowgunList());
		}
		else if (cbSearchBy.getValue().equalsIgnoreCase("search heavy bowguns"))
		{
			a.searchHeavyBowgun(tfSearch.getText());
			heavybowgunTable.setItems(a.getHeavyBowgunList());
		}
		else if (cbSearchBy.getValue().equalsIgnoreCase("search bows"))
		{
			a.searchBow(tfSearch.getText());
			bowTable.setItems(a.getBowList());
		}
	}
	
	/**
	 * Setup table selection for default constructor.
	 */
	private void setupTableClicks()
	{
		greatswordTable.getSelectionModel().selectedItemProperty().addListener(
				(ObservableValue<? extends Weapon_Greatsword> ov, Weapon_Greatsword oldVal, Weapon_Greatsword newVal) -> weaponClick(newVal));
		longswordTable.getSelectionModel().selectedItemProperty().addListener(
				(ObservableValue<? extends Weapon_Longsword> ov, Weapon_Longsword oldVal, Weapon_Longsword newVal) -> weaponClick(newVal));
		swordandshieldTable.getSelectionModel().selectedItemProperty().addListener(
				(ObservableValue<? extends Weapon_SwordAndShield> ov, Weapon_SwordAndShield oldVal, Weapon_SwordAndShield newVal) -> weaponClick(newVal));
		dualbladesTable.getSelectionModel().selectedItemProperty().addListener(
				(ObservableValue<? extends Weapon_DualBlades> ov, Weapon_DualBlades oldVal, Weapon_DualBlades newVal) -> weaponClick(newVal));
		switchaxeTable.getSelectionModel().selectedItemProperty().addListener(
				(ObservableValue<? extends Weapon_SwitchAxe> ov, Weapon_SwitchAxe oldVal, Weapon_SwitchAxe newVal) -> weaponClick(newVal));
		chargebladeTable.getSelectionModel().selectedItemProperty().addListener(
				(ObservableValue<? extends Weapon_ChargeBlade> ov, Weapon_ChargeBlade oldVal, Weapon_ChargeBlade newVal) -> weaponClick(newVal));
		lanceTable.getSelectionModel().selectedItemProperty().addListener(
				(ObservableValue<? extends Weapon_Lance> ov, Weapon_Lance oldVal, Weapon_Lance newVal) -> weaponClick(newVal));
		gunlanceTable.getSelectionModel().selectedItemProperty().addListener(
				(ObservableValue<? extends Weapon_Gunlance> ov, Weapon_Gunlance oldVal, Weapon_Gunlance newVal) -> weaponClick(newVal));
		insectglaiveTable.getSelectionModel().selectedItemProperty().addListener(
				(ObservableValue<? extends Weapon_InsectGlaive> ov, Weapon_InsectGlaive oldVal, Weapon_InsectGlaive newVal) -> weaponClick(newVal));
		kinsectTable.getSelectionModel().selectedItemProperty().addListener(
				(ObservableValue<? extends Weapon_Kinsect> ov, Weapon_Kinsect oldVal, Weapon_Kinsect newVal) -> weaponClick(newVal));
		hammerTable.getSelectionModel().selectedItemProperty().addListener(
				(ObservableValue<? extends Weapon_Hammer> ov, Weapon_Hammer oldVal, Weapon_Hammer newVal) -> weaponClick(newVal));
		huntinghornTable.getSelectionModel().selectedItemProperty().addListener(
				(ObservableValue<? extends Weapon_Huntinghorn> ov, Weapon_Huntinghorn oldVal, Weapon_Huntinghorn newVal) -> weaponClick(newVal));
		lightbowgunTable.getSelectionModel().selectedItemProperty().addListener(
				(ObservableValue<? extends Weapon_LightBowgun> ov, Weapon_LightBowgun oldVal, Weapon_LightBowgun newVal) -> weaponClick(newVal));
		heavybowgunTable.getSelectionModel().selectedItemProperty().addListener(
				(ObservableValue<? extends Weapon_HeavyBowgun> ov, Weapon_HeavyBowgun oldVal, Weapon_HeavyBowgun newVal) -> weaponClick(newVal));
		bowTable.getSelectionModel().selectedItemProperty().addListener(
				(ObservableValue<? extends Weapon_Bow> ov, Weapon_Bow oldVal, Weapon_Bow newVal) -> weaponClick(newVal));
	}
	
	/**
	 * Setup table selection for alternate constructor.
	 */
	private void setupTableClicksForArmorBuilder()
	{
		greatswordTable.getSelectionModel().selectedItemProperty().addListener(
				(ObservableValue<? extends Weapon_Greatsword> ov, Weapon_Greatsword oldVal, Weapon_Greatsword newVal) -> weaponClickForArmorBuilder(newVal));
		longswordTable.getSelectionModel().selectedItemProperty().addListener(
				(ObservableValue<? extends Weapon_Longsword> ov, Weapon_Longsword oldVal, Weapon_Longsword newVal) -> weaponClickForArmorBuilder(newVal));
		swordandshieldTable.getSelectionModel().selectedItemProperty().addListener(
				(ObservableValue<? extends Weapon_SwordAndShield> ov, Weapon_SwordAndShield oldVal, Weapon_SwordAndShield newVal) -> weaponClickForArmorBuilder(newVal));
		dualbladesTable.getSelectionModel().selectedItemProperty().addListener(
				(ObservableValue<? extends Weapon_DualBlades> ov, Weapon_DualBlades oldVal, Weapon_DualBlades newVal) -> weaponClickForArmorBuilder(newVal));
		switchaxeTable.getSelectionModel().selectedItemProperty().addListener(
				(ObservableValue<? extends Weapon_SwitchAxe> ov, Weapon_SwitchAxe oldVal, Weapon_SwitchAxe newVal) -> weaponClickForArmorBuilder(newVal));
		chargebladeTable.getSelectionModel().selectedItemProperty().addListener(
				(ObservableValue<? extends Weapon_ChargeBlade> ov, Weapon_ChargeBlade oldVal, Weapon_ChargeBlade newVal) -> weaponClickForArmorBuilder(newVal));
		lanceTable.getSelectionModel().selectedItemProperty().addListener(
				(ObservableValue<? extends Weapon_Lance> ov, Weapon_Lance oldVal, Weapon_Lance newVal) -> weaponClickForArmorBuilder(newVal));
		gunlanceTable.getSelectionModel().selectedItemProperty().addListener(
				(ObservableValue<? extends Weapon_Gunlance> ov, Weapon_Gunlance oldVal, Weapon_Gunlance newVal) -> weaponClickForArmorBuilder(newVal));
		insectglaiveTable.getSelectionModel().selectedItemProperty().addListener(
				(ObservableValue<? extends Weapon_InsectGlaive> ov, Weapon_InsectGlaive oldVal, Weapon_InsectGlaive newVal) -> weaponClickForArmorBuilder(newVal));
		kinsectTable.getSelectionModel().selectedItemProperty().addListener(
				(ObservableValue<? extends Weapon_Kinsect> ov, Weapon_Kinsect oldVal, Weapon_Kinsect newVal) -> weaponClickForArmorBuilder(newVal));
		hammerTable.getSelectionModel().selectedItemProperty().addListener(
				(ObservableValue<? extends Weapon_Hammer> ov, Weapon_Hammer oldVal, Weapon_Hammer newVal) -> weaponClickForArmorBuilder(newVal));
		huntinghornTable.getSelectionModel().selectedItemProperty().addListener(
				(ObservableValue<? extends Weapon_Huntinghorn> ov, Weapon_Huntinghorn oldVal, Weapon_Huntinghorn newVal) -> weaponClickForArmorBuilder(newVal));
		lightbowgunTable.getSelectionModel().selectedItemProperty().addListener(
				(ObservableValue<? extends Weapon_LightBowgun> ov, Weapon_LightBowgun oldVal, Weapon_LightBowgun newVal) -> weaponClickForArmorBuilder(newVal));
		heavybowgunTable.getSelectionModel().selectedItemProperty().addListener(
				(ObservableValue<? extends Weapon_HeavyBowgun> ov, Weapon_HeavyBowgun oldVal, Weapon_HeavyBowgun newVal) -> weaponClickForArmorBuilder(newVal));
		bowTable.getSelectionModel().selectedItemProperty().addListener(
				(ObservableValue<? extends Weapon_Bow> ov, Weapon_Bow oldVal, Weapon_Bow newVal) -> weaponClickForArmorBuilder(newVal));
	}
	
	/**
	 * Sets up the JavaFX of the table.
	 */
	private void setupGreatswordTable()
	{
		greatswordTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		TableColumn<Weapon_Greatsword, String> nameCol = new TableColumn<>("Name");
	    nameCol.setMinWidth(200);
	    nameCol.setCellValueFactory(new PropertyValueFactory<Weapon_Greatsword, String>("name"));
	    
	    TableColumn<Weapon_Greatsword, Integer> attackCol = new TableColumn<>("Attack");
	    attackCol.setMinWidth(75);
	    attackCol.setCellValueFactory(new PropertyValueFactory<Weapon_Greatsword, Integer>("attack"));

	    TableColumn<Weapon_Greatsword, String> sharpnessCol = new TableColumn<>("Sharpness");
	    sharpnessCol.setMinWidth(75);
	    sharpnessCol.setCellValueFactory(new PropertyValueFactory<Weapon_Greatsword, String>("sharpness"));
	    
	    TableColumn<Weapon_Greatsword, String> elementTypeCol = new TableColumn<>("Element\nType");
	    elementTypeCol.setMinWidth(75);
	    elementTypeCol.setCellValueFactory(new PropertyValueFactory<Weapon_Greatsword, String>("elementType"));
	    
	    TableColumn<Weapon_Greatsword, Integer> affinityCol = new TableColumn<>("Affinity\n(%)");
	    affinityCol.setMinWidth(75);
	    affinityCol.setCellValueFactory(new PropertyValueFactory<Weapon_Greatsword, Integer>("affinity"));

	    greatswordTable.getColumns().addAll(nameCol, attackCol, sharpnessCol, elementTypeCol, affinityCol);
	    BorderPane.setMargin(greatswordTable, new Insets(0, 10, 10, 10));	
	}
	
	/**
	 * Sets up the JavaFX of the table.
	 */
	private void setupLongswordTable()
	{
		longswordTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		TableColumn<Weapon_Longsword, String> nameCol = new TableColumn<>("Name");
	    nameCol.setMinWidth(200);
	    nameCol.setCellValueFactory(new PropertyValueFactory<Weapon_Longsword, String>("name"));
	    
	    TableColumn<Weapon_Longsword, Integer> attackCol = new TableColumn<>("Attack");
	    attackCol.setMinWidth(75);
	    attackCol.setCellValueFactory(new PropertyValueFactory<Weapon_Longsword, Integer>("attack"));

	    TableColumn<Weapon_Longsword, String> sharpnessCol = new TableColumn<>("Sharpness");
	    sharpnessCol.setMinWidth(75);
	    sharpnessCol.setCellValueFactory(new PropertyValueFactory<Weapon_Longsword, String>("sharpness"));
	    
	    TableColumn<Weapon_Longsword, String> elementTypeCol = new TableColumn<>("Element\nType");
	    elementTypeCol.setMinWidth(75);
	    elementTypeCol.setCellValueFactory(new PropertyValueFactory<Weapon_Longsword, String>("elementType"));
	    
	    TableColumn<Weapon_Longsword, Integer> affinityCol = new TableColumn<>("Affinity\n(%)");
	    affinityCol.setMinWidth(75);
	    affinityCol.setCellValueFactory(new PropertyValueFactory<Weapon_Longsword, Integer>("affinity"));

	    longswordTable.getColumns().addAll(nameCol, attackCol, sharpnessCol, elementTypeCol, affinityCol);
	    BorderPane.setMargin(longswordTable, new Insets(0, 10, 10, 10));
	}
	
	/**
	 * Sets up the JavaFX of the table.
	 */
	private void setupSwordAndShieldTable()
	{
		swordandshieldTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		TableColumn<Weapon_SwordAndShield, String> nameCol = new TableColumn<>("Name");
	    nameCol.setMinWidth(200);
	    nameCol.setCellValueFactory(new PropertyValueFactory<Weapon_SwordAndShield, String>("name"));
	    
	    TableColumn<Weapon_SwordAndShield, Integer> attackCol = new TableColumn<>("Attack");
	    attackCol.setMinWidth(75);
	    attackCol.setCellValueFactory(new PropertyValueFactory<Weapon_SwordAndShield, Integer>("attack"));

	    TableColumn<Weapon_SwordAndShield, String> sharpnessCol = new TableColumn<>("Sharpness");
	    sharpnessCol.setMinWidth(75);
	    sharpnessCol.setCellValueFactory(new PropertyValueFactory<Weapon_SwordAndShield, String>("sharpness"));
	    
	    TableColumn<Weapon_SwordAndShield, String> elementTypeCol = new TableColumn<>("Element\nType");
	    elementTypeCol.setMinWidth(75);
	    elementTypeCol.setCellValueFactory(new PropertyValueFactory<Weapon_SwordAndShield, String>("elementType"));
	    
	    TableColumn<Weapon_SwordAndShield, Integer> affinityCol = new TableColumn<>("Affinity\n(%)");
	    affinityCol.setMinWidth(75);
	    affinityCol.setCellValueFactory(new PropertyValueFactory<Weapon_SwordAndShield, Integer>("affinity"));

	    swordandshieldTable.getColumns().addAll(nameCol, attackCol, sharpnessCol, elementTypeCol, affinityCol);
	    BorderPane.setMargin(swordandshieldTable, new Insets(0, 10, 10, 10));	
	}
	
	/**
	 * Sets up the JavaFX of the table.
	 */
	private void setupDualBladesTable()
	{
		dualbladesTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		TableColumn<Weapon_DualBlades, String> nameCol = new TableColumn<>("Name");
	    nameCol.setMinWidth(200);
	    nameCol.setCellValueFactory(new PropertyValueFactory<Weapon_DualBlades, String>("name"));
	    
	    TableColumn<Weapon_DualBlades, Integer> attackCol = new TableColumn<>("Attack");
	    attackCol.setMinWidth(75);
	    attackCol.setCellValueFactory(new PropertyValueFactory<Weapon_DualBlades, Integer>("attack"));

	    TableColumn<Weapon_DualBlades, String> sharpnessCol = new TableColumn<>("Sharpness");
	    sharpnessCol.setMinWidth(75);
	    sharpnessCol.setCellValueFactory(new PropertyValueFactory<Weapon_DualBlades, String>("sharpness"));
	    
	    TableColumn<Weapon_DualBlades, String> elementTypeCol = new TableColumn<>("Element\nType");
	    elementTypeCol.setMinWidth(75);
	    elementTypeCol.setCellValueFactory(new PropertyValueFactory<Weapon_DualBlades, String>("elementType"));
	    
	    TableColumn<Weapon_DualBlades, Integer> affinityCol = new TableColumn<>("Affinity\n(%)");
	    affinityCol.setMinWidth(75);
	    affinityCol.setCellValueFactory(new PropertyValueFactory<Weapon_DualBlades, Integer>("affinity"));

	    dualbladesTable.getColumns().addAll(nameCol, attackCol, sharpnessCol, elementTypeCol, affinityCol);
	    BorderPane.setMargin(dualbladesTable, new Insets(0, 10, 10, 10));
	}
	
	/**
	 * Sets up the JavaFX of the table.
	 */
	private void setupSwitchAxeTable()
	{
		switchaxeTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		TableColumn<Weapon_SwitchAxe, String> nameCol = new TableColumn<>("Name");
	    nameCol.setMinWidth(200);
	    nameCol.setCellValueFactory(new PropertyValueFactory<Weapon_SwitchAxe, String>("name"));
	    
	    TableColumn<Weapon_SwitchAxe, Integer> attackCol = new TableColumn<>("Attack");
	    attackCol.setMinWidth(75);
	    attackCol.setCellValueFactory(new PropertyValueFactory<Weapon_SwitchAxe, Integer>("attack"));

	    TableColumn<Weapon_SwitchAxe, String> sharpnessCol = new TableColumn<>("Sharpness");
	    sharpnessCol.setMinWidth(75);
	    sharpnessCol.setCellValueFactory(new PropertyValueFactory<Weapon_SwitchAxe, String>("sharpness"));
	    
	    TableColumn<Weapon_SwitchAxe, String> elementTypeCol = new TableColumn<>("Element\nType");
	    elementTypeCol.setMinWidth(75);
	    elementTypeCol.setCellValueFactory(new PropertyValueFactory<Weapon_SwitchAxe, String>("elementType"));
	    
	    TableColumn<Weapon_SwitchAxe, Integer> affinityCol = new TableColumn<>("Affinity\n(%)");
	    affinityCol.setMinWidth(75);
	    affinityCol.setCellValueFactory(new PropertyValueFactory<Weapon_SwitchAxe, Integer>("affinity"));

	    switchaxeTable.getColumns().addAll(nameCol, attackCol, sharpnessCol, elementTypeCol, affinityCol);
	    BorderPane.setMargin(switchaxeTable, new Insets(0, 10, 10, 10));	
	}
	
	/**
	 * Sets up the JavaFX of the table.
	 */
	private void setupChargeBladeTable()
	{
		chargebladeTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		TableColumn<Weapon_ChargeBlade, String> nameCol = new TableColumn<>("Name");
	    nameCol.setMinWidth(200);
	    nameCol.setCellValueFactory(new PropertyValueFactory<Weapon_ChargeBlade, String>("name"));
	    
	    TableColumn<Weapon_ChargeBlade, Integer> attackCol = new TableColumn<>("Attack");
	    attackCol.setMinWidth(75);
	    attackCol.setCellValueFactory(new PropertyValueFactory<Weapon_ChargeBlade, Integer>("attack"));

	    TableColumn<Weapon_ChargeBlade, String> sharpnessCol = new TableColumn<>("Sharpness");
	    sharpnessCol.setMinWidth(75);
	    sharpnessCol.setCellValueFactory(new PropertyValueFactory<Weapon_ChargeBlade, String>("sharpness"));
	    
	    TableColumn<Weapon_ChargeBlade, String> elementTypeCol = new TableColumn<>("Element\nType");
	    elementTypeCol.setMinWidth(75);
	    elementTypeCol.setCellValueFactory(new PropertyValueFactory<Weapon_ChargeBlade, String>("elementType"));
	    
	    TableColumn<Weapon_ChargeBlade, Integer> affinityCol = new TableColumn<>("Affinity\n(%)");
	    affinityCol.setMinWidth(75);
	    affinityCol.setCellValueFactory(new PropertyValueFactory<Weapon_ChargeBlade, Integer>("affinity"));

	    chargebladeTable.getColumns().addAll(nameCol, attackCol, sharpnessCol, elementTypeCol, affinityCol);
	    BorderPane.setMargin(chargebladeTable, new Insets(0, 10, 10, 10));
	}
	
	/**
	 * Sets up the JavaFX of the table.
	 */
	private void setupLanceTable()
	{
		lanceTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		TableColumn<Weapon_Lance, String> nameCol = new TableColumn<>("Name");
	    nameCol.setMinWidth(200);
	    nameCol.setCellValueFactory(new PropertyValueFactory<Weapon_Lance, String>("name"));
	    
	    TableColumn<Weapon_Lance, Integer> attackCol = new TableColumn<>("Attack");
	    attackCol.setMinWidth(75);
	    attackCol.setCellValueFactory(new PropertyValueFactory<Weapon_Lance, Integer>("attack"));

	    TableColumn<Weapon_Lance, String> sharpnessCol = new TableColumn<>("Sharpness");
	    sharpnessCol.setMinWidth(75);
	    sharpnessCol.setCellValueFactory(new PropertyValueFactory<Weapon_Lance, String>("sharpness"));
	    
	    TableColumn<Weapon_Lance, String> elementTypeCol = new TableColumn<>("Element\nType");
	    elementTypeCol.setMinWidth(75);
	    elementTypeCol.setCellValueFactory(new PropertyValueFactory<Weapon_Lance, String>("elementType"));
	    
	    TableColumn<Weapon_Lance, Integer> affinityCol = new TableColumn<>("Affinity\n(%)");
	    affinityCol.setMinWidth(75);
	    affinityCol.setCellValueFactory(new PropertyValueFactory<Weapon_Lance, Integer>("affinity"));

	    lanceTable.getColumns().addAll(nameCol, attackCol, sharpnessCol, elementTypeCol, affinityCol);
	    BorderPane.setMargin(lanceTable, new Insets(0, 10, 10, 10));		
	}
	
	/**
	 * Sets up the JavaFX of the table.
	 */
	private void setupGunlanceTable()
	{
		gunlanceTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		TableColumn<Weapon_Gunlance, String> nameCol = new TableColumn<>("Name");
	    nameCol.setMinWidth(200);
	    nameCol.setCellValueFactory(new PropertyValueFactory<Weapon_Gunlance, String>("name"));
	    
	    TableColumn<Weapon_Gunlance, Integer> attackCol = new TableColumn<>("Attack");
	    attackCol.setMinWidth(75);
	    attackCol.setCellValueFactory(new PropertyValueFactory<Weapon_Gunlance, Integer>("attack"));

	    TableColumn<Weapon_Gunlance, String> sharpnessCol = new TableColumn<>("Sharpness");
	    sharpnessCol.setMinWidth(75);
	    sharpnessCol.setCellValueFactory(new PropertyValueFactory<Weapon_Gunlance, String>("sharpness"));
	    
	    TableColumn<Weapon_Gunlance, String> elementTypeCol = new TableColumn<>("Element\nType");
	    elementTypeCol.setMinWidth(75);
	    elementTypeCol.setCellValueFactory(new PropertyValueFactory<Weapon_Gunlance, String>("elementType"));
	    
	    TableColumn<Weapon_Gunlance, Integer> affinityCol = new TableColumn<>("Affinity\n(%)");
	    affinityCol.setMinWidth(75);
	    affinityCol.setCellValueFactory(new PropertyValueFactory<Weapon_Gunlance, Integer>("affinity"));

	    gunlanceTable.getColumns().addAll(nameCol, attackCol, sharpnessCol, elementTypeCol, affinityCol);
	    BorderPane.setMargin(gunlanceTable, new Insets(0, 10, 10, 10));
	}
	
	/**
	 * Sets up the JavaFX of the table.
	 */
	private void setupInsectGlaiveTable()
	{
		insectglaiveTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		TableColumn<Weapon_InsectGlaive, String> nameCol = new TableColumn<>("Name");
	    nameCol.setMinWidth(200);
	    nameCol.setCellValueFactory(new PropertyValueFactory<Weapon_InsectGlaive, String>("name"));
	    
	    TableColumn<Weapon_InsectGlaive, Integer> attackCol = new TableColumn<>("Attack");
	    attackCol.setMinWidth(75);
	    attackCol.setCellValueFactory(new PropertyValueFactory<Weapon_InsectGlaive, Integer>("attack"));

	    TableColumn<Weapon_InsectGlaive, String> sharpnessCol = new TableColumn<>("Sharpness");
	    sharpnessCol.setMinWidth(75);
	    sharpnessCol.setCellValueFactory(new PropertyValueFactory<Weapon_InsectGlaive, String>("sharpness"));
	    
	    TableColumn<Weapon_InsectGlaive, String> elementTypeCol = new TableColumn<>("Element\nType");
	    elementTypeCol.setMinWidth(75);
	    elementTypeCol.setCellValueFactory(new PropertyValueFactory<Weapon_InsectGlaive, String>("elementType"));
	    
	    TableColumn<Weapon_InsectGlaive, Integer> affinityCol = new TableColumn<>("Affinity\n(%)");
	    affinityCol.setMinWidth(75);
	    affinityCol.setCellValueFactory(new PropertyValueFactory<Weapon_InsectGlaive, Integer>("affinity"));

	    insectglaiveTable.getColumns().addAll(nameCol, attackCol, sharpnessCol, elementTypeCol, affinityCol);
	    BorderPane.setMargin(insectglaiveTable, new Insets(0, 10, 10, 10));	
	}
	
	/**
	 * Sets up the JavaFX of the table.
	 */
	private void setupKinsectTable()
	{
		kinsectTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		TableColumn<Weapon_Kinsect, String> nameCol = new TableColumn<>("Name");
	    nameCol.setMinWidth(150);
	    nameCol.setCellValueFactory(new PropertyValueFactory<Weapon_Kinsect, String>("name"));
	    
	    TableColumn<Weapon_Kinsect, String> attackTypeCol = new TableColumn<>("Attack\nType");
	    attackTypeCol.setMinWidth(75);
	    attackTypeCol.setCellValueFactory(new PropertyValueFactory<Weapon_Kinsect, String>("attackType"));

	    TableColumn<Weapon_Kinsect, String> dustCol = new TableColumn<>("Dust\nEffect");
	    dustCol.setMinWidth(75);
	    dustCol.setCellValueFactory(new PropertyValueFactory<Weapon_Kinsect, String>("dustEffect"));
	    
	    TableColumn<Weapon_Kinsect, Integer> powerLevelCol = new TableColumn<>("Power\nLevel");
	    powerLevelCol.setMinWidth(70);
	    powerLevelCol.setCellValueFactory(new PropertyValueFactory<Weapon_Kinsect, Integer>("powerLevel"));
	    
	    TableColumn<Weapon_Kinsect, Integer> speedLevelCol = new TableColumn<>("Speed\nLevel");
	    speedLevelCol.setMinWidth(70);
	    speedLevelCol.setCellValueFactory(new PropertyValueFactory<Weapon_Kinsect, Integer>("speedLevel"));

	    TableColumn<Weapon_Kinsect, Integer> healLevelCol = new TableColumn<>("Speed\nLevel");
	   	healLevelCol.setMinWidth(70);
	    healLevelCol.setCellValueFactory(new PropertyValueFactory<Weapon_Kinsect, Integer>("healLevel"));

	    kinsectTable.getColumns().addAll(nameCol, attackTypeCol, dustCol, powerLevelCol, speedLevelCol, healLevelCol);
	    BorderPane.setMargin(kinsectTable, new Insets(0, 10, 10, 10));
	}
	
	/**
	 * Sets up the JavaFX of the table.
	 */
	private void setupHammerTable()
	{
		hammerTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		TableColumn<Weapon_Hammer, String> nameCol = new TableColumn<>("Name");
	    nameCol.setMinWidth(200);
	    nameCol.setCellValueFactory(new PropertyValueFactory<Weapon_Hammer, String>("name"));
	    
	    TableColumn<Weapon_Hammer, Integer> attackCol = new TableColumn<>("Attack");
	    attackCol.setMinWidth(75);
	    attackCol.setCellValueFactory(new PropertyValueFactory<Weapon_Hammer, Integer>("attack"));

	    TableColumn<Weapon_Hammer, String> sharpnessCol = new TableColumn<>("Sharpness");
	    sharpnessCol.setMinWidth(75);
	    sharpnessCol.setCellValueFactory(new PropertyValueFactory<Weapon_Hammer, String>("sharpness"));
	    
	    TableColumn<Weapon_Hammer, String> elementTypeCol = new TableColumn<>("Element\nType");
	    elementTypeCol.setMinWidth(75);
	    elementTypeCol.setCellValueFactory(new PropertyValueFactory<Weapon_Hammer, String>("elementType"));
	    
	    TableColumn<Weapon_Hammer, Integer> affinityCol = new TableColumn<>("Affinity\n(%)");
	    affinityCol.setMinWidth(75);
	    affinityCol.setCellValueFactory(new PropertyValueFactory<Weapon_Hammer, Integer>("affinity"));

	    hammerTable.getColumns().addAll(nameCol, attackCol, sharpnessCol, elementTypeCol, affinityCol);
	    BorderPane.setMargin(hammerTable, new Insets(0, 10, 10, 10));	
	}
	
	/**
	 * Sets up the JavaFX of the table.
	 */
	private void setupHuntinghornTable()
	{
		huntinghornTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		TableColumn<Weapon_Huntinghorn, String> nameCol = new TableColumn<>("Name");
	    nameCol.setMinWidth(200);
	    nameCol.setCellValueFactory(new PropertyValueFactory<Weapon_Huntinghorn, String>("name"));
	    
	    TableColumn<Weapon_Huntinghorn, Integer> attackCol = new TableColumn<>("Attack");
	    attackCol.setMinWidth(75);
	    attackCol.setCellValueFactory(new PropertyValueFactory<Weapon_Huntinghorn, Integer>("attack"));

	    TableColumn<Weapon_Huntinghorn, String> sharpnessCol = new TableColumn<>("Sharpness");
	    sharpnessCol.setMinWidth(75);
	    sharpnessCol.setCellValueFactory(new PropertyValueFactory<Weapon_Huntinghorn, String>("sharpness"));
	    
	    TableColumn<Weapon_Huntinghorn, String> elementTypeCol = new TableColumn<>("Element\nType");
	    elementTypeCol.setMinWidth(75);
	    elementTypeCol.setCellValueFactory(new PropertyValueFactory<Weapon_Huntinghorn, String>("elementType"));
	    
	    TableColumn<Weapon_Huntinghorn, Integer> affinityCol = new TableColumn<>("Affinity\n(%)");
	    affinityCol.setMinWidth(75);
	    affinityCol.setCellValueFactory(new PropertyValueFactory<Weapon_Huntinghorn, Integer>("affinity"));

	    huntinghornTable.getColumns().addAll(nameCol, attackCol, sharpnessCol, elementTypeCol, affinityCol);
	    BorderPane.setMargin(huntinghornTable, new Insets(0, 10, 10, 10));
	}
	
	/**
	 * Sets up the JavaFX of the table.
	 */
	private void setupLightBowgunTable()
	{
		lightbowgunTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		TableColumn<Weapon_LightBowgun, String> nameCol = new TableColumn<>("Name");
	    nameCol.setMinWidth(200);
	    nameCol.setCellValueFactory(new PropertyValueFactory<Weapon_LightBowgun, String>("name"));
	    
	    TableColumn<Weapon_LightBowgun, Integer> attackCol = new TableColumn<>("Attack");
	    attackCol.setMinWidth(75);
	    attackCol.setCellValueFactory(new PropertyValueFactory<Weapon_LightBowgun, Integer>("attack"));

	    TableColumn<Weapon_LightBowgun, String> deviationCol = new TableColumn<>("Deviation");
	    deviationCol.setMinWidth(75);
	    deviationCol.setCellValueFactory(new PropertyValueFactory<Weapon_LightBowgun, String>("deviation"));
	    
	    TableColumn<Weapon_LightBowgun, String> modslotsCol = new TableColumn<>("Mod Slots");
	    modslotsCol.setMinWidth(75);
	    modslotsCol.setCellValueFactory(new PropertyValueFactory<Weapon_LightBowgun, String>("modSlots"));

	    lightbowgunTable.getColumns().addAll(nameCol, attackCol, deviationCol, modslotsCol);
	    BorderPane.setMargin(lightbowgunTable, new Insets(0, 10, 10, 10));	
	}
	
	/**
	 * Sets up the JavaFX of the table.
	 */
	private void setupHeavyBowgunTable()
	{
		heavybowgunTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		TableColumn<Weapon_HeavyBowgun, String> nameCol = new TableColumn<>("Name");
	    nameCol.setMinWidth(200);
	    nameCol.setCellValueFactory(new PropertyValueFactory<Weapon_HeavyBowgun, String>("name"));
	    
	    TableColumn<Weapon_HeavyBowgun, Integer> attackCol = new TableColumn<>("Attack");
	    attackCol.setMinWidth(50);
	    attackCol.setCellValueFactory(new PropertyValueFactory<Weapon_HeavyBowgun, Integer>("attack"));

	    TableColumn<Weapon_HeavyBowgun, String> specialAmmoCol = new TableColumn<>("Special\nAmmo");
	    specialAmmoCol.setMinWidth(100);
	    specialAmmoCol.setCellValueFactory(new PropertyValueFactory<Weapon_HeavyBowgun, String>("specialAmmo"));
	    
	    TableColumn<Weapon_HeavyBowgun, String> deviationCol = new TableColumn<>("Deviation");
	    deviationCol.setMinWidth(75);
	    deviationCol.setCellValueFactory(new PropertyValueFactory<Weapon_HeavyBowgun, String>("deviation"));
	    
	    TableColumn<Weapon_HeavyBowgun, Integer> modslotsCol = new TableColumn<>("Mod Slots");
	    modslotsCol.setMinWidth(75);
	    modslotsCol.setCellValueFactory(new PropertyValueFactory<Weapon_HeavyBowgun, Integer>("modSlots"));

	    heavybowgunTable.getColumns().addAll(nameCol, attackCol, specialAmmoCol, deviationCol, modslotsCol);
	    BorderPane.setMargin(heavybowgunTable, new Insets(0, 10, 10, 10));
	}
	
	/**
	 * Sets up the JavaFX of the table.
	 */
	private void setupBowTable()
	{
		bowTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		TableColumn<Weapon_Bow, String> nameCol = new TableColumn<>("Name");
	    nameCol.setMinWidth(200);
	    nameCol.setCellValueFactory(new PropertyValueFactory<Weapon_Bow, String>("name"));
	    
	    TableColumn<Weapon_Bow, Integer> attackCol = new TableColumn<>("Attack");
	    attackCol.setMinWidth(75);
	    attackCol.setCellValueFactory(new PropertyValueFactory<Weapon_Bow, Integer>("attack"));

	    TableColumn<Weapon_Bow, String> elementTypeCol = new TableColumn<>("Element\nType");
	    elementTypeCol.setMinWidth(75);
	    elementTypeCol.setCellValueFactory(new PropertyValueFactory<Weapon_Bow, String>("elementType"));
	    
	    TableColumn<Weapon_Bow, Integer> affinityCol = new TableColumn<>("Affinity\n(%)");
	    affinityCol.setMinWidth(75);
	    affinityCol.setCellValueFactory(new PropertyValueFactory<Weapon_Bow, Integer>("affinity"));
	    
	    bowTable.getColumns().addAll(nameCol, attackCol, elementTypeCol, affinityCol);
	    BorderPane.setMargin(bowTable, new Insets(0, 10, 10, 10));
	}
}
