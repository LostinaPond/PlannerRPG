package gameApp;

import java.util.ArrayList;
import java.util.Random;

import gameEnums.Rarity;
import items.Accessory;
import items.Armor;
import items.Item;
import items.Potion;
import items.Weapon;

public class Inventory {

	static ArrayList<Item> inventory = new ArrayList<Item>();
	static Random rand = new Random();

	public static void generateRandomItem() {

		int choice = rand.nextInt(5) + 1;

		switch (choice) {
		case 1:
			Item i = new Item();
			inventory.add(i);
			break;
		case 2:
			Potion p = new Potion();
			inventory.add(p);
			break;
		case 3:
			Weapon w = new Weapon();
			inventory.add(w);
			break;
		case 4:
			Armor a = new Armor();
			inventory.add(a);
			break;
		case 5:
			Accessory e = new Accessory();
			inventory.add(e);
			break;
		}
	}
	
	public static void generateItem(Rarity rarity, int percent) {

		int choice = rand.nextInt(3) + 1;

		switch (choice) {
		case 1:
			Weapon w = new Weapon(rarity, percent);
			inventory.add(w);
			break;
		case 2:
			Armor a = new Armor(rarity, percent);
			inventory.add(a);
			break;
		case 3:
			Accessory e = new Accessory(rarity, percent);
			inventory.add(e);
			break;
		}
	}
}