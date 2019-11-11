package com.demo.test4.test4_1_win;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JPanel;

/**
 * A class to test Maze.java.
 * @author Koffman and Wolfgang
 */
public class MazeTest extends JFrame implements GridColors {

    // data field
    private TwoDimGrid theGrid; // a 2-D grid of buttons

    /** Reads data file and defines array bitMap to match data file */
    public static void main(String[] args) {
        try {
            if (args.length < 1) {
                // no file name given
                String reply =
                        JOptionPane.showInputDialog("Enter number of rows");
                int nRows = Integer.parseInt(reply);
                reply =
                        JOptionPane.showInputDialog("Enter number of columns");
                int nCols = Integer.parseInt(reply);
                TwoDimGrid aGrid = new TwoDimGrid(nRows, nCols);
                new MazeTest(aGrid);
            } else {
                // Create array bitMap from a data file
                BufferedReader br =
                        new BufferedReader(new FileReader(args[0]));

                // Read each data line (a string) into
                // gridArrayList. Each element is a char array.
                ArrayList<char[]> gridArrayList = new ArrayList<char[]>();
                String line;
                while ((line = br.readLine()) != null) {
                    char[] row = line.toCharArray();
                    gridArrayList.add(row);
                }

                // bitMap is a 2-D array based on data in gridArrayList
                char[][] bitMap =
                        gridArrayList.toArray(new char[gridArrayList.size()][]);
                int nRows = bitMap.length;
                int nCols = bitMap[0].length;

                // create a new TwoDimGrid and recolor it based on bitMap
                TwoDimGrid aGrid = new TwoDimGrid(nRows, nCols);
                aGrid.recolor(bitMap, NON_BACKGROUND);
                new MazeTest(aGrid);
            }
        } catch (Exception ex) {
            System.err.println("Exception " + ex);
            ex.printStackTrace();
            System.exit(1);
        }
    }

    // Builds the GUI
    private MazeTest(TwoDimGrid aGrid) {
        theGrid = aGrid;
        getContentPane().add(aGrid, BorderLayout.CENTER);
 //       Blob aBlob = new Blob(aGrid);
        JTextArea instruct = new JTextArea(2, 20);
        instruct.setText("Toggle a button to change its color"
                + "\nPress SOLVE when ready");
        getContentPane().add(instruct, BorderLayout.NORTH);
        JButton solveButton = new JButton("SOLVE");
        solveButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                solve();
            }
        });
        JButton resetButton = new JButton("RESET");
        resetButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                (new Maze(theGrid)).restore();
            }
        });
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(solveButton);
        bottomPanel.add(resetButton);
        getContentPane().add(bottomPanel, BorderLayout.SOUTH);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);
    }

    public void solve() {
        Maze m = new Maze(theGrid);
        //test the findAllMazePaths and findMazePathMin's outputs
        //(only if there is a path to the exit exists)
        ArrayList<PairInt> tempBlack = new ArrayList<PairInt>();
        for(int i = 0 ; i < theGrid.getNRows() ; i++){
            for(int j = 0; j < theGrid.getNCols() ; j++){
                Color color = theGrid.getColor(i, j);
                if(color.equals(NON_BACKGROUND)){
                    tempBlack.add(new PairInt(i,j));
                }
            }
        }

        ArrayList<ArrayList<PairInt>> arr = new ArrayList<ArrayList<PairInt>>();
        for (int i = 0; i < m.findAllMazePaths(0,0).size(); ++i) {
            ArrayList<PairInt> pairIntstemp = new ArrayList<PairInt>();
            ArrayList<PairInt> pairInts = m.findAllMazePaths(0, 0).get(i);
            pairIntstemp = pairInts;
            arr.add(pairIntstemp);
            System.out.println("The paths are1:" + pairInts);

        }
        System.out.println("The shortest path is:" + m.findMazePathMin(0,0));
        boolean found = false;
        for (ArrayList<PairInt> pairInts :arr) {
            for (PairInt p:pairInts) {
//                System.out.println(p);
                tempBlack.remove(p);
//                System.out.println(pairInts);
//                System.out.println(tempBlack);

                theGrid.recolor(p.getX(),p.getY(),PATH);
                found = true;
            }
        }
        for (PairInt p:tempBlack) {
            int x =p.getX();
            int y =p.getY();
            theGrid.recolor(x,y,TEMPORARY);

        }

        if (found) {
            JOptionPane.showMessageDialog(null, "Success - reset maze and try again");

        } else {
            JOptionPane.showMessageDialog(null, "No path - reset maze and try again");
        }
    }
}