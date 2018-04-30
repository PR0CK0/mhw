package cs317.project.mhw.ui;

import cs317.project.mhw.db.CharmList;
import cs317.project.mhw.item.Charm;
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
 * Class to search and display charms.
 *
 */

public class SearchCharm extends SearchPARENT
{
	private CharmList b = new CharmList();
	public SimpleObjectProperty<Charm> charmStored = new SimpleObjectProperty<Charm>(this, "charmStored", new Charm());
	
	private ItemPage page;
	private TableView<Charm> tableCharm = new TableView<Charm>();
	
	/**
	 * Default constructor. Used in ProgramHUD.
	 */
	public SearchCharm()
	{	
		constructorHelp(true);
	}
	
	/**
	 * Alternate constructor. Used in ArmorBuilder.
	 * 
	 * @param normal
	 */
	public SearchCharm(boolean normal)
	{
		constructorHelp(normal);
	}

	/**
	 * Called from the constructors. Sets up the majority of the JavaFX, especially on the tableviews.
	 * 
	 * @param normal
	 */
	public void constructorHelp(boolean normal)
	{
		// If called in ProgramHUD, call charmClick() on a selected item in the tableview
		if (normal)
		{
			tableCharm.getSelectionModel().selectedItemProperty().addListener(
					(ObservableValue<? extends Charm> ov, Charm oldVal, Charm newVal) -> charmClick(newVal));
		}
		// If called in ProgramHUD, call charmClickForArmorBuilder() on a selected item in the tableview
		else
		{
			tableCharm.getSelectionModel().selectedItemProperty().addListener(
					(ObservableValue<? extends Charm> ov, Charm oldVal, Charm newVal) -> charmClickForArmorBuilder(newVal));
		}

		// Set up combobox
		cbSearchBy.getItems().setAll("Display All Charms", "Search Charm Name", "Search Charm Skills");
		cbSearchBy.setValue("Display All Charms");
		
		// Setup table
		setupCharmTable();
		// Super call
	    setup("charm");
	    root.setCenter(tableCharm);
	
	    // Populate the table
		tableCharm.setItems(b.getCharmList());
		
		// Initially disable the textfield when all items are displayed		
		tfSearch.setDisable(true);
		
		// Combobox functionality
		cbSearchBy.setOnAction(e -> 
		{
			if (cbSearchBy.getValue().equalsIgnoreCase("display all charms"))
			{
				b.populateCharmList();
				tableCharm.setItems(b.getCharmList());		
				tfSearch.setText("");
				tfSearch.setPromptText(tfValue);
				tfSearch.setDisable(true);
				root.setCenter(tableCharm);
			}
			else if (cbSearchBy.getValue().equalsIgnoreCase("search charm name") || cbSearchBy.getValue().equalsIgnoreCase("search charm skills"))
			{
				b.populateCharmList();
				tableCharm.setItems(b.getCharmList());
				root.setCenter(tableCharm);
				tfSearch.setDisable(false);
			}
		});
		
		// Search button functionality
		btSearch.setOnAction(e -> searchClick());
	}
	
	/**
	 * Default charmClick method, used when instance of this class
	 * is created through the default constructor. Displays a new
	 * item page for the selected item.
	 * 
	 * @param chm
	 */
	private void charmClick(Charm chm)
	{
		page = new ItemPage(chm);
		ProgramHUD.root.setCenter(page.getRoot());
	}
	
	/**
	 * Alternate charmClick method, used when instance of this class
	 * is created through the alternate constructor. Simply saves the
	 * selected item into a local variable.
	 * 
	 * @param newVal
	 */
	private void charmClickForArmorBuilder(Charm newVal) 
	{
		charmStored.setValue(newVal);
	}
	
	/**
	 * Allows searching of items based on name or skill.
	 */
	public void searchClick()
	{
		if (cbSearchBy.getValue().equalsIgnoreCase("search charm name"))
		{
			b.search(tfSearch.getText(), "name");
			tableCharm.setItems(b.getCharmList());		
		}
		else if (cbSearchBy.getValue().equalsIgnoreCase("search charm skills"))
		{
			b.search(tfSearch.getText(), "skill");
			tableCharm.setItems(b.getCharmList());
		}
	}
	
	/**
	 * Sets up the JavaFX of the table.
	 */
	private void setupCharmTable()
	{
		tableCharm.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		TableColumn<Charm, String> nameCol = new TableColumn<>("Name");
	    nameCol.setMinWidth(150);
	    nameCol.setCellValueFactory(new PropertyValueFactory<Charm, String>("name"));

	    TableColumn<Charm, String> skill1Col = new TableColumn<>("Skill One");
	    skill1Col.setMinWidth(120);
	    skill1Col.setCellValueFactory(new PropertyValueFactory<Charm, String>("skill1"));
	    
	    TableColumn<Charm, Integer> skill1LevelCol = new TableColumn<>("Skill 1\nLevel");
	    skill1LevelCol.setMinWidth(50);
	    skill1LevelCol.setCellValueFactory(new PropertyValueFactory<Charm, Integer>("skill1Level"));
	    
	    TableColumn<Charm, String> skill2Col = new TableColumn<>("Skill Two");
	    skill2Col.setMinWidth(120);
	    skill2Col.setCellValueFactory(new PropertyValueFactory<Charm, String>("skill2"));
	    
	    TableColumn<Charm, Integer> skill2LevelCol = new TableColumn<>("Skill 2\nLevel");
	    skill2LevelCol.setMinWidth(50);
	    skill2LevelCol.setCellValueFactory(new PropertyValueFactory<Charm, Integer>("skill2Level"));
	
	    tableCharm.getColumns().addAll(nameCol, skill1Col, skill1LevelCol, skill2Col, skill2LevelCol);
	    BorderPane.setMargin(tableCharm, new Insets(0, 10, 10, 10));
	}
}