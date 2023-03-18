package sprint2.product;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

import sprint2.product.Board.Cell;

/* 
 * GUI Code references the work of Prof. Chua Hock Chuan, NTU
 */

@SuppressWarnings("serial")
public class SosGUI extends JFrame {

	public static final int CELL_SIZE = 50; 
	public static final int GRID_WIDTH = 4;
	public static final int GRID_WIDHT_HALF = GRID_WIDTH / 2; 

	public static final int CELL_PADDING = CELL_SIZE / 6;
	public static final int SYMBOL_SIZE = CELL_SIZE - CELL_PADDING * 2; 
	public static final int SYMBOL_STROKE_WIDTH = 8; 

	private int CANVAS_WIDTH; 
	private int CANVAS_HEIGHT;


	private GameBoardCanvas gameBoardCanvas; 
	private JLabel gameStatusBar; 
	private Container contentPane;

	private Board board;
	
	final static String S = "S";
	final static String O = "O";
	
	JLabel sizeLabel;
	JTextField sizeField;
	JButton startGame;
	JRadioButton simpleModeButton = new JRadioButton("Simple");
	JRadioButton generalModeButton = new JRadioButton("General");
	
	private JRadioButton sBlueMove;
	private JRadioButton oBlueMove;
	
	private JRadioButton sRedMove;
	private JRadioButton oRedMove;
	
	private int playerMove;


	public SosGUI(Board board) {
		this.board = board;
		setContentPane();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack(); 
		setTitle("SOS GAME");
		setVisible(true);  
	}
	
	private void setContentPane(){

		CANVAS_WIDTH = CELL_SIZE * board.getgridSize();  
		CANVAS_HEIGHT = CELL_SIZE * board.getgridSize();
		

		//add mode buttons
		JPanel modePane = new JPanel();

		simpleModeButton = new JRadioButton("Simple");
		generalModeButton = new JRadioButton("General");
		
		ButtonGroup modeGroup = new ButtonGroup();
		modeGroup.add(simpleModeButton);
		modeGroup.add(generalModeButton);
		simpleModeButton.setPreferredSize(new Dimension(100,25));
		simpleModeButton.setSelected(true);
		generalModeButton.setPreferredSize(new Dimension(100,25));
		
		modePane.add(simpleModeButton, BorderLayout.LINE_START);
		modePane.add(generalModeButton, BorderLayout.LINE_END);
		
		//textfield for grid size
		sizeLabel = new JLabel("Enter GridSize");
		sizeField = new JTextField(2);
		startGame = new JButton("Start");
		modePane.add(sizeLabel);
		modePane.add(sizeField);
		modePane.add(startGame);
	
		modePane.setPreferredSize(new Dimension(CANVAS_WIDTH, 75));
		

		
		//Blue move choice S or O
		sBlueMove = new JRadioButton("S");
		sBlueMove.setMnemonic(KeyEvent.VK_D);
		sBlueMove.setActionCommand("S");
		
		oBlueMove = new JRadioButton("O");
		oBlueMove.setMnemonic(KeyEvent.VK_D);
		oBlueMove.setActionCommand("O");
		
		ButtonGroup blueChoiceGroup = new ButtonGroup();
		blueChoiceGroup.add(sBlueMove);
		blueChoiceGroup.add(oBlueMove);
		sBlueMove.setSelected(true);
		
		//Red  move choice S or O
		sRedMove = new JRadioButton("S");
		sRedMove.setMnemonic(KeyEvent.VK_D);
		sRedMove.setActionCommand("S");
		
		oRedMove = new JRadioButton("O");
		oRedMove.setMnemonic(KeyEvent.VK_D);
		oRedMove.setActionCommand("O");
		
		ButtonGroup redChoiceGroup = new ButtonGroup();
		redChoiceGroup.add(sRedMove);
		redChoiceGroup.add(oRedMove);
		sRedMove.setSelected(true);
		//add Blue Player Information
		JPanel bluePlayerPane = new JPanel(new GridLayout(5,5));
		JLabel bluePlayLabel = new JLabel("Blue Player");
		

		
		bluePlayerPane.add(bluePlayLabel);
		bluePlayerPane.add(sBlueMove);
		bluePlayerPane.add(oBlueMove);
		
		//add Red Player Information
		JPanel redPlayerPane = new JPanel(new GridLayout(5,5));
		JLabel redPlayLabel = new JLabel("Red Player");
		
		redPlayerPane.add(redPlayLabel);
		redPlayerPane.add(sRedMove);
		redPlayerPane.add(oRedMove);
		
		
		//adding gameboard
		gameBoardCanvas = new GameBoardCanvas();  

		gameBoardCanvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
	
		gameStatusBar = new JLabel("Current Turn: Blue");

		gameStatusBar.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 15));
		gameStatusBar.setBorder(BorderFactory.createEmptyBorder(2, 5, 4, 5));
		

		contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());
		contentPane.add(modePane, BorderLayout.PAGE_START);
		contentPane.add(bluePlayerPane, BorderLayout.LINE_START);
		contentPane.add(redPlayerPane, BorderLayout.LINE_END);
		
		contentPane.add(gameBoardCanvas, BorderLayout.CENTER);
		contentPane.add(gameStatusBar, BorderLayout.PAGE_END); 	

	}
	



	class GameBoardCanvas extends JPanel{

		GameBoardCanvas(){
			addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {  
						int rowSelected = e.getY() / CELL_SIZE;
						int colSelected = e.getX() / CELL_SIZE;

						board.makeMove(rowSelected, colSelected);
						gameStatusBar.setText("Current Turn: " + board.getTurn());
						
						repaint(); 
				}
			});
		}

		@Override
		public void paintComponent(Graphics g) { 
			super.paintComponent(g);   
			setBackground(Color.WHITE);
			drawGridLines(g);
			drawBoard(g);
		}
		
		private void drawGridLines(Graphics g){
			g.setColor(Color.GRAY);
			
			for (int row = 1; row < board.getgridSize(); ++row) {
				g.fillRoundRect(0, CELL_SIZE * row - GRID_WIDHT_HALF,
						CANVAS_WIDTH-1, GRID_WIDTH, GRID_WIDTH, GRID_WIDTH);
			}
			for (int col = 1; col < board.getgridSize(); ++col) {
				g.fillRoundRect(CELL_SIZE * col - GRID_WIDHT_HALF, 0,
						GRID_WIDTH, CANVAS_HEIGHT-1, GRID_WIDTH, GRID_WIDTH);
			}

		}

		private void drawBoard(Graphics g){
			Font myFont = new Font("Arial", 1, 50);
			Graphics2D g2d = (Graphics2D)g;
			g2d.setStroke(new BasicStroke(SYMBOL_STROKE_WIDTH, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND)); 
			for (int rowSelected = 0; rowSelected < board.getgridSize(); ++rowSelected) {
				for (int colSelected = 0; colSelected < board.getgridSize(); ++colSelected) {
					int x1 = colSelected * CELL_SIZE + CELL_PADDING;
					int y1 = rowSelected * CELL_SIZE + CELL_PADDING;
					
					if (board.getCell(rowSelected,colSelected) == Cell.BLUE) {
						if (sBlueMove.isSelected() == true) {
							g2d.setColor(Color.BLUE);
							int y2 = (rowSelected + 1) * CELL_SIZE - CELL_PADDING;
							g2d.setFont(myFont);
							g2d.drawString("S", x1, y2);
							
						}else if (oBlueMove.isSelected() == true) {
							g2d.setColor(Color.BLUE);
							g2d.drawOval(x1, y1, SYMBOL_SIZE, SYMBOL_SIZE);

						}
						
					} else if (board.getCell(rowSelected,colSelected) == Cell.RED) {
						if (sRedMove.isSelected() == true) {

							g2d.setColor(Color.RED);
							int y2 = (rowSelected + 1) * CELL_SIZE - CELL_PADDING;
							g2d.setFont(myFont);
							g2d.drawString("S", x1, y2);
						}else if (oRedMove.isSelected() == true) {

							g2d.setColor(Color.RED);
							g2d.drawOval(x1, y1, SYMBOL_SIZE, SYMBOL_SIZE);

						}
					}
				}
			}
		}

	}
	

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new SosGUI(new Board()); 
			}
		});
	}

	
}