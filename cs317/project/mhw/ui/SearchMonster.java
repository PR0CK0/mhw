package cs317.project.mhw.ui;

import cs317.project.mhw.db.MonsterList;
import cs317.project.mhw.item.Monster;
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
 * Class to search and display monsters.
 *
 */

public class SearchMonster extends SearchPARENT
{
	private MonsterList a = new MonsterList();
	
	private ItemPage page;
	private TableView<Monster> table = new TableView<Monster>();
	
	/**
	 * Default constructor.
	 */
	public SearchMonster()
	{	
		// Table selection
		table.getSelectionModel().selectedItemProperty().addListener(
				(ObservableValue<? extends Monster> ov, Monster oldVal, Monster newVal) -> monsterClick(newVal));
		
		// Combobox setup
		cbSearchBy.getItems().setAll("Display All Monsters", "Search Monster Name");
		cbSearchBy.setValue("Display All Monsters");
		
		// Tableview setup
		TableColumn<Monster, String> nameCol = new TableColumn<>("Name");
	    nameCol.setMinWidth(150);
	    nameCol.setCellValueFactory(new PropertyValueFactory<Monster, String>("name"));
	    
	    TableColumn<Monster, String> typeCol = new TableColumn<>("Type");
	    typeCol.setMinWidth(30);
	    typeCol.setCellValueFactory( new PropertyValueFactory<Monster, String>("type"));
	
	    table.getColumns().addAll(nameCol, typeCol);
	    table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
	    BorderPane.setMargin(table, new Insets(0, 10, 10, 10));	
		
	    // Super call
	    setup("monster");
		root.setCenter(table);
		
		// Populate table
		table.setItems(a.getMonsterList());
		
		// Initially disable the textfield when all items are displayed
		tfSearch.setDisable(true);
		
		// Combobox functionality
		cbSearchBy.setOnAction(e -> 
		{
			if (cbSearchBy.getValue().equalsIgnoreCase("display all monsters"))
			{
				a.populateMonsterList();
				table.setItems(a.getMonsterList());
				tfSearch.setText("");
				tfSearch.setPromptText(tfValue);
				tfSearch.setDisable(true);
			}
			else
			{
				tfSearch.setText("");
				tfSearch.setPromptText(tfValue);
				tfSearch.setDisable(false);
			}
		});
		
		// Search button functionality
		btSearch.setOnAction(e -> searchClick());
	}
	
	/**
	 * Displays a specific item page when an item is selected in the table.
	 * 
	 * @param mon
	 */
	private void monsterClick(Monster mon)
	{
		page = new ItemPage(mon);
		ProgramHUD.root.setCenter(page.getRoot());
	}
	
	/**
	 * Allows searching by monster name.
	 */
	public void searchClick()
	{
		a.search(tfSearch.getText());
		table.setItems(a.getMonsterList());	
	}
}