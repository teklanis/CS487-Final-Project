package data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;

public class Game extends Observable {
	public static final int MAX_NUM_PLAYERS = 4;
    
    public static final String STARTED_EVENT = "Started";
    
    public static final String UPDATED_EVENT = "Updated";
    
    public static final String GAMEOVER_EVENT = "GameOver";
    
    /** Message type enumeration */
    public enum MessageType {
            Normal,
            Error,
            GameOver,
    }
    
    private Board board;
    
    private ArrayList<Player> players;
    
    private HashMap<Player,Integer> playerScores;
    
    private LinkedHashMap<Integer, Integer> rankedPlayerIds;
    
    
	private int curPlayerIdx;
    
    private boolean isRunning;
    
    private Thread monitor;
    
    private synchronized void setRunningStatus(boolean status) {
            isRunning = status;
    }
    
    /**
     * Sets current player index to a new value.
     * 
     * @param idx new player index
     */
    private synchronized void setCurrentPlayerIndex(int idx)
    {
            curPlayerIdx = idx;
    }
    public synchronized HashMap<Player, Integer> getPlayerScores() {
		return playerScores;
	}
    
	public synchronized LinkedHashMap<Integer, Integer> getRankedPlayerIds() {
		return rankedPlayerIds;
	}

	/**
     * Processes player's move.
     * 
     * @param move player's move
     */
    private synchronized void processPlayerMove(Move move) {

            switch (move.getType()) {
            case Normal:
                    // place the Piece on the board
                    board.place(move.getPiece(), move.getRow(), move.getColumn(), players.get(curPlayerIdx));

                    break;
                    
            case Skip:
                    // do nothing
                    break;
                    
            case Quit:
                    // this is processed in Monitor.run() method.
                    break;
            }
            
            
    }

    
    public Game() {
            board = new Board();
            players = new ArrayList<Player>();
            playerScores = new HashMap<Player, Integer>();
    }
    
    public synchronized boolean isRunning() {
            return isRunning;
    }
    
    public synchronized boolean hasMoreMoves() {
            for (Player player : players) {
                    if (player.hasMoreMoves())
                            return true;
            }
            return false;
    }
    
    /**
     * Returns player for the specified index.
     * 
     * @param idx player index
     * @return player for the specified index.
     */
    public synchronized Player getPlayer(int index) {
            return players.get(index);
    }
    
    public synchronized Board getBoard() {
            return board;
    }
    
    /**
     * Returns index for current player.
     * 
     * @return current player index
     */
    public synchronized int getCurrentPlayerIndex() {
            return curPlayerIdx;
    }

    public synchronized Player getCurrentPlayer() {
            return players.get(curPlayerIdx);
    }
    
    public synchronized void addPlayer(Player player) {
            // check if we're already running
            if (isRunning()) {
                    Bulletin.getBoard().appendMsg(MessageType.Error, "Player cannot be added after game has started.");
                    return;
            }
            
            // check if there is room for another player
            if (players.size() == MAX_NUM_PLAYERS ) {
                    Bulletin.getBoard().appendMsg(MessageType.Error, "Table is full.");
                    return;
            }

            // check if the player already added
            if (players.contains(player)) {
                    Bulletin.getBoard().appendMsg(MessageType.Error, "Player is already at the table.");
                    return;
            }

            players.add(player);
    }

    public synchronized void removePlayer(int index) {
            // check if we're already running
            if (isRunning()) {
                    Bulletin.getBoard().appendMsg(MessageType.Error, "A game has already started.");
                    return;
            }
            
            players.remove(index);
    }

    public synchronized void reset() {
            // remove old data
            board.reset();
            players.clear();
            
            // set defaults
            isRunning = false;
            curPlayerIdx = 0;
    }
    
    private  void computeScore()
    {
    	int playerscore =0;
    	HashMap<Integer, Integer> unsortedplayerScores = new HashMap<Integer, Integer>();
            for (Player p: players)
            {
                    Hand h = p.getHand();
                    
                    if (h.piecesLeft() == 0)
                    {
                    	playerscore= p.addScore(20);
                    }
                    else
                    {
                            Iterator<Piece> i = h.getIterator();
                    
                            while (i.hasNext())
                            {
                            	
                            	playerscore = p.addScore(-1 * i.next().getNumBlocks());
                                   
                                
                                    
                            }    
                           playerscore = 20 +( playerscore);
                    }
                  
//                	if(h.getLastPiecePlaced().getType() == Piece.Type.One)
//                	{
//                		playerscore += 5;
//                	}
                	playerScores.put(p, playerscore);
                	
                
                	unsortedplayerScores.put((p.getIndex()),playerscore);
                	

                    System.out.println("compute score playesss score....."+playerscore);
            }
            rankedPlayerIds = sortHashMapByValuesD(unsortedplayerScores);
    }
    // sort or rank the players scores and return the hashmap
    @SuppressWarnings({ "unchecked", "unused", "rawtypes" })
	private LinkedHashMap<Integer, Integer> sortHashMapByValuesD(HashMap passedMap) {
	    List mapKeys = new ArrayList(passedMap.keySet());
	    List mapValues = new ArrayList(passedMap.values());
	    Collections.sort(mapValues);
	    Collections.sort(mapKeys);
	        
	    LinkedHashMap sortedMap = 
	        new LinkedHashMap();
	    
	    Iterator valueIt = mapValues.iterator();
	    while (valueIt.hasNext()) {
	        Object val = valueIt.next();
	        Iterator keyIt = mapKeys.iterator();
	        
	        while (keyIt.hasNext()) {
	            Object key = keyIt.next();
	            String comp1 = passedMap.get(key).toString();
	            String comp2 = val.toString();
	            
	            if (comp1.equals(comp2)){
	                passedMap.remove(key);
	                mapKeys.remove(key);
	                sortedMap.put((Integer)key, (Integer)val);
	                break;
	            }

	        }

	    }
	    return sortedMap;
	}
    
    public void start() {
            
            // check if we're already running
            if (isRunning()) {
                    Bulletin.getBoard().appendMsg(MessageType.Error, "A game has already started.");
                    return;
            }

            Bulletin.getBoard().appendMsg(MessageType.Normal, "New game has started.");
            
            // start game monitor thread
            monitor = new Thread(new Monitor());
            monitor.run();
    }
    
    public void abort() {
            
            // check if there is a game running
            if (!isRunning()) {
                    Bulletin.getBoard().appendMsg(MessageType.Error, "There is no running game.");
                    return;
            }
            
            // get reference to current player
            int idx = getCurrentPlayerIndex();
            Player player = getPlayer(idx);

            // request player to abort
            player.abort();
            
            // wait for monitor thread to die
            try {
                    monitor.join();
            } catch (InterruptedException ex) {
                    // do nothing
            }

            Bulletin.getBoard().appendMsg(MessageType.GameOver, "Player aborted game.");
            
            // update game status
            setRunningStatus(false);
            
            // notify observers about change
            setChanged();
            notifyObservers(GAMEOVER_EVENT);
    }

    private class Monitor implements Runnable {
            
            public synchronized void run() {
                    
                    // update game status
                    setRunningStatus(true);
                    
                    // notify observers to refresh
                    setChanged();
                    notifyObservers(STARTED_EVENT);

                    while (hasMoreMoves()) {
                            
                            System.out.println("monitor on player: " + getCurrentPlayerIndex());
                            // get reference to current player
                            int idx = getCurrentPlayerIndex();
                            Player player = getPlayer(idx);
                            
                            // obtain and process user's move 
                            Move move = player.waitForNextMove(board);
                            if (move.getType() == Move.Type.Quit) {
                                    break;
                            } else {
                                    processPlayerMove(move);
                            }
                            Bulletin.getBoard().appendMsg(MessageType.Normal, "Player " +player.getIndex() +
                                            " made a move: " + move);
                            
                            // change turn
                            setCurrentPlayerIndex((getCurrentPlayerIndex() + 1) % 4);
                            
                            System.out.println("now it's this guys turn " +  getCurrentPlayerIndex());
                            computeScore();
                            // notify observers about change
                            setChanged();
                            notifyObservers(UPDATED_EVENT);
                    }
                   
                    Bulletin.getBoard().appendMsg(MessageType.GameOver, "Game Over");
                   // computeScore();
                    //todo - some reason the score at this point is coming as zero always - need to check
                    for (Player player : players)
                    {
                            Bulletin.getBoard().appendMsg(MessageType.Normal, "Player " + player.getIndex() 
                                            + ": " + player.getScore());
                    }
                    
                    isRunning = false;
                 // update game status
                    setRunningStatus(false);
                    setChanged();
                   
                    notifyObservers(GAMEOVER_EVENT);
            }
    }

}
