package interfaces;

import models.User;

public interface Consumable {

	void use(User u);
	String getDescription();
	
}
