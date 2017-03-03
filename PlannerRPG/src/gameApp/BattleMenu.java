package gameApp;

import java.io.IOException;

import characters.Hero;

public class BattleMenu {
	
	CombatRules c1 = new CombatRules();
	Hero h1 = new Hero();

	public void menu(){
		int x = 0;
		do{
			try{
				x = lib.ConsoleIO.promptForInt("Press 1 to Attack, " + "\nPress 2 to use a potion, or" + "\nPress 3 to run.", 1, 3);
				switch(x){
				case 1:
//					c1.critHit(c1.doesHit(h1.getLuckMod(), enemyLuckMod), luckMod, damage);
				}
			} catch (IOException ioe){
				
			}
		} while (x!=3);
	}
	
}
