package characters;

import java.io.IOException;
import java.util.ArrayList;

import gameApp.LootGenerator;
import items.Item;

public class Shopkeep {

	ArrayList<Item> shopInventory = new ArrayList<Item>();
	Hero h1 = new Hero();

	public void interact() {
		int i = 0;
		setInventory();
		do {
			try {
				i = lib.ConsoleIO.promptForInt("Press 1 to check the Shopkeep's Inventory,"
						+ "\nPress 2 to Sell an Item, or" + "\nPress 3 to Buy an Item," + "\nPress 4 to exit the menu.",
						1, 4);
			} catch (IOException ioe) {
			}
			
			switch(i){
			case 1:
				showInv();
				break;
			case 2:
				sellItem(selectItem());
				break;
			case 3:
				buyItem();
				break;
			case 4:
				System.out.println("Come again!");
				System.out.println();
				clearInv();
			}
		} while (i != 4);
	}

	public void sellItem(Item i) {
		h1.setRP(i.getValue());
		h1.inventory.remove(i);
	}

	public void setInventory() {
		shopInventory.add(LootGenerator.generateRandomShopItem());
		shopInventory.add(LootGenerator.generateRandomShopItem());
		shopInventory.add(LootGenerator.generateRandomShopItem());
		shopInventory.add(LootGenerator.generateRandomShopItem());
		shopInventory.add(LootGenerator.generateRandomShopItem());
	}

	public void showInv() {
		for (Item i : shopInventory) {
			System.out.println(i);
			System.out.println();
		}
	}

	public void buyItem() {

	}
	
	public Item selectItem(){
		int i = 0;
		h1.showInventory();
		try{
			i = lib.ConsoleIO.promptForInt("Select an item number corresponding to the items above.", 1, h1.inventory.size()) - 1;
		}catch (IOException ioe){
		}
		
		return h1.inventory.get(i);
	}
	
	public void clearInv(){
		shopInventory.clear();
	}

}
