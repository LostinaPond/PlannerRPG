package gameApp;

import java.util.Random;

import app.Planner;
import lib.ConsoleIO;
import models.Difficulty;
import models.Monster;
import models.User;

public class BattleMenu {

	public static void menu(User u){
		if(u.getCurrentHP() == 0){
			System.out.println("You can't battle, you're incapacitated. Heal up and come back later.");
			Planner.RPGMenu();
		}
		Random rand = new Random();
		int a = rand.nextInt(3) + 1;
		Monster m = new Monster(Difficulty.Easy);
		if (a == 1){
			m = new Monster(Difficulty.Easy);
		} else if (a == 2){
			m = new Monster(Difficulty.Medium);
		} else if (a == 3){
			m = new Monster(Difficulty.Hard);
		}
		int x = 0;
		boolean stillAlive = true;
		System.out.println("It's a " + m.getDifficulty() + " difficulty monster!");
		System.out.println("The monster's Base HP is " + m.getBaseHP() + ".");
		do{	
			x = ConsoleIO.promptForInt("Press 1 to Attack, " + "\nPress 2 to use a potion, or" + "\nPress 3 to run.", 1, 3);
			switch(x){
			case 1:
				int damage = u.attack();
				int newHP = m.takeDamage(damage);
				m.setCurrentHP(newHP);
				System.out.println("You struck the monster and caused " + damage + " damage!");
				System.out.println("The monster's HP is " + m.getCurrentHP() + "/" + m.getBaseHP());
				if(m.getCurrentHP() == 0){
					System.out.println("You killed the monster! Good job!");
					LootGenerator.generateRandomItem(u);
					stillAlive = false;
					break;
				} else {
					int mDamage = m.attack();
					int newHPYou = u.takeDamage(mDamage);
					u.setCurrentHP(newHPYou);
					System.out.println("The monster struck you and caused " + mDamage + " damage!");
					System.out.println("Your current HP is " + u.getCurrentHP() + "/" + u.getBaseHP());
					if(u.getCurrentHP() == 0){
						stillAlive = false;
						Random jennifer = new Random();
						if(u.getInventory().isEmpty()){
							System.out.println("You've been incapacitated, but you didn't have anything to lose. Heal up before you battle again.");
						} else {
							System.out.println("You've been incapacitated and lost an item. Heal up before you battle again.");
							int index = jennifer.nextInt(u.getInventory().size());
							u.getInventory().remove(index);
						}
					}
				}
				break;
			case 2: 
				Planner.potionPick();
				break;
			case 3: 
				System.out.println("You fled. Loser.");
				stillAlive = false;
				break;
			}
		} while (stillAlive);
		Planner.RPGMenu();
	}
	
}
