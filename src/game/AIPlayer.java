package game;

import java.util.List;

public class AIPlayer extends Player
{
	public AIPlayer(String name, Square side)
	{
		//Must be false because not a human player
		super(name, false, side);
	}
	
//	//implements minimax algo
	/**
	 * Performs minimax algo
	 * @param depth depth of the search
	 * @param curSide current side that is playing, either maximizer or minimizer
	 * @param game the current game
	 * @return 3 element array of {score, row, col} where row and col are the row and column of the best move
	 */
	public int[] minimax(int depth, Square curSide, Game game)
	{
		//All possible next moves for curSide
		List<int[]> moves = game.nextMoves(curSide);
		//The opponent
		Square opSide = curSide == Square.O ? Square.X : Square.O;
		//Treat the invoking player as the maximizer
		Square maximizer = this.getSide();
		
		//Initial value should either be as high or as low as possible, depending on whether 
		//You are minimizing or maximizing. If you are maximizing, for example, the 
		//init value should be as low as possible, that way the first evaluation is always bigger
		//than it and thus is always initially picked
		int maxMinEval = (curSide == maximizer) ? Integer.MIN_VALUE : Integer.MAX_VALUE;
		
		int eval;
		int bestRow = -1;
		int bestCol = -1;
		
		//If we reached end depth or the game is over
		if(depth == 0 || game.isWon(curSide) || game.isWon(opSide))
		{
			//Just evaluate the current board configuration and the best move is whatever
			//the best move is currently set to
			int[] retArr = {game.getBoard().evaluate(maximizer), bestRow, bestCol};
			return retArr;
		}
		
		if(curSide == maximizer)
		{
			for(int[] move : moves)
			{
				//Perform the move
				game.getBoard().place(curSide, move[0], move[1]);
				
				//perform minimax no this new configuration, record the score
				eval = minimax(depth-1,opSide,game)[0];
				
				//if the score from the above minimax is better than your current score,
				//then it is a better move and so we set the current score equal to it 
				//and the current best move equal to it
				if(eval > maxMinEval)
				{
					maxMinEval = eval;
					bestRow = move[0];
					bestCol = move[1];
				}
				//undo move so we can look at more configurations
				game.getBoard().place(Square.BLANK, move[0], move[1]);
			}
			
			int retArr[] = {maxMinEval, bestRow, bestCol};
			return retArr;
		}
		else
		{
			for(int[] move : moves)
			{
				game.getBoard().place(curSide, move[0], move[1]);
				eval = minimax(depth-1,opSide,game)[0];
				if(eval < maxMinEval)
				{
					maxMinEval = eval;
					bestRow = move[0];
					bestCol = move[1];
				}
				game.getBoard().place(Square.BLANK, move[0], move[1]);
			}
			int retArr[] = {maxMinEval, bestRow, bestCol};
			return retArr;
		}
		
		
		
	}

	/**
	 * Performs minimax at given depth and returns only the move from it
	 * @param game the game that is being played
	 * @return an int[] {row,col} where row,col are the coordinates of the best move
	 */
	public int[] move(Game game)
	{
		//Perform minimax at given depth
		int[] miniMaxArr = this.minimax(Constants.MINIMAX_DEPTH, this.getSide(), game);
		
		//Ignore the first element since that is the score
		int[] retArr = {miniMaxArr[1], miniMaxArr[2]};
		
		return retArr;
	}
	


}
