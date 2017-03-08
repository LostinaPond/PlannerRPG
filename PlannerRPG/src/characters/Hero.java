package characters;

import java.io.IOException;
import java.util.ArrayList;

import items.Item;

public class Hero extends Character {

	ArrayList<Item> inventory = new ArrayList<Item>();
	private int RP;
	
	public int points = 15;

	public int getRP(){
		return RP;
	}
	
	public void setRP(int RP){
		this.RP = this.RP + RP;
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
		baseHP = (rand.nextInt(30) + 1) * 2;
	}

	public int getCurrentHP() {
		return currentHP;
	}

	public void setCurrentHP(int i) {
		currentHP = i;
	}
	
	public void setPointsLeft(int i){
		points = points - i;
	}
	
	public void levelUpStr(){
		int str = getStrBase();
		str = str + 1;
		setStrMod();
	}
	
	public void levelUpDef(){
		int def = getDefBase();
		def = def + 1;
		setDefMod();
	}
	
	public void levelUpLuck(){
		int luck = getLuckBase();
		if(luck < 10){
		luck = luck + 1;
		setLuckMod();
		}
	}

	public void setValues() {
		try {
			setStrBase(lib.ConsoleIO.promptForInt(
					"You have " + points + " points left to spend, how many would you like to add to strength?"
							+ "\n The base is 1 point, you can add between 0 and 9 points to any stat, if you have enough points left.",
					0, 9));
			setDefBase(lib.ConsoleIO.promptForInt(
					"You have " + points + " points left to spend, how many would you like to add to defense?"
							+ "\n The base is 1 point, you can add between 0 and 9 points to any stat, if you have enough points left.",
					0, 9));
			setLuckBase(lib.ConsoleIO.promptForInt(
					"You have " + points + " points left to spend, how many would you like to add to luck?"
							+ "\n The base is 1 point, you can add between 0 and 9 points to any stat, if you have enough points left.",
					0, 9));
			setBaseHP();
			setCurrentHP(baseHP);
			setStrMod();
			setDefMod();
			setLuckMod();
		} catch (IOException ioe) {

		}
	}
	
	public void addToInv(Item e){
		inventory.add(e);
	}
	
	public void showInventory(){
		for (Item i : inventory) {
			System.out.println(i);
			System.out.println();
		}
	}

	@Override
	public int attack() {
		int damage = getStrMod() + equippedWeapon.dealDamage();
		return damage;
	}

	@Override
	public int takeDamage(int damage, int armorDR, int defense) {
		int hp = getCurrentHP();
		hp = hp - (damage - (armorDR + defense));
		return hp;
	}

}