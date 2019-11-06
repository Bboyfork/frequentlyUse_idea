package com.demo.test4.test4_2;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Class that solves maze problems with backtracking.
 * @author Koffman and Wolfgang
 **/
public class Maze implements GridColors {

    /** The maze */
    private TwoDimGrid maze;

    ArrayList<Stack <PairInt>> result = new ArrayList();
    Stack<PairInt> trace = new Stack<>();

    public Maze(TwoDimGrid m) {
        maze = m;
}

    /** Wrapper method. */
    public boolean findMazePath() {
        // (0, 0) is the start point.
        findMazePath(0, 0);
        if(result.isEmpty()){
            System.out.println(result);
            System.out.println("空集合");
            return false;
        }else{
            System.out.println(result);
            return true;
        }
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
     * @return 如果 路径经过 (x, y) 被找到
     *         true;
     *         否则false;
     *         //找上下左右、判断是否红色、走下去、返回true/false。
     */
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
            return true;
        }

//找路径 不能往回走
        if(eqClor(x+1,y) && findStrack(x,y) && findMazePath(x+1,y)){
            System.out.println("往右走" + x +"<--x、y-->"+ y);
            trace.pop();
            return true;
        }else if(eqClor(x,y+1) && findStrack(x,y) && findMazePath(x,y+1)){
            System.out.println("往下走" + x +"<--x、y-->"+ y);
            trace.pop();
            return true;
        }else if(eqClor(x-1,y) && findStrack(x,y) && findMazePath(x-1,y)){
            System.out.println("往左走" + x +"<--x、y-->"+ y);
            trace.pop();
            return true;
        }else if(eqClor(x,y-1) && findStrack(x,y) && findMazePath(x,y-1)){
            System.out.println("往上走" + x +"<--x、y-->"+ y);
            trace.pop();
            return true;
        }else{
            //这里代表应该回退
            System.out.println("回退" + x +"<--x、y-->"+ y);
        }
        return false;
    }

//    //记录路径
//    public void logPath (int x,int y){
//        PairInt pairInt = new PairInt(x, y);
//        trace.push(pairInt);
//    }



//检验是否为走过的路径
    public boolean findStrack(int x ,int y){
        boolean flag1 = false;
        for (PairInt p :trace) {
            if(p.equals(new PairInt(x,y)))
                flag1 = true;
        }
        return flag1;
    }

    //颜色判断 是否该走：0白色 不能走      1红色 2绿色  能走
    public boolean eqClor(int x,int y){
        if(x<0 || y<0 ||x>=maze.getNRows()||y>=maze.getNCols()){
            return false;
        }
        if(maze.getColor(x,y).equals(PATH)||maze.getColor(x,y).equals(NON_BACKGROUND)){//绿色、红色
            return true;
        }else if(maze.getColor(x,y).equals(BACKGROUND)){//白色
            return false;
        }else{
            System.out.println("返回了非 白、红、绿 有异常");
            return false;
        }
    }

    //都走 然后将所有情况和堆栈中比较 更新、
    // ADD METHOD FOR PROBLEM 2 HERE
    // ADD METHOD FOR PROBLEM 3 HERE

    public void findMazePathStackBased
    (int x, int y, ArrayList<ArrayList<PairInt>> result, Stack<PairInt> trace){

    }

    public ArrayList<ArrayList<PairInt>> findAllMazePaths (int x, int y){
        ArrayList<ArrayList <PairInt>> result = new ArrayList <>();
        Stack<PairInt> trace = new Stack <>();
        findMazePathStackBased (0,0,result,trace);
        return result;
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
