package com.demo.test4.test4_1_win;

import java.util.ArrayList;
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

    /**
     * Attempts to find a path through point (x, y).
     * @pre Possible path cells are in BACKGROUND color;
     *      barrier cells are in ABNORMAL color.
     * @post If a path is found, all cells on it are set to the
     *       PATH color; all cells that were visited but are
     *       not on the path are in the TEMPORN ARY color.
     * @param x The x-coordinate of current point
     * @param y The y-coordinate of current point
     * @return If a path through (x, y) is found, true;
     *         otherwise, false
     */
    
 /* Problem1
  		public boolean findMazePath(int x, int y) {
        //边界判断
        if(x<0 || y<0 ||x>=maze.getNRows()||y>=maze.getNCols()){
           return false;
        }

        //记录路径
        PairInt pairInt = new PairInt(x, y);
        trace.push(pairInt);

        //终点判断
        if(x == maze.getNRows()-1 && y == maze.getNCols()-1){
            Stack<PairInt> traceTemp = new Stack<>();
            for (PairInt p :trace) {
                traceTemp.push(p);
            }
            result.add(traceTemp);
            maze.recolor(x,y,TEMPORARY);

        }
        //颜色是否可走    ！下一步节点是否为走过的路径
        if(eqClor(x+1,y) && !findStrack(x+1,y) && findMazePath(x+1,y)){
            System.out.println("往右走" + x +"<--x、y-->"+ y);
        }else if(eqClor(x,y+1) && !findStrack(x,y+1) && findMazePath(x,y+1)){
            System.out.println("往下走" + x +"<--x、y-->"+ y);
        }else if(eqClor(x-1,y) && !findStrack(x-1,y) && findMazePath(x-1,y)){
            System.out.println("往左走" + x +"<--x、y-->"+ y);
        }else if(eqClor(x,y-1) && !findStrack(x,y-1) && findMazePath(x,y-1)){
            System.out.println("往上走" + x +"<--x、y-->"+ y);
        }else{
            trace.pop();
            maze.recolor(x,y,TEMPORARY);
            return false;
        }

        //Backtracking
        System.out.println("回退" + x +"<--x、y-->"+ y);
        trace.pop();
        return true;


    }
    */
    
    //Problem 1
    public boolean findMazePath(int x, int y) {

        // 越界，返回false
        if (x < 0 || y < 0 || x >= maze.getNCols() || y >= maze.getNRows()) {
            return false;
        // 非出口路线之一，返回false
        } else if (!maze.getColor(x, y).equals(NON_BACKGROUND)) {
            return false;
        // 是出口，返回true
        } else if (x == maze.getNCols() - 1 && y == maze.getNRows() - 1) {
            maze.recolor(x, y, PATH);
            return true;
        } else {
            maze.recolor(x, y, PATH);
            //附近是path，返回true
            if (findMazePath(x - 1, y) || findMazePath(x + 1, y) ||
                findMazePath(x, y + 1) || findMazePath(x, y -1)) {
                return true;
            // 染temp色
            } else {
                maze.recolor(x, y, TEMPORARY);
                return false;
            }
        }
    }

    //Helper method for Problem2
    public void findMazePathStackBased(int x, int y,ArrayList<ArrayList<PairInt>> result,
                             		   Stack<PairInt> trace){
    	
        if (x < 0 || y < 0 || x > maze.getNCols() - 1 || y > maze.getNRows() - 1 ||
                (!maze.getColor(x, y).equals(NON_BACKGROUND))){
            return;
        // if we find the exit, we push the exit point to trace and add it to the result
        } else if (x == maze.getNCols() - 1 && y == maze.getNRows() - 1) {
            trace.push(new PairInt(x, y));
            ArrayList<PairInt> current = new ArrayList<>(trace);
            result.add(current);
            trace.pop(); 
            maze.recolor(x, y, NON_BACKGROUND); //recolor
            return;
        } else {
            // if this point is not the exit, continue the recursion 
            trace.push(new PairInt(x, y));
            maze.recolor(x, y, PATH); // recolor
            findMazePathStackBased(x + 1, y, result, trace);
            findMazePathStackBased(x - 1, y, result, trace);
            findMazePathStackBased(x, y + 1, result, trace);
            findMazePathStackBased(x, y - 1, result, trace);
            //backtracking?
            maze.recolor(x, y, NON_BACKGROUND);
            trace.pop();
            return;
        }
    }
    
    //Problem2
    public ArrayList<ArrayList<PairInt>> findAllMazePaths(int x, int y) {

        ArrayList<ArrayList<PairInt>> result = new ArrayList<>();
        Stack<PairInt> trace = new Stack<PairInt>();
        findMazePathStackBased(0, 0, result, trace);
        return result;
    }

    //Problem3
    public ArrayList<PairInt> findMazePathMin(int x, int y) {

        int target = 0;//the shortest path's index in the counterArray
        int temp;//temp value uesd to compare to
        int[] counterArray;//a Array used to store all the paths' length
        //这个allMazePaths只有在找到出口路线时候 才能存到里面，说白了没有出口时候他根本接受不到值，
        //我用它存的所有出口路线，findAllMazePath(x,y)是写好的，自动返回所有出口路线
        //这时候156行就new不出来数组 因为他没有.size()
        //加的那个if 看起来好像行，其实还是拉稀
        ArrayList<ArrayList<PairInt>> allMazePaths;
        allMazePaths = findAllMazePaths(x, y);
        counterArray = new int[allMazePaths.size()];
        for (int i = 0; i < allMazePaths.size(); ++i) {
            counterArray[i] = allMazePaths.get(i).size();
        }
        //
        if(!(allMazePaths.size()==0)) {
            temp = counterArray[0];
            for (int i = 1; i < counterArray.length; ++i) {
                if (counterArray[i] < temp) {
                    temp = counterArray[i];
                    target = i;
                    }
                }
            return allMazePaths.get(target);
        }else
            return new ArrayList();        // return the shortest path
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