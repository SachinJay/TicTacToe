package game;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.border.Border;
import javax.swing.border.MatteBorder;

public class Constants
{
	//******Board*******/
	public static final int BOARD_SIZE = 3; 
	public static final int[][] LINES = {{0,0,0,1,0,2},{1,0,1,1,1,2},{2,0,2,1,2,2}, //horizontal
			{0,0,1,0,2,0},{0,1,1,1,2,1},{0,2,1,2,2,2},//vertical
			{0,0,1,1,2,2},{0,2,1,1,2,0}};//diagonals
	
	public static final int THREE_IN_LINE_SCORE = 100;
	public static final int TWO_IN_LINE_SCORE = 10;
	public static final int ONE_IN_LINE_SCORE = 1;
	
	
	//******Player******//
	public static final String DEFAULT_PLAYER_NAME = "Sachin";
	public static final int MINIMAX_DEPTH = 2;
	
	
	//*****GUI**********/
	//Frame
	public static final String FRAME_NAME = "";
	public static final Dimension FRAME_DIM = new Dimension(500,500);

	//MenuBar
	public static final String WINDOW_MENU_NAME = "Window";

	//SquarePanel
	public static final Dimension SQUARE_DIM = new Dimension(60,60);
	public static final Dimension BOARD_DIM = new Dimension(450,450);

	public static final String IMAGES_PATH ="src/images/";
	public static final String IMAGE_SUFFIX = ".png";
	
	public static final Border BOARD_BORDER = new MatteBorder(5, 5, 5, 5, Color.BLACK);
	public static final Border SQUARE00_BORDER = new MatteBorder(0, 0, 5, 5, Color.BLACK);
	public static final Border SQUARE01_BORDER = new MatteBorder(0, 5, 5, 5, Color.BLACK);
	public static final Border SQUARE02_BORDER = new MatteBorder(0, 5, 5, 0, Color.BLACK);
	public static final Border SQUARE10_BORDER = new MatteBorder(5, 0, 5, 5, Color.BLACK);
	public static final Border SQUARE11_BORDER = new MatteBorder(5, 5, 5, 5, Color.BLACK);
	public static final Border SQUARE12_BORDER = new MatteBorder(5, 5, 5, 0, Color.BLACK);
	public static final Border SQUARE20_BORDER = new MatteBorder(5, 0, 0, 5, Color.BLACK);
	public static final Border SQUARE21_BORDER = new MatteBorder(5, 5, 0, 5, Color.BLACK);
	public static final Border SQUARE22_BORDER = new MatteBorder(5, 5, 0, 0, Color.BLACK);

	public static final Color STATUS_BACKGROUND = Color.decode("0x89CFF0"); //Baby blue
	public static final Font STATUS_FONT = new Font("Times New Roman", 1, 20);
	public static final Dimension STATUS_DIM = new Dimension(450,30);
	
}
