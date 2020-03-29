package game;

public class Board
{
	private Square[][] board;
	
	public Board()
	{
		Square blank = Square.BLANK;
		for(Square arr[] : board)
		{
			for(Square sqr : arr)
			{
				sqr = blank;				
			}
		}
	}

}
