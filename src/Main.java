/*
 	This file is part of MenuMaze.
	MenuMaze is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.
    MenuMaze is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.
    You should have received a copy of the GNU General Public License
    along with MenuMaze.  If not, see <http://www.gnu.org/licenses/>.
*/

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;


import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

/**********************************************************
 * Class: Main
 * Purpose: Sets up and runs the main program
**********************************************************/
//JMenu maze, double chanceToFork, int levelToIndent, boolean correctPath, int maxItems

public class Main {
	
	// Properties
	static long level = 1;
	static final int INIT_HEIGHT = 600, INIT_WIDTH = 800;
	static final int LIMIT_X = 50;
	static final int LIMIT_Y = 30;
	static JPanel mainPanel;
	static JMenu maze;
	static final double INIT_CHANCE_TO_FORK = .2;
	static final int INIT_LEVEL_TO_INDENT = 5, INIT_MAX_ITEMS = 10;
	static final boolean INIT_CORRECT_PATH = true;
	

	public static void main(String[] args){
		
		// Default 
		GridBagConstraints c = new GridBagConstraints();
		
		// Setup frame
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(INIT_WIDTH, INIT_HEIGHT);
		frame.setResizable(true);
		frame.setLayout(new GridBagLayout());
		
		// Setup menu bar
		JMenuBar menuBar = new JMenuBar();
		menuBar.setPreferredSize(new Dimension(INIT_WIDTH, 30));
		menuBar.setMinimumSize(new Dimension(INIT_WIDTH, 30));
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;
		frame.getContentPane().add(menuBar, c);
		
		// Setup maze
		maze = new JMenu("Maze");
		maze.setMnemonic('M');
		MazeGenerator.generate(maze, INIT_CHANCE_TO_FORK, INIT_LEVEL_TO_INDENT, INIT_CORRECT_PATH, INIT_MAX_ITEMS);
		menuBar.add(maze);
		
		// Setup ABOUT menu
		JMenu about = new JMenu("About");
		about.setMnemonic('A');
		menuBar.add(about);
		
		// Setup HELP menu
		JMenu help = new JMenu("Help");
		help.setMnemonic('H');
		menuBar.add(help);
		
		// Make Panel
		mainPanel = new JPanel(){
			
			// Serial..
			private static final long serialVersionUID = 1L;
			
			// Paint everything
			public void paint(Graphics g){
				super.paint(g);
				
				Graphics2D g2 = (Graphics2D) g;
				
				// Draw Text centered using getWidth() and getHeight()
				Font font = new Font(Font.SERIF, Font.PLAIN, 67);
				Font levelFont = new Font(Font.SERIF, Font.PLAIN, 33);
				
				// Title
				g2.setColor(Color.BLACK);
				g2.setFont(font);
				g2.drawString("MenuMaze", getWidth()/2 -150, getHeight()/2 - 50);
				
				// Level 
				g2.setColor(Color.BLACK);
				g2.setFont(levelFont);
				g2.drawString("Level:" + level , getWidth()/2 -50 , getHeight()/2 - 10);
				
				
			}
		};
		
		
		c.gridx = 0;
		c.gridy = 1;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 1;
		frame.getContentPane().add(mainPanel, c);
		
		frame.setVisible(true);
		
	}
	
	 public static void regenMaze(){

		 // Clear maze
		 maze.removeAll();
		  
		 // Level settings
		 double cToFork;
		 int lToIndent;
		 int mItems;
		 
		 cToFork = INIT_CHANCE_TO_FORK + level * .01;
	
		 // Make sure user does not go past x limit
		 if((INIT_LEVEL_TO_INDENT  + level) > LIMIT_X){
			  lToIndent = LIMIT_X;
		 }
		 
		 // Calculate indents as normal!!
		 else{ 
			  lToIndent = (int) (INIT_LEVEL_TO_INDENT  + level);
		 }
			 
		 // Make sure user does not go past x limit
		 if(INIT_MAX_ITEMS + (int)(level/3) > LIMIT_Y){
			 mItems = LIMIT_Y;
		 }
		 
		 // Calculate indents as normal
		 else{
			 
			 mItems = (INIT_MAX_ITEMS + (int)(level/3));
		 }
		 
		 MazeGenerator.generate(maze, cToFork, lToIndent, true, mItems);
	 }
	
	
	
}
