//Sen Li

package data;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JLabel;

public class GameTimer extends JLabel{
	
	Integer timeRemaining;
	
	public GameTimer(int roundTime){
		timeRemaining = roundTime;
		this.setText(timeRemaining.toString());
		this.setHorizontalAlignment(CENTER);
		
	}
	
	public void start(){
		for (Integer i = timeRemaining; i >= 0; --i){
			 try {
				    Thread.sleep(1000); // wait 1 sec 
			 }
			 catch (InterruptedException ex) {
				 
			 }
			 this.setText(i.toString());
			 this.setFont(new java.awt.Font("Dialog", 1, 18));
			 
			 if(i <= timeRemaining/3)
				 this.setForeground(Color.RED);
			 else
				 this.setForeground(Color.BLACK);
		}
				
	}
	
	public void reset(){
		this.setText(timeRemaining.toString());
	}
	
}
