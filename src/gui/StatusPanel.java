package gui;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import game.Constants;
import game.Game;
import game.Player;
import game.Square;
import game.Status;

public class StatusPanel extends JPanel
{
	private Status status;
	private Square turn;
	private JLabel statusLabel;
	
	public StatusPanel(Game game)
	{
		super(new BorderLayout());
		this.status = game.getStatus();
		this.turn = game.getTurnSide();
		
		
		setBackground(Constants.STATUS_BACKGROUND);
		setBorder(new EtchedBorder(EtchedBorder.RAISED));
		
		statusLabel = new JLabel();
		this.add(statusLabel,BorderLayout.CENTER);
		
		setPreferredSize(Constants.STATUS_DIM);
	}
	
	public void setText(Game game)
	{
		removeAll();
		
		this.add(statusLabel,BorderLayout.CENTER);
		
		statusLabel.setText("Game status: "+ game.getStatus().toString().toLowerCase());
		statusLabel.setFont(Constants.STATUS_FONT);
		
		validate();
		
	}

}
