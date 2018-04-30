package cs317.project.mhw.ui;

import cs317.project.mhw.item.Armor;
import cs317.project.mhw.item.Charm;
import cs317.project.mhw.item.Item;
import cs317.project.mhw.item.Monster;
import cs317.project.mhw.item.PalicoArmor;
import cs317.project.mhw.item.PalicoWeapon;
import cs317.project.mhw.item.Quest;
import cs317.project.mhw.item.Weapon;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

/**
 * 
 * @author Tyler Procko
 * @date January-May 2018
 * 
 * An instance of this class will display the specific info about whatever
 * item type it is instantiated as. There are many constructors for it. Each
 * page consists of the item name, a generic image for the item type, and a
 * long block of item-specific info.
 *
 */

public class ItemPage 
{
	private Label lblName;
	private ImageView imgVw;
	private Label lblInfo;
	private VBox vbContent;
	private BorderPane root;
	
	/**
	 * Constructor for item type.
	 * 
	 * @param item
	 */
	public ItemPage(Item item)
	{
		lblName = new Label(item.getName());
		
		imgVw = new ImageView(new Image("cs317/project/mhw/ui/img/unknown.png", 50, 50, true, false));
		
		String itemInfo =
				"Capacity: " + item.getCapacity() +"\n\n"
				+ item.getDescription();
		
		setupItemView(itemInfo);
	}
	
	/**
	 * Constructor for weapon type.
	 * 
	 * @param wpn
	 */
	public ItemPage(Weapon wpn)
	{
		lblName = new Label(wpn.getName());
		
		if (wpn.getType().equalsIgnoreCase("greatsword"))
			imgVw = new ImageView(new Image("cs317/project/mhw/ui/img/weapon/greatsword.png", 50, 50, true, false));
		else if (wpn.getType().equalsIgnoreCase("longsword"))
			imgVw = new ImageView(new Image("cs317/project/mhw/ui/img/weapon/longsword.png", 50, 50, true, false));
		else if (wpn.getType().equalsIgnoreCase("swordandshield"))
			imgVw = new ImageView(new Image("cs317/project/mhw/ui/img/weapon/sns.png", 50, 50, true, false));
		else if (wpn.getType().equalsIgnoreCase("dualblades"))
			imgVw = new ImageView(new Image("cs317/project/mhw/ui/img/weapon/dualblades.png", 50, 50, true, false));
		else if (wpn.getType().equalsIgnoreCase("switchaxe"))
			imgVw = new ImageView(new Image("cs317/project/mhw/ui/img/weapon/switchaxe.png", 50, 50, true, false));
		else if (wpn.getType().equalsIgnoreCase("chargeblade"))
			imgVw = new ImageView(new Image("cs317/project/mhw/ui/img/weapon/chargeblade.png", 50, 50, true, false));
		else if (wpn.getType().equalsIgnoreCase("lance"))
			imgVw = new ImageView(new Image("cs317/project/mhw/ui/img/weapon/lance.png", 50, 50, true, false));
		else if (wpn.getType().equalsIgnoreCase("gunlance"))
			imgVw = new ImageView(new Image("cs317/project/mhw/ui/img/weapon/gunlance.png", 50, 50, true, false));
		else if (wpn.getType().equalsIgnoreCase("insectglaive"))
			imgVw = new ImageView(new Image("cs317/project/mhw/ui/img/weapon/insectglaive.png", 50, 50, true, false));
		else if (wpn.getType().equalsIgnoreCase("kinsect"))
			imgVw = new ImageView(new Image("cs317/project/mhw/ui/img/weapon/kinsect_cut.png", 50, 50, true, false));
		//else if (wpn.getType().equalsIgnoreCase("kinsect") && wpn.getAllInfo().contains("blunt"))
			//imgVw = new ImageView(new Image("cs317/project/mhw/ui/img/weapon/kinsect_blunt.png", 50, 50, true, false));
		else if (wpn.getType().equalsIgnoreCase("hammer"))
			imgVw = new ImageView(new Image("cs317/project/mhw/ui/img/weapon/hammer.png", 50, 50, true, false));
		else if (wpn.getType().equalsIgnoreCase("huntinghorn"))
			imgVw = new ImageView(new Image("cs317/project/mhw/ui/img/weapon/huntinghorn.png", 50, 50, true, false));
		else if (wpn.getType().equalsIgnoreCase("lightbowgun"))
			imgVw = new ImageView(new Image("cs317/project/mhw/ui/img/weapon/lbg.png", 50, 50, true, false));
		else if (wpn.getType().equalsIgnoreCase("heavybowgun"))
			imgVw = new ImageView(new Image("cs317/project/mhw/ui/img/weapon/hbg.png", 50, 50, true, false));
		else if (wpn.getType().equalsIgnoreCase("bow"))
			imgVw = new ImageView(new Image("cs317/project/mhw/ui/img/weapon/bow.png", 50, 50, true, false));
		
		setupItemView(wpn.getAllInfo());
	}
	
	/**
	 * Constructor for armor type.
	 * 
	 * @param amr
	 */
	public ItemPage(Armor amr)
	{
		lblName = new Label(amr.getName());
		
		if (amr.getType().equalsIgnoreCase("head"))
			imgVw = new ImageView(new Image("cs317/project/mhw/ui/img/armor/head.png", 50, 50, true, false));
		else if (amr.getType().equalsIgnoreCase("chest"))
			imgVw = new ImageView(new Image("cs317/project/mhw/ui/img/armor/chest.png", 50, 50, true, false));
		else if (amr.getType().equalsIgnoreCase("arms"))
			imgVw = new ImageView(new Image("cs317/project/mhw/ui/img/armor/arms.png", 50, 50, true, false));
		else if (amr.getType().equalsIgnoreCase("waist"))
			imgVw = new ImageView(new Image("cs317/project/mhw/ui/img/armor/waist.png", 50, 50, true, false));
		else if (amr.getType().equalsIgnoreCase("legs"))
			imgVw = new ImageView(new Image("cs317/project/mhw/ui/img/armor/legs.png", 50, 50, true, false));
		
		String armorInfo = 
			"Rarity: " + amr.getRarity() + "\n\n"
				
			+ "Defense: " + amr.getDefense() + "\n"
			+ "Fire resistance: " + amr.getFireRes() + "\n"
			+ "Water resistance: " + amr.getWaterRes() + "\n"
			+ "Thunder resistance: " + amr.getThunderRes() + "\n"
			+ "Ice resistance: " + amr.getIceRes() + "\n"
			+ "Dragon resistance: " + amr.getDragonRes() + "\n\n"
			
			+ "Level 1 gem slots: " + amr.getLevel1Gem() + "\n"
			+ "Level 2 gem slots: " + amr.getLevel2Gem() + "\n"
			+ "Level 3 gem slots: " + amr.getLevel3Gem() + "\n\n"
			
			+ "Skill one: " + amr.getSkill1() + ", at level " + amr.getSkill1Level() + "\n"
			+ "Skill two: " + amr.getSkill2() + ", at level " + amr.getSkill2Level() + "\n\n"
			
			+ "Two-piece set bonus: " + amr.getSetBonus2Pc() + "\n"
			+ "Three-piece set bonus: " + amr.getSetBonus3Pc() + "\n"
			+ "Four-piece set bonus: " + amr.getSetBonus4Pc() + "\n";

		setupItemView(armorInfo);
 	}
	
	/**
	 * Constructor for charm type.
	 * 
	 * @param chm
	 */
	public ItemPage(Charm chm)
	{
		lblName = new Label(chm.getName());
	
		imgVw = new ImageView(new Image("cs317/project/mhw/ui/img/charm.png", 150, 150, true, false));
		
		String chmInfo = 
			"Skill one: " + chm.getSkill1() + ", at level " + chm.getSkill1Level() + "\n"
			+ "Skill two: " + chm.getSkill2() + ", at level " + chm.getSkill2Level() + "\n";

		setupItemView(chmInfo);
	}

	/**
	 * Constructor for palico armor type.
	 * 
	 * @param plcamr
	 */
	public ItemPage(PalicoArmor plcamr)
	{
		lblName = new Label(plcamr.getName());
	
		if (plcamr.getType().equalsIgnoreCase("head"))
			imgVw = new ImageView(new Image("cs317/project/mhw/ui/img/palico_head.jpg", 50, 50, true, false));
		else if (plcamr.getType().equalsIgnoreCase("chest"))
			imgVw = new ImageView(new Image("cs317/project/mhw/ui/img/palico_chest.jpg", 50, 50, true, false));
		
		String plcAmrInfo = 
				"Type: " + plcamr.getType() + "\n"
				+ "Rarity: " + plcamr.getRarity() + "\n\n"
				+ "Defense: " + plcamr.getDefense() + "\n"
				+ "Fire resistance: " + plcamr.getVsFire() + "\n"
				+ "Water resistance: " + plcamr.getVsWater() + "\n"
				+ "Thunder resistance: " + plcamr.getVsThunder() + "\n"
				+ "Ice resistance: " + plcamr.getVsIce() + "\n"
				+ "Dragon resistance: " + plcamr.getVsDragon() + "\n";
				
		setupItemView(plcAmrInfo);
	}
	
	/**
	 * Constructor for palico weapon type.
	 * @param plcwpn
	 */
	public ItemPage(PalicoWeapon plcwpn)
	{
		lblName = new Label(plcwpn.getName());
	
		imgVw = new ImageView(new Image("cs317/project/mhw/ui/img/palico_weapon.jpg", 50, 50, true, false));

		String plcWpnInfo = 
				"Rarity: " + plcwpn.getRarity() + "\n\n"
				+ "Melee attack: " + plcwpn.getAttackMelee() + "\n"
				+ "Ranged attack: " + plcwpn.getAttackRanged() + "\n"
				+ "Attack type: " + plcwpn.getAttackType() + "\n\n"
				
				+ "Element: " + plcwpn.getElementType() + ", with a damage of " + plcwpn.getElementDamage() + "\n\n"
				+ "Affinity: " + plcwpn.getAffinity() + "%\n"
				+ "Defense bonus: " + plcwpn.getDefenseBonus() + "\n"
				+ "Elderseal: " + plcwpn.getElderSeal() + "\n"; 
		
		setupItemView(plcWpnInfo);
	}
	
	/**
	 * Constructor for monster type.
	 * 
	 * @param mon
	 */
	public ItemPage(Monster mon)
	{
		lblName = new Label(mon.getName());
		
		imgVw = new ImageView(new Image("cs317/project/mhw/ui/img/unknown.png", 50, 50, true, false));

		String monsterInfo = 
				"Type: " + mon.getType() + "\n\n"
				+ "Poison weakness: " + mon.getPoisonWeakness() + "\n"
				+ "Sleep weakness: " + mon.getSleepWeakness() + "\n"
				+ "Paralysis weakness: " + mon.getParalysisWeakness() + "\n"
				+ "Blast weakness: " + mon.getBlastWeakness() + "\n"
				+ "Stun weakness: " + mon.getStunWeakness() + "\n";

		setupItemView(monsterInfo);
	}
	
	/**
	 * Constructor for quest type.
	 * @param qst
	 */
	public ItemPage(Quest qst)
	{
		lblName = new Label(qst.getName());
		
		imgVw = new ImageView(new Image("cs317/project/mhw/ui/img/unknown.png", 50, 50, true, false));

		String questInfo = 
				"Level: " + qst.getLevel() + "\n"
				+ "HR: " + qst.getHr() + "\n\n"
				+ "Target Monster: " + qst.getTargetMonster() + "\n\n"
				+ "Reward: " + qst.getRewardMoney() + "z\n";
		
		setupItemView(questInfo);
	}
	
	/**
	 * Helper method. Sets up the JavaFX for any type of item.
	 * 
	 * @param lbl
	 */
	private void setupItemView(String lbl)
	{
		lblName.setId("news-label");
		
		lblInfo = new Label(lbl);
		lblInfo.setTextAlignment(TextAlignment.CENTER);
		lblInfo.setWrapText(true);
		lblInfo.setPadding(new Insets(0, 10, 0, 10));

		vbContent = new VBox(10, lblName, imgVw, lblInfo);

		BorderPane.setMargin(vbContent, new Insets(50, 60, 50, 60));
		vbContent.setAlignment(Pos.CENTER);
		vbContent.setId("vbox-item");
		root = new BorderPane(vbContent);
	}
	
	public BorderPane getRoot() {
		return root;
	}
}