package cs317.project.mhw.ui;

import cs317.project.mhw.db.ItemList;
import cs317.project.mhw.item.Item;
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
 * Class to search and display items.
 *
 */

public class SearchItem extends SearchPARENT
{
	private ItemList a = new ItemList();
	
	private ItemPage page;
	private TableView<Item> table = new TableView<Item>();
	
	/**
	 * Default constructor.
	 */
	public SearchItem()
	{	
		// Table selection
		table.getSelectionModel().selectedItemProperty().addListener(
				(ObservableValue<? extends Item> ov, Item oldVal, Item newVal) -> itemClick(newVal));
		
		// Combobox setup
		cbSearchBy.getItems().setAll("Display All Items", "Search Item Name");
		cbSearchBy.setValue("Display All Items");
		
		// Table setup
		TableColumn<Item, String> nameCol = new TableColumn<>("Name");
	    nameCol.setMinWidth(100);
	    nameCol.setCellValueFactory(new PropertyValueFactory<Item, String>("name"));
	    
	    TableColumn<Item, Integer> capacityCol = new TableColumn<>("Capacity");
	    capacityCol.setMinWidth(30);
	    capacityCol.setCellValueFactory( new PropertyValueFactory<Item, Integer>("capacity"));
	    
	    TableColumn<Item, String> descCol = new TableColumn<>("Description");
	    descCol.setMinWidth(100);
	    descCol.setCellValueFactory(new PropertyValueFactory<Item, String>("description"));
	
	    table.getColumns().addAll(nameCol, capacityCol, descCol);
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		BorderPane.setMargin(table, new Insets(0, 10, 10, 10));	
		
		// Super call
	    setup("item");
	    root.setCenter(table);
	    
	    // Populate table
		table.setItems(a.getItemList());
		
		// Initially disable the textfield when all items are displayed
		tfSearch.setDisable(true);
		
		// Combobox functionality
		cbSearchBy.setOnAction(e -> 
		{
			if (cbSearchBy.getValue().equalsIgnoreCase("display all items"))
			{
				a.populateItemList();
				table.setItems(a.getItemList());
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
	 * @param newVal
	 */
	private void itemClick(Item newVal)
	{
		page = new ItemPage(newVal);
		ProgramHUD.root.setCenter(page.getRoot());
	}
	
	/**
	 * Allows searching by item name.
	 */
	public void searchClick()
	{
		if (cbSearchBy.getValue().equalsIgnoreCase("search item name"))
		{
			a.search(tfSearch.getText());
			table.setItems(a.getItemList());		
		}
	}
}