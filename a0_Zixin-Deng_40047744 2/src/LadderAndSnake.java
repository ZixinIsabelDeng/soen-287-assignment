
/**
 * 
 * @author Zixin Deng(40047744)
 * COMP249
 * Assignment #0
 * Due Date Friday, 3 February 2023
 */

public class LadderAndSnake {
	// private attributes
	private static int[][] board1 = new int[10][10];//a board with number 1-100, which connects the position of the player and the ladder or snake in board 2
	private static int[][] board2 = { { 37, 0, 0, 10, 0, 0, 0, 0, 22, 0 }, { 0, 0, 0, 0, 0, -10, 0, 0, 0, 0 },
			{ 21, 0, 0, 0, 0, 0, 0, 56, 0, 0 }, { 0, 0, 0, 0, 0, 8, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, -18, 0, 0 },
			{ 16, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, -4, 0, 0, 0, 0, 0, 0 }, { 20, 0, 0, 0, 0, 0, 0, 0, 0, 20 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, }, { 0, 0, -25, 0, -71, 0, -21, -20, 0, 0 } };// a board with a collection of ladder and snakes
	private int[] position = new int[2]; //position of 2 players 
	private int turn;// which player's turn
	private int[] dice = new int[2];//an array of two dice value
	private int move;//record the value of ladder or snakes in board2
	private int previous_value;

/**
 * default constructor 
 */
	public LadderAndSnake() {
//filling the board1 with number 1 to 100
		int k = 0;
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				k += 1;
				board1[i][j] = k;
			}
		}
		//starting position are 0 for both players
		position[0] = 0;
		position[1] = 0;
		turn = 0;

	}

	/**
	 * parameterized constructor 
	 * @param player_num The number of players user entered
	 */
	public LadderAndSnake(int player_num) {
		int k = 0;
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				k += 1;
				board1[i][j] = k;
			}
		}
		position[0] = 0;
		position[1] = 0;
		turn = 0;

		// check if The value of player's number is legal
		if (player_num > 2) {
			System.out.println(
					"Initialization was attempted for x member of players; however, this is only expected for an extended version the game. Value will be set to 2");
		}
		if (player_num < 2) {
			System.out.println("Error: Cannot execute the game with less than 2 players! Will exit");
			System.exit(0);
		}
	}

	/**
	 * 
	 * @return A random number from 1-6 that simulates dice value
	 */
	public static int flipDice() {
		return (int) (Math.random() * 6) + 1;
	}

	/**
	 * A method to help calculate who first
	 * @param p1 The dice value of the first player
	 * @param p2 The dice value of the second player
	 * @return The player number 
	 */
	public int whoWin(int p1, int p2) {

		if (p1 > p2) {
			turn = 0;
			return 1;
		}
		if (p1 < p2) {
			turn = 1;
			return 2;
		} else
			return 3;

	}

	/**
	 * Decide which player goes first 
	 */
	public void whoFirst() {
		System.out.println("Now deciding which player will start playing;");
		int attempt = 1;
		int p1 = flipDice();
		int p2 = flipDice();

		if (p1 != p2) {
			System.out.println("Player " + whoWin(p1, p2) + " got a dice value of " + p1);
			System.out.println("Player " + (3 - whoWin(p1, p2)) + " got a dice value of " + p2);
			System.out.println("Reached final decision on order of playing: Player " + whoWin(p1, p2) + " then Player "
					+ (3 - whoWin(p1, p2)) + ".");
		}

		else {
			while (p1 == p2) {

				System.out.println("Player 1 got a dice value of " + p1);
				System.out.println("Player 2 got a dice value of " + p2);
				System.out.println("A tie was achieved between Player 1 and Player 2. Attempting to break the tie.");
				p1 = flipDice();
				p2 = flipDice();
				attempt++;
				if (p1 != p2) {
					System.out.println("Player 1 got a dice value of " + p1);
					System.out.println("Player 2 got a dice value of " + p2);
				}
			}

			System.out.println("Reached final decision on order of playing: Player " + whoWin(p1, p2) + " then Player "
					+ (3 - whoWin(p1, p2)) + ". It took " + attempt + " attempts before a decision could be made.");
		}

	}

    /**
     * Check if two player both reach the same position, the one who reach the first back to position 0
     * @param turn_select The selected player
     */
	public void isSamePosition(int turn_select) {
		if (position[turn_select] == position[1 - turn_select])
			position[1 - turn_select] = 0;
		;
	}
   /**
    * A method to check if there is a Ladder
    * @param turn_select The selected player
    * @return Whether it is a ladder or not
    */
	public boolean isLadder(int turn_select) {
		boolean ladder = false;
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				//check of there are any positive value on board2 which indicates a ladder
				if (position[turn_select] == board1[i][j] && board2[i][j] > 0) {
					this.previous_value = position[turn_select];
					this.move = board2[i][j];
					ladder = true;
				}
			}
		}
		return ladder;
	}
	
	/**
	 * A method to print the result when ladder occurs
	 * @param turn_select The selected player
	 * @return Print the result include dice value, original position and the position after ladder value.
	 */

	public String printLadder(int turn_select) {
		if (isLadder(turn_select))
			this.position[turn_select] = this.position[turn_select] + move;
		return "Player " + (turn_select + 1) + " got a dice value of " + dice[turn_select] + "; gone to square "
				+ previous_value + " then up to square " + position[turn_select];
	}
	
	/**
	 * A method to check if there is a snake
	 * @param turn_select The selected player
	 * @return Whether there is a snake or not
	 */

	public boolean isSnake(int turn_select) {
		boolean snake = false;

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (position[turn_select] == board1[i][j] && board2[i][j] < 0) {
					this.previous_value = position[turn_select];
					this.move = board2[i][j];
					this.position[turn_select] = this.position[turn_select] + move;
					snake = true;
				}
			}
		}
		return snake;
	}

	/**
	 * a method to print the result when snake occurs
	 * @param turn_select The selected player
	 * @return Print the result include dice value, original position and the position after snake value.
	 */
	public String printSnake(int turn_select) {
		return "Player " + (turn_select + 1) + " got a dice value of " + dice[turn_select] + "; gone to square "
				+ previous_value + " then down to square " + this.position[turn_select];
	}
	
	/**
	 * Check if the position goes over 100, if it is, going backward starting from 100
	 * @param turn_select The selected player
	 */

	public void isOver100(int turn_select) {
		if (position[turn_select] > 100)
			position[turn_select] = 200 - position[turn_select];

	}
	
	/**
	 * Check all the possible situations one by one: snake, ladders, getting over 100, and print the position
	 * @param turn_select The selected plater
	 */

	public void checkAndPrint(int turn_select) {
		isOver100(turn_select);
		if (isSnake(turn_select))
			System.out.println(printSnake(turn_select));
		else if (isLadder(turn_select))
			System.out.println(printLadder(turn_select));
		else
			System.out.println("Player " + (turn_select + 1) + " got a dice value of " + dice[turn_select]
					+ " now in square " + position[turn_select]);
	}
	
	/**
	 * Decide which player goes to 100 first
	 */

	public void printWinner() {
		int winner = 0;
		if (position[turn] == 100)
			winner = turn + 1;
		if (position[1 - turn] == 100)
			winner = 2 - turn;
		System.out.println("Game over! The winner is player " + winner);
	}
	
	/**
	 * The game starts, including all the rules from Ladder and Snakes
	 */

	public void play() {

		while (position[turn] != 100 || position[1 - turn] != 100) {
			dice[turn] = flipDice();
			dice[1 - turn] = flipDice();

			position[turn] = position[turn] + dice[turn];
			isSamePosition(turn);
			checkAndPrint(turn);

			position[1 - turn] = (position[1 - turn] + dice[1 - turn]);
			isSamePosition(1 - turn);
			checkAndPrint(1 - turn);

			if (position[turn] == 100 || position[1 - turn] == 100)
				break;
			else
				System.out.println("Game not over;flipping again");

		}
		printWinner();
	}

}
