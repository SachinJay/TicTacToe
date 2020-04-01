package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import game.AIPlayer;
import game.Board;
import game.Constants;
import game.Game;
import game.Player;
import game.Square;

public class Grid
{
	private JFrame gameFrame;
	private Game game;
	private BoardPanel boardPanel;
	private StatusPanel statusPanel;
	private Player p1;
	private AIPlayer aiPlayer;
	
	
	public Grid()
	{
		//Basics of the frame
		gameFrame = new JFrame(Constants.FRAME_NAME);
		gameFrame.setLayout(new BorderLayout());
		
		//Give it a menuBar
		JMenuBar menuBar= new JMenuBar();
		addToMenuBar(menuBar);
		gameFrame.setJMenuBar(menuBar);
		
		//Game between person and AI
		p1 = new Player("Sachin", false, Square.X);
		aiPlayer = new AIPlayer("HAL",Square.O);
		game = new Game(p1, aiPlayer);
		
		boardPanel = new BoardPanel();
		statusPanel = new StatusPanel(game);
		
		gameFrame.add(boardPanel, BorderLayout.CENTER);
		gameFrame.add(statusPanel,BorderLayout.SOUTH);

		//Give it dimensions and make it visible
		gameFrame.setSize(Constants.FRAME_DIM);
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame.setVisible(true);
	}

	/**
	 * Adds different menus to the menu bar
	 * @param menuBar the JMenuBar to add to
	 */
	private void addToMenuBar(JMenuBar menuBar)
	{
		menuBar.add(createWindowMenu());
	}

	/**
	 * Creates window menu, used to exit the GUI
	 * @return window menu
	 */
	private JMenu createWindowMenu()
	{
		JMenu windowMenu = new JMenu(Constants.WINDOW_MENU_NAME);
		JMenuItem exit = new JMenuItem("Exit");
		exit.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				System.exit(0);
			}
		});;
		
		windowMenu.add(exit);
		return windowMenu;
	}
	
	private class BoardPanel extends JPanel
	{
		private List<SquarePanel> boardSquares;
		
		public BoardPanel()
		{
			super(new GridLayout(Constants.BOARD_SIZE, Constants.BOARD_SIZE));
			boardSquares = new ArrayList<>();
			
			for(int r = 0; r < Constants.BOARD_SIZE; r++)
			{
				for(int c = 0; c < Constants.BOARD_SIZE; c++)
				{
					SquarePanel sp = new SquarePanel(this, r, c);
					boardSquares.add(sp);
					add(sp);
				}
			}
			
			setPreferredSize(Constants.BOARD_DIM);
			setBackground(Color.white);
			validate();
		}
		
		public void drawBoard(Board board)
		{			
			removeAll();
			
			for(SquarePanel sp: boardSquares)
			{
				sp.drawSquare(board);
				add(sp);
			}
			
			validate();
			repaint();
			
			if(game.isWon(Square.X) || game.isWon(Square.O))
			{
				int response = JOptionPane.showConfirmDialog(this, "Wanna restart the game?");
				if(response == 0)
				{
					Grid newGame = new Grid();
				}
			}
		}
		
	}
	
	private class SquarePanel extends JPanel
	{
		//row and column
		private int r;
		private int c;
		
		public SquarePanel(BoardPanel bp, int row, int col)
		{
			super(new GridBagLayout());
			this.r = row; 
			this.c = col;
			setBackground(Color.white);
			assignBorder();
			setPreferredSize(Constants.SQUARE_DIM);
			assignSquareMark(game.getBoard());
			
			addMouseListener(new MouseListener()
			{
				
				@Override
				public void mouseReleased(MouseEvent e)
				{
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mousePressed(MouseEvent e)
				{
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseExited(MouseEvent e)
				{
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseEntered(MouseEvent e)
				{
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseClicked(MouseEvent e)
				{
					game.place(game.getTurnSide(), r, c);
					game.getBoard().print();
					System.out.println();
					
					SwingUtilities.invokeLater(new Runnable()
					{
						
						@Override
						public void run()
						{
							bp.drawBoard(game.getBoard());
							statusPanel.setText(game);
						}
					});					
				}
			});
		}
		
		public void drawSquare(Board board)
		{
			setBackground(Color.white);
			assignSquareMark(board);
			assignBorder();
			
			validate();
			repaint();
		}
		
		private void assignBorder()
		{
			if(this.r == 0)
			{
				if(this.c == 0) setBorder(Constants.SQUARE00_BORDER);
				if(this.c == 1) setBorder(Constants.SQUARE01_BORDER);
				if(this.c == 2) setBorder(Constants.SQUARE02_BORDER);
			}
			else if(this.r == 1)
			{
				if(this.c == 0) setBorder(Constants.SQUARE10_BORDER);
				if(this.c == 1) setBorder(Constants.SQUARE11_BORDER);
				if(this.c == 2) setBorder(Constants.SQUARE12_BORDER);
			}
			else if(this.r == 2)
			{
				if(this.c == 0) setBorder(Constants.SQUARE20_BORDER);
				if(this.c == 1) setBorder(Constants.SQUARE21_BORDER);
				if(this.c == 2) setBorder(Constants.SQUARE22_BORDER);
			}
		}
		
		private void assignSquareMark(Board board)
		{
			removeAll();
			//If this square is not blank
			if(!board.getBoard()[this.r][this.c].equals(Square.BLANK))
			{
				String fileName = Constants.IMAGES_PATH + board.getBoard()[this.r][this.c].toString().toLowerCase()+Constants.IMAGE_SUFFIX;
				File file = new File(fileName);
				try
				{
					BufferedImage img = ImageIO.read(file);
					add(new JLabel(new ImageIcon(img)));
				} catch (IOException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		
	}
}
