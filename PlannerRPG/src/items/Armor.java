package items;

import java.util.Random;

import gameEnums.Rarity;

public class Armor extends Item {
	private int armorRating;
	private Rarity rarity;

	public int getArmorRating() {
		return armorRating;
	}

	public void setArmorRating(Rarity rarity) {
		int output = 0;

		if (rarity.equals(Rarity.Trash)) {
			output = rand.nextInt(8) + -2;
		} else if (rarity.equals(Rarity.Common)) {
			output = rand.nextInt(8) + 1;
		} else if (rarity.equals(Rarity.Rare)) {
			output = rand.nextInt(6) + 8;
		} else if (rarity.equals(Rarity.Epic)) {
			output = rand.nextInt(12) + 8;
		} else {
			output = rand.nextInt(24) + 8;
		}

		armorRating = output;
	}

	public int getValue() {
		return value;
	}

	public void setValue(Rarity rarity) {
		int output = 0;

		if (rarity.equals(Rarity.Trash)) {
			output = rand.nextInt(140) + 10;
		} else if (rarity.equals(Rarity.Common)) {
			output = rand.nextInt(290) + 10;
		} else if (rarity.equals(Rarity.Rare)) {
			output = rand.nextInt(590) + 10;
		} else if (rarity.equals(Rarity.Epic)) {
			output = rand.nextInt(1190) + 10;
		} else {
			output = rand.nextInt(2190) + 10;
		}

		value = output;
	}

	public String getName() {
		return name;
	}

	public void setName() {
		String[] options = { "Leather Armor", "Iron Armor", "Steel Armor", "Adamentine Armor", "Orchalcum Armor",
				"Dragon Bone Armor" };

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

			String[] options = { "Broken", "Tattered", "Destroyed", "Rusted" };

			output = options[rand.nextInt(options.length)];
		} else if (rarity.equals(Rarity.Common)) {

			String[] options = { "Old", "Frail", "Worn", "Damaged" };

			output = options[rand.nextInt(options.length)];
		} else if (rarity.equals(Rarity.Rare)) {

			String[] options = { "New", "Ornate", "Magic", "Reinforced" };

			output = options[rand.nextInt(options.length)];
		} else if (rarity.equals(Rarity.Epic)) {

			String[] options = { "Adamant", "Steadfast", "Enchanted" };

			output = options[rand.nextInt(options.length)];
		} else {

			String[] options = { "Legendary", "Mythical" };

			output = options[rand.nextInt(options.length)];
		}

		mod = output;
	}

	public void setValues() {
		setRarity(whatRarity());
		setArmorRating(getRarity());
		setMod(getRarity());
		setName();
		setValue(getRarity());
	}

	public void setValues(Rarity rarity, int percent) {
		setRarity(whatRarity(rarity, percent));
		setArmorRating(getRarity());
		setMod(getRarity());
		setName();
		setValue(getRarity());
	}

	public Armor() {
		setValues();
		this.rarity = getRarity();
		this.mod = getMod();
		this.name = getName();
		this.value = getValue();
		this.armorRating = getArmorRating();
	}

	public Armor(Rarity rarity, int percent) {
		setValues(rarity, percent);
		this.rarity = getRarity();
		this.mod = getMod();
		this.name = getName();
		this.value = getValue();
		this.armorRating = getArmorRating();
	}

	@Override
	public String toString() {
		String itemInfo = "Name: " + getName() + "\nArmor Rating: " + getArmorRating() + "\nRarity: " + getRarity()
				+ "\nValue: " + getValue();
		return itemInfo;
	}

}
