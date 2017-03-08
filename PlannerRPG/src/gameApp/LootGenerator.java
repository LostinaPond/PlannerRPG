package gameApp;

import java.util.Random;

import characters.Hero;
import gameEnums.Rarity;
import items.Accessory;
import items.Armor;
import items.Item;
import items.Potion;
import items.Weapon;

public class LootGenerator {

	static Hero h1 = new Hero();
	static Random rand = new Random();

	public static void generateRandomItem() {

		int choice = rand.nextInt(5) + 1;

		switch (choice) {
		case 1:
			Item i = new Item();
			h1.addToInv(i);
			break;
		case 2:
			Potion p = new Potion();
			h1.addToInv(p);;
			break;
		case 3:
			Weapon w = new Weapon();
			h1.addToInv(w);;
			break;
		case 4:
			Armor a = new Armor();
			h1.addToInv(a);
			break;
		case 5:
			Accessory e = new Accessory();
			h1.addToInv(e);
			break;
		}
	}
	
	public static Item generateRandomShopItem() {

		Item i = null;
		int choice = rand.nextInt(5) + 1;

		switch (choice) {
		case 1:
			i = new Item();
			break;
		case 2:
			i = new Potion();
			break;
		case 3:
			i = new Weapon();
			break;
		case 4:
			i = new Armor();
			break;
		case 5:
			i = new Accessory();
			break;
		}
		
		return i;
	}
	
	public static void generateItem(Rarity rarity, int percent) {

		int choice = rand.nextInt(3) + 1;

		switch (choice) {
		case 1:
			Weapon w = new Weapon(rarity, percent);
			h1.addToInv(w);
			break;
		case 2:
			Armor a = new Armor(rarity, percent);
			h1.addToInv(a);
			break;
		case 3:
			Accessory e = new Accessory(rarity, percent);
			h1.addToInv(e);
			break;
		}
	}
}