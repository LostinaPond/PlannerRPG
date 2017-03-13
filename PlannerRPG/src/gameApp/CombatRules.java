package gameApp;

import java.util.Random;

public class CombatRules {
	
	public Random rando = new Random();

	public static int critHit(boolean doesHit, int luckMod, int damage){
		int roll = (rando.nextInt(20) + 1) + luckMod;
		if(doesHit && roll >= 15){
			damage = damage * 2;
		} else if(!doesHit){
			damage = damage / 2;
		}
		
		return damage;
	}
	
	public static boolean doesHit(int playerLuckMod, int enemyLuckMod){
		boolean doesHit = true;
		int pRoll = (rando.nextInt(20) + 1) + playerLuckMod;
		int eRoll = (rando.nextInt(20) + 1) + enemyLuckMod;
		
		if(pRoll > eRoll){
			doesHit = false;
		} else {
			doesHit = true;
		}
		
		return doesHit;
	}
	
}
