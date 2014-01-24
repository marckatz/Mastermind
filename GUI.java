import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;

public class GUI extends JFrame implements ActionListener{

    private int turn;
    private GridLayout grid;
    private Color[] colors = {Color.red, Color.blue, Color.green, Color.yellow, Color.pink, Color.darkGray};
    private ArrayList<String> colorsS = new ArrayList<String>();
    private ArrayList<String> colorsN = new ArrayList<String>();
    private JButton[][] tempName;
    private Color selectedColor;
    private int selectedColumn;    

    public JFrame initializeBoard(){
        turn = 1;
        tempName = new JButton[2][6];
        grid = new GridLayout(2,6);
	selectedColor = Color.MAGENTA;
	selectedColumn = -1;
	String[] colorsA = {"RED", "BLUE", "GREEN", "YELLOW", "PINK", "DARK_GRAY"};
	String[] colorsNA = {"1", "2", "3", "4"};
	for(String s:colorsA){
	    colorsS.add(s);
	}
	for(String h:colorsNA){
	    colorsN.add(h);
	}
	
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();

        panel.setLayout(grid);
        panel.setSize(500, 1000);
        frame.getContentPane().add(BorderLayout.CENTER, panel);
        frame.setTitle("Mastermind");

	tempName[0][0] = new JButton("CLEAR");
	tempName[0][0].addActionListener(this);
	tempName[0][0].setBackground(Color.WHITE);
	tempName[0][0].setActionCommand("CLEAR");
	panel.add(tempName[0][0]);
	
	for (int y = 1; y < 5; y++){
	    tempName[0][y] = new JButton("" + y);
	    tempName[0][y].addActionListener(this);
	    tempName[0][y].setBackground(Color.MAGENTA);
	    tempName[0][y].setActionCommand("" + y);
	    panel.add(tempName[0][y]);
	}

	tempName[0][5] = new JButton("SUBMIT");
	tempName[0][5].addActionListener(this);
	tempName[0][5].setBackground(Color.WHITE);
	tempName[0][5].setActionCommand("SUBMIT");
	panel.add(tempName[0][5]);

	for (int x = 0; x < 6; x++){
	    tempName[1][x] = new JButton("color");
	    tempName[1][x].addActionListener(this);
	    tempName[1][x].setBackground(colors[x]);
	    tempName[1][x].setActionCommand(colorsS.get(x));
	    panel.add(tempName[1][x]);
	}

	/*
        for (int r = 0; r < 10; r++){
	    for (int c = 0; c < 4; c++) {
                button[r][c] = new JButton("Button");
                button[r][c].addActionListener(this);
                button[r][c].setBackground(Color.RED);
                panel.add(button[r][c]);
	    }
	    }*/

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 1100);
        frame.setVisible(true);
        frame.add(panel);

        return frame;
    }

    public void actionPerformed (ActionEvent e) {
	//getSource action listener: if in tempName[1][?], set selectedColor, if in tempName[0][1-4], set selectedRo
	if(colorsS.contains(e.getActionCommand())){
	    selectedColor = colors[colorsS.indexOf(e.getActionCommand())];     
	}
	else if(colorsN.contains(e.getActionCommand())){
	    Integer i = new Integer(e.getActionCommand());
	    selectedColumn = i.intValue();
	}
	if(selectedColumn != -1 && !(selectedColor.equals(Color.MAGENTA))){
	    tempName[0][selectedColumn].setBackground(selectedColor);
	    selectedColumn = -1;
	    selectedColor = Color.MAGENTA;
	}
        if("CLEAR".equals(e.getActionCommand())){
	    for (int x = 1; x < 5; x++){
		tempName[0][x].setBackground(Color.MAGENTA);
	    }
	    selectedColumn = -1;
	    selectedColor = Color.MAGENTA;
	}
	
    }

    public static void main(String[] args) {
	GUI newgame = new GUI();
	JFrame box = new JFrame();
	JOptionPane.showMessageDialog(box, "This is the Mastermind board game.[Instructions here] Click OK to continue.");
                //int x = Integer.parseInt(JOptionPane.showInputDialog("Please input the board size."));
                //int[][] board=new int [x][x];
	newgame.initializeBoard();
    }
}


