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
	public int[] minimax(int depth, Square curSide, Game game)
	{
		List<int[]> moves = game.nextMoves(curSide);
		Square opSide = curSide == Square.O ? Square.X : Square.O;
		Square maximizer = this.getSide();
		int maxMinEval = (curSide == maximizer) ? Integer.MIN_VALUE : Integer.MAX_VALUE;
		int currentScore;
		int bestRow = -1;
		int bestCol = -1;
		int[] theMove = new int[2];
		
		//If we reached end depth or the game is over
		if(depth == 0 || game.isWon(curSide) || game.isWon(opSide))
		{
			int[] retArr = {game.getBoard().evaluate(maximizer), theMove[0], theMove[1]};
			return retArr;
		}
		if(curSide == maximizer)
		{
			for(int[] move : moves)
			{
				game.getBoard().place(curSide, move[0], move[1]);
				int eval = minimax(depth-1,opSide,game)[0];
				maxMinEval = Math.max(maxMinEval, eval);
				if(eval > maxMinEval)
				{
					theMove[0] = move[0];
					theMove[1] = move[1];
				}
				//undo move
				game.getBoard().place(Square.BLANK, move[0], move[1]);
			}
			int retArr[] = {maxMinEval, theMove[0], theMove[1]};
			return retArr;
		}
		else
		{
			for(int[] move : moves)
			{
				game.getBoard().place(curSide, move[0], move[1]);
				int eval = minimax(depth-1,opSide,game)[0];
				maxMinEval = Math.min(maxMinEval, eval);
				if(eval < maxMinEval)
				{
					theMove[0] = move[0];
					theMove[1] = move[1];
				}
				game.getBoard().place(Square.BLANK, move[0], move[1]);
			}
			int retArr[] = {maxMinEval, theMove[0], theMove[1]};
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
