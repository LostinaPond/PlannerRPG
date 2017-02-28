package lib;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import gameEnums.Rarity;

public class ConsoleIO {
	// PRO TIP: Make sure to create and close a new BufferedReader in each
	// method
	// where a BufferedReader is required. Also, catch ALL IOExceptions, do not
	// simply mark the method as "throws IOException".

	/**
	 * Generates a console-based menu using the Strings in options as the menu
	 * items. Reserves the number 0 for the "quit" option when withQuit is true.
	 * 
	 * @param options
	 *            - Strings representing the menu options
	 * @param withQuit
	 *            - adds option 0 for "quit" when true
	 * @return the int of the selection made by the user
	 */
	public static int promptForMenuSelection(String[] options, boolean withQuit) {

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		boolean keepGoing = true;
		
		int max = options.length;
		int min = withQuit ? 0 : 1;
		int userNum = 0;
				
		do {
			try {
				
				System.out.println("Choose one of the following: ");
				
				for (int i = 0; i < max; i++) {
					
					System.out.println((i + 1) + " " + options[i]);
				
					}
				
				if (withQuit) {
					System.out.println(0 + " Quit.");
				}
				
				String input = reader.readLine();
				userNum = Integer.parseInt(input);
				
				if (userNum > max || userNum < min) {
					System.out.println("Invalid choice, please try again.");
				} else {
					keepGoing = false;
				}
				
				
			} catch (IOException ioe) {
				System.out.println("An IOException has been caught, please try again.");
			}
		} while (keepGoing);
		
		return userNum; 
	}
	/**
	 * Generates a prompt that expects the user to enter one of two responses that will equate
	 * to a boolean value. The trueString represents the case insensitive response that will equate to true. 
	 * The falseString acts similarly, but for a false boolean value.
	 * 		Example: Assume this method is called with a trueString argument of "yes" and a falseString argument of
	 * 		"no". If the enters "YES", the method returns true. If the user enters "no", the method returns false.
	 * 		All other inputs are considered invalid, the user will be informed, and the prompt will repeat.
	 * @param prompt - the prompt to be displayed to the user
	 * @param trueString - the case insensitive value that will evaluate to true
	 * @param falseString - the case insensitive value that will evaluate to false
	 * @return the boolean value
	 */
	public static boolean promptForBool(String prompt, String trueString, String falseString){
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		String input = null;
		boolean keepGoing = true;
		boolean trueOrFalse = false;
		
		do {
		try {
			
			System.out.println(prompt);
			input = reader.readLine();
			
			if (input.equalsIgnoreCase(trueString)) {
				trueOrFalse = true;
				keepGoing = false;
			} else if (input.equalsIgnoreCase(falseString)) {
				trueOrFalse = false;
				keepGoing = false;
			} else {
				System.out.println("Your input was invalid, please try again.");
			}
		
		} catch (IOException ioe) {
			System.out.println("An IOException has been caught, please try again.");
		}
		} while (keepGoing);
		return trueOrFalse;
	}

	/**
	 * Generates a prompt that expects a numeric input representing a byte
	 * value. This method loops until valid input is given.
	 * 
	 * @param prompt
	 *            - the prompt to be displayed to the user
	 * @param min
	 *            - the inclusive minimum boundary
	 * @param max
	 *            - the inclusive maximum boundary
	 * @return the byte value
	 * @throws IOException 
	 */
	public static byte promptForByte(String prompt, byte min, byte max) throws IOException {

		System.out.println(prompt);
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		String in = read.readLine();
		byte x = Byte.parseByte(in);
		if(x < min || x > max){
			System.out.println("Your input was invalid. Please choose something between " + min + " and " + max + ".");
			promptForByte(prompt, max, min);
		}
		return x;
	}

	/**
	 * Generates a prompt that expects a numeric input representing a short
	 * value. This method loops until valid input is given.
	 * 
	 * @param prompt
	 *            - the prompt to be displayed to the user
	 * @param min
	 *            - the inclusive minimum boundary
	 * @param max
	 *            - the inclusive maximum boundary
	 * @return the short value
	 * @throws IOException 
	 */
	public static short promptForShort(String prompt, short min, short max) throws IOException {

		System.out.println(prompt);
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		String in = read.readLine();
		short x = Short.parseShort(in);
		if(x < min || x > max){
			System.out.println("Your input was invalid. Please choose something between " + min + " and " + max + ".");
			promptForShort(prompt, max, min);
		}
		return x;
	}

	/**
	 * Generates a prompt that expects a numeric input representing an int
	 * value. This method loops until valid input is given.
	 * 
	 * @param prompt
	 *            - the prompt to be displayed to the user
	 * @param min
	 *            - the inclusive minimum boundary
	 * @param max
	 *            - the inclusive maximum boundary
	 * @return the int value
	 * @throws IOException 
	 */
	public static int promptForInt(String prompt, int min, int max) throws IOException {

		System.out.println(prompt);
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		String in = read.readLine();
		int x = Integer.parseInt(in);
		if(x < min || x > max){
			System.out.println("Your input was invalid. Please choose something between " + min + " and " + max + ".");
			promptForInt(prompt, max, min);
		}
		return x;
	}

	/**
	 * Generates a prompt that expects a numeric input representing a long
	 * value. This method loops until valid input is given.
	 * 
	 * @param prompt
	 *            - the prompt to be displayed to the user
	 * @param min
	 *            - the inclusive minimum boundary
	 * @param max
	 *            - the inclusive maximum boundary
	 * @return the long value
	 * @throws IOException 
	 */
	public static long promptForLong(String prompt, long min, long max) throws IOException {

		System.out.println(prompt);
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		String in = read.readLine();
		long x = Long.parseLong(in);
		if(x < min || x > max){
			System.out.println("Your input was invalid. Please choose something between " + min + " and " + max + ".");
			promptForLong(prompt, max, min);
		}
		return x;
	}

	/**
	 * Generates a prompt that expects a numeric input representing a float
	 * value. This method loops until valid input is given.
	 * 
	 * @param prompt
	 *            - the prompt to be displayed to the user
	 * @param min
	 *            - the inclusive minimum boundary
	 * @param max
	 *            - the inclusive maximum boundary
	 * @return the float value
	 * @throws IOException 
	 */
	public static float promptForFloat(String prompt, float min, float max) throws IOException {

		System.out.println(prompt);
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		String in = read.readLine();
		float x = Float.parseFloat(in);
		if(x < min || x > max){
			System.out.println("Your input was invalid. Please choose something between " + min + " and " + max + ".");
			promptForFloat(prompt, max, min);
		}
		return x;
	}

	/**
	 * Generates a prompt that expects a numeric input representing a double
	 * value. This method loops until valid input is given.
	 * 
	 * @param prompt
	 *            - the prompt to be displayed to the user
	 * @param min
	 *            - the inclusive minimum boundary
	 * @param max
	 *            - the inclusive maximum boundary
	 * @return the double value
	 * @throws IOException 
	 */
	public static double promptForDouble(String prompt, double min, double max) throws IOException {

		System.out.println(prompt);
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		String in = read.readLine();
		double x = Double.parseDouble(in);
		if(x < min || x > max){
			System.out.println("Your input was invalid. Please choose something between " + min + " and " + max + ".");
			promptForDouble(prompt, max, min);
		}
		return x;
	}

	/**
	 * Generates a prompt that allows the user to enter any response and returns
	 * the String. When allowEmpty is true, empty responses are valid. When
	 * false, responses must contain at least one character (including
	 * whitespace).
	 * 
	 * @param prompt
	 *            - the prompt to be displayed to the user.
	 * @param allowEmpty
	 *            - when true, makes empty responses valid
	 * @return the input from the user as a String
	 * @throws IOException 
	 */
	public static String promptForInput(String prompt, boolean allowEmpty) throws IOException {

		System.out.println(prompt);
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		String in = read.readLine();
		if(!allowEmpty && in.equals("")){
			System.out.println("Cannot be empty,");
			promptForInput(prompt, allowEmpty);
		}
		return in;
	}

	/**
	 * Generates a prompt that expects a single character input representing a
	 * char value. This method loops until valid input is given.
	 * 
	 * @param prompt
	 *            - the prompt to be displayed to the user
	 * @param min
	 *            - the inclusive minimum boundary
	 * @param max
	 *            - the inclusive maximum boundary
	 * @return the char value
	 */
	public static char promptForChar(String prompt, char min, char max) {

		BufferedReader readRacer = new BufferedReader(new InputStreamReader(System.in));
		
		char c = 0;
		boolean keepGoing = true;
		
		do {
			try {
				System.out.println(prompt);
				String input = readRacer.readLine();
				c = input.charAt(0);
				if (c == (Character) null) {
					System.out.println("Your input was invalid, please try again.");
				} else {
					keepGoing = false;
				}
			} catch (IOException ioe){
				System.out.println("An IOException was caught, please try again.");
			}
				
		}while (keepGoing);

		return c;
}
	
	public static Rarity promptForRarity(String prompt, int choice){
		
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		boolean keepGoing = true;
		Rarity r = Rarity.Common;
		
		do {
			try {
				System.out.println(prompt);
				int i = promptForInt(prompt, 1, 5);
				
				switch(i){
				case 1:
					r = Rarity.Trash;
					break;
				case 2:
					r = Rarity.Common;
					break;
				case 3:
					r = Rarity.Rare;
					break;
				case 4:
					r = Rarity.Epic;
					break;
				case 5:
					r = Rarity.Legendary;
					break;
				}
				
			} catch (IOException ioe){
			
			}
				
		}while (keepGoing);
		return r;
	}
	
}
