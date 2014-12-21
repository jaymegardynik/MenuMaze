import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class MazeGenerator {

	public static void generate(JMenu maze, double chanceToFork, int levelToIndent, boolean correctPath, int maxItems){
		
		// Base case
		if(levelToIndent == 0){
			
			JMenuItem nextLevel = new JMenuItem("Next level");
			maze.add(nextLevel);
			
			nextLevel.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
				Main.level++;
				Main.mainPanel.repaint();
				Main.regenMaze();
					
				}
			});
			
			
			return;
		}
		// Decide number of items
		Random randItems = new Random();
		int items = randItems.nextInt(maxItems) + 2;

		for(int i = 0; i < items; i++){
			
			// Decide fork
			boolean shouldWeFork = randItems.nextFloat() < chanceToFork;
			
			if(shouldWeFork){
				
				JMenu jmenu = new JMenu("fork");
				maze.add(jmenu);
				generate(jmenu, chanceToFork/2, levelToIndent, false, maxItems);
			}

			else{
				JMenuItem jmi = new JMenuItem("spoon");
				maze.add(jmi);
			
			}	
		}
		
		// Decide which item is correct path	
		Random ranCor = new Random();
		int ran = ranCor.nextInt(items);
		
		if(correctPath){
			
			JMenu corr = new JMenu("knife");
			maze.add(corr, ran);
			generate(corr, chanceToFork, levelToIndent -1, correctPath, maxItems);
		}
	}	
	
}
