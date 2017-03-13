package models;

import java.io.Serializable;
import java.util.ArrayList;

import org.joda.time.LocalDate;

import characters.TheCharacter;
import items.Item;
import lib.ConsoleIO;

public class User extends TheCharacter implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String name;
	private ArrayList<Task> taskList = new ArrayList<>();
	private ArrayList<LocalDate> dates = new ArrayList<>();
	private ArrayList<Item> inventory = new ArrayList<Item>();
	private int RP;
	public int points = 15;

	public User(String name) {
		setValues();
		setRP(0);
		this.name = name;
	}
	
	public ArrayList<Item> getInventory(){
		return inventory;
	}
	
	public void setInventory(ArrayList<Item> i){
		inventory = i;
	}

	public ArrayList<Task> getTaskList() {
		return taskList;
	}

	public void setTaskList(ArrayList<Task> taskList) {
		this.taskList = taskList;
	}

	public ArrayList<LocalDate> getDates() {
		return dates;
	}

	public void setDates(ArrayList<LocalDate> dates) {
		this.dates = dates;
	}

	public void addToTaskList(Task e) {
		taskList.add(e);
	}

	public void addToDates(LocalDate e) {
		dates.add(e);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void addToInv(Item e) {
		inventory.add(e);
	}

	public void showInventory() {
		if (inventory.isEmpty()) {
			System.out.println("There is nothing in your inventory!");
		} else {
			for (Item i : inventory) {
				System.out.println(i);
				System.out.println();
			}
		}
	}
	

	public int getRP() {
		return RP;
	}

	public void setRP(int RP) {
		this.RP = RP;
	}

	public int getStrBase() {
		return strBase;
	}

	public void setStrBase(int i) {
		int str = 1 + i;
		strBase = str;
		setPointsLeft(i);
	}

	public int getDefBase() {
		return defBase;
	}

	public void setDefBase(int i) {
		int def = 1 + i;
		defBase = def;
		setPointsLeft(i);
	}

	public int getLuckBase() {
		return luckBase;
	}

	public void setLuckBase(int i) {
		int luck = 1 + i;
		luckBase = luck;
		setPointsLeft(i);
	}

	public int getStrMod() {
		return strMod;
	}

	public void setStrMod() {
		strMod = getStrBase() - 5;
	}

	public int getDefMod() {
		return defMod;
	}

	public void setDefMod() {
		defMod = getDefBase();
	}

	public int getLuckMod() {
		return luckMod;
	}

	public void setLuckMod() {
		luckMod = getLuckBase() - 5;
	}

	public int getStrength() {
		return strBase + strMod;
	}

	public int getDexterity() {
		return defBase + defMod;
	}

	public int getIntelligence() {
		return luckBase + luckMod;
	}

	public int getBaseHP() {
		return baseHP;
	}

	public void setBaseHP() {
		baseHP = 100;
	}

	public int getCurrentHP() {
		return currentHP;
	}

	public void setCurrentHP(int i) {
		if (i > getBaseHP()) {
			currentHP = getBaseHP();
		} else if (i < 0) {
			currentHP = 0;
		} else {
			currentHP = i;
		}
	}

	public void setPointsLeft(int i) {
		points = points - i;
	}

	public void levelUpStr() {
		int str = getStrBase();
		str = str + 1;
		setStrMod();
	}

	public void levelUpDef() {
		int def = getDefBase();
		def = def + 1;
		setDefMod();
	}

	public void levelUpLuck() {
		int luck = getLuckBase();
		if (luck < 10) {
			luck = luck + 1;
			setLuckMod();
		}
	}

	public void setValues() {
		setStrBase(ConsoleIO.promptForInt(
				"You have " + points + " points left to spend, how many would you like to add to strength?"
						+ "\n The base is 1 point, you can add between 0 and 9 points to any stat, if you have enough points left.",
				0, 9));
		setDefBase(ConsoleIO.promptForInt(
				"You have " + points + " points left to spend, how many would you like to add to defense?"
						+ "\n The base is 1 point, you can add between 0 and 9 points to any stat, if you have enough points left.",
				0, 9));
		setLuckBase(ConsoleIO.promptForInt(
				"You have " + points + " points left to spend, how many would you like to add to luck?"
						+ "\n The base is 1 point, you can add between 0 and 9 points to any stat, if you have enough points left.",
				0, 9));
		setBaseHP();
		setCurrentHP(baseHP);
		setStrMod();
		setDefMod();
		setLuckMod();
	}

	@Override
	public int takeDamage(int damage) {
		int hp = 0;
		if (damage >= 0) {
			hp = getCurrentHP() - damage;
		}
		return hp;
	}

	@Override
	public int attack() {
		int damage = 0;
		if(getEquippedWeapon() != null){
			damage = getStrMod() + getEquippedWeapon().dealDamage();
		} else {
			System.out.println("You don't have a weapon! You should run.");
		}
		return damage;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getName() + "'s Stats: ");
		sb.append("\n\tCurrent RP: " + getRP());
		sb.append("\n\tCurrent HP: " + getCurrentHP() + "/" + getBaseHP());
		sb.append("\n\tStrength: " + getStrength());
		sb.append("\n\tDefense: " + getDexterity());
		sb.append("\n\tLuck: " + getIntelligence());
		if (equippedWeapon == null) {
			sb.append("\n\tEquipped Weapon: None");
		} else {
			sb.append("\n\tEquipped Weapon: " + getEquippedWeapon().getName());
			sb.append("\n\t\tWeapon Mod: " + getEquippedWeapon().getMod());
			sb.append(
					"\n\t\tDamage Range: " + getEquippedWeapon().getDamageMin() + " - " + getEquippedWeapon().getDamageMax());
			sb.append("\n\t\tValue: " + getEquippedWeapon().getValue());
			sb.append("\n\t\tRarity: " + getEquippedWeapon().getRarity());
		}
		if (equippedArmor == null) {
			sb.append("\n\tEquipped Armor: None");
		} else {
			sb.append("\n\tEquipped Armor: " + getEquippedArmor().getName());
			sb.append("\n\t\tWeapon Mod: " + getEquippedArmor().getMod());
			sb.append("\n\t\tArmor Rating: " + getEquippedArmor().getArmorRating());
			sb.append("\n\t\tValue: " + getEquippedArmor().getValue());
			sb.append("\n\t\tRarity: " + getEquippedArmor().getRarity());
		}
		if (equippedAccessory == null) {
			sb.append("\n\tEquipped Accessory: None");
		} else {
			sb.append("\n\tEquipped Armor: " + getEquippedAccessory().getName());
			sb.append("\n\t\tWeapon Mod: " + getEquippedAccessory().getMod());
			sb.append("\n\t\tValue: " + getEquippedAccessory().getValue());
			sb.append("\n\t\tRarity: " + getEquippedAccessory().getRarity());
		}
		return sb.toString();
	}
}
