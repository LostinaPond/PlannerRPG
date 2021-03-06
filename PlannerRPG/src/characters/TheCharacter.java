package characters;

import java.util.Random;

import items.Accessory;
import items.Armor;
import items.Weapon;

public abstract class TheCharacter {

	protected int strBase;
	protected int defBase;
	protected int luckBase;
	protected int strMod = 0;
	protected int defMod = 0;
	protected int luckMod = 0;
	protected int baseHP;
	protected int currentHP;
	protected Weapon equippedWeapon;
	protected Armor equippedArmor;
	protected Accessory equippedAccessory;

	public Random rand = new Random();

	public Accessory getEquippedAccessory(){
		return equippedAccessory;
	}
	
	public void setEquippedAccessory(Accessory a){
		this.equippedAccessory = a;
	}
	
	public Weapon getEquippedWeapon(){
		return equippedWeapon;
	}
	
	public void setEquippedWeapon(Weapon w){
		this.equippedWeapon = w;
	}
	
	public Armor getEquippedArmor(){
		return equippedArmor;
	}
	
	public void setEquippedArmor(Armor a){
		this.equippedArmor = a;
	}
	
	public int getStrBase() {
		return strBase;
	}

	public void setStrBase() {
		int str = rand.nextInt(27) + 3;
		strBase = str;
	}

	public int getDefBase() {
		return defBase;
	}

	public void setDefBase() {
		int str = rand.nextInt(27) + 3;
		defBase = str;
	}

	public int getLuckBase() {
		return luckBase;
	}

	public void setLuckBase() {
		int str = rand.nextInt(27) + 3;
		luckBase = str;
	}

	public int getStrMod() {
		return strMod;
	}

	public void setStrMod() {
		strMod = (getStrBase() - 10) / 2;
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
		luckMod = (getLuckBase() - 10) / 2;
	}

	public int getBaseHP() {
		return baseHP;
	}

	public void setBaseHP() {
		baseHP = strBase * 10;
	}

	public int getCurrentHP() {
		return currentHP;
	}

	public void setCurrentHP(int i) {
		currentHP = i;
	}

	public void setValues() {
		setStrBase();
		setDefBase();
		setLuckBase();
		setBaseHP();
		setCurrentHP(baseHP);
		setStrMod();
		setDefMod();
		setLuckMod();
	}

	public abstract int attack();

	public abstract int takeDamage(int damage);
}
