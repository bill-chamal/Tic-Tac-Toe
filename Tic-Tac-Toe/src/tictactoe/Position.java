package tictactoe;

public class Position {
	private int virtualRow;
	private char virtualColm;
	private int MAX;
	private int Row;
	private int Colm;
	
	public Position(int m) {
		MAX = m;
		virtualRow = 1;
		virtualColm = 'A';
		Row = 0;
		Colm = 0;
	}
	// VirtualPos is what the user sees A1, B3
	public void setVirtualPos(char colm, int row) {
		virtualRow = row;
		virtualColm = colm;
	}
	// RealPos is for the programm (0,0) (2,1)
	public void setRealPos(int row, int colm) {
		Row = row;
		Colm = colm;
	}
	
	public boolean isDiagonal() {
		return (Row == Colm || Row+Colm == MAX-1);
	}
	
	// * Getters and Setters * //
	public int getVirtualRow() {
		return virtualRow;
	}

	public void setVirtualRow(int virtualRow) {
		this.virtualRow = virtualRow;
	}

	public char getVirtualColm() {
		return virtualColm;
	}

	public void setVirtualColm(char virtualColm) {
		this.virtualColm = virtualColm;
	}

	public int getRealRow() {
		return Row;
	}

	public void setRealRow(int row) {
		Row = row;
	}

	public int getRealColm() {
		return Colm;
	}

	public void setRealColm(int colm) {
		Colm = colm;
	}

	public int getMAX() {
		return MAX;
	}

	public void setMAX(int mAX) {
		MAX = mAX;
	}
	
}
