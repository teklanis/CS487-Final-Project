//Sen Li

package app;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

import data.GameTimer;


public class StartupGameInterface {
public static void main(String[] args){
		
		BlokusFrame mainFrame = new StartupGameInterface().new BlokusFrame();
		mainFrame.launchFrame();
						
	}
	
	class BlokusFrame extends JFrame implements ActionListener, ItemListener {
		
		final static String HUMAN_PLAYER = "Human player";
	    final static String AI_PLAYER = "AI player";
	    final static int DEFAULT_ROUND_TIME = 45;
		final static int ADVANCED_ROUND_TIME = 30;
		final static int MASTER_ROUND_TIME = 15;
		
		JPanel mainPanel = new JPanel();// main panel uses card layout.
		JPanel startupPanel = new JPanel(); //show "start game" button and "about us" bottom.
		JPanel backGroundPanel = new JPanel();// show background.
		JPanel playerPanel = new JPanel(); //choose human/AI, enter player name.
		JPanel gamePanel = new JPanel(); //play the game on this panel.
		JPanel gameBoardPanel = new JPanel(); //pieces placed on this panel.
		JPanel playerIdPanel = new JPanel();// show current player ID here.
		JPanel timerPanel = new JPanel();// show timer here.
		
		JButton aboutButton = new JButton("About us");
		JButton startGameButton = new JButton("Start game");
		JButton beginButton = new JButton("GO !");
		
		ImageIcon background = new ImageIcon("c://background.jpg");
		
		String playerComboBoxItems[] = { HUMAN_PLAYER, AI_PLAYER };
		JComboBox comboBoxP1 = new JComboBox(playerComboBoxItems);
	    JComboBox comboBoxP2 = new JComboBox(playerComboBoxItems);
	    JComboBox comboBoxP3 = new JComboBox(playerComboBoxItems);
	    JComboBox comboBoxP4 = new JComboBox(playerComboBoxItems);
	    
	    JTextField textFieldP1 = new JTextField();
	    JTextField textFieldP2 = new JTextField();
	    JTextField textFieldP3 = new JTextField();
	    JTextField textFieldP4 = new JTextField();
	    
	    public void launchFrame(){
			
			//initialize the game window
			this.setBounds(100, 50, 900, 630);
			this.setVisible(true);
			this.setTitle("Blokus");
			this.setResizable(false);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.add(mainPanel,BorderLayout.CENTER);
			
			//set up main panel(card layout)
			mainPanel.setLayout(new CardLayout());
			mainPanel.add(startupPanel, "1");
			mainPanel.add(playerPanel, "2");
			mainPanel.add(gamePanel, "3");
			((CardLayout)mainPanel.getLayout()).show(mainPanel, "1");
			
			startupPanel.setLayout(null);
			playerPanel.setLayout(null);
			gamePanel.setLayout(null);
			gamePanel.setBackground(Color.WHITE);
						
			//set up start game button
			startGameButton.setBounds(400, 450, 100, 30);
			startGameButton.addActionListener(
					new ActionListener(){
						public void actionPerformed(ActionEvent e){
							((CardLayout)mainPanel.getLayout()).show(mainPanel, "2");
						}
					}
			);
			
			startupPanel.add(startGameButton);
			
			//set up about us button
			aboutButton.setBounds(400, 500, 100, 30);
			aboutButton.addActionListener(this);
			startupPanel.add(aboutButton);
			
			//our team info		
			JLabel startGameInfo = new JLabel("Kaleidoscope team, 2013 Spring");
			startGameInfo.setBounds(700, 570, 200, 30);
			startupPanel.add(startGameInfo, -1);
			
			//set up a background image
			
			ImageIcon backGroundIcon = new ImageIcon("c://background2.jpg");
			JLabel backImage = new JLabel(backGroundIcon);
			backImage.setBounds(0, 0, 900, 625);
			backImage.setOpaque(false);
			startupPanel.add(backImage);
			repaint();
			
			//set up the combo boxes for selecting human player
					    
		    comboBoxP1.setEditable(false);
		    comboBoxP2.setEditable(false);
		    comboBoxP3.setEditable(false);
		    comboBoxP4.setEditable(false);
		    
		    comboBoxP1.setBounds(265, 200, 150 ,30);
		    comboBoxP2.setBounds(265, 250, 150 ,30);
		    comboBoxP3.setBounds(265, 300, 150 ,30);
		    comboBoxP4.setBounds(265, 350, 150 ,30);
		    
		    comboBoxP1.addItemListener(this);
		    comboBoxP2.addItemListener(this);
		    comboBoxP3.addItemListener(this);
		    comboBoxP4.addItemListener(this);
		    
		    JLabel p1Lable = new JLabel("Player 1");
		    JLabel p2Lable = new JLabel("Player 2");
		    JLabel p3Lable = new JLabel("Player 3");
		    JLabel p4Lable = new JLabel("Player 4");
		    JLabel playerTypeLable = new JLabel("Player type");
		    JLabel playerNameLable = new JLabel("Player name");
		    
		    p1Lable.setBounds(170, 200, 180 ,30);
		    p2Lable.setBounds(170, 250, 180 ,30);
		    p3Lable.setBounds(170, 300, 180 ,30);
		    p4Lable.setBounds(170, 350, 180 ,30);
		    playerTypeLable.setBounds(270, 150, 180 ,30);
		    playerNameLable.setBounds(510, 150, 180 ,30);
		    
		    textFieldP1.setBounds(500, 200, 180 ,30);
		    textFieldP2.setBounds(500, 250, 180 ,30);
		    textFieldP3.setBounds(500, 300, 180 ,30);
		    textFieldP4.setBounds(500, 350, 180 ,30);
		    
		    beginButton.setBounds(380, 470, 140, 30);
		    
		    playerPanel.add(comboBoxP1);
		    playerPanel.add(comboBoxP2);
		    playerPanel.add(comboBoxP3);
		    playerPanel.add(comboBoxP4);
		    playerPanel.add(textFieldP1);
		    playerPanel.add(textFieldP2);
		    playerPanel.add(textFieldP3);
		    playerPanel.add(textFieldP4);
		    playerPanel.add(p1Lable);
		    playerPanel.add(p2Lable);
		    playerPanel.add(p3Lable);
		    playerPanel.add(p4Lable);
		    playerPanel.add(playerTypeLable);
		    playerPanel.add(playerNameLable);		    		    
		    playerPanel.add(beginButton);
		    
		    //background for player panel.
			ImageIcon backGroundIcon1 = new ImageIcon("c://background3.jpg");
			JLabel backImage1 = new JLabel(backGroundIcon1);
			backImage1.setBounds(0, 0, 900, 625);
			backImage1.setOpaque(false);
			playerPanel.add(backImage1);
			repaint();
		    
		    // when GO! button clicked, turn to game panel. 
		    beginButton.addActionListener(
					new ActionListener(){
						public void actionPerformed(ActionEvent e){
							((CardLayout)mainPanel.getLayout()).show(mainPanel, "3");
						}
					}
			);
		    
		    gameBoardPanel.setBounds(293, 1, 600, 600);
		    gameBoardPanel.setBackground(Color.GRAY);
		    Border gameBoardBorder = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
		    gameBoardPanel.setBorder(gameBoardBorder);
		   
		    JLabel playerIdLable = new JLabel("Current player:");
		    JLabel timerLable = new JLabel("Time Remaining:");
		    JLabel piecesLable = new JLabel("Game pieces:");
		    
		    playerIdLable.setBounds(20, 10, 200, 30);
		    timerLable.setBounds(20, 50, 200, 30);
		    piecesLable.setBounds(20, 90, 200, 30);
		    
		    playerIdPanel.setBounds(150, 10, 100, 30);
		    playerIdPanel.setBackground(Color.WHITE);
		    playerIdPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		    playerIdPanel.setLayout(new BorderLayout());
		    timerPanel.setBounds(150, 50, 100, 30);
		    timerPanel.setBackground(Color.WHITE);
		    timerPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		    timerPanel.setLayout(new BorderLayout());
		    
		    gamePanel.add(playerIdLable);
		    gamePanel.add(timerLable);
		    gamePanel.add(piecesLable);
		    gamePanel.add(gameBoardPanel);
		    gamePanel.add(playerIdPanel);
		    gamePanel.add(timerPanel);
		    
		    JLabel currentPlayerLable = new JLabel("Simon");
		    currentPlayerLable.setHorizontalAlignment(0);
		    playerIdPanel.add(currentPlayerLable, BorderLayout.CENTER);
		    
		    //set up the game timer.
		    
		    GameTimer gameTimerP1 = new GameTimer(MASTER_ROUND_TIME);
		    timerPanel.add(gameTimerP1, BorderLayout.CENTER);
		    gameTimerP1.reset();
		    gameTimerP1.start();
		    		    
		}
		
		public void actionPerformed(ActionEvent e){
			if (e.getSource() == aboutButton){
				 JOptionPane.showMessageDialog(this,"CS487 project - Blokus\n\n" +
				 		"Kaleidoscope team:\n" +
				 		"Sen Li\n" +
				 		"Sreevani Nagendran\n" +
				 		"Tejaswini Sonth\n" +
				 		"Troy Makulec\n\n" +
				 		"2013 spring",
						"About us",JOptionPane.PLAIN_MESSAGE);
			}
		}
		
		
		
		//Item listener used to control text field.
		//If player is human player, the text field will be enabled. Otherwise disabled.
		public void itemStateChanged(ItemEvent e) {
	        if(e.getSource() == comboBoxP1){
	        	if(comboBoxP1.getSelectedIndex() == 1)
	        	textFieldP1.setEnabled(false);
	        	else
	        	textFieldP1.setEnabled(true);	
	        }
	        if(e.getSource() == comboBoxP2){
	        	if(comboBoxP2.getSelectedIndex() == 1)
	        	textFieldP2.setEnabled(false);
	        	else
	        	textFieldP2.setEnabled(true);	
	        }
	        if(e.getSource() == comboBoxP3){
	        	if(comboBoxP3.getSelectedIndex() == 1)
	        	textFieldP3.setEnabled(false);
	        	else
	        	textFieldP3.setEnabled(true);	
	        }
	        if(e.getSource() == comboBoxP4){
	        	if(comboBoxP4.getSelectedIndex() == 1)
	        	textFieldP4.setEnabled(false);
	        	else
	        	textFieldP4.setEnabled(true);	
	        }
	        
	    }
		
	}
	
}
