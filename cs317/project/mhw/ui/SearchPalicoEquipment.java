package cs317.project.mhw.ui;

import cs317.project.mhw.db.PalicoArmorList;
import cs317.project.mhw.db.PalicoWeaponList;
import cs317.project.mhw.item.PalicoArmor;
import cs317.project.mhw.item.PalicoWeapon;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

/**
 * 
 * @author Tyler Procko
 * @date January-May 2018
 * 
 * Class to search palico weapons and armor.
 *
 */

public class SearchPalicoEquipment extends SearchPARENT
{
	private PalicoWeaponList a = new PalicoWeaponList();
	private PalicoArmorList b = new PalicoArmorList();
	
	private ItemPage page;
	private TableView<PalicoArmor> palicoArmorTable = new TableView<PalicoArmor>();
	private TableView<PalicoWeapon> palicoWeaponTable = new TableView<PalicoWeapon>();

	/**
	 * Default constructor.
	 */
	public SearchPalicoEquipment()
	{
		// Table selection
		palicoArmorTable.getSelectionModel().selectedItemProperty().addListener(
				(ObservableValue<? extends PalicoArmor> ov, PalicoArmor oldVal, PalicoArmor newVal) -> palicoArmorClick(newVal));
		palicoWeaponTable.getSelectionModel().selectedItemProperty().addListener(
				(ObservableValue<? extends PalicoWeapon> ov, PalicoWeapon oldVal, PalicoWeapon newVal) -> palicoWeaponClick(newVal));
		
		// Combobox setup
		cbSearchBy.getItems().setAll("Display All Palico Weapons", "Search Palico Weapon Name", "Display All Palico Armor", "Search Palico Armor Name");
		cbSearchBy.setValue("Display All Weapons");
		
		// Setup tables
		setupPalicoWeaponTable();
	    setupPalicoArmorTable(); 
	    // Super call
	    setup("palico equipment");
	    root.setCenter(palicoWeaponTable);
	    
	    // Populate tables
		palicoWeaponTable.setItems(a.getPalicoWeaponList());
		palicoArmorTable.setItems(b.getPalicoArmorList());
		
		// Initially disable the textfield when all items are displayed
		tfSearch.setDisable(true);
		
		// Combobox functionality
		cbSearchBy.setOnAction(e -> 
		{
			if (cbSearchBy.getValue().equalsIgnoreCase("display all palico weapons"))
			{
				a.populatePalicoWeaponList();
				palicoWeaponTable.setItems(a.getPalicoWeaponList());
				tfSearch.setText("");
				tfSearch.setPromptText(tfValue);
				tfSearch.setDisable(true);
				root.setCenter(palicoWeaponTable);
			}
			else if (cbSearchBy.getValue().equalsIgnoreCase("display all palico armor"))
			{
				b.populatePalicoArmorList();
				palicoArmorTable.setItems(b.getPalicoArmorList());
				tfSearch.setText("");
				tfSearch.setPromptText(tfValue);
				tfSearch.setDisable(true);
				root.setCenter(palicoArmorTable);
			}
			else if (cbSearchBy.getValue().equalsIgnoreCase("search palico weapon name"))
			{
				a.populatePalicoWeaponList();
				palicoWeaponTable.setItems(a.getPalicoWeaponList());
				root.setCenter(palicoWeaponTable);
				tfSearch.setDisable(false);
			}
			else if (cbSearchBy.getValue().equalsIgnoreCase("search palico armor name"))
			{
				b.populatePalicoArmorList();
				palicoArmorTable.setItems(b.getPalicoArmorList());
				root.setCenter(palicoArmorTable);
				tfSearch.setDisable(false);
			}
		});
		
		// Search button functionality
		btSearch.setOnAction(e -> searchClick());
	}
	
	/**
	 * Displays a new item page for the selected item.
	 * 
	 * @param wpn
	 */
	private void palicoWeaponClick(PalicoWeapon wpn)
	{
		page = new ItemPage(wpn);
		ProgramHUD.root.setCenter(page.getRoot());
	}
	
	/**
	 * Displays a new item page for the selected item.
	 * 
	 * @param amr
	 */
	private void palicoArmorClick(PalicoArmor amr)
	{
		page = new ItemPage(amr);
		ProgramHUD.root.setCenter(page.getRoot());
	}
	
	/**
	 * Allows searching by palico weapon name or armor name;
	 */
	public void searchClick()
	{
		if (cbSearchBy.getValue().equalsIgnoreCase("search palico weapon name"))
		{
			a.search(tfSearch.getText());
			palicoWeaponTable.setItems(a.getPalicoWeaponList());
		}
		else if (cbSearchBy.getValue().equalsIgnoreCase("search palico armor name"))
		{
			b.search(tfSearch.getText());
			palicoArmorTable.setItems(b.getPalicoArmorList());		
		}
	}

	/**
	 * Sets up the JavaFX of the table.
	 */
	private void setupPalicoWeaponTable()
	{
		palicoWeaponTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		TableColumn<PalicoWeapon, String> nameCol = new TableColumn<>("Name");
	    nameCol.setMinWidth(200);
	    nameCol.setCellValueFactory(new PropertyValueFactory<PalicoWeapon, String>("name"));
	    
	    TableColumn<PalicoWeapon, Integer> attackMeleeCol = new TableColumn<>("Attack -\nMelee");
	    attackMeleeCol.setMinWidth(75);
	    attackMeleeCol.setCellValueFactory(new PropertyValueFactory<PalicoWeapon, Integer>("attackMelee"));

	    TableColumn<PalicoWeapon, String> attackTypeCol = new TableColumn<>("Attack\nType");
	    attackTypeCol.setMinWidth(75);
	    attackTypeCol.setCellValueFactory(new PropertyValueFactory<PalicoWeapon, String>("attackType"));
	    
	    TableColumn<PalicoWeapon, String> elementTypeCol = new TableColumn<>("Element\nType");
	    elementTypeCol.setMinWidth(75);
	    elementTypeCol.setCellValueFactory(new PropertyValueFactory<PalicoWeapon, String>("elementType"));
	    
	    TableColumn<PalicoWeapon, Integer> affinityCol = new TableColumn<>("Affinity\n(%)");
	    affinityCol.setMinWidth(75);
	    affinityCol.setCellValueFactory(new PropertyValueFactory<PalicoWeapon, Integer>("affinity"));

	    palicoWeaponTable.getColumns().addAll(nameCol, attackMeleeCol, attackTypeCol, elementTypeCol, affinityCol);
	    BorderPane.setMargin(palicoWeaponTable, new Insets(0, 10, 10, 10));	
	}
	
	/**
	 * Sets up the JavaFX of the table.
	 */
	private void setupPalicoArmorTable()
	{
	    palicoArmorTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		TableColumn<PalicoArmor, String> nameCol = new TableColumn<>("Name");
	    nameCol.setMinWidth(150);
	    nameCol.setCellValueFactory(new PropertyValueFactory<PalicoArmor, String>("name"));
	    
	    TableColumn<PalicoArmor, String> armorTypeCol = new TableColumn<>("Type");
	    armorTypeCol.setMinWidth(50);
	    armorTypeCol.setCellValueFactory(new PropertyValueFactory<PalicoArmor, String>("type"));
	    
	    TableColumn<PalicoArmor, Integer> rarityCol = new TableColumn<>("Rarity");
	    rarityCol.setMinWidth(75);
	    rarityCol.setCellValueFactory(new PropertyValueFactory<PalicoArmor, Integer>("rarity"));
	    
	    TableColumn<PalicoArmor, String> defenseCol = new TableColumn<>("Defense");
	    defenseCol.setMinWidth(75);
	    defenseCol.setCellValueFactory(new PropertyValueFactory<PalicoArmor, String>("defense"));
	    
	    palicoArmorTable.getColumns().addAll(nameCol, armorTypeCol, rarityCol, defenseCol);
		BorderPane.setMargin(palicoArmorTable, new Insets(0, 10, 10, 10));	
	}
}