package com.demo.test4.test4_1;

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
        //染色
        printPath(result);
        if(result.isEmpty()){
            System.out.println(result);
            System.out.println("空集合");
            return false;
        }else{
            System.out.println(result);
            return true;
        }
    }
    //染色
    public void printPath(ArrayList<Stack <PairInt>> arr){
        for (Stack<PairInt> sta:arr){
            for (PairInt p :sta) {
                maze.recolor(p.getX(),p.getY(),PATH);
            }
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

        //这里代表应该回退
        System.out.println("回退" + x +"<--x、y-->"+ y);
        trace.pop();
        return true;
    }

//    //记录路径
//    public void logPath (int x,int y){
//        PairInt pairInt = new PairInt(x, y);
//        trace.push(pairInt);
//    }



//检验是否为走过的路径    当前节点x y
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
        if(maze.getColor(x,y).equals(PATH)||maze.getColor(x,y).equals(NON_BACKGROUND)){//绿色、红色、黑色
            return true;
        }else if(maze.getColor(x,y).equals(BACKGROUND)||maze.getColor(x,y).equals(TEMPORARY)){//白色、黑色
            return false;
        }else{
            System.out.println("返回了非 白、红、绿 有异常");
            return false;
        }
    }

/*
* 这俩方法没用上 思路不融合 想不出来怎么用
* */
    public void findMazePathStackBased
    (int x, int y, ArrayList<ArrayList<PairInt>> result, Stack<PairInt> trace){

    }

    public ArrayList<ArrayList< PairInt >> findAllMazePaths (int x, int y){
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
