package items;

import java.util.Random;

import gameEnums.Rarity;

public class Accessory extends Item {
	private Rarity rarity;

	public int getValue() {
		return value;
	}

	public void setValue(Rarity rarity) {
		int output = 0;

		if (rarity.equals(Rarity.Rare)) {
			output = rand.nextInt(50) + 50;
		} else if (rarity.equals(Rarity.Epic)) {
			output = rand.nextInt(150) + 50;
		} else {
			output = rand.nextInt(250) + 50;
		}

		value = output;
	}

	public String getName() {
		return name;
	}

	public void setName(Rarity rarity) {
		String output = null;

		if (rarity.equals(Rarity.Rare)) {
			String[] options = { "Halo", "Horns" };

			output = options[rand.nextInt(options.length)];
		} else if (rarity.equals(Rarity.Epic)) {
			String[] options = { "Angel Wings", "Demon Wings" };

			output = options[rand.nextInt(options.length)];
		} else {
			String[] options = { "Summoning Circle", "Divine Address" };

			output = options[rand.nextInt(options.length)];
		}

		name = output;
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
		Rarity output = Rarity.Rare;

		if(i <= 50){
			output = Rarity.Rare;
		} else if(i > 50 && i <= 90){
			output = Rarity.Epic;
		} else {
			output = Rarity.Legendary;
		}
		
		return output;
	}
	
	public Rarity whatRarity(Rarity rarity,int percent) {
		Random rand = new Random();
		int i = rand.nextInt(100) + percent;
		Rarity output = Rarity.Rare;

		if(i <= 50){
			output = rarity;
		} else if(i <= 95){
			output = Rarity.Legendary;
		} else {
			output = Rarity.Epic;
		}
		
		return output;
	}

	public void setValues() {
		setRarity(whatRarity());
		setName(getRarity());
		setValue(getRarity());
	}
	
	public void setValues(Rarity rarity, int i) {
		setRarity(whatRarity(rarity, i));
		setName(getRarity());
		setValue(getRarity());
	}

	public Accessory() {
		setValues();
		this.rarity = getRarity();
		this.name = getName();
		this.value = getValue();
	}
	
	public Accessory(Rarity rarity, int percent){
		setValues(rarity, percent);
		this.rarity = getRarity();
		this.name = getName();
		this.value = getValue();
	}

	@Override
	public String toString() {
		String itemInfo = "Name: " + getName() + "\nRarity: " + getRarity() + "\nValue: " + getValue();
		return itemInfo;
	}

}
