package cs317.project.mhw.ui;

import cs317.project.mhw.db.ArmorBuilderSetList;
import cs317.project.mhw.db.ArmorList;
import cs317.project.mhw.db.CharmList;
import cs317.project.mhw.db.WeaponList;
import cs317.project.mhw.item.ArmorBuilderSet;
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
 * Class intended to provide update/delete functionality to a user's
 * saved armor sets... Not completed.
 *
 */

public class ArmorBuilderSetsPage 
{
	private ArmorList aList = new ArmorList();
	private WeaponList wList = new WeaponList();
	private CharmList cList = new CharmList();
	private ArmorBuilderSetList a = new ArmorBuilderSetList();
	private ArmorBuilder ab;
	
	private TableView<ArmorBuilderSet> table = new TableView<ArmorBuilderSet>();
	
	private BorderPane root;
	
	public ArmorBuilderSetsPage()
	{	
		root = new BorderPane();
		
		// Table selection
		table.getSelectionModel().selectedItemProperty().addListener(
				(ObservableValue<? extends ArmorBuilderSet> ov, ArmorBuilderSet oldVal, ArmorBuilderSet newVal) -> click(newVal));
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		// Setting up the table
		TableColumn<ArmorBuilderSet, String> nameCol = new TableColumn<>("Set Name");
	    nameCol.setMinWidth(100);
	    nameCol.setCellValueFactory(new PropertyValueFactory<ArmorBuilderSet, String>("setName"));
	    
	    TableColumn<ArmorBuilderSet, String> weaponCol = new TableColumn<>("Weapon");
	    weaponCol.setMinWidth(30);
	    weaponCol.setCellValueFactory(new PropertyValueFactory<ArmorBuilderSet, String>("weaponName"));
	    
	    TableColumn<ArmorBuilderSet, String> headCol = new TableColumn<>("Head");
	    headCol.setMinWidth(100);
	    headCol.setCellValueFactory(new PropertyValueFactory<ArmorBuilderSet, String>("headName"));
	    
	    TableColumn<ArmorBuilderSet, String> charmCol = new TableColumn<>("Charm");
	    charmCol.setMinWidth(100);
	    charmCol.setCellValueFactory(new PropertyValueFactory<ArmorBuilderSet, String>("charmName"));
	
	    table.getColumns().addAll(nameCol, weaponCol, headCol, charmCol);
	    
	    table.setItems(a.getArmorBuilderSetList());
		BorderPane.setMargin(table, new Insets(0, 10, 10, 10));	
		root.setCenter(table);
	}
	
	/**
	 * TODO
	 * 
	 * Method intended to create and populate an Armor Builder when an item in the
	 * tableview is clicked.
	 * 
	 * @param newVal
	 */
	private void click(ArmorBuilderSet newVal)
	{
		/*ab = new ArmorBuilder(new Weapon(), aList.getSpecificHeadOnly(newVal.getHeadName()), 
				aList.getSpecificChestOnly(newVal.getChestName()), aList.getSpecificArmsOnly(newVal.getArmsName()), 
				aList.getSpecificWaistOnly(newVal.getWaistName()), aList.getSpecificLegsOnly(newVal.getLegsName()), 
				cList.getSpecificCharm(newVal.getCharmName()));
		ProgramHUD.root.setCenter(ab.getRoot());
		*/
	}
	
	public BorderPane getRoot() {
		return root;
	}
}