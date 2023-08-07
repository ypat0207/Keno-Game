/*  
 * kenoGame.java
 * Class for the lottery game Keno
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class kenoGame {
    private int boardSize;
    private ArrayList<ArrayList<Integer>> board;
    private int spotsMatched;
    private ArrayList<Integer> drawNums;
    private HashMap<Integer, HashMap<Integer, Double>> payoutMap;
    private double currentPayout;

    // Default constructor
    public kenoGame() {
	boardSize = 80;
	spotsMatched = 0;
	drawNums = new ArrayList<Integer>();
	generateBoard();
	setPayout();
	currentPayout = 0;
    }

    // Generate the board based on boardSize
    private void generateBoard() {
	board = new ArrayList<ArrayList<Integer>>();
	for (int i = 0; i < 8; i++) {
            ArrayList<Integer> row = new ArrayList<>();
            for (int j = 0; j < 10; j++) {
                row.add((i * 10) + j);
            }
            board.add(row);
        }
    }
    
    // Set up payoutMap according to prizes in North Carolina Lottery
    private void setPayout() {
	payoutMap = new HashMap<Integer, HashMap<Integer, Double>>();
	HashMap<Integer, Double> pay1 = new HashMap<Integer, Double>();
	pay1.put(1, 2.0);
	
	HashMap<Integer, Double> pay4 = new HashMap<Integer, Double>();
	pay4.put(2, 1.0);
	pay4.put(3, 5.0);
	pay4.put(4, 75.0);
	
	HashMap<Integer, Double> pay8 = new HashMap<Integer, Double>();
	pay8.put(4, 2.0);
	pay8.put(5, 12.0);
	pay8.put(6, 50.0);
	pay8.put(7, 750.0);
	pay8.put(8, 10000.0);

	HashMap<Integer, Double> pay10 = new HashMap<Integer, Double>();
	pay10.put(0, 5.0);
	pay10.put(5, 2.0);
	pay10.put(6, 15.0);
	pay10.put(7, 40.0);
	pay10.put(8, 450.0);
	pay10.put(9, 4250.0);
	pay10.put(10, 100000.0);
	
	payoutMap.put(1, pay1);
	payoutMap.put(4, pay4);
	payoutMap.put(8, pay8);
	payoutMap.put(10, pay10);
    }

    // Play a round based on betCard submitted
    public void playRound(betCard card) {
	Random rand = new Random();
	for (int i = 0; i < 20; i++) {
	    Integer randInt = rand.nextInt(80) + 1;
	    if (!drawNums.contains(randInt)) {
		drawNums.add(randInt);
	    	if (card.getChosenSpots().contains(randInt)) {spotsMatched++;}
	    } else {i--;}
	}
	calculatePayout(card.getNumSpots());
    }

    // Calculate Payout at the end of every draw
    private void calculatePayout(int cardSpots) {
	Double pay = payoutMap.get(cardSpots).get(spotsMatched);
	if (pay != null) {
	    currentPayout += pay;
	}
    }

    public int getSpotsMatched() {
    	return spotsMatched;
    	}
    public void setSpotsMatched(int spots) {
    	spotsMatched = spots;
    	}
    public ArrayList<Integer> getDrawNums() {
    	return drawNums;
    	}
    public void clearDrawNums() {
    	drawNums.clear();
    	}
    public ArrayList<ArrayList<Integer>> getBoard() {
    	return board;
    	}
    public double getCurrentPayout() {
    	return currentPayout;
    	}
    public void clearCurrentPayout() {currentPayout = 0;}
}
