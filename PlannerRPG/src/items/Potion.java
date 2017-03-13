package items;

import java.util.Random;

import characters.Character;
import gameEnums.Rarity;
import interfaces.Consumable;
import models.User;

public class Potion extends Item implements Consumable {

	private int num;
	private int value;
	private Rarity rarity;

	public Rarity getRarity() {
		return rarity;
	}

	public void setRarity(Rarity output) {
		rarity = output;
	}

	public Rarity whatRarity() {
		Random rand = new Random();

		Rarity[] rarities = Rarity.values();

		Rarity output = rarities[rand.nextInt(rarities.length)];

		if (output.equals(Rarity.Trash)) {
			output = Rarity.Common;
		} else if (output.equals(Rarity.Epic)) {
			output = Rarity.Common;
		} else if (output.equals(Rarity.Legendary)) {
			output = Rarity.Rare;
		}

		return output;
	}

	public int getNum() {
		return num;
	}

	public void setNum(Rarity rarity) {
		int number = 0;

		if (rarity.equals(Rarity.Common)) {
			number = rand.nextInt(500) + 1;
		} else {
			number = rand.nextInt(1000) + 1;
		}

		num = number * 10;
	}

	public int getValue() {
		return value;
	}

	public void setValue() {
		value = getNum() / 10;
	}

	public Potion() {
		setRarity(whatRarity());
		setNum(getRarity());
		setValue();
	}

	@Override
	public void use(User u) {
		int i = u.getCurrentHP() + getNum();
		u.setCurrentHP(i);
	}

	@Override
	public String getDescription() {
		String potionInfo = "Potion" + "\nHeal Amount: " + getNum() + "\nRarity: " + getRarity() + "\nValue: "
				+ getValue();
		return potionInfo;
	}
	
	@Override
	public String toString() {
		String itemInfo = "Potion" + "\nHeal Amount: " + getNum() + "\nRarity: " + getRarity() + "\nValue: " + getValue() + "RP";
		return itemInfo;
	}

}
