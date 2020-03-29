package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import game.Board;
import game.Square;

class GameTests
{

	@Test
	void boardConstructorTests()
	{
		Board board = new Board();
		
		Square blank = Square.BLANK;
		Square[][] b = {{blank,blank,blank},{blank,blank,blank},{blank,blank,blank}};
		
		for(int r = 0; r < b.length; r++)
		{
			for(int c = 0; c < b[r].length; c++)
			{
				assertEquals(b[r][c], board.getBoard()[r][c]);
			}
		}
	}
	
	@Test
	void boardPlaceTests()
	{
		Board def = new Board();
		Square x = Square.X;
		Square o = Square.O;
		
		int[][] xCoords = {{1,1},{0,0},{2,2},{0,2},{2,0}};
		int[][] oCoords = {{0,1},{1,0},{1,2},{2,1}};
		
		
		//Two for loops to place x's and o's in position
		for(int[] coord : xCoords)
		{
			def.place(x, coord[0], coord[1]);
		}
		
		for(int[] coord : oCoords)
		{
			def.place(o, coord[0], coord[1]);
		}
		
		
		Square[][] board = def.getBoard();
		
		//Assertions for each spot on the board		
		for(int[] coord: xCoords)
		{
			assertEquals(x,board[coord[0]][coord[1]]);			
		}
		
		for(int[] coord: oCoords)
		{
			assertEquals(o,board[coord[0]][coord[1]]);			
		}
	}

}
