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
		System.out.println("Current Side: "+ curSide);
		System.out.println("Opposite side: " + opSide);
		Square maximizer = this.getSide();
		int maxMinEval = (curSide == maximizer) ? Integer.MIN_VALUE : Integer.MAX_VALUE;
		int eval;
		int bestRow = -1;
		int bestCol = -1;
		
		//If we reached end depth or the game is over
		if(depth == 0 || game.isWon(curSide) || game.isWon(opSide))
		{
			System.out.println("In minimax base case");
			System.out.println("Why in base case?:\nDepth is zero?: "+depth+"\nX won?: "+(game.isWon(Square.X)) 
					+"\nO won?: "+ (game.isWon(Square.O)));
			int[] retArr = {game.getBoard().evaluate(maximizer), bestRow, bestCol};
			return retArr;
		}
		if(curSide == maximizer)
		{
			System.out.println("In maximizer if block");
			for(int[] move : moves)
			{
				System.out.println("In the move for loop of maximizer block");
				game.getBoard().place(curSide, move[0], move[1]);
				eval = minimax(depth-1,opSide,game)[0];
				System.out.println("Evaluation for below board: " + eval);
				game.getBoard().print();
				System.out.println("Current score: " + maxMinEval);
				System.out.println("Max of current and evaluation: " + maxMinEval);
				System.out.println("Should enter the if block to assign move: "+ (eval>maxMinEval));
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
			System.out.println("In minimizer block");
			for(int[] move : moves)
			{
				System.out.println("In for loop of minimizer");
				game.getBoard().place(curSide, move[0], move[1]);
				eval = minimax(depth-1,opSide,game)[0];
				System.out.println("Evaluation for below board: " + eval);
				game.getBoard().print();
				System.out.println("Current score: " + maxMinEval);
				System.out.println("Min of current score and eval: " + maxMinEval);
				System.out.println("Should enter the if block to assign move: "+ (eval<maxMinEval));
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
		int[] miniMaxArr = this.minimax(3, this.getSide(), game);
		int[] retArr = {miniMaxArr[1], miniMaxArr[2]};
		
		System.out.println("Maximized score: " + miniMaxArr[0]);
		
		return retArr;
	}
	


}
