/**
 * 
 */
package reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * @author Kevin O'Shea
 *
 */
 
public class Start {
	
	public static Queue<String> wholeText = new LinkedList<String>();
	public static ReadBookToScreen readBookToScreen = new ReadBookToScreen();
	public static SpeakBook speakBook = new SpeakBook();

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Welcome to the book reading app");
		menu();
		System.out.println("Goodbye");
	}

	/**
	 * Prints menu options and runs switch statement to call methods based on user
	 * choice
	 */
	public static void menu() {
		Scanner sc = new Scanner(System.in);
		int userInput;
		boolean done;
		done = false;
		do {
			System.out.println("Select an option:");
			System.out.println("1. Load Book");
			System.out.println("2. Read/Resume Book");
			System.out.println("3. Pause Book");
			System.out.println("4. Read Book With Text To Speach");
			System.out.println("5. Pause Text To Speach");
			System.out.println("6. Quit");

			if (sc.hasNextInt()) {
				userInput = sc.nextInt();

				if (userInput > 0 && userInput <= 6) {

					switch (userInput) {
					case 1:
						loadBook();
						break;
					case 2:
						readBook();
						break;
					case 3:
						stopBook();
						break;
					case 4:
						speakBook();
						break;
					case 5:
						stopSpeak();
						break;
					case 6:
						quit();
						done = true;
						break;
					default:
						System.out.println("Error\n");
					}
				}
			} else {
				System.out.println("Choose a valid number option");
				sc.nextLine();
			}
		} while (!done);
		sc.close();
	}
	
	public static void stopSpeak() {
		speakBook.setStop(true);
		System.out.println("Pausing voice...\n");
	}
	
	public static void speakBook() {
		System.out.println("Reading book with text to speach...\n");
		speakBook.setStop(false);
		Thread speak = new Thread(speakBook);
		speak.start();
	}
	
	
	public static void stopBook() {
		readBookToScreen.setStop(true);
		System.out.println("Pausing book...\n");
	}
	
	
	public static void readBook() {
		System.out.println("Reading book...\n");
		readBookToScreen.setStop(false);
		Thread readbook = new Thread(readBookToScreen);
		readbook.start();
		}

	/**
	 * Loads book from file and stores in Queue LinkedList
	 */
	public static void loadBook() {
		File file = new File("Dracula.txt");
		String line;
		try {
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			line = br.readLine();
			while(line!=null) {
				wholeText.add(line);
				line = br.readLine();
			}
			br.close();
			fr.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Book loaded\n");
	}
	
	public static void quit() {
		readBookToScreen.setStop(true);
		System.out.println("Quitting...\n");
	}

}
