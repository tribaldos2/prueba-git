package prprpr;

import java.util.*;

public class Flip {
	// TODO
	public static void main(String[] args) {
		int score = 0;
		boolean nextPuzzle = true;
		
		while (nextPuzzle) {
			nextPuzzle = doPuzzle(score);
			if (nextPuzzle) {
				score++;
				System.out.println("Congratulations! You've solved " 
						+ score + " puzzles.");
			} else {
				System.out.println("You've failed. Your final score is " + score);
			}
		}
	}
	
	public static boolean doPuzzle(int score) {
		Scanner sc = new Scanner(System.in);
		boolean win = false;
		int posX, posY;
		boolean[][] solutionBoard = chooseBoard(score);
		boolean[][] startingBoard = 
				new boolean[solutionBoard.length][solutionBoard[0].length];
		copyBoard(startingBoard, solutionBoard);
		boolean[][] currentBoard = 
				new boolean[solutionBoard.length][solutionBoard[0].length];
		int moves = setBoard(startingBoard, score);
		copyBoard(currentBoard, startingBoard);
		
		for (int i = 0; i < 3 && !win; i++) {
			for (int j = 0; j < moves; j++) {
				printBoard(solutionBoard);
				System.out.println();
				printBoard(currentBoard);
				System.out.println("Moves left: " + (moves - j) + ". Choose your move:");
				do {
					posX = sc.nextInt() - 1;
					posY = sc.nextInt() - 1;
					sc.nextLine();
				} while (posX < 0 || posX >= currentBoard.length || 
						posY < 0 || posY >= currentBoard[0].length);
				
				flip(currentBoard, posX, posY);
			}
			printBoard(solutionBoard);
			System.out.println();
			printBoard(currentBoard);
			if (equalBoard(solutionBoard, currentBoard)) {
				win = true;
			} else {
				System.out.println("You have " + (2 - i) + " more tries.");
				copyBoard(currentBoard, startingBoard);
			}
		}
			
		return win;
	}
	
	public static boolean[][] chooseBoard(int score) {
		int size = setBoardSize(score);
		boolean[][] board = new boolean[size][size];
		llenaBool(board, false);
		drawPattern(board);
		
		return board;
	}
	
	public static int setBoard(boolean[][] board, int score) {
		Random rd = new Random();
		int posX, posY;
		int moves = setMoves(score);
		
		for (int i = 0; i < moves; i++) {
			posX = (int) (rd.nextDouble() * board.length);
			posY = (int) (rd.nextDouble() * board[0].length);
			flip(board, posX, posY);
		}
		
		return moves;
	}
	
	public static void flip(boolean[][] board, int posX, int posY) {
		int position = ((posX == 0) ? 3 : 0) + ((posX == board.length - 1) ? 6 : 0) 
				+ ((posY == 0) ? 1 : 0) + ((posY == board[0].length - 1) ? 2 : 0);
		
		switch (position) {
			case 0:
				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < 3; j++) {
						board[posX - 1 + i][posY - 1 + j] = 
								!board[posX - 1 + i][posY - 1 + j];
					}
				}
			break;
			case 1:
				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < 2; j++) {
						board[posX - 1 + i][posY + j] = 
								!board[posX - 1 + i][posY + j];
					}
				}
			break;
			case 2:
				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < 2; j++) {
						board[posX - 1 + i][posY - 1 + j] = 
								!board[posX - 1 + i][posY - 1 + j];
					}
				}
			break;
			case 3:
				for (int i = 0; i < 2; i++) {
					for (int j = 0; j < 3; j++) {
						board[posX + i][posY - 1 + j] = 
								!board[posX + i][posY - 1 + j];
					}
				}
			break;
			case 4:
				for (int i = 0; i < 2; i++) {
					for (int j = 0; j < 2; j++) {
						board[posX + i][posY + j] = 
								!board[posX + i][posY + j];
					}
				}
			break;
			case 5:
				for (int i = 0; i < 2; i++) {
					for (int j = 0; j < 2; j++) {
						board[posX + i][posY - 1 + j] = 
								!board[posX + i][posY - 1 + j];
					}
				}
			break;
			case 6:
				for (int i = 0; i < 2; i++) {
					for (int j = 0; j < 3; j++) {
						board[posX - 1 + i][posY - 1 + j] = 
								!board[posX - 1 + i][posY - 1 + j];
					}
				}
			break;
			case 7:
				for (int i = 0; i < 2; i++) {
					for (int j = 0; j < 2; j++) {
						board[posX - 1 + i][posY + j] = 
								!board[posX - 1 + i][posY + j];
					}
				}
			break;
			case 8:
				for (int i = 0; i < 2; i++) {
					for (int j = 0; j < 2; j++) {
						board[posX - 1 + i][posY - 1 + j] = 
								!board[posX - 1 + i][posY - 1 + j];
					}
				}
			break;
			default:
				System.out.println("Error.");
				break;
		}
	}
	
	public static void copyBoard(boolean[][] modified, boolean[][] copied) {
		for (int i = 0; i < modified.length; i++) {
			for (int j = 0; j < modified[0].length; j++) {
				modified[i][j] = copied[i][j];
			}
		}
	}
	
	public static void drawPattern(boolean[][] board) {
		Random rd = new Random();
		int pattern;
		
		switch (board.length) {
			case 4:
				pattern = (int) (rd.nextDouble() * 4);
				switch (pattern) {
					case 0:
						board[0][1] = true;
						board[0][2] = true;
						board[1][0] = true;
						board[1][3] = true;
						board[2][0] = true;
						board[2][3] = true;
						board[3][1] = true;
						board[3][2] = true;
					break;
					case 1:
						board[1][1] = true;
						board[1][2] = true;
						board[2][1] = true;
						board[2][2] = true;
					break;
					case 2:
						board[0][0] = true;
						board[0][3] = true;
						board[3][0] = true;
						board[3][3] = true;
					break;
					default:
					break;
				}
			break;
			case 5:
				pattern = (int) (rd.nextDouble() * 5);
				switch (pattern) {
					case 0:
						board[0][0] = true;
						board[0][4] = true;
						board[1][1] = true;
						board[1][3] = true;
						board[2][2] = true;
						board[3][1] = true;
						board[3][3] = true;
						board[4][0] = true;
						board[4][4] = true;
					break;
					case 1:
						board[0][1] = true;
						board[0][3] = true;
						board[1][0] = true;
						board[1][2] = true;
						board[1][4] = true;
						board[2][1] = true;
						board[2][3] = true;
						board[3][0] = true;
						board[3][2] = true;
						board[3][4] = true;
						board[4][1] = true;
						board[4][3] = true;
					break;
					case 2:
						board[0][1] = true;
						board[0][2] = true;
						board[0][3] = true;
						board[1][0] = true;
						board[1][4] = true;
						board[2][0] = true;
						board[2][2] = true;
						board[2][4] = true;
						board[3][0] = true;
						board[3][4] = true;
						board[4][1] = true;
						board[4][2] = true;
						board[4][3] = true;
					break;
					case 3:
						board[0][0] = true;
						board[0][4] = true;
						board[1][2] = true;
						board[2][1] = true;
						board[2][2] = true;
						board[2][3] = true;
						board[3][2] = true;
						board[4][0] = true;
						board[4][4] = true;
					break;
					default:
					break;
				}
			break;
			case 6:
				pattern = (int) (rd.nextDouble() * 6);
				switch (pattern) {
					case 0:
						board[0][1] = true;
						board[0][4] = true;
						board[1][0] = true;
						board[1][1] = true;
						board[1][2] = true;
						board[1][3] = true;
						board[1][4] = true;
						board[1][5] = true;
						board[2][1] = true;
						board[2][4] = true;
						board[3][1] = true;
						board[3][4] = true;
						board[4][0] = true;
						board[4][1] = true;
						board[4][2] = true;
						board[4][3] = true;
						board[4][4] = true;
						board[4][5] = true;
						board[5][1] = true;
						board[5][4] = true;
					break;
					case 1:
						board[0][0] = true;
						board[0][2] = true;
						board[0][3] = true;
						board[0][5] = true;
						board[1][1] = true;
						board[1][4] = true;
						board[2][0] = true;
						board[2][5] = true;
						board[3][0] = true;
						board[3][5] = true;
						board[4][1] = true;
						board[4][4] = true;
						board[5][0] = true;
						board[5][2] = true;
						board[5][3] = true;
						board[5][5] = true;
					break;
					case 2:
						board[0][0] = true;
						board[0][1] = true;
						board[0][4] = true;
						board[0][5] = true;
						board[1][0] = true;
						board[1][1] = true;
						board[1][4] = true;
						board[1][5] = true;
						board[2][2] = true;
						board[2][3] = true;
						board[3][2] = true;
						board[3][3] = true;
						board[4][0] = true;
						board[4][1] = true;
						board[4][4] = true;
						board[4][5] = true;
						board[5][0] = true;
						board[5][1] = true;
						board[5][4] = true;
						board[5][5] = true;
					break;
					case 3:
						board[0][1] = true;
						board[0][2] = true;
						board[0][3] = true;
						board[0][4] = true;
						board[1][0] = true;
						board[1][5] = true;
						board[2][0] = true;
						board[2][2] = true;
						board[2][3] = true;
						board[2][5] = true;
						board[3][0] = true;
						board[3][2] = true;
						board[3][3] = true;
						board[3][5] = true;
						board[4][0] = true;
						board[4][5] = true;
						board[5][1] = true;
						board[5][2] = true;
						board[5][3] = true;
						board[5][4] = true;
					break;
					case 4:
						board[1][2] = true;
						board[1][3] = true;
						board[2][1] = true;
						board[2][2] = true;
						board[2][3] = true;
						board[2][4] = true;
						board[3][1] = true;
						board[3][2] = true;
						board[3][3] = true;
						board[3][4] = true;
						board[4][2] = true;
						board[4][3] = true;
					break;
					default:
					break;
				}
			break;
			case 7:
				pattern = (int) (rd.nextDouble() * 7);
				switch (pattern) {
					case 0:
						board[0][0] = true;
						board[0][6] = true;
						board[1][2] = true;
						board[1][4] = true;
						board[2][1] = true;
						board[2][5] = true;
						board[3][3] = true;
						board[4][1] = true;
						board[4][5] = true;
						board[5][2] = true;
						board[5][4] = true;
						board[6][0] = true;
						board[6][6] = true;
					break;
					case 1:
						board[1][2] = true;
						board[1][3] = true;
						board[1][4] = true;
						board[2][1] = true;
						board[2][2] = true;
						board[2][4] = true;
						board[2][5] = true;
						board[3][1] = true;
						board[3][5] = true;
						board[4][1] = true;
						board[4][2] = true;
						board[4][4] = true;
						board[4][5] = true;
						board[5][2] = true;
						board[5][3] = true;
						board[5][4] = true;
					break;
					case 2:
						board[0][3] = true;
						board[1][1] = true;
						board[1][5] = true;
						board[2][2] = true;
						board[2][4] = true;
						board[3][0] = true;
						board[3][6] = true;
						board[4][2] = true;
						board[4][4] = true;
						board[5][1] = true;
						board[5][5] = true;
						board[6][3] = true;
					break;
					case 3:
						board[0][1] = true;
						board[0][2] = true;
						board[0][4] = true;
						board[0][5] = true;
						board[1][0] = true;
						board[1][3] = true;
						board[1][6] = true;
						board[2][0] = true;
						board[2][6] = true;
						board[3][1] = true;
						board[3][3] = true;
						board[3][5] = true;
						board[4][0] = true;
						board[4][6] = true;
						board[5][0] = true;
						board[5][3] = true;
						board[5][6] = true;
						board[6][1] = true;
						board[6][2] = true;
						board[6][4] = true;
						board[6][5] = true;
					break;
					case 4:
						board[0][0] = true;
						board[0][2] = true;
						board[0][4] = true;
						board[0][6] = true;
						board[1][2] = true;
						board[1][4] = true;
						board[2][0] = true;
						board[2][1] = true;
						board[2][5] = true;
						board[2][6] = true;
						board[3][3] = true;
						board[4][0] = true;
						board[4][1] = true;
						board[4][5] = true;
						board[4][6] = true;
						board[5][2] = true;
						board[5][4] = true;
						board[6][0] = true;
						board[6][2] = true;
						board[6][4] = true;
						board[6][6] = true;
					break;
					case 5:
						board[0][1] = true;
						board[0][5] = true;
						board[1][0] = true;
						board[1][3] = true;
						board[1][6] = true;
						board[2][2] = true;
						board[2][4] = true;
						board[3][1] = true;
						board[3][5] = true;
						board[4][2] = true;
						board[4][4] = true;
						board[5][0] = true;
						board[5][3] = true;
						board[5][6] = true;
						board[6][1] = true;
						board[6][5] = true;
					break;
					default:
					break;
				}
			break;
			case 8:
				pattern = (int) (rd.nextDouble() * 8);
				switch (pattern) {
					case 0:
						board[1][1] = true;
						board[1][2] = true;
						board[1][5] = true;
						board[1][6] = true;
						board[2][1] = true;
						board[2][2] = true;
						board[2][5] = true;
						board[2][6] = true;
						board[5][1] = true;
						board[5][2] = true;
						board[5][5] = true;
						board[5][6] = true;
						board[6][1] = true;
						board[6][2] = true;
						board[6][5] = true;
						board[6][6] = true;
					break;
					case 1:
						board[0][0] = true;
						board[0][3] = true;
						board[0][4] = true;
						board[0][7] = true;
						board[3][0] = true;
						board[3][3] = true;
						board[3][4] = true;
						board[3][7] = true;
						board[4][0] = true;
						board[4][3] = true;
						board[4][4] = true;
						board[4][7] = true;
						board[7][0] = true;
						board[7][3] = true;
						board[7][4] = true;
						board[7][7] = true;
					break;
					case 2:
						board[0][1] = true;
						board[0][2] = true;
						board[0][5] = true;
						board[0][6] = true;
						board[1][0] = true;
						board[1][3] = true;
						board[1][4] = true;
						board[1][7] = true;
						board[2][0] = true;
						board[2][3] = true;
						board[2][4] = true;
						board[2][7] = true;
						board[3][1] = true;
						board[3][2] = true;
						board[3][5] = true;
						board[3][6] = true;
						board[4][1] = true;
						board[4][2] = true;
						board[4][5] = true;
						board[4][6] = true;
						board[5][0] = true;
						board[5][3] = true;
						board[5][4] = true;
						board[5][7] = true;
						board[6][0] = true;
						board[6][3] = true;
						board[6][4] = true;
						board[6][7] = true;
						board[7][1] = true;
						board[7][2] = true;
						board[7][5] = true;
						board[7][6] = true;
					break;
					case 3:
						board[0][1] = true;
						board[0][6] = true;
						board[1][0] = true;
						board[1][2] = true;
						board[1][5] = true;
						board[1][7] = true;
						board[2][1] = true;
						board[2][6] = true;
						board[3][3] = true;
						board[3][4] = true;
						board[4][3] = true;
						board[4][4] = true;
						board[5][1] = true;
						board[5][6] = true;
						board[6][0] = true;
						board[6][2] = true;
						board[6][5] = true;
						board[6][7] = true;
						board[7][1] = true;
						board[7][6] = true;
					break;
					case 4:
						board[0][0] = true;
						board[0][7] = true;
						board[1][2] = true;
						board[1][5] = true;
						board[2][1] = true;
						board[2][2] = true;
						board[2][5] = true;
						board[2][6] = true;
						board[3][3] = true;
						board[3][4] = true;
						board[4][3] = true;
						board[4][4] = true;
						board[5][1] = true;
						board[5][2] = true;
						board[5][5] = true;
						board[5][6] = true;
						board[6][2] = true;
						board[6][5] = true;
						board[7][0] = true;
						board[7][7] = true;
					break;
					case 5:
						board[0][0] = true;
						board[0][3] = true;
						board[0][4] = true;
						board[0][7] = true;
						board[1][1] = true;
						board[1][2] = true;
						board[1][5] = true;
						board[1][6] = true;
						board[2][1] = true;
						board[2][6] = true;
						board[3][0] = true;
						board[3][7] = true;
						board[4][0] = true;
						board[4][7] = true;
						board[5][1] = true;
						board[5][6] = true;
						board[6][1] = true;
						board[6][2] = true;
						board[6][5] = true;
						board[6][6] = true;
						board[7][0] = true;
						board[7][3] = true;
						board[7][4] = true;
						board[7][7] = true;
					break;
					case 6:
						board[0][0] = true;
						board[0][2] = true;
						board[0][5] = true;
						board[0][7] = true;
						board[2][0] = true;
						board[2][2] = true;
						board[2][5] = true;
						board[2][7] = true;
						board[3][3] = true;
						board[3][4] = true;
						board[4][3] = true;
						board[4][4] = true;
						board[5][0] = true;
						board[5][2] = true;
						board[5][5] = true;
						board[5][7] = true;
						board[7][0] = true;
						board[7][2] = true;
						board[7][5] = true;
						board[7][7] = true;
					break;
					default:
					break;
				}
			break;
			default:
			break;
		}
		
	}
                  	
	public static int setBoardSize(int score) {
		int size = 4;
		int[] jumpPoints = {10, 20, 40, 70};
		
		for (int i = 0; i < jumpPoints.length; i++) {
			if (score >= jumpPoints[i]) {
				size++;
			}
		}
		
		
		return size;
	}
	
	public static int setMoves(int score) {
		int moves = 1;
		int[] jumpPoints = {2, 5, 15, 25, 35, 50, 75, 90, 95};
		
		for (int i = 0; i < jumpPoints.length; i++) {
			if (score >= jumpPoints[i]) {
				moves++;
			}
		}
		
		
		return moves;
	}
	
	public static void printBoard(boolean[][] board) {
		System.out.print("  ");
		for (int i = 0; i < board[0].length; i++) {
			System.out.print((i + 1) + " ");
		}
		System.out.println();
		
		for (int i = 0; i < board.length; i++) {
			System.out.print((i + 1) + " ");
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j])
					System.out.print("O ");
				else
					System.out.print("X ");
			}
			System.out.println();
		}
	}
	
	public static void llenaBool(boolean[][] a, boolean x) {
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[0].length; j++) {
				a[i][j] = x;
			}
		}
	}
	
	public static boolean equalBoard(boolean[][] b1, boolean[][] b2) {
		boolean same = true;
		for (int i = 0; i < b1.length; i++) {
			for (int j = 0; j < b1[0].length; j++) {
				if (b1[i][j] != b2[i][j]) {
					same = false;
				}
			}
		}
		
		return same;
	}
}
