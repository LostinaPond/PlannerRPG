package items;

import java.util.Random;

import gameEnums.Rarity;

public class Weapon extends Item {

	private int damageMin;
	private int damageMax;
	private int value;
	private String name;
	private String mod;
	private Rarity rarity;

	public int getDamageMin() {
		return damageMin;
	}

	public void setDamageMin(Rarity rarity) {
		int output = 0;

		if (rarity.equals(Rarity.Trash)) {

			output = rand.nextInt(5) + 1;

		} else if (rarity.equals(Rarity.Common)) {

			output = rand.nextInt(10) + 1;

		} else if (rarity.equals(Rarity.Rare)) {

			output = rand.nextInt(20) + 1;

		} else if (rarity.equals(Rarity.Epic)) {

			output = rand.nextInt(30) + 1;

		} else {

			output = rand.nextInt(40) + 1;

		}

		damageMin = output;
	}

	public int getDamageMax() {
		return damageMax;
	}

	public void setDamageMax(Rarity rarity) {
		int output = 0;

		if (rarity.equals(Rarity.Trash)) {

			output = rand.nextInt(5) + getDamageMin();

		} else if (rarity.equals(Rarity.Common)) {

			output = rand.nextInt(10) + getDamageMin();

		} else if (rarity.equals(Rarity.Rare)) {

			output = rand.nextInt(15) + getDamageMin();

		} else if (rarity.equals(Rarity.Epic)) {

			output = rand.nextInt(20) + getDamageMin();

		} else {

			output = rand.nextInt(30) + getDamageMin();

		}

		damageMax = output;
	}

	public int getValue() {
		return value;
	}

	public void setValue(Rarity rarity) {
		int output = 0;

		if (rarity.equals(Rarity.Trash)) {
			output = rand.nextInt(50) + 1;
		} else if (rarity.equals(Rarity.Common)) {
			output = rand.nextInt(100) + 1;
		} else if (rarity.equals(Rarity.Rare)) {
			output = rand.nextInt(200) + 1;
		} else if (rarity.equals(Rarity.Epic)) {
			output = rand.nextInt(400) + 1;
		} else {
			output = rand.nextInt(800) + 1;
		}

		value = output;
	}

	public String getName() {
		return name;
	}

	public void setName() {
		String[] options = { "Battle Axe", "Broad Sword", "Long Sword", "Dagger", "Bow", "Greatsword", "Greataxe",
				"Great Hammer", "Mace", "Sling" };

		String output = options[rand.nextInt(options.length)];

		name = getMod() + " " + output;
	}

	public Rarity getRarity() {
		return rarity;
	}

	public void setRarity(Rarity output) {
		rarity = output;
	}

	public Rarity whatRarity() {
		Random rand = new Random();
		int i = rand.nextInt(100) + 1;
		int j = rand.nextInt(100) + 1;
		Rarity output = Rarity.Common;

		if (i <= 70) {
			if (j <= 70) {
				output = Rarity.Common;
			} else {
				output = Rarity.Trash;
			}
		} else {
			if (j <= 70) {
				output = Rarity.Rare;
			} else if (j > 70 && j <= 95) {
				output = Rarity.Epic;
			} else {
				output = Rarity.Legendary;
			}
		}

		return output;
	}

	public Rarity whatRarity(Rarity rarity, int percent) {
		Random rand = new Random();
		int i = rand.nextInt(100) + percent;
		int j = rand.nextInt(100) + percent;
		Rarity output = rarity;

		if (i <= 70) {
			output = rarity;
		} else {
			if (j <= 70) {
				output = Rarity.Rare;
			} else if (j > 95) {
				output = Rarity.Legendary;
			} else {
				output = Rarity.Epic;
			}
		}
		return output;
	}

	public String getMod() {
		return mod;
	}

	public void setMod(Rarity rarity) {
		String output = null;
		if (rarity.equals(Rarity.Trash)) {

			String[] options = { "Broken", "Tattered", "Destroyed" };

			output = options[rand.nextInt(options.length)];
		} else if (rarity.equals(Rarity.Common)) {

			String[] options = { "Old", "Frail", "Rusty", "Damaged" };

			output = options[rand.nextInt(options.length)];
		} else if (rarity.equals(Rarity.Rare)) {

			String[] options = { "New", "Fancy", "Magic", "Reinforced" };

			output = options[rand.nextInt(options.length)];
		} else if (rarity.equals(Rarity.Epic)) {

			String[] options = { "Strong", "Expert", "Enchanted" };

			output = options[rand.nextInt(options.length)];
		} else {

			String[] options = { "Legendary", "Mythical" };

			output = options[rand.nextInt(options.length)];
		}

		mod = output;
	}

	public void setValues() {
		setRarity(whatRarity());
		setDamageMin(getRarity());
		setDamageMax(getRarity());
		setMod(getRarity());
		setName();
		setValue(getRarity());
	}

	public void setValues(Rarity rarity, int percent) {
		setRarity(whatRarity(rarity, percent));
		setDamageMin(getRarity());
		setDamageMax(getRarity());
		setMod(getRarity());
		setName();
		setValue(getRarity());
	}

	public Weapon() {
		setValues();
		this.mod = getMod();
		this.name = getName();
		this.value = getValue();
		this.damageMin = getDamageMin();
		this.damageMax = getDamageMax();
	}

	public Weapon(Rarity rarity, int percent) {
		setValues(rarity, percent);
		this.rarity = getRarity();
		this.mod = getMod();
		this.name = getName();
		this.value = getValue();
		this.damageMin = getDamageMin();
		this.damageMax = getDamageMax();
	}

	public int dealDamage() {
		int damage = rand.nextInt(getDamageMax()) + getDamageMin();
		return damage;
	}

	@Override
	public String toString() {
		String itemInfo = "(Weapon) Name: " + getName() + "\nDamage Range: " + getDamageMin() + "-" + getDamageMax()
				+ "\nRarity: " + getRarity() + "\nValue: " + getValue() + "RP";
		return itemInfo;
	}

}
