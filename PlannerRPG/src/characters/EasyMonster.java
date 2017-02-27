package characters;

public class EasyMonster extends Character {

	public int getStrBase() {
		return strBase;
	}

	public void setStrBase() {
		int str = rand.nextInt(10) + 1;
		strBase = str;
	}

	public int getDefBase() {
		return defBase;
	}

	public void setDefBase() {
		int def = rand.nextInt(10) + 1;
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
		baseHP = (rand.nextInt(30) + 1) * 2;
	}

	public int getCurrentHP() {
		return currentHP;
	}

	public void setCurrentHP(int i) {
		currentHP = i;
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
		int damage = getStrMod() + equippedWeapon.dealDamage();
		return damage;
	}

	@Override
	public int takeDamage(int damage, int armorDR, int defense) {
		int hp = getCurrentHP();
		hp = hp - (damage - (armorDR + defense));
		return hp;
	}

}
