import java.util.Scanner;
import java.util.Random;


public class BattleShip {

	public static int[][] grid(int x, int y) {
		int [][] board;
		board = new int[x][y];
		return board;
	}
	
	public static int [][] deploy_player_ships(int j) {
		Scanner sc = new Scanner(System.in);
		int [][] game_board = grid(10,10);
		int i = 1;
		for (i = 1; i < j+1;i++) {
			System.out.println("Enter x coordinate between 0-9 for your ship " + i + " : ");
			int x = sc.nextInt();
			while (x > 9 || x < 0 ) {
				System.out.println("Invalid x coordinate, please enter again");
				x = sc.nextInt();
			}
			System.out.println("Enter y coordinate for your ship " + i + ":");
			int y = sc.nextInt();
			while (y > 9 || y < 0) {
				System.out.println("Invalid y coordinate, please enter again");
				y = sc.nextInt();
			}
			while (game_board[x][y] == 1) {
				System.out.println("Already entered this position for ship! enter new coordinates");
				x = sc.nextInt();
				y = sc.nextInt();
			}
			game_board[x][y] = 1;
			System.out.println("Ship " + i + " is deployed");
		}
		System.out.println("All player ships are deployed");
		return game_board;
	}
	
	public static int [][] deploy_comp_ships(int j) {
		//Scanner sc = new Scanner(System.in);
		int [][] comp_board = grid(10,10);
		int i = 1;
		Random rnd = new Random();
		System.out.println("Beginning deploy of computer ships");
		for (i = 1; i < j+1;i++) {
			//need to deploy all of the computer ships randomly
			int x = rnd.nextInt(10);
			int y = rnd.nextInt(10);
			while (comp_board[x][y] == 2) {
				x = rnd.nextInt(10);
				y = rnd.nextInt(10);
			}
			comp_board[x][y] = 2;
			System.out.println("Comp Ship "+ i + " is deployed");
		}
		System.out.println("All comp ships deployed");
		return comp_board;
	}
	
	public static void battle() {
		int [][] player_guess = grid(10,10);
		int [][] comp_guess = grid(10,10);
		Scanner sc = new Scanner(System.in);
		int [][] player_board = deploy_player_ships(5);
		int [][] comp_board = deploy_comp_ships(5);
		int player_ships = 5;
		int comp_ships = 5;
		Random rnd = new Random();
		while (player_ships > 0 || comp_ships > 0) {
					
			//now time to start the battle logic
			System.out.println("Player 1 Turn: ");
			System.out.println("Enter X and Y Coordinates: ");
			int x_guess = sc.nextInt();
			int y_guess = sc.nextInt();
			while (player_guess[x_guess][y_guess] == 1) {
				System.out.println("Already guessed those coordinates, try again");
				x_guess = sc.nextInt();
				y_guess = sc.nextInt();
			}
			player_guess[x_guess][y_guess] = 1; //marked the guess as done
			if (comp_board[x_guess][y_guess] == 2) {
				System.out.println("Computer Ship is sunk!");
				comp_ships--;
				System.out.println(comp_ships + " computer ships remaining");
			} else {
				System.out.println("Player guess missed");
			}
			if (comp_ships == 0 ) {
				System.out.println("PLayer has won the game!");
				break;
			}
			
			///Start of Computer Player's Turn
			
			
			System.out.println("Computer Player Turn: ");
			int x_comp = rnd.nextInt(10);
			int y_comp = rnd.nextInt(10);
			
			
			while (comp_guess[x_comp][y_comp] == 1) {
				System.out.println("Already guessed those coordinates, try again");
				x_comp = rnd.nextInt(10);
				y_comp = rnd.nextInt(10);	
			}
			comp_guess[x_comp][y_comp] = 2; //marked the computer guess
			if (player_board[x_comp][y_comp] == 2) {
				System.out.println("Player Ship is sunk!");
				player_ships--;
				System.out.println(player_ships + " computer ships remaining");
			} else {
				System.out.println("Computer guess missed");
			}
			
			if (player_ships == 0) {
				System.out.println("Computer has won the game!") ;
				break;
			}
			//
			System.out.println("Restart turn back to player");
		}
	}
		
	public static void main(String[] args) {
		battle();
	}

}
