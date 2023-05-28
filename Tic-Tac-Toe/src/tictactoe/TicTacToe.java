package tictactoe;
import java.util.Scanner;
import java.util.Random;

public class TicTacToe {
	private final int MAX_SIZE = 4;	// Size, square, custom size tictactoe
	private final char START_LETTER = 'A';
	private char arr[][] = new char[MAX_SIZE][MAX_SIZE];
	private int count;	
	// Save the last position 
	private Position position = new Position(MAX_SIZE);
	private Scanner scanf = new Scanner(System.in);
	private Random rand = new Random();
	
	
	public TicTacToe() {
		// fill arr[][] with empty spaces
		for (int i=0; i<MAX_SIZE; i++) 
			for (int j=0; j<MAX_SIZE; j++)
				arr[i][j] = ' ';
		count = 0;
		System.out.println("************");
		System.out.println("Tic-Tac-Toe!");
		System.out.println("************\n");
		System.out.println("Please enter the column (A, B or C) and then the row (1, 2, or 3) of your move.");
	}
	
	public void printTable() {
		System.out.printf("\n  ");
		// print column letters, A B C ... 
		for (int i=0; i<MAX_SIZE; i++)
			System.out.printf(" %c", START_LETTER+i);
		System.out.printf("\n");
		// print rows with numbers, i |arr[i][j]| ... | ...
		for (int i=0; i<MAX_SIZE; i++) {
			System.out.printf("%d |", i+1);
			for (int j=0; j<MAX_SIZE; j++) 
				System.out.printf("%c|", arr[i][j]);
			System.out.printf("\n");
		}
	}
		
	public void playerMove() {
		String in;
		char letter;
		int num;
		boolean flag = true;
		// do-while valid input
		do {
			System.out.printf("\nPlayer Move (X): ");
			in = scanf.nextLine();
			in = in.trim(); 		// remove empty spaces 
			letter = in.charAt(0); 	// take the first letter from the string
			num = in.charAt(1) - 48;// take the second letter from the string and convert it to integer
			flag = checkValidPosition(letter, num);	// check if the letter and the number are correct
			if(!flag) 	// if invalid position is present
				System.out.println("\nInvalid Input: Please enter the column and row of your move (Example: A1).");
			else {
				// check if the position is already taken
				flag = checkTakenSpace(letter, num);	// if the space in not taken -> true
				if (!flag) 	// if the space in taken
					System.out.println("\nThe space entered is already taken.");
			}
			
		} while(!flag); // continue if no error 
		// Set the correct position 
		position.setVirtualPos(letter, num);
		position.setRealPos(num-1, letter-65);
		setPosition('X');
	}
	
	public void checkWinner(char symbol) {
		boolean flag = false;
		
		if (position.isDiagonal())
			flag = analyzeDiagonal();	// someone won if not then try Row & Colm
		if (!flag) 
			flag = analyzeRowColm();
		
		if (flag) {
			printTable();
			if (symbol == 'X')
				System.out.println("\nYou win!");
			else
				System.out.println("\nThe Computer won!");
			count = -1;	// end the program
		}
				
	}
	
	public boolean analyzeRowColm() {
		/*
		 * We checking if one row or column has the same symbol [O|X] continues
		 * in every position from the last player move. If not immediately stop the loop 
		 */
		boolean flag1 = true, flag2 = true;
		// analyze, all the row
		if (arr[position.getRealRow()][0] != ' ')
		for (int j=1; j<MAX_SIZE; j++) {
			if (arr[position.getRealRow()][j-1] != arr[position.getRealRow()][j]) {
				flag1 = false;
				break;
			}
		}
		else
			flag1 = false;
		// analyze, all the column
		if (arr[0][position.getRealColm()] != ' ')
		for (int i=1; i<MAX_SIZE; i++) {
				if (arr[i-1][position.getRealColm()] != arr[i][position.getRealColm()]) {
					flag2 = false;
					break;
					}
		}
		else 
			flag2 = false;
		return flag1 || flag2;
	}

	public boolean analyzeDiagonal() {
		/*
		 * We checking diagonal
		 */
		boolean flag1 = true, flag2 = true;
		if (arr[0][0] != ' ')
			for (int k=1; k<MAX_SIZE; k++) {
				if (arr[k-1][k-1] != arr[k][k]) {
					flag1 = false;
					break;
				}
			}
		else 
			flag1 = false;
		
		if (arr[0][MAX_SIZE-1] != ' ')
			for (int k=1; k<MAX_SIZE; k++) {
				if (arr[k-1][MAX_SIZE-k] != arr[k][MAX_SIZE-k-1]) {
					flag2 = false;
					break;
				}
			}
		else 
			flag2 = false;
		
		return flag1 || flag2;	// if one (or more) is true then 
	}
	
	public boolean GameOn() {
		if (count < 0)	// the game finished with a winner
			return false;
		if (count == MAX_SIZE*MAX_SIZE) {	// the game finished with draw
			System.out.println("\nDRAW\nMax move limit reached!");
			return false;
		}
		return true;
	}
	
	public void ComputerMove() {
		int i,j;
		do {
			j = rand.nextInt(MAX_SIZE);
			i = rand.nextInt(MAX_SIZE);
		} while (arr[i][j] != ' ');
		
		position.setRealPos(i, j);
		System.out.printf("\nComputer Move (O): %c%d\n", j+65, i+1);
		setPosition('O');
	}
	
	// Private methods //
	private void setPosition(char symbol) {
		// set the position to array arr[][], the method is private because we don't check the values
		arr[position.getRealRow()][position.getRealColm()] = symbol;
		count++;
		checkWinner(symbol);
	}
	
	private boolean checkValidPosition(char le, int num) {
		return ( (num>0 & num<=MAX_SIZE) && (le >='A' & le < ('A'+MAX_SIZE)) );
	}

	private boolean checkTakenSpace(char let, int num) {
		return (arr[num-1][let-65] == ' ');
	}

	
	//	**	Getters & Setters	**	//
	public int getMAX_SIZE() {
		return MAX_SIZE;
	}
	
	public int getCount() {
		return count;
	}

	public Position getPosition() {
		return position;
	}
	
}
