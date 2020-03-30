package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import game.AIPlayer;
import game.Game;
import game.Player;
import game.Square;

class MinimaxTests
{

	@Test
	void test()
	{
		AIPlayer hal = new AIPlayer("HAL", Square.X);
		Player sachin = new Player("Sachin", true, Square.O);
		Game game = new Game(hal, sachin);
		
		int[] move = hal.move(game);
		System.out.println(move[0] + ", " +move[1]);		
		
		//MY move
		game.place(Square.O, 1, 1);
		game.getBoard().print();		
		
		int[] move2 = hal.move(game);
		System.out.println(move2[0] + ", " +move2[1]);
		
		//Something seems off so let's check the base case
		game.getBoard().place(Square.X, 0, 0);
		game.getBoard().place(Square.X, 0, 1);
		game.getBoard().place(Square.X, 0, 2);
		game.getBoard().print();
		
		//Now the game is in a win state, so the AI player has no next move
		assertEquals(-1,hal.move(game)[0]);
		assertEquals(-1,hal.move(game)[1]);
		
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		//Just because those work does not mean the function works, the issue is that it always returns 
		//-1,-1
		
		//Test inductive step
		game.getBoard().place(Square.BLANK, 0, 1);
		
		int[] move3 = hal.move(game);
		System.out.println("The move: (" + move[0]+","+move[1]+")");
		game.getBoard().print();
	}

}
