package items;

import java.util.Random;

import gameEnums.Rarity;

public class Item {

	protected String name;
	protected String mod;
	protected Rarity rarity;
	protected int value;
	
	public Random rand = new Random();
	
	
	public String getName(){
		return name;
	}
	
	public void setName(){
		String[] options = {
			"Knife",
			"Fork",
			"Spoon",
			"Bucket",
			"Plate",
			"Bear Pelt",
			"Cat Pelt",
			"Wolf Pelt",
			"Mammoth Tusk",
			"Rope",
			"Bowl",
			"Clothes",
			"Chain"};
		 
		String output = options[rand.nextInt(options.length)];
		
		name = getMod() + " " + output;
	}
	
	public Rarity getRarity(){
		return rarity;
	}
	
	public void setRarity(Rarity output){
		output = Rarity.Trash;
		rarity = output;
	}
	
	public Rarity whatRarity(){
		Random rand = new Random();
		
		Rarity[] rarities = Rarity.values();
		 
		Rarity output = rarities[rand.nextInt(rarities.length)]; 
		
		return output;
	}
	
	public String getMod(){
		return mod;
	}
	
	public void setMod(Rarity rarity){
			String [] options = {
					"Broken",
					"Tattered",
					"Destroyed",
					"Old",
					"Frail",
					"Worn-Out"};
			
			String output = options[rand.nextInt(options.length)];
		
		mod = output;
	}
	
	public int getValue(){
		return value;
	}
	
	public void setValue(){
		int output = rand.nextInt(15) + 1;
		
		value = output;
	}
	
	public void setValues(){
		setRarity(whatRarity());
		setValue();
		setMod(getRarity());
		setName();
	}
	
	public Item(){
		setValues();
		this.rarity = getRarity();
		this.mod = getMod();
		this.name = getName();
		this.value = getValue();
	}
	
	public Item(Rarity rarity, String mod, String name, int value){
	this.rarity = rarity;
	this.mod = mod;
	this.name = name;
	this.value = value;
	}
	
	@Override
	public String toString(){
		String itemInfo = "Name: " + getName() + "\nRarity: " + getRarity() + "\nValue: " + getValue();
		return itemInfo;
	}
}

