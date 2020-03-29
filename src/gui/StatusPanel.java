package gui;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import game.Constants;
import game.Game;
import game.Player;
import game.Status;

public class StatusPanel extends JPanel
{
	private Status status;
	private JLabel statusLabel;
	
	public StatusPanel(Status stat)
	{
		super(new BorderLayout());
		this.status = stat;
		
		setBackground(Constants.STATUS_BACKGROUND);
		setBorder(new EtchedBorder(EtchedBorder.RAISED));
		
		statusLabel = new JLabel();
		
	}
	
	private void setText(Status status)
	{
		removeAll();
		
		statusLabel.setText("The game is in "+ status.toString().toLowerCase());
		statusLabel.setFont(Constants.STATUS_FONT);
		
		validate();
		
	}

}
