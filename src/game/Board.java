package game;

public class Board
{
	private Square[][] board;
	
	/**
	 * Constructor, creates a blank board
	 */
	public Board()
	{
		board = new Square[Constants.BOARD_SIZE][Constants.BOARD_SIZE];
		
		for(int r = 0; r < board.length; r++)
		{
			for(int c = 0; c < board[r].length; c++)
			{
				board[r][c] = Square.BLANK;
			}
		}
	}
	
	/**
	 * Prints current board
	 */
	public void print()
	{
		for(Square[] arr : board)
		{
			for(Square sqr : arr)
			{
				if(sqr.equals(Square.BLANK))
				{
					System.out.print("-"+" ");
				}
				else
				{
					System.out.print(sqr + " ");
				}
			}
			System.out.println();
		}
	}
	
	/**
	 * Board is indexed from top left down, so top row is row zero etc
	 * E.x. place(Side.X,1,1) places an X in the center
	 * @param square either an X or an O to be placed
	 * @param r the row number 
	 * @param c the column number
	 */
	public void place(Square square, int r, int c)
	{
		board[r][c] = square;
	}
	
	/**
	 * 
	 * @return the current board
	 */
	public Square[][] getBoard()
	{
		return board;
	}

}
