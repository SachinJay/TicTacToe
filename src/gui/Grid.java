package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import game.Constants;

public class Grid
{
	private JFrame gameFrame;
	private JPanel boardPanel;
	
	public Grid()
	{
		//Basics of the frame
		gameFrame = new JFrame(Constants.FRAME_NAME);
		gameFrame.setLayout(new BorderLayout());
		
		//Give it a menuBar
		JMenuBar menuBar= new JMenuBar();
		addToMenuBar(menuBar);
		gameFrame.setJMenuBar(menuBar);

		//Give it dimensions and make it visible
		this.gameFrame.setSize(Constants.FRAME_DIM);
		this.gameFrame.setVisible(true);
	}

	/**
	 * Adds different menus to the menu bar
	 * @param menuBar the JMenuBar to add to
	 */
	private void addToMenuBar(JMenuBar menuBar)
	{
		menuBar.add(createWindowMenu());
	}

	
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
}
