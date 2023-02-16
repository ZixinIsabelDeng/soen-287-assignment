/**
 * 
 * @author Zixin Deng(40047744)
 * COMP249
 * Assignment #0
 * Due Date Friday, 3 February 2023
 */
import java.util.Scanner;

public class PlayLadderAndSnake {

	public static void main(String[] args) {
		//print the welcome message
		System.out.println("Welcome to Ladder and Snake");
		//check the validity of the player number
		System.out.println("Please Enter the number of players");
		Scanner keyboard = new Scanner(System.in);
		int enter = keyboard.nextInt();
		LadderAndSnake game = new LadderAndSnake(enter);
		//play the game
		game.whoFirst();
		game.play();
		keyboard.close();

	}

}
