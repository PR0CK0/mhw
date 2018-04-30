package cs317.project.mhw.ui;

import cs317.project.mhw.db.ArmorList;
import cs317.project.mhw.item.Armor;
import javafx.beans.property.SimpleObjectProperty;
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
 * Class to search and display armor. 
 *
 */

public class SearchArmor extends SearchPARENT
{
	private ArmorList a = new ArmorList();
	public SimpleObjectProperty<Armor> amrStored = new SimpleObjectProperty<>(this, "amrStored", null);
	
	private ItemPage page;
	private TableView<Armor> tableArmor = new TableView<Armor>();

	/**
	 * Default constructor. Used in ProgramHUD.
	 */
	public SearchArmor()
	{	
		constructorHelp(true, "NA");
	}
	
	/**
	 * Alternate constructor. Used in ArmorBuilder.
	 * 
	 * @param normal
	 * @param type
	 */
	public SearchArmor(boolean normal, String type)
	{
		constructorHelp(normal, type);
	}
	
	/**
	 * Called from the constructors. Sets up the majority of the JavaFX, especially on the tableviews.
	 * 
	 * @param normal
	 * @param type
	 */
	public void constructorHelp(boolean normal, String type)
	{
		// If called in ProgramHUD, call armorClick() on a selected item in the tableview
		if (normal)
		{
			tableArmor.getSelectionModel().selectedItemProperty().addListener(
					(ObservableValue<? extends Armor> ov, Armor oldVal, Armor newVal) -> armorClick(newVal));
			tableArmor.setItems(a.getArmorList());
		}
		// If called in ArmorBuilder call armorClickForArmorBuilder() on a selcted item in the tableview
		else
		{
			tableArmor.getSelectionModel().selectedItemProperty().addListener(
					(ObservableValue<? extends Armor> ov, Armor oldVal, Armor newVal) -> armorClickForArmorBuilder(newVal));
			
			// Important:
			// Must specify the type selected from the armor builder, as it is
			// nonsensical to display all types of armor when the user has 
			// selected to update their headpiece, etc.
			if (type == "head") {
				a.getHeadOnly();
				tableArmor.setItems(a.getArmorList());
			}
			else if (type == "chest") {
				a.getChestOnly();
				tableArmor.setItems(a.getArmorList());
			}
			else if (type == "arms") {
				a.getArmsOnly();
				tableArmor.setItems(a.getArmorList());
			}
			else if (type == "waist") {
				a.getWaistOnly();
				tableArmor.setItems(a.getArmorList());
			}
			else if (type == "legs") {
				a.getLegsOnly();
				tableArmor.setItems(a.getArmorList());
			}
		}

		// Set up combobox
		cbSearchBy.getItems().setAll("Display All Armor", "Search Armor Name", "Search Armor Skills");
		cbSearchBy.setValue("Display All Armor");
		
		// Setup the table
		setupArmorTable();
		// Super call
	    setup("armor");
		root.setCenter(tableArmor);
		
		// Initially disable the textfield when all items are displayed
		tfSearch.setDisable(true);

		// Combobox functionality
		cbSearchBy.setOnAction(e -> 
		{
			if (cbSearchBy.getValue().equalsIgnoreCase("display all armor"))
			{
				if (type == "head") a.getHeadOnly();
				else if (type == "chest") a.getChestOnly();
				else if (type == "arms") a.getArmorList();
				else if (type == "waist") a.getWaistOnly();
				else if (type == "legs") a.getLegsOnly();
				else a.populateArmorList();
				
				tableArmor.setItems(a.getArmorList());
				tfSearch.setPromptText(tfValue);
				tfSearch.setText("");
				tfSearch.setDisable(true);
				root.setCenter(tableArmor);
			}
			else if (cbSearchBy.getValue().equalsIgnoreCase("search armor name") || cbSearchBy.getValue().equalsIgnoreCase("search armor skills"))
			{
				if (type == "head") a.getHeadOnly();
				else if (type == "chest") a.getChestOnly();
				else if (type == "arms") a.getArmorList();
				else if (type == "waist") a.getWaistOnly();
				else if (type == "legs") a.getLegsOnly();
				else a.populateArmorList();
				
				tableArmor.setItems(a.getArmorList());
				root.setCenter(tableArmor);
				tfSearch.setDisable(false);
			}
		});
		
		// Search button functionality
		btSearch.setOnAction(e -> searchClick());
	}
	
	/**
	 * Default armorClick method, used when instance of this class
	 * is created through the default constructor. Displays a new
	 * item page for the selected item.
	 * 
	 * @param amr
	 */
	private void armorClick(Armor amr)
	{
		page = new ItemPage(amr);
		ProgramHUD.root.setCenter(page.getRoot());
	}

	/**
	 * Alternate armorClick method, used when instance of this class
	 * is created through the alternate constructor. Simply saves the
	 * selected item into a local variable.
	 * 
	 * @param newVal
	 */
	private void armorClickForArmorBuilder(Armor newVal) 
	{
		amrStored.setValue(newVal);
	}
	
	/**
	 * Allows searching of items based on name or skill.
	 */
	public void searchClick()
	{
		if (cbSearchBy.getValue().equalsIgnoreCase("search armor name"))
		{
			a.search(tfSearch.getText(), "name");
			tableArmor.setItems(a.getArmorList());		
		}
		else if (cbSearchBy.getValue().equalsIgnoreCase("search armor skills"))
		{
			a.search(tfSearch.getText(), "skill");
			tableArmor.setItems(a.getArmorList());
		}
	}
	
	/**
	 * Sets up the JavaFX of the table.
	 */
	private void setupArmorTable()
	{
		tableArmor.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		TableColumn<Armor, String> nameCol = new TableColumn<>("Name");
	    nameCol.setMinWidth(150);
	    nameCol.setCellValueFactory(new PropertyValueFactory<Armor, String>("name"));
	    
	    TableColumn<Armor, String> typeCol = new TableColumn<>("Type");
	    typeCol.setMinWidth(30);
	    typeCol.setCellValueFactory( new PropertyValueFactory<Armor, String>("type"));
	    
	    TableColumn<Armor, Integer> defCol = new TableColumn<>("Defense");
	    defCol.setMinWidth(50);
	    defCol.setCellValueFactory(new PropertyValueFactory<Armor, Integer>("defense"));
	
	    TableColumn<Armor, String> skill1Col = new TableColumn<>("Skill One");
	    skill1Col.setMinWidth(85);
	    skill1Col.setCellValueFactory(new PropertyValueFactory<Armor, String>("skill1"));
	    
	    TableColumn<Armor, String> skill2Col = new TableColumn<>("Skill Two");
	    skill2Col.setMinWidth(85);
	    skill2Col.setCellValueFactory(new PropertyValueFactory<Armor, String>("skill2"));
	
	    tableArmor.getColumns().addAll(nameCol, typeCol, defCol, skill1Col, skill2Col);
		BorderPane.setMargin(tableArmor, new Insets(0, 10, 10, 10));	
	}
}