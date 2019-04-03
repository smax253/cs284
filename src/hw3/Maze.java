package hw3;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

/**
 * Class that solves maze problems with backtracking.
 * @author Koffman and Wolfgang
 **/
public class Maze implements GridColors {

    /** The maze */
    private TwoDimGrid maze;

    public Maze(TwoDimGrid m) {
        maze = m;
    }

    /** Wrapper method. */
    public boolean findMazePath() {
        return findMazePath(0, 0); // (0, 0) is the start point.
    }
    private boolean inMaze(int x, int y) {
    	return !(x<0 || x>=maze.getNCols() || y<0 || y>=maze.getNRows());
    }

    /**
     * Attempts to find a path through point (x, y).
     * @pre Possible path cells are in BACKGROUND color;
     *      barrier cells are in ABNORMAL color.
     * @post If a path is found, all cells on it are set to the
     *       PATH color; all cells that were visited but are
     *       not on the path are in the TEMPORARY color.
     * @param x The x-coordinate of current point
     * @param y The y-coordinate of current point
     * @return If a path through (x, y) is found, true;
     *         otherwise, false
     */
    public boolean findMazePath(int x, int y) {
        // COMPLETE HERE FOR PROBLEM 1
    	if(!inMaze(x,y)) {
    		return false;
    	}
    	else if(maze.getColor(x, y)!=NON_BACKGROUND) {
    		return false;
    	}
    	else if(x==maze.getNCols()-1 && y == maze.getNRows()-1) {
    		maze.recolor(x, y, PATH);
    		return true;
    	}
    	else {
    		maze.recolor(x, y, TEMPORARY);
    		boolean path = findMazePath(x+1,y) || findMazePath(x-1,y) ||
    			   findMazePath(x,y+1) || findMazePath(x,y-1);
    		if(path) {
    			maze.recolor(x, y, PATH);
        		return true;
    		}
    		else {
    			maze.recolor(x, y, TEMPORARY);
        		return false;
    		}
    	}
    }

    // ADD METHOD FOR PROBLEM 2 HERE
    
    private void findMazePathStackBased(int x, int y, ArrayList<ArrayList<PairInt>> result, Stack<PairInt> trace) {
    	trace.push(new PairInt(x,y));
    	if(!inMaze(x,y) || maze.getColor(x, y)!=NON_BACKGROUND) trace.pop();
    	else if(x==maze.getNCols()-1 && y == maze.getNRows()-1) {
    		ArrayList<PairInt> newList = new ArrayList<>();
    		newList.addAll(trace);
    		trace.clear();
    		trace.push(new PairInt(0,0));
    		result.add(newList);
    		return;
    	}else {
    		maze.recolor(x, y, PATH);
    		findMazePathStackBased(x+1, y, result, trace);
    		findMazePathStackBased(x-1, y, result, trace);
    		findMazePathStackBased(x, y+1, result, trace);
    		findMazePathStackBased(x, y-1, result, trace);
    		maze.recolor(x, y, NON_BACKGROUND);
    	}
    }
    public ArrayList<ArrayList<PairInt>> findAllMazePaths(int x, int y){
    	ArrayList<ArrayList<PairInt>> result = new ArrayList<>();
    	Stack<PairInt> trace = new Stack<>();
    	findMazePathStackBased(x,y,result,trace);
    	return result;
    }
    // ADD METHOD FOR PROBLEM 3 HERE
    public ArrayList<PairInt> findMazePathMin(int x, int y){
    	ArrayList<ArrayList<PairInt>> paths = findAllMazePaths(x,y);
    	int minSize = Integer.MAX_VALUE;
    	ArrayList<PairInt> shortest = new ArrayList<>();
    	Iterator itr = paths.iterator();
    	while(itr.hasNext())
    }

    /*<exercise chapter="5" section="6" type="programming" number="2">*/
    public void resetTemp() {
        maze.recolor(TEMPORARY, BACKGROUND);
    }
    /*</exercise>*/

    /*<exercise chapter="5" section="6" type="programming" number="3">*/
    public void restore() {
        resetTemp();
        maze.recolor(PATH, BACKGROUND);
        maze.recolor(NON_BACKGROUND, BACKGROUND);
    }
    /*</exercise>*/
    public static void main(String[] args) {
    	ArrayList<ArrayList<Integer>> test = new ArrayList<>();
    	Stack<Integer> stacktest = new Stack<>();
    	for(int i = 0; i<5; i++) {
    		stacktest.add(i);
    	}
    	ArrayList<Integer> newlist = new ArrayList<>();
    	newlist.addAll(stacktest);
    	test.add(newlist);
    	System.out.println(test);
    }
}
/*</listing>*/
