package app;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;


import Tests.*;
import data.*;
import data.Piece.Type;
public class BlokusTestApplication implements Observer {

        /** Stores reference to Game */
        private Game game;
        int no_players;
        
        private void printSeparatorLine() {
                for (int i=0; i < Board.X_DIMENSION; i++) {
                        System.out.print(" -");
                }
                System.out.println();
        }

        private void printBoardLine(Board board, int row) {
                for (int i=0; i < Board.X_DIMENSION; i++) {
                        
                        System.out.print("|" + board.getBlock(row, i));
                }
                System.out.println("|");
        }
        
        private void printBoard(Board board) {
                printSeparatorLine();
                for (int row=0; row < Board.Y_DIMENSION; row++) {
                        printBoardLine(board, row);
                }
                printSeparatorLine();
        }
        
        private void processUserInput() {

                while (true) {

                        System.out.print("(s->step, q->quit, n->new game, h->see hand) ? ");
                        boolean done = true;
                        
                        Scanner scan = new Scanner(System.in);
                        String input = scan.next();
                        
                        switch (input.charAt(0)) {
                        case 'q':
                                System.exit(0);
                                break;
                                
                        case 'n':
                        	try{
                        		
                        	BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
                        	
                        	System.out.println("Enter the number of players in the game");
                        	
                        	no_players= Integer.parseInt(br.readLine());
                                start();
                        	}
                        	catch(Exception e)
                        	{
                        		
                        	}
                                break;
                                
                        case 's':
                        	try{
                        		BufferedReader br1= new BufferedReader(new InputStreamReader(System.in));
                        		PieceTest pt= new PieceTest();
                        		
                        		pt.testConstructor();
                        		pt.testFlipAboutX();
                        		pt.testFlipAboutY();
                        		pt.testHasCorners();
                        		Piece piece;
                        		System.out.println("Select one of the above pieces");
                        		int s=Integer.parseInt(br1.readLine());
                        		if(s==1)
                        		{
                        			Type t= Piece.Type.F;
                        		
                                Move move = game.getCurrentPlayer().getNextMove(game.getBoard());
                                game.getCurrentPlayer().putNextMove(move);
                        	}
                        		else
                        		{
                                    Move move = game.getCurrentPlayer().getNextMove(game.getBoard());
                                    game.getCurrentPlayer().putNextMove(move);
                        			
                        		}
                        	}
                        	catch(Exception e)
                        	{
                        		
                        	}
                                break;
                                
                        case 'h':
                                Hand hand = game.getCurrentPlayer().getHand();
                                Iterator theHand = hand.getIterator(); 
                                while (theHand.hasNext())
                                {
                                        System.out.println(theHand.next().toString());
                                }
                                
                        default:
                                System.out.println("\nInvalid command.");
                                done = false;
                        }
                        
                        if (done)
                                return;
                                
                }
        }
        
        /** 
         * Creates a new Blokus game and sets up the Game. 
         */
        public BlokusTestApplication() {
                
                game = new Game();
                
                // add self as observers
                Bulletin.getBoard().addObserver(this);
                game.addObserver(this);
        }

        /**
         * Starts game by showing UI.
         */
        public void start() {
                game.reset();

                // HACK: add some players
                
                AI anAI;
                		for(int i=0;i<=4;i++)
                		{
                        anAI = new AIRandom();
                        Player player = new ComputerPlayer(i+no_players, anAI);
                        player.setAutoProgress(false);
                        game.addPlayer(player);
                		}
                
                game.start();
        }

        public synchronized void update(Observable obj, Object arg) {
                if (obj instanceof Bulletin) {
                        Bulletin b = (Bulletin) obj;
                        System.out.println(b.getLastMsg());
                }
                if (obj instanceof Game) {
                        printBoard(game.getBoard());
                        processUserInput();
                }
        }
        
        /**
         * Program execution entry point.
         * 
         * @param args command line arguments (unused)
         */
        public static void main(String[] args) {
                BlokusTestApplication app = new BlokusTestApplication();
                app.start();
        }

}
