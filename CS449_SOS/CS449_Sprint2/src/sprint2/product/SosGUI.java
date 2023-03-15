package sprint2.product;

import java.awt.*;
import javax.swing.*;


public class SosGUI {

	private final static int GAP = 2;
	Board board;
	private MainPanel mainPanel;
	
	SosGUI(Board board){
		this.board = board;
		displayGUI();
	}
	
	void refresh() {
		mainPanel.repaint();
	}
	
	private void displayGUI() {
		JFrame frame = new JFrame("SOS Game");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainPanel = new MainPanel();
		frame.add(mainPanel);
		frame.pack();
		frame.setVisible(true);
	}
	
	//JButton
	
	@SuppressWarnings("serial")
	class MainPanel extends JPanel {

       // private BottomPanel bPanel;

        MainPanel() {
            setLayout(new BorderLayout(GAP,GAP));
            add(new TopPanel(), BorderLayout.PAGE_START);
            add(new BoardPanel(), BorderLayout.CENTER);
            //bPanel = new BottomPanel();
            //add(bPanel, BorderLayout.PAGE_END);
        }

        //JButton getButton() {   return bPanel.getButton();  }
    }

	

	@SuppressWarnings("serial")
	class TopPanel extends JPanel{
		TopPanel(){
			setLayout(new FlowLayout(FlowLayout.LEADING));
			add(new JLabel("This is the game board"));
		}
	}
	
	@SuppressWarnings("serial")
	class BoardPanel extends JPanel{
		
		BoardPanel()   {

            setBorder(BorderFactory.createLineBorder(Color.BLACK, GAP));
            GridLayout layout = new GridLayout(board.getgridSize(), 
            board.getgridSize());
            setLayout(layout);

            for (int i = 0; i < board.getgridSize(); i++)   {

                for (int j = 0; j < board.getgridSize(); j++)  {
                    add(new Tile());
                }
            }
		
	} 
	
	
	class Tile extends JLabel {
	
	    Tile() {
	        setPreferredSize(new Dimension(board.getgridSize(), board.getgridSize()));
	        setBorder(BorderFactory.createLineBorder(Color.BLACK, GAP));
	    }
	}
	
	public class BottomPanel extends JLabel {

        JLabel turnLabel = new JLabel("Current turn: ");

        BottomPanel(){
            add(turnLabel);
        }

        
    }

}
}
		
		
	
	

