package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import game.Board;
import game.Square;

class GameTests
{

	@Test
	void test()
	{
		Board board = new Board();
		board.print();
	}

}
