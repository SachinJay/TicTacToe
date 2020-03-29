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
	
	/**
	 * Evaluates the board based on following heuristic
	 * +100 for EACH 3-in-a-line for computer.
	 * +10 for EACH 2-in-a-line (with a empty cell) for computer.
	 * +1 for EACH 1-in-a-line (with two empty cells) for computer.
	 * Negative scores for opponent, i.e., -100, -10, -1 for EACH opponent's 3-in-a-line, 2-in-a-line and 1-in-a-line.
	 * 0 otherwise (empty lines or lines with both computer's and opponent's seed).
	 * Source: https://www3.ntu.edu.sg/home/ehchua/programming/java/JavaGame_TicTacToe_AI.html#zz-1.5
	 * @param maximizer the side that is maximizing (i.e. what side the AI is on) 
	 * @return the evaluation of the board according to the heuristic
	 */
	public int evaluate(Square maximizer)
	{
		int score = 0;
		
		for(int[] line : Constants.LINES)
		{
			System.out.println("Line: "+line[0]+line[1]+line[2]+line[3]+line[4]+line[5]);
			score+= evalLine(line[0],line[1],line[2],line[3],line[4],line[5],maximizer);
			System.out.println();
		}
		
		return score;
	}
	
	/**
	 * helper function
	 * @param r1 row 1
	 * @param c1 column 1
	 * @param r2 row 2
	 * @param c2 column 2
	 * @param r3 row 3
	 * @param c3 column 3
	 * @param maximizer the maximizing side
	 * @return score of this line
	 */
	private int evalLine(int r1, int c1, int r2, int c2, int r3, int c3, Square maximizer)
	{
		//TODO delete print statements when done debugging

		int score = 0;
		Square minimizer = maximizer == Square.O ? Square.X : Square.O;

		// First square
		if (board[r1][c1] == maximizer)
		{
			System.out.println("max in first");
			score = Constants.ONE_IN_LINE_SCORE;
		} 
		else if (board[r1][c1] == minimizer)
		{
			System.out.println("min in first");
			score = -1*Constants.ONE_IN_LINE_SCORE;
		}

		// Second square
		if (board[r2][c2] == maximizer)
		{
			System.out.println("max in second");
			//Previous square was maximizer
			if (score == Constants.ONE_IN_LINE_SCORE)
			{ 
				System.out.println("Max in first");
				score = Constants.TWO_IN_LINE_SCORE;
			} 
			//Previous square was minimizer
			else if (score == -1*Constants.ONE_IN_LINE_SCORE)
			{ 
				System.out.println("Min in first");
				return 0;
			} 
			//No one had the first square
			else
			{ 
				System.out.println("no one had first");
				score = Constants.ONE_IN_LINE_SCORE;
			}
		} 
		else if (board[r2][c2] == minimizer)
		{
			System.out.println("min in second");
			// square 1 is minimizer
			if (score == -1*Constants.ONE_IN_LINE_SCORE)
			{ 
				System.out.println("min in first");
				score = -1*Constants.TWO_IN_LINE_SCORE;
			} 
			// square 1 is maximizer
			else if (score == Constants.ONE_IN_LINE_SCORE)
			{ 
				System.out.println("max in second");
				return 0;
			} 
			// square 1 is empty
			else
			{ 
				System.out.println("first empty");
				score = -1*Constants.ONE_IN_LINE_SCORE;
			}
		}

		// Third Square
		if (board[r3][c3] == maximizer)
		{
			System.out.println("max in third");
			// square 1 and/or square 2 is maximizer
			if (score > 0)
			{ 
				System.out.println("max was in first or second or both");
				System.out.println(score);
				score *= 10;
				System.out.println(score);
			} 
			else if (score < 0)
			{ // cell1 and/or cell2 is oppSeed
				System.out.println("min was in first or second or both");
				return 0;
			} 
			else
			{ // cell1 and cell2 are empty
				System.out.println("no one in first or second");
				score = Constants.ONE_IN_LINE_SCORE;
			}
		} 
		else if (board[r3][c3] == minimizer)
		{
			System.out.println("min in third");
			if (score < 0)
			{ // cell1 and/or cell2 is oppSeed
				System.out.println("min in second or first or both");
				score *= 10;
			} 
			else if (score > 1)
			{ // cell1 and/or cell2 is mySeed
				System.out.println("max in second or first or both");
				return 0;
			} 
			else
			{ // cell1 and cell2 are empty
				System.out.println("first and second empty");
				score = -1*Constants.ONE_IN_LINE_SCORE;
			}
		}
		
		System.out.println(score);
		return score;

	}

}
