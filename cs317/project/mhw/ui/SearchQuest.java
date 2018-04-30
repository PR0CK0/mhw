package cs317.project.mhw.ui;

import cs317.project.mhw.db.QuestList;
import cs317.project.mhw.item.Quest;
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
 * Class to search and display quests.
 *
 */

public class SearchQuest extends SearchPARENT
{
	private QuestList a = new QuestList();
	
	private ItemPage page;
	private TableView<Quest> table = new TableView<Quest>();
	
	/**
	 * Default constructor.
	 */
	public SearchQuest()
	{	
		// Table selection
		table.getSelectionModel().selectedItemProperty().addListener(
				(ObservableValue<? extends Quest> ov, Quest oldVal, Quest newVal) -> questClick(newVal));
		
		// Combobox setup
		cbSearchBy.getItems().setAll("Display All Quests", "Search Quest Name", "Search By Monster");
		cbSearchBy.setValue("Display All Quests");
				
		// Table setup
		TableColumn<Quest, String> nameCol = new TableColumn<>("Name");
	    nameCol.setMinWidth(150);
	    nameCol.setCellValueFactory(new PropertyValueFactory<Quest, String>("name"));
	    
	    TableColumn<Quest, String> targetCol = new TableColumn<>("Target Monster");
	    targetCol.setMinWidth(30);
	    targetCol.setCellValueFactory( new PropertyValueFactory<Quest, String>("targetMonster"));
	    
	    TableColumn<Quest, String> hrCol = new TableColumn<>("HR");
	    hrCol.setMinWidth(50);
	    hrCol.setCellValueFactory(new PropertyValueFactory<Quest, String>("hr"));
	
	    table.getColumns().addAll(nameCol, targetCol, hrCol);
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		BorderPane.setMargin(table, new Insets(0, 10, 10, 10));	

		// Super call
	    setup("quest");
	    root.setCenter(table);
	    
	    // Populate table
		table.setItems(a.getQuestList());
		
		// Initially disable the textfield when all items are displayed
		tfSearch.setDisable(true);
		
		// Combobox functionality
		cbSearchBy.setOnAction(e -> 
		{
			if (cbSearchBy.getValue().equalsIgnoreCase("display all quests"))
			{
				a.populateQuestList();
				table.setItems(a.getQuestList());
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
	 * @param qst
	 */
	private void questClick(Quest qst)
	{
		page = new ItemPage(qst);
		ProgramHUD.root.setCenter(page.getRoot());
	}
	
	/**
	 * Allows searching by quest name or monster name.
	 */
	public void searchClick()
	{
		if (cbSearchBy.getValue().equalsIgnoreCase("search quest name"))
		{
			a.search(tfSearch.getText(), "name");
			table.setItems(a.getQuestList());		
		}
		else if (cbSearchBy.getValue().equalsIgnoreCase("search by monster"))
		{
			a.search(tfSearch.getText(), "monster");
			table.setItems(a.getQuestList());
		}
	}
}