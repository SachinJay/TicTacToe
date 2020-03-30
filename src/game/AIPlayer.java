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
	 * @param game
	 * @return
	 */
	public int[] minimax(int depth, Square curSide, Game game)
	{
		List<int[]> moves = game.nextMoves(curSide);
		Square opSide = curSide == Square.O ? Square.X : Square.O;
		Square maximizer = this.getSide();
		int maxMinEval = (curSide == maximizer) ? Integer.MIN_VALUE : Integer.MAX_VALUE;
		int eval;
		int bestRow = -1;
		int bestCol = -1;
		
		//If we reached end depth or the game is over
		if(depth == 0 || game.isWon(curSide) || game.isWon(opSide))
		{
			int[] retArr = {game.getBoard().evaluate(maximizer), bestRow, bestCol};
			return retArr;
		}
		if(curSide == maximizer)
		{
			for(int[] move : moves)
			{
				game.getBoard().place(curSide, move[0], move[1]);
				eval = minimax(depth-1,opSide,game)[0];
				if(eval > maxMinEval)
				{
					maxMinEval = eval;
					bestRow = move[0];
					bestCol = move[1];
				}
				//undo move
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

	public int[] move(Game game)
	{
		int[] miniMaxArr = this.minimax(2, this.getSide(), game);
		int[] retArr = {miniMaxArr[1], miniMaxArr[2]};
		
		return retArr;
	}
	


}
