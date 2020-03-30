package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import game.AIPlayer;
import game.Game;
import game.Player;
import game.Square;

class MinimaxTests
{

	//My tests that helped me find the bug in minimax
	@Test
	void initialTests()
	{
		AIPlayer hal = new AIPlayer("HAL", Square.X);
		Player sachin = new Player("Sachin", true, Square.O);
		Game game = new Game(hal, sachin);
		
		int[] move = hal.move(game);
		System.out.println(move[0] + ", " +move[1]);		
		
		//MY move
		game.place(Square.O, 1, 1);
		
		int[] move2 = hal.move(game);
		System.out.println(move2[0] + ", " +move2[1]);
		
		//Something seems off so let's check the base case
		game.getBoard().place(Square.X, 0, 0);
		game.getBoard().place(Square.X, 0, 1);
		game.getBoard().place(Square.X, 0, 2);
		
		//Now the game is in a win state, so the AI player has no next move
		assertEquals(-1,hal.move(game)[0]);
		assertEquals(-1,hal.move(game)[1]);
		
		//Just because those work does not mean the function works, the issue is that it always returns 
		//-1,-1
		
		//Test inductive step
		game.getBoard().place(Square.BLANK, 0, 1);
		
		int[] move3 = hal.move(game);
		System.out.println("The move: (" + move3[0]+","+move3[1]+")");
	}
	
	@Test
	void inductiveStepCases()
	{
		AIPlayer hal = new AIPlayer("HAL", Square.X);
		Player sachin = new Player("Sachin", true, Square.O);
		Game game = new Game(hal, sachin);
		//Above already tested the base case where you there is already a win
		//What's left is testing average everyday cases
		
		//Obvious first case: blank board, first move should be in the center
		int[] move = hal.move(game);
		assertEquals(1,move[0]);
		assertEquals(1,move[1]);
		
		//Commit to that move
		game.place(Square.X, move[0], move[1]);
		
		//My player will try to block
		game.place(Square.O, 0, 2);
	}
	
	//Curious how this will go
	@Test
	void twoAIs()
	{
		
		AIPlayer hal = new AIPlayer("HAL", Square.X);
		AIPlayer sachin = new AIPlayer("Sachin",Square.O);
		Game game = new Game(hal, sachin);
		
		for(int i = 0; i < 9; i++)
		{
			int[] move = i % 2 == 0 ? hal.move(game) : sachin.move(game);
			Square side = i%2 == 0 ? hal.getSide() : sachin.getSide();
			
			game.place(side, move[0], move[1]);
		}
		
		game.getBoard().print();
		
	}

}
