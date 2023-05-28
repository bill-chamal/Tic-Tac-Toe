package tictactoe;
/*
 *@author 		Bill Chamalidis 
 *@exercise 	1 TIC-TAC-TOE
 */


public class Main {

	public static void main(String[] args) {
		TicTacToe tictac = new TicTacToe();
		
		tictac.printTable();
		
		do {
			tictac.playerMove();
			if (!tictac.GameOn())
				break;
			tictac.printTable();
			tictac.ComputerMove();
			if (!tictac.GameOn())
				break;
			tictac.printTable();
		} while (tictac.GameOn());
	}

}
