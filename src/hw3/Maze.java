package hw3;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Class that solves maze problems with backtracking.
 * @author Koffman and Wolfgang
 * @author Max Shi
 * I pledge my honor that I have abided by the Stevens Honor System
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

    /**
     * Checks to see if the given coordinates are within the maze bounds
     * @param x The x-coordinate to check
     * @param y	The y-coordinate to check
     * @return If the point is in the maze, true;
     * 		   otherwise, false
     */
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
        if(!inMaze(x,y)) {	//check if point is in maze
            return false;
        }
        else if(maze.getColor(x, y)!=NON_BACKGROUND) {	//check if point is part of maze
            return false;
        }
        else if(x==maze.getNCols()-1 && y == maze.getNRows()-1) {	//check if point is goal
            maze.recolor(x, y, PATH, test variable whatever);
            return true;
        }
        else {	//point is in maze but not goal
            //mark current as visited
            maze.recolor(x, y, TEMPORARY);
            //visit neighbors and set boolean if path leads to end
            boolean path = findMazePath(x+1,y) || findMazePath(x-1,y) ||
                   findMazePath(x,y+1) || findMazePath(x,y-1);
            if(path) {
            	//color path as correct if path exists
                maze.recolor(x, y, PATH);
                return true;
            }
            else {
            	//restore color if path does not exist
                maze.recolor(x, y, TEMPORARY);
                return false;
            }
        }
    }

    // ADD METHOD FOR PROBLEM 2 HERE

    /**
     * Uses a stack to accumulate the possible paths to the end of the maze.
     * @param x The x-coordinate being checked
     * @param y The y-coordinate being checked
     * @param result The ArrayList of possible paths
     * @param trace The stack representing the path currently being explored
     */
	private void findMazePathStackBased(int x, int y, ArrayList<ArrayList<PairInt>> result, Stack<PairInt> trace) {
        trace.push(new PairInt(x,y));  //add current point to stack
        if(!inMaze(x,y) || maze.getColor(x, y)!=NON_BACKGROUND) trace.pop();  //remove current point if not valid
        else if(x==maze.getNCols()-1 && y == maze.getNRows()-1) {  //check if point is goal
        	//add path to list of possible paths
            ArrayList<PairInt> newList = new ArrayList<>();
            newList.addAll(trace);
            trace.pop();
            result.add(newList);
        }else {
            maze.recolor(x, y, PATH);	//prevent visiting already visited point
			//visit neighbors
            findMazePathStackBased(x+1, y, result,  trace);
            findMazePathStackBased(x-1, y, result,  trace);
            findMazePathStackBased(x, y+1, result, trace);
            findMazePathStackBased(x, y-1, result, trace);
            trace.pop();
            maze.recolor(x, y, NON_BACKGROUND);	//restore color
        }
    }

    /**
     * Finds all paths in the maze from the given start coordinates to the bottom right corner of the maze,
     * the point with coordinates (width-1, height-1).
     * @param x The x-coordinate to start from
     * @param y	The y-coordinate to start from
     * @return The ArrayList of ArrayList of PairInts representing the different paths in PairInt coordinates on the path to the end
     */
    public ArrayList<ArrayList<PairInt>> findAllMazePaths(int x, int y){
        ArrayList<ArrayList<PairInt>> result = new ArrayList<>();
        Stack<PairInt> trace = new Stack<>();
        findMazePathStackBased(x,y,result,trace);
        return result;
    }
    // ADD METHOD FOR PROBLEM 3 HERE

    /**
     * Returns the ArrayList of PairInts representing the shortest path from given x and y to the bottom right corner, the point
     * at (width-1, height-1).
     * @param x The x-coordinate to start from
     * @param y The y-coordinate to start from
     * @return The ArrayList of PairInts representing the shortest path
     */
    public ArrayList<PairInt> findMazePathMin(int x, int y){
        ArrayList<ArrayList<PairInt>> paths = findAllMazePaths(x,y);  //generate all possible paths
        int minSize = Integer.MAX_VALUE;  //set max value to store length of current shortest path
        ArrayList<PairInt> shortest = new ArrayList<>();
        for (ArrayList<PairInt> path:paths) {
            if (path.size()<minSize) {
                shortest = path;
                minSize = path.size();
            }
        }
        return shortest;
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
}
/*</listing>*/
