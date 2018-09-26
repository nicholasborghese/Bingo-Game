package edu.cuny.csi.csc330.groupproject; //may need to change between computers depending on package name

//for collections shuffle
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.io.*;


public class BingoGame {
	public final static int DEFAULT_GAME_COUNT = 1;
	private final static int SELECTION_COUNT = 25; 
	private Integer[][] board;
	private Integer[] nums = new Integer[SELECTION_COUNT];
	private int[] numCall = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,
			26,27,28,29,30,31,32,32,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,
			51,52,53,54,56,57,58,59,60,61,62,63,64,65,66,67,68,69,70,71,72,73,74,75};
	boolean won = false;
	private int turnCount = 0;
	private short winCombinationCode;
	private char rollLetter;
	private int rollNumber;
	private String[] l = new String[25];
	
	
	
	public BingoGame() {
		init();
	}
	
	public void resetGame() {
		reset();
	}
	
	public boolean getWon() {
		return won;
	}
	
	public short getWinCombinationCode() {
		return winCombinationCode;
	}
	
	public char getRollLetter() {
		return rollLetter;
	}
	
	public int getRollNumber() {
		return rollNumber;
	}
	
	public String[] to1DArray() {
	    for (int i = 0; i < board.length; i++) {
	        for (int j = 0; j < board[i].length; j++) { 
	            l[i*5 + j] = Integer.toString(board[i][j]);
	        }
	    }
		return l;
	}
	
	private void init() {
		//default constructor
		rollLetter = ' ';
		rollNumber = 0;
		board = new Integer[5][5]; // creates the 5x5 array for game board.
		int nextnum;
		//board randomization/initialization
		for (int i = 0; i < 5; i++)
		{
			for (int j = 0; j < 5; j++)
			{
				switch(j) {
				case 0:
					nextnum = generateInt(1,15);
					for(int k = 0; k < (i*5)+j; k++) {
						if(nums[k] == nextnum) {
							nextnum = generateInt(1,15);
							k = 0;
						}
					}
					nums[i*5+j] = nextnum;
					board[i][j] = nextnum;
					break;
				case 1:
					nextnum = generateInt(16,30);
					for(int k = 0; k < (i*5)+j; k++) {
						if(nums[k] == nextnum) {
							nextnum = generateInt(16,30);
							k = 0;
						}
					}
					nums[i*5+j] = nextnum;
					board[i][j] = nextnum;
					break;
				case 2:
					if (i == 2)
					{
						nextnum = 0;
					}
					else 
					{
						nextnum = generateInt(31,45);
						for(int k = 0; k < (i*5)+j; k++) {
							if(nums[k] == nextnum) {
								nextnum = generateInt(31,45);
								k = 0;
							}
						}
					}
					nums[i*5+j] = nextnum;
					board[i][j] = nextnum;
					break;
				case 3:
					nextnum = generateInt(46,60);
					for(int k = 0; k < (i*5)+j; k++) {
						if(nums[k] == nextnum) {
							nextnum = generateInt(46,60);
							k = 0;
						}
					}
					nums[i*5+j] = nextnum;
					board[i][j] = nextnum;
					break;
				case 4:
					nextnum = generateInt(61,75);
					for(int k = 0; k < (i*5)+j; k++) {
						if(nums[k] == nextnum) {
							nextnum = generateInt(61,75);
							k = 0;
						}
					}
					nums[i*5+j] = nextnum;
					board[i][j] = nextnum;
					break;
				default:
					System.err.println("Error");
				}
			}//end inner loop
		}//end outer loop
		display();
		shuffleArray(numCall);
	};
	
	private void reset() {
		init();
		won = false;
		turnCount = 0;
	}
	
	static void shuffleArray(int[] ar) {
		Random rnd = ThreadLocalRandom.current();
		for (int i = ar.length - 1; i > 0; i--) {
			int index = rnd.nextInt(i + 1);
			// Simple swap
			int a = ar[index];
			ar[index] = ar[i];
			ar[i] = a;
		}
	}
	
	public void display() {

				System.out.printf("%6s%8s%8s%8s%8s","B","I","N","G","O"); //print "BINGO" on first line of board.
				System.out.printf(System.lineSeparator());
				System.out.printf(System.lineSeparator());
				
				for (int i = 0; i < 5; i++)
				{
					for (int j = 0; j < 5; j++)
					{
						if(i == 2 && j == 2) {
							System.out.printf("%7s ", "Free");
						}
						else {
							System.out.printf("    %02d  ", board[i][j]);
						}
					}
					System.out.printf(System.lineSeparator());
					System.out.printf(System.lineSeparator());
				}
	}//end display
	
	private boolean gameWon() {
		//check row 1
		if(board[0][0] == board[0][1] && board[0][0] == board[0][2] && 
				board[0][0] == board[0][3] && board[0][0] == board[0][4] && board[0][0] == 0) {
			winCombinationCode = 1;
			return true;
		}
		
		//check row 2
		if(board[1][0] == board[1][1] && board[1][0] == board[1][2] && 
				board[1][0] == board[1][3] && board[1][0] == board[1][4] && board[1][0] == 0) {
			winCombinationCode = 2;
			return true;
		}
		
		//check row 3
		if(board[2][0] == board[2][1] && board[2][0] == board[2][3] && board[2][0] == board[2][4] && board[2][0] == 0) {
			winCombinationCode = 3;
			return true;
		}
		
		//check row 4
		if(board[3][0] == board[3][1] && board[3][0] == board[3][2] && 
				board[3][0] == board[3][3] && board[3][0] == board[3][4] && board[3][0] == 0) {
			winCombinationCode = 4;
			return true;
		}
		
		//check row 5
		if(board[4][0] == board[4][1] && board[4][0] == board[4][2] && 
				board[4][0] == board[4][3] && board[4][0] == board[4][4] && board[4][0] == 0) {
			winCombinationCode = 5;
			return true;
		}
		
		//check column 1
		if(board[0][0] == board[1][0] && board[0][0] == board[2][0] && 
				board[0][0] == board[3][0] && board[0][0] == board[4][0] && board[0][0] == 0) {
			winCombinationCode = 6;
			return true;
		}
		
		//check column 2
		if(board[0][1] == board[1][1] && board[0][1] == board[2][1] && 
				board[0][1] == board[3][1] && board[0][1] == board[4][1] && board[0][1] == 0) {
			winCombinationCode = 7;
			return true;
		}
		
		//check column 3
		if(board[0][2] == board[1][2] && board[0][2] == board[3][2] && board[0][2] == board[4][2] && board[0][2] == 0) {
			winCombinationCode = 8;
			return true;
		}
		
		//check column 4
		if(board[0][3] == board[1][3] && board[0][3] == board[2][3] && 
				board[0][3] == board[3][3] && board[0][3] == board[4][3] && board[0][3] == 0) {
			winCombinationCode = 9;
			return true;
		}
		
		//check column 5
		if(board[0][4] == board[1][4] && board[0][4] == board[2][4] && 
				board[0][4] == board[3][4] && board[0][4] == board[4][4] && board[0][4] == 0) {
			winCombinationCode = 10;
			return true;
		}
		
		//check down diag
		if(board[0][0] == board[1][1] && board[0][0] == board[3][3] && board[0][0] == board[4][4] && board[0][0] == 0) {
			winCombinationCode = 11;
			return true;
		}
		
		//check up diag
		if(board[4][0] == board[3][1] && board[4][0] == board[1][3] && board[4][0] == board[0][4] && board[4][0] == 0) {
			winCombinationCode = 12;
			return true;
		}
		return false;
	}
	
	public void find(int pos) {
		if (pos <= 15)
		{
			for(int j = 0; j<board[0].length; j++) {
				if(board[j][0] == pos) {
					setPosition(j,0);
					break;
				}
			}
		}
		else if (pos <= 30)
		{
			for(int j = 0; j<board[0].length; j++) {
				if(board[j][1] == pos) {
					setPosition(j,1);
					break;
				}
			}
		}
		else if(pos <= 45)
		{
			for(int j = 0; j<board[0].length; j++) {
				if(board[j][2] == pos) {
					setPosition(j,2);
					break;
				}
			}
		}
		else if(pos <= 60)
		{
			for(int j = 0; j<board[0].length; j++) {
				if(board[j][3] == pos) {
					setPosition(j,3);
					break;
				}
			}
		}
		else if(pos <= 75)
		{
			for(int j = 0; j<board[0].length; j++) {
				if(board[j][4] == pos) {
					setPosition(j,4);
					break;
				}
			}
		}
		else {
			System.err.println("Error");
		}
		display();
	}
	
	public boolean positionNotChanged(int row, int col) {
		if(board[row][col] == 0)
			return false;
		return true;
	}
	
	public void setPosition(int row, int col) {
		if(!won && positionNotChanged(row, col) ) {
			board[row][col] = 0;
			won = gameWon();
		}
	}
	
	public void roll() {
		rollNumber = numCall[turnCount];
		if (numCall[turnCount] <= 15)
		{
			rollLetter = 'B';
			System.out.printf("%5s %02d", "B",  numCall[turnCount]);
		}
		else if (numCall[turnCount] <= 30)
		{
			rollLetter = 'I';
			System.out.printf("%5s %02d", "I",  numCall[turnCount]);
		}
		else if(numCall[turnCount] <= 45)
		{
			rollLetter = 'N';
			System.out.printf("%5s %02d", "N",  numCall[turnCount]);
		}
		else if(numCall[turnCount] <= 60)
		{
			rollLetter = 'G';
			System.out.printf("%5s %02d", "G",  numCall[turnCount]);
		}
		else
		{
			rollLetter = 'O';
			System.out.printf("%5s %02d", "O",  numCall[turnCount]);
		}
		System.out.printf(System.lineSeparator());
		System.out.printf(System.lineSeparator());
		find(rollNumber);
		turnCount++;
		if(won) {
			System.out.println("BINGO!!");
		}
	}
	
	public static int generateInt(int low, int high) {
        return ( (int) ( (Math.random() * 1000000000 )  % ((high + 1) - low) )  + low );
    }
	public static int generateInt(double low, double high) {
		return generateInt((int) low, (int) high);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			int numberOfGames = 1;
			if(args.length>0) {
				numberOfGames = Integer.parseInt(args[0]);
			}
			
			BingoGame game = new BingoGame();
			for(int i = 0; i< 10; i++) {
				game.roll();
				System.out.println();
			}
			game.reset();
	}
}