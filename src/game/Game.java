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
	
	public Game()
	{
		players[0] = new Player();
		players[1] = new Player("AI", false);
		
		setTurn(players[0]);
		setBoard(new Board());
	}
	
	public Game(Player p1, Player p2)
	{
		players[0] = p1;
		players[1] = p2;
		
		setTurn(players[0]);
		setBoard(new Board());
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

}
