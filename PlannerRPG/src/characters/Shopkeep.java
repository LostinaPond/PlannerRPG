package characters;

import java.io.IOException;
import java.util.ArrayList;

import app.Planner;
import gameApp.LootGenerator;
import items.Item;
import items.Rarity;
import lib.ConsoleIO;
import models.User;

public class Shopkeep {

	private static ArrayList<Item> shopInventory = new ArrayList<Item>();

	public static void runShop(User u) {
		setInventory();
		interact(u);
	}

	public static void interact(User u) {
		System.out.println("RP: " + u.getRP());
		int i = 0;
		boolean isValid = false;
		do {
			i = ConsoleIO.promptForInt("Press 1 to check the Shopkeep's Inventory,"
					+ "\nPress 2 to Sell an Item, or" + "\nPress 3 to Buy an Item," + "\nPress 4 to exit the menu.",
					1, 4);
			switch (i) {
			case 1:
				showInv(u);
				break;
			case 2:
				sellItem(selectItem(u), u);
				break;
			case 3:
				buyItem(u);
				break;
			case 4:
				System.out.println("Come again!");
				System.out.println();
				clearInv();
				isValid = true;
				break;
			}
		} while (!isValid);
		Planner.RPGMenu();
	}

	public static void sellItem(Item i, User h1) {
		h1.setRP((h1.getRP() + i.getValue()));
		if (!h1.getInventory().isEmpty()) {
			if (h1.equippedAccessory != null) {
				if (h1.equippedAccessory.equals(i)) {
					h1.setEquippedAccessory(null);
				}
			}
			if (h1.equippedArmor != null) {
				if (h1.equippedArmor.equals(i)) {
					h1.setEquippedArmor(null);
				}
			}

			if (h1.equippedWeapon != null)
				if (h1.equippedWeapon.equals(i)) {
					h1.setEquippedWeapon(null);
				}
		}
		h1.getInventory().remove(i);
		interact(h1);
	}

	public static void setInventory() {
		shopInventory.add(LootGenerator.generateRandomShopItem());
		shopInventory.add(LootGenerator.generateRandomShopItem());
		shopInventory.add(LootGenerator.generateRandomShopItem());
		shopInventory.add(LootGenerator.generateRandomShopItem());
		shopInventory.add(LootGenerator.generateRandomShopItem());
	}

	public static void showInv(User u) {
		System.out.println("You have " + u.getRP() + "RP");
		for (Item i : shopInventory) {
			System.out.println(i);
			System.out.println();
		}
		interact(u);
	}

	public static void showInv2() {
		for (Item i : shopInventory) {
			System.out.println(i);
			System.out.println();
		}
	}

	public static void buyItem(User u) {
		int i = 0;
		showInv2();
		i = ConsoleIO.promptForInt("Select an item number corresponding to the items above.", 1,
				shopInventory.size()) - 1;

		Item e = shopInventory.get(i);

		if (u.getRP() >= e.getValue()) {
			u.setRP((u.getRP() - e.getValue()));
			u.addToInv(e);
			shopInventory.remove(i);
		} else {
			System.out.println("That Item is too expensive for you.");
		}
		interact(u);
	}

	public static Item selectItem(User h1) {
		int i = 0;
		Item select = new Item(Rarity.Common, "x", "x", 0);
		if (h1.getInventory().isEmpty()) {
			System.out.println("Sorry! You have nothing to sell!");
		} else {
			h1.showInventory();
			i = ConsoleIO.promptForInt("Select an item number corresponding to the items above.", 1,
					h1.getInventory().size());
			select = h1.getInventory().get(i - 1);
		}
		return select;
	}

	public static void clearInv() {
		shopInventory.clear();
	}

}
