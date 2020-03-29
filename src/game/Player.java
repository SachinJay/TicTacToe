package game;

/**
 * Class to represent a player
 * Keeps track of name, side the player is on, and whether the player is a human or is AI
 * @author sachi
 *
 */
public class Player
{
	private String name; 
	private Boolean isHuman;
	private Square side;
	
	/**
	 * Default constructor, given default name and default human status
	 */
	public Player()
	{
		setName(Constants.DEFAULT_PLAYER_NAME);
		setIsHuman(true);
		setSide(Square.X);
	}
	
	/**
	 * 
	 * @param name name of player
	 * @param isHuman whether or not the player is human
	 */
	public Player(String name, Boolean isHuman)
	{
		setName(name);
		setIsHuman(isHuman);
		setSide(Square.X);
	}
	
	/**
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * @return the isHuman
	 */
	public Boolean getIsHuman()
	{
		return isHuman;
	}

	/**
	 * @param isHuman the isHuman to set
	 */
	public void setIsHuman(Boolean isHuman)
	{
		this.isHuman = isHuman;
	}

	/**
	 * @return the side
	 */
	public Square getSide()
	{
		return side;
	}

	/**
	 * @param side the side to set
	 */
	public void setSide(Square side)
	{
		this.side = side;
	}

}
