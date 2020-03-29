package game;

/**
 * Class to represent game. Keeps track of board, players, and turn
 * @author Sachin Devasahayam
 *
 */
public class Game
{
	private Player[] players = new Player[2];
	private Player turn;
	private Board board; 
	private Status status;
	
	public Game()
	{
		players[0] = new Player();
		players[1] = new Player("AI", false, Square.O);
		
		setTurn(players[0]);
		setBoard(new Board());
		setStatus(Status.IN_PLAY);
	}
	
	public Game(Player p1, Player p2)
	{
		players[0] = p1;
		players[1] = p2;
		
		setTurn(players[0]);
		setBoard(new Board());
		setStatus(Status.IN_PLAY);
	}

	/**
	 * @return the turn
	 */
	public Player getTurn()
	{
		return turn;
	}

	/**
	 * @param turn the turn to set
	 */
	public void setTurn(Player turn)
	{
		this.turn = turn;
	}

	/**
	 * @return the board
	 */
	public Board getBoard()
	{
		return board;
	}

	/**
	 * @param board the board to set
	 */
	public void setBoard(Board board)
	{
		this.board = board;
	}

	/**
	 * 
	 * @return Both players
	 */
	public Player[] getPlayers()
	{
		return players;
	}
	
	/**
	 * 
	 * @param players the players to be set
	 */
	public void setPlayers(Player[] players)
	{
		this.players = players;
	}
	
	/**
	 * 
	 * @return what side the current player is on
	 */
	public Square getTurnSide()
	{
		return this.turn.getSide();
	}
	
	/**
	 *  Just like the Board.place() function except checks that the placement is valid
	 * Side effects: mutates board, changes turn
	 * @param sqr thing to be placed
	 * @param r row to place it
	 * @param c column to place it
	 * @return true iff valid place
	 */
	public Boolean place(Square square, int r, int c)
	{
		Square opSide = square == Square.O ? Square.X : Square.O;
		if(this.getTurnSide().equals(square) && board.getBoard()[r][c].equals(Square.BLANK) && !isWon(opSide))
		{
			this.getBoard().place(square, r, c);
			Status curStat = isWon(Square.O)?  Status.O_WON : isWon(Square.X) ? Status.X_WON : Status.IN_PLAY; 
			setStatus(curStat);
			//Switch turns
			if(this.getTurn().equals(this.getPlayers()[0]))
			{
				this.setTurn(this.getPlayers()[1]);
			}
			else
			{
				this.setTurn(this.getPlayers()[0]);
			}
			return true;
		}
		else
		{
			return false;
		}
		
	}
	
	/**
	 * Returns true if the given side has tictactoe
	 * @param side the side in question
	 * @return true if side won
	 */
	public Boolean isWon(Square side)
	{
		Square[][] board = this.getBoard().getBoard();
		
		//Horizontal wins
		Boolean win1 = board[0][0] == side && board[0][1] == side && board[0][2] == side;
		Boolean win2 = board[1][0] == side && board[1][1] == side && board[1][2] == side;
		Boolean win3 = board[2][0] == side && board[2][1] == side && board[2][2] == side;
		
		//Vertical wins
		Boolean win4 = board[0][0] == side && board[1][0] == side && board[2][0] == side;
		Boolean win5 = board[0][1] == side && board[1][1] == side && board[2][1] == side;
		Boolean win6 = board[0][2] == side && board[1][2] == side && board[2][2] == side;
		
		//Diagonal wins
		Boolean win7 = board[0][0] == side && board[1][1] == side && board[2][2] == side;
		Boolean win8 = board[0][2] == side && board[1][1] == side && board[2][0] == side;
		
		return win1||win2 ||win3 ||win4 ||win5 ||win6 ||win7 ||win8;
		
	}

	/**
	 * @return the status
	 */
	public Status getStatus()
	{
		return calcStatus();
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Status status)
	{
		this.status = status;
	}
	
	//TODO I believe this is now redundant due to calculation in place function
	/**
	 * 
	 * @return status of the game as calculated
	 */
	private Status calcStatus()
	{
		if(isWon(Square.X))
		{
			return Status.X_WON;
		}
		else if(isWon(Square.O))
		{
			return Status.O_WON;
		}
		else return Status.IN_PLAY;
	}
}
