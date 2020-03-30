package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import game.Board;
import game.Constants;
import game.Game;
import game.Player;
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

	@Test
	void playerTests()
	{
		//Default
		Player sachin = new Player();
		assertEquals(Constants.DEFAULT_PLAYER_NAME, sachin.getName());
		assertTrue(sachin.getIsHuman());
		assertEquals(Square.X, sachin.getSide());
		
		//Custom
		Player AI = new Player("AI", false, Square.O);
		assertEquals("AI", AI.getName());
		assertFalse(AI.getIsHuman());
		assertEquals(Square.O, AI.getSide());
	}
	
	@Test
	void gameTests()
	{
		//Default
		Game game = new Game();
		
		assertEquals(Constants.DEFAULT_PLAYER_NAME, game.getPlayers()[0].getName());
		assertEquals("AI", game.getPlayers()[1].getName());
		
		assertEquals("Sachin", game.getTurn().getName());
		assertEquals(Square.X, game.getTurnSide());
		assertEquals(game.getTurnSide(), game.getTurn().getSide());
		
		//Test mutability of the board
		System.out.println("The default game board:");
		game.getBoard().print();
		System.out.println();
		
		game.getBoard().place(Square.X, 1, 1);
		System.out.println("The board after placing an x in the center");
		game.getBoard().print();
		System.out.println();
		
		//Custom
		Player p1 = new Player();
		Player p2 = new Player("HAL", false, Square.O);
		Game game2 = new Game(p1,p2);
		
		assertEquals(p1, game2.getPlayers()[0]);
		assertEquals(p2, game2.getPlayers()[1]);
		assertEquals(p1,game2.getTurn());
		assertEquals(p1.getSide(), game2.getTurnSide());
		
		//Test the game place function
		assertFalse(game2.place(Square.O,1,1));
		assertEquals(p1,game2.getTurn());
		
		assertTrue(game2.place(Square.X, 1, 1));
		assertEquals(p2,game2.getTurn());
		assertEquals(p2.getSide(), game2.getTurnSide());
		//game2.getBoard().print();
		
		assertFalse(game2.place(Square.O, 1, 1));
		assertTrue(game2.place(Square.O, 0, 0));
		assertEquals(p1,game2.getTurn());
		
		game2.getBoard().print();
		
		
		//Test next moves function
		ArrayList<int[]> moves = game.nextMoves(Square.O);
		ArrayList<int[]> moves2 = game.nextMoves(Square.X);
		int[][] myGuess = {{0,0},{0,1},{0,2},{1,0},{1,2},{2,0},{2,1},{2,2}};
		
		for(int i = 0; i < myGuess.length; i++)
		{
			assertEquals(myGuess[i][0], moves.get(i)[0]);
			assertEquals(myGuess[i][1], moves.get(i)[1]);
			
			//Regardless of side, the next possible moves should be the same in this case
			assertEquals(myGuess[i][0], moves2.get(i)[0]);
			assertEquals(myGuess[i][1], moves2.get(i)[1]);
		}
		
		//Make X win
		game.getBoard().place(Square.X, 0, 1);
		game.getBoard().place(Square.X, 2, 1);
		
		//Since X has won, neither side has any more possible moves
		assertTrue(game.nextMoves(Square.O).isEmpty());
		assertTrue(game.nextMoves(Square.X).isEmpty());
		
		//Assert evaluations
		game.getBoard().print();
		
		//One line of three and 5 lines with just 1
		assertEquals(Constants.THREE_IN_LINE_SCORE + 5*Constants.ONE_IN_LINE_SCORE, game.getBoard().evaluate(Square.X));
		
		//Turn the vertical three in a row into just two in a row by placing a blocking O
		game.getBoard().place(Square.O, 0, 1);
		//Now, there should be, 4 one in a row, and 1 one in a row for the opposition
		//Thus: 
		int expected = Constants.ONE_IN_LINE_SCORE * 3;
		assertEquals(expected, game.getBoard().evaluate(Square.X));
		
		game.getBoard().place(Square.BLANK, 0, 1);
		game.getBoard().place(Square.BLANK, 2, 1);
		game.getBoard().place(Square.X, 0, 0);
		game.getBoard().place(Square.X, 0, 2);
		game.getBoard().print();
		System.out.println(game.getBoard().evaluate(Square.X));
	}
}
