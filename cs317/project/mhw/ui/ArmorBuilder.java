package cs317.project.mhw.ui;

import cs317.project.mhw.item.Armor;
import cs317.project.mhw.item.Charm;
import cs317.project.mhw.item.Weapon;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

/**
 * 
 * @author Tyler Procko
 * 
 * Class enabling a user to create a custom armor set. Persists until the application closes.
 *
 */

public class ArmorBuilder 
{
	private SearchArmor saHead = new SearchArmor(false, "head");
	private SearchArmor saChest = new SearchArmor(false, "chest");
	private SearchArmor saArms = new SearchArmor(false, "arms");
	private SearchArmor saWaist = new SearchArmor(false, "waist");
	private SearchArmor saLegs = new SearchArmor(false, "legs");
	private SearchCharm sc = new SearchCharm(false);
	private SearchWeapon sw = new SearchWeapon(false);
	
	private Weapon wpnStored = new Weapon();
	private Armor headStored = new Armor();
	private Armor chestStored = new Armor();
	private Armor armsStored = new Armor();
	private Armor waistStored = new Armor();
	private Armor legsStored = new Armor();
	private Charm charmStored = new Charm();
	
	private int attack;
	private int headDefense, headFireRes, headWaterRes, headThunderRes, headIceRes, headDragonRes;
	private int chestDefense, chestFireRes, chestWaterRes, chestThunderRes, chestIceRes, chestDragonRes;
	private int armsDefense, armsFireRes, armsWaterRes, armsThunderRes, armsIceRes, armsDragonRes;
	private int waistDefense, waistFireRes, waistWaterRes, waistThunderRes, waistIceRes, waistDragonRes;
	private int legsDefense, legsFireRes, legsWaterRes, legsThunderRes, legsIceRes, legsDragonRes;
	private int defense = 0;
	private int affinity;
	private int fireRes = 0;
	private int waterRes = 0;
	private int thunderRes = 0;
	private int iceRes = 0;
	private int dragonRes = 0;
	private String skills = "", skillsCharm = "", skillsHead = "", skillsChest = "", skillsArms = "", skillsWaist = "", skillsLegs = "";
	
	private BorderPane root;
	private Label lblHealth, lblDefense, lblAttack, lblAffinity, lblFireRes, lblWaterRes, lblThunderRes, lblIceRes, lblDragonRes,
			lblHealthVal, lblDefVal, lblAttackVal, lblAffinityVal, lblFireResVal, lblWaterResVal, lblThunderResVal, lblIceResVal, lblDragonResVal;
	private GridPane gpTop;
	private Label lblSkillsText;
	private VBox vbSkills;
	private ScrollPane spSkills;
	private HBox hbTop;
	private Button btWeapon, btHead, btChest, btArms, btWaist, btLegs, btCharm;
	private Label lblWeapon, lblHead, lblChest, lblArms, lblWaist, lblLegs, lblCharm;
	private GridPane gpRight;
	private VBox vbRight;
	
	/**
	 * Default constructor.
	 */
	public ArmorBuilder() 
	{	
		// JavaFX stuff
		setup();
		
		// Allow tableviews for each item click
		btWeapon.setOnAction(e -> root.setCenter(sw.getRoot()));
		btHead.setOnAction(e -> root.setCenter(saHead.getRoot()));
		btChest.setOnAction(e -> root.setCenter(saChest.getRoot()));
		btArms.setOnAction(e -> root.setCenter(saArms.getRoot()));
		btWaist.setOnAction(e -> root.setCenter(saWaist.getRoot()));
		btLegs.setOnAction(e -> root.setCenter(saLegs.getRoot()));
		btCharm.setOnAction(e -> root.setCenter(sc.getRoot()));

		// Weapon object listener
		sw.wpnStored.addListener((args, oldWep, newWep) ->
		{
			defense -= wpnStored.getDefenseBonus();
			wpnStored = newWep;
			defense += wpnStored.getDefenseBonus();
			attack = wpnStored.getAttack();
			affinity = wpnStored.getAffinity();
			lblDefVal.setText(Integer.toString(defense));
			lblAttackVal.setText(Integer.toString(attack));
			lblAffinityVal.setText(Integer.toString(affinity));
			lblWeapon.setText(wpnStored.getName() + ", Atk: " + wpnStored.getAttack() + ", DefBns: " + wpnStored.getDefenseBonus());
		});
		// Head armor object listener
		saHead.amrStored.addListener((args, oldHead, newHead) ->
		{
			skillsHead = "-HEAD-";
			defense -= headDefense;
			fireRes -= headFireRes;
			waterRes -= headWaterRes;
			thunderRes -= headThunderRes;
			iceRes -= headIceRes;
			dragonRes -= headDragonRes;
			headStored = newHead;
			headDefense = headStored.getDefense();
			headFireRes = headStored.getFireRes();
			headWaterRes = headStored.getWaterRes();
			headThunderRes = headStored.getThunderRes();
			headIceRes = headStored.getIceRes();
			headDragonRes = headStored.getDragonRes();
			defense += headDefense;
			fireRes += headFireRes;
			waterRes += headWaterRes;
			thunderRes += headThunderRes;
			iceRes += headIceRes;
			dragonRes += headDragonRes;
			setArmorLabels();
			if (!headStored.getSkill1().equalsIgnoreCase("none"))
				skillsHead += "\n" + headStored.getSkill1() + ", at level " + headStored.getSkill1Level();
			if (!headStored.getSkill2().equalsIgnoreCase("none"))
				skillsHead += "\n" + headStored.getSkill2() + ", at level " + headStored.getSkill2Level();
			skills = skillsCharm + "\n" + skillsHead + "\n" + skillsChest + "\n" + skillsArms + "\n" + skillsWaist + "\n" + skillsLegs;
			lblSkillsText.setText(skills);
			lblHead.setText(headStored.getName() + ", Def: " + headStored.getDefense());
		});	
		// Chest armor object listener
		saChest.amrStored.addListener((args, oldChest, newChest) ->
		{
			skillsChest = "-CHEST-";
			defense -= chestDefense;
			fireRes -= chestFireRes;
			waterRes -= chestWaterRes;
			thunderRes -= chestThunderRes;
			iceRes -= chestIceRes;
			dragonRes -= chestDragonRes;
			chestStored = newChest;
			chestDefense = chestStored.getDefense();
			chestFireRes = chestStored.getFireRes();
			chestWaterRes = chestStored.getWaterRes();
			chestThunderRes = chestStored.getThunderRes();
			chestIceRes = chestStored.getIceRes();
			chestDragonRes = chestStored.getDragonRes();
			defense += chestDefense;
			fireRes += chestFireRes;
			waterRes += chestWaterRes;
			thunderRes += chestThunderRes;
			iceRes += chestIceRes;
			dragonRes += chestDragonRes;
			setArmorLabels();
			if (!chestStored.getSkill1().equalsIgnoreCase("none"))
				skillsChest += "\n" + chestStored.getSkill1() + ", at level " + chestStored.getSkill1Level();
			if (!chestStored.getSkill2().equalsIgnoreCase("none"))
				skillsChest += "\n" + chestStored.getSkill2() + ", at level " + chestStored.getSkill2Level();
			skills = skillsCharm + "\n" + skillsHead + "\n" + skillsChest + "\n" + skillsArms + "\n" + skillsWaist + "\n" + skillsLegs;
			lblSkillsText.setText(skills);
			lblChest.setText(chestStored.getName() + ", Def: " + chestStored.getDefense());
		});
		// Arms armor object listener
		saArms.amrStored.addListener((args, oldArms, newArms) ->
		{
			skillsArms = "-ARMS-";
			defense -= armsDefense;
			fireRes -= armsFireRes;
			waterRes -= armsWaterRes;
			thunderRes -= armsThunderRes;
			iceRes -= armsIceRes;
			dragonRes -= armsDragonRes;
			armsStored = newArms;
			armsDefense = armsStored.getDefense();
			armsFireRes = armsStored.getFireRes();
			armsWaterRes = armsStored.getWaterRes();
			armsThunderRes = armsStored.getThunderRes();
			armsIceRes = armsStored.getIceRes();
			armsDragonRes = armsStored.getDragonRes();
			defense += armsDefense;
			fireRes += armsFireRes;
			waterRes += armsWaterRes;
			thunderRes += armsThunderRes;
			iceRes += armsIceRes;
			dragonRes += armsDragonRes;
			setArmorLabels();
			if (!armsStored.getSkill1().equalsIgnoreCase("none"))
				skillsArms += "\n" + armsStored.getSkill1() + ", at level " + armsStored.getSkill1Level();
			if (!armsStored.getSkill2().equalsIgnoreCase("none"))
				skillsArms += "\n" + armsStored.getSkill2() + ", at level " + armsStored.getSkill2Level();
			skills = skillsCharm + "\n" + skillsHead + "\n" + skillsChest + "\n" + skillsArms + "\n" + skillsWaist + "\n" + skillsLegs;
			lblSkillsText.setText(skills);
			lblArms.setText(armsStored.getName() + ", Def: " + armsStored.getDefense());
		});
		// Waist armor object listener
		saWaist.amrStored.addListener((args, oldWaist, newWaist) ->
		{
			skillsWaist = "-WAIST-";
			defense -= waistDefense;
			fireRes -= waistFireRes;
			waterRes -= waistWaterRes;
			thunderRes -= waistThunderRes;
			iceRes -= waistIceRes;
			dragonRes -= waistDragonRes;
			waistStored = newWaist;
			waistDefense = waistStored.getDefense();
			waistFireRes = waistStored.getFireRes();
			waistWaterRes = waistStored.getWaterRes();
			waistThunderRes = waistStored.getThunderRes();
			waistIceRes = waistStored.getIceRes();
			waistDragonRes = waistStored.getDragonRes();
			defense += waistDefense;
			fireRes += waistFireRes;
			waterRes += waistWaterRes;
			thunderRes += waistThunderRes;
			iceRes += waistIceRes;
			dragonRes += waistDragonRes;
			setArmorLabels();
			if (!waistStored.getSkill1().equalsIgnoreCase("none"))
				skillsWaist += "\n" + waistStored.getSkill1() + ", at level " + waistStored.getSkill1Level();
			if (!headStored.getSkill2().equalsIgnoreCase("none"))
				skillsWaist += "\n" + waistStored.getSkill2() + ", at level " + waistStored.getSkill2Level();
			skills = skillsCharm + "\n" + skillsHead + "\n" + skillsChest + "\n" + skillsArms + "\n" + skillsWaist + "\n" + skillsLegs;
			lblSkillsText.setText(skills);
			lblWaist.setText(waistStored.getName() + ", Def: " + waistStored.getDefense());
		});
		// Legs armor object listener
		saLegs.amrStored.addListener((args, oldLegs, newLegs) ->
		{
			skillsLegs = "-LEGS-";
			defense -= legsDefense;
			fireRes -= legsFireRes;
			waterRes -= legsWaterRes;
			thunderRes -= legsThunderRes;
			iceRes -= legsIceRes;
			dragonRes -= legsDragonRes;
			legsStored = newLegs;
			legsDefense = legsStored.getDefense();
			legsFireRes = legsStored.getFireRes();
			legsWaterRes = legsStored.getWaterRes();
			legsThunderRes = legsStored.getThunderRes();
			legsIceRes = legsStored.getIceRes();
			legsDragonRes = legsStored.getDragonRes();
			defense += legsDefense;
			fireRes += legsFireRes;
			waterRes += legsWaterRes;
			thunderRes += legsThunderRes;
			iceRes += legsIceRes;
			dragonRes += legsDragonRes;
			setArmorLabels();
			if (!legsStored.getSkill1().equalsIgnoreCase("none"))
				skillsLegs += "\n" + legsStored.getSkill1() + ", at level " + legsStored.getSkill1Level();
			if (!headStored.getSkill2().equalsIgnoreCase("none"))
				skillsLegs += "\n" + legsStored.getSkill2() + ", at level " + legsStored.getSkill2Level();
			skills = skillsCharm + "\n" + skillsHead + "\n" + skillsChest + "\n" + skillsArms + "\n" + skillsWaist + "\n" + skillsLegs;
			lblSkillsText.setText(skills);
			lblLegs.setText(legsStored.getName() + ", Def: " + legsStored.getDefense());
		});
		// Charm object listener
		sc.charmStored.addListener((args, oldCharm, newCharm) ->
		{
			lblSkillsText.setText("");
			skillsCharm = "-CHARM-";
			charmStored = newCharm;
			if (!charmStored.getSkill1().equalsIgnoreCase("none"))
				skillsCharm += "\n" + charmStored.getSkill1() + ", at level " + charmStored.getSkill1Level();
			if (!charmStored.getSkill2().equalsIgnoreCase("none"))
				skillsCharm += "\n" + charmStored.getSkill2() + ", at level " + charmStored.getSkill2Level();
			skills = skillsCharm + "\n" + skillsHead + "\n" + skillsChest + "\n" + skillsArms + "\n" + skillsWaist + "\n" + skillsLegs;
			lblSkillsText.setText(skills);
			lblCharm.setText(charmStored.getName() + ",\n" + charmStored.getSkill1() + ", " + charmStored.getSkill2());
		});
	}
	
	/**
	 * TODO
	 * 
	 * Constructor intended for use in loading an armorset pulled from the database.
	 * 
	 * @param wpn
	 * @param head
	 * @param chest
	 * @param arms
	 * @param waist
	 * @param legs
	 * @param charm
	 */
	public ArmorBuilder(Weapon wpn, Armor head, Armor chest, Armor arms, Armor waist, Armor legs, Charm charm)
	{
		wpnStored = wpn;
		headStored = head;
		chestStored = chest;
		armsStored = chest;
		waistStored = waist;
		legsStored = legs;
		charmStored = charm;
	}
	
	/**
	 * Helper method. Sets up the JavaFX for this class.
	 */
	private void setup()
	{
		root = new BorderPane();
		root.setCenter(new Label("Select one of the icons on the right to get started!"));

		lblHealth = new Label("Health: ");
		lblDefense = new Label("Defense: ");
		lblAttack = new Label("Attack: ");
		lblAffinity = new Label("Affinity: ");
		lblFireRes = new Label("Vs. Fire: ");
	    lblWaterRes = new Label("Vs. Water: ");
		lblThunderRes = new Label("Vs. Thunder: ");
		lblIceRes = new Label("Vs. Ice: ");
		lblDragonRes = new Label("Vs. Dragon: ");
		lblHealthVal = new Label("100");
		lblDefVal = new Label("0");
		lblAttackVal = new Label("0");
		lblAffinityVal = new Label("0");
		lblFireResVal = new Label("0");
		lblWaterResVal = new Label("0");
		lblThunderResVal = new Label("0");
		lblIceResVal = new Label("0");
		lblDragonResVal = new Label("0");
		gpTop = new GridPane();
		gpTop.add(lblHealth, 0, 0);
		gpTop.add(lblDefense, 0, 1);
		gpTop.add(lblAttack, 0, 2);
		gpTop.add(lblAffinity, 0, 3);
		gpTop.add(lblHealthVal, 1, 0);
		gpTop.add(lblDefVal, 1, 1);
		gpTop.add(lblAttackVal, 1, 2);
		gpTop.add(lblAffinityVal, 1, 3);
		gpTop.add(lblFireRes, 2, 0);
		gpTop.add(lblWaterRes, 2, 1);
		gpTop.add(lblThunderRes, 2, 2);
		gpTop.add(lblIceRes, 2, 3);
		gpTop.add(lblDragonRes, 2, 4);
		gpTop.add(lblFireResVal, 3, 0);
		gpTop.add(lblWaterResVal, 3, 1);
		gpTop.add(lblThunderResVal, 3, 2);
		gpTop.add(lblIceResVal, 3, 3);
		gpTop.add(lblDragonResVal, 3, 4);
		gpTop.setHgap(10);
		gpTop.setAlignment(Pos.CENTER_LEFT);
		gpTop.setPadding(new Insets(10, 10, 10, 10));
		lblHealth.setMinWidth(55);
		lblDefense.setMinWidth(55);
		lblAttack.setMinWidth(55);
		lblAffinity.setMinWidth(55);
		lblFireRes.setMinWidth(80);
		lblWaterRes.setMinWidth(80);
		lblThunderRes.setMinWidth(80);
		lblIceRes.setMinWidth(80);
		lblDragonRes.setMinWidth(80);
		lblHealthVal.setMinWidth(30);
		lblDefVal.setMinWidth(30);
		lblAttackVal.setMinWidth(30);
		lblFireResVal.setMinWidth(30);
		lblWaterResVal.setMinWidth(30);
		lblThunderResVal.setMinWidth(30);
		lblIceResVal.setMinWidth(30);
		lblDragonResVal.setMinWidth(30);
		
		
		lblSkillsText = new Label("No skills yet.");
		vbSkills = new VBox(lblSkillsText);
		spSkills = new ScrollPane(vbSkills);
		hbTop = new HBox(gpTop, spSkills);
		lblSkillsText.setWrapText(true);
		HBox.setMargin(spSkills, new Insets(10, 10, 10, 0));
		vbSkills.setAlignment(Pos.TOP_LEFT);
		spSkills.setPadding(new Insets(0, 10, 0, 10));
		spSkills.setFitToWidth(true);		spSkills.setPrefHeight(100);
		HBox.setHgrow(spSkills, Priority.ALWAYS);
		HBox.setHgrow(gpTop, Priority.ALWAYS);
		gpTop.setAlignment(Pos.CENTER);
		
		btWeapon = new Button("", new ImageView(new Image("cs317/project/mhw/ui/img/weapon/greatsword.png", 44, 44, true, false)));
		btHead = new Button("", new ImageView(new Image("cs317/project/mhw/ui/img/armor/head.png", 44, 44, true, false)));
		btChest = new Button("", new ImageView(new Image("cs317/project/mhw/ui/img/armor/chest.png", 44, 44, true, false)));
		btArms = new Button("", new ImageView(new Image("cs317/project/mhw/ui/img/armor/arms.png", 44, 44, true, false)));
		btWaist = new Button("", new ImageView(new Image("cs317/project/mhw/ui/img/armor/waist.png", 44, 44, true, false)));
		btLegs = new Button("", new ImageView(new Image("cs317/project/mhw/ui/img/armor/legs.png", 44, 44, true, false)));
		btCharm = new Button("", new ImageView(new Image("cs317/project/mhw/ui/img/charm.png", 44, 44, true, false)));
		lblWeapon = new Label("Select a weapon...");
		lblHead = new Label("Select head armor...");
		lblChest = new Label("Select chest armor...");
		lblArms = new Label("Select arms armor...");
		lblWaist = new Label("Select waist armor...");
		lblLegs = new Label("Select legs armor...");
		lblCharm = new Label("Select a charm...");
		gpRight = new GridPane();
		vbRight = new VBox(gpRight);
		gpRight.add(btWeapon, 0, 0);
		gpRight.add(lblWeapon, 1, 0);
		gpRight.add(btHead, 0, 1);
		gpRight.add(lblHead, 1, 1);
		gpRight.add(btChest, 0, 2);
		gpRight.add(lblChest, 1, 2);
		gpRight.add(btArms, 0, 3);
		gpRight.add(lblArms, 1, 3);
		gpRight.add(btWaist, 0, 4);
		gpRight.add(lblWaist, 1, 4);
		gpRight.add(btLegs, 0, 5);
		gpRight.add(lblLegs, 1, 5);
		gpRight.add(btCharm, 0, 6);
		gpRight.add(lblCharm, 1, 6);
		btWeapon.setId("button-special");
		btHead.setId("button-special");
		btChest.setId("button-special");
		btArms.setId("button-special");
		btWaist.setId("button-special");
		btLegs.setId("button-special");
		btCharm.setId("button-special");
		lblWeapon.setWrapText(true);
		lblHead.setWrapText(true);
		lblChest.setWrapText(true);
		lblArms.setWrapText(true);
		lblWaist.setWrapText(true);
		lblLegs.setWrapText(true);
		lblCharm.setWrapText(true);
		lblWeapon.setId("smallish-label");
		lblHead.setId("smallish-label");
		lblChest.setId("smallish-label");
		lblArms.setId("smallish-label");
		lblWaist.setId("smallish-label");
		lblLegs.setId("smallish-label");
		lblCharm.setId("smallish-label");
		gpRight.setPadding(new Insets(0, 10, 0, 0));
		gpRight.setStyle("-fx-background-color: #2B2B2B");
		gpRight.setVgap(6);
		gpRight.setHgap(20);
		vbRight.setPadding(new Insets(0, 10, 0, 0));
		vbRight.setAlignment(Pos.CENTER);
		vbRight.setMaxWidth(220);
		vbRight.setMinWidth(220);

		root.setTop(hbTop);
		root.setRight(vbRight);
	}
	
	/**
	 * Called when labels need to be updated.
	 */
	private void setArmorLabels()
	{
		lblDefVal.setText(Integer.toString(defense));
		lblFireResVal.setText(Integer.toString(fireRes));
		lblWaterResVal.setText(Integer.toString(waterRes));
		lblThunderResVal.setText(Integer.toString(thunderRes));
		lblIceResVal.setText(Integer.toString(iceRes));
		lblDragonResVal.setText(Integer.toString(dragonRes));
	}
	
	public BorderPane getRoot(){
		return root;
	}
}