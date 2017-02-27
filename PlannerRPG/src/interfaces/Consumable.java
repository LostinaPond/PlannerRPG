package interfaces;

import characters.Character;

public interface Consumable {

	void use(Character c, int rdr);
	String getDescription();
	
}
