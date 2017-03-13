package characters;

import java.util.Random;

import enums.Difficulty;

public class Monster extends Character{

	private Difficulty d;
	private int a;
	private int b;
	private int c;

	public Monster(Difficulty d) {
		setDifficulty(d);
		if (d == Difficulty.Easy) {
			a = 10;
			b = 10;
			c = 30;
		} else if (d == Difficulty.Medium) {
			a = 20;
			b = 20;
			c = 60;
		} else {
			a = 30;
			b = 30;
			c = 120;
		}
		setValues();
	}

	public Difficulty getDifficulty() {
		return d;
	}

	public void setDifficulty(Difficulty d) {
		this.d = d;
	}

	public int getStrBase() {
		return strBase;
	}

	public void setStrBase() {
		int str = rand.nextInt(a) + 1;
		strBase = str;
	}

	public int getDefBase() {
		return defBase;
	}

	public void setDefBase() {
		int def = rand.nextInt(b) + 1;
		defBase = def;
	}

	public int getLuckBase() {
		return luckBase;
	}

	public void setLuckBase() {
		int luck = rand.nextInt(10) + 1;
		luckBase = luck;
	}

	public int getStrMod() {
		return strMod;
	}

	public void setStrMod() {
		strMod = getStrBase() - 5;
	}

	public int getDefMod() {
		return defMod;
	}

	public void setDefMod() {
		defMod = getDefBase();
	}

	public int getLuckMod() {
		return luckMod;
	}

	public void setLuckMod() {
		luckMod = getLuckBase() - 5;
	}

	public int getStrength() {
		return strBase + strMod;
	}

	public int getDexterity() {
		return defBase + defMod;
	}

	public int getIntelligence() {
		return luckBase + luckMod;
	}

	public int getBaseHP() {
		return baseHP;
	}

	public void setBaseHP() {
		baseHP = (rand.nextInt(c) + 1) * 2;
	}

	public int getCurrentHP() {
		return currentHP;
	}

	public void setCurrentHP(int i) {
		if (i > getBaseHP()) {
			currentHP = getBaseHP();
		} else if (i < 0) {
			currentHP = 0;
		} else {
			currentHP = i;
		}
	}

	public void setValues() {
		setStrBase();
		setDefBase();
		setLuckBase();
		setBaseHP();
		setCurrentHP(baseHP);
		setStrMod();
		setDefMod();
		setLuckMod();
	}

	@Override
	public int attack() {
		Random rand = new Random();
		int weaponDamage = rand.nextInt(a) + 1;
		int damage = getStrMod() + weaponDamage;
		return damage;
	}

	@Override
	public int takeDamage(int damage) {
		int hp = 0;
		if (damage >= 0) {
			hp = getCurrentHP();
			hp = hp - damage;
		}
		return hp;
	}
}
