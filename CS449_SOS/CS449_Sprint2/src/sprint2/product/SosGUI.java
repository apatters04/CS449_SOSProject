package sprint2.product;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

import sprint2.product.Board.Cell;

/* 
 * The GUI code was originally written by 
 * Prof. Chua Hock Chuan, Nanyang Technological University 
 */

@SuppressWarnings("serial")
public class SosGUI extends JFrame{

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
	
	private JLabel sizeLabel;
	private JTextField sizeField;
	private JButton startGame;

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
		//add radio buttons
		JPanel modePane = new JPanel();

		JRadioButton simpleModeButton = new JRadioButton("Simple");
		JRadioButton generalModeButton = new JRadioButton("General");
		
		ButtonGroup modeGroup = new ButtonGroup();
		modeGroup.add(simpleModeButton);
		modeGroup.add(generalModeButton);
		simpleModeButton.setPreferredSize(new Dimension(100,25));
		simpleModeButton.setSelected(true);
		generalModeButton.setPreferredSize(new Dimension(100,25));
		
		modePane.add(simpleModeButton, BorderLayout.LINE_START);
		modePane.add(generalModeButton, BorderLayout.LINE_END);
		
		
		JLabel sizeLabel = new JLabel("Enter GridSize");
		JTextField sizeField = new JTextField(2);
		JButton startGame = new JButton("Start");
		modePane.add(sizeLabel);
		modePane.add(sizeField);
		modePane.add(startGame);
		
		modePane.setPreferredSize(new Dimension(CANVAS_WIDTH, 75));
		
		//Blue move choice S or O
		JRadioButton sBlueMove = new JRadioButton("S");
		JRadioButton oBlueMove = new JRadioButton("O");
		ButtonGroup blueChoiceGroup = new ButtonGroup();
		blueChoiceGroup.add(sBlueMove);
		blueChoiceGroup.add(oBlueMove);
		sBlueMove.setSelected(true);
		
		//Red  move choice S or O
		JRadioButton sRedMove = new JRadioButton("S");
		JRadioButton oRedMove = new JRadioButton("O");
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
	
		gameStatusBar = new JLabel("Current Turn: ");
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
	
	//take user input
	/*public void actionPerformed(ActionEvent e) {
		String s = e.getActionCommand();
		if (s.equals("Start")) {
			sizeLabel.setText(sizeField.getText());
			int size = Integer.parseInt(sizeField.getText());
			board.setgridSize(size);
			
			
			sizeField.setText("  ");

			gameBoardCanvas = new GameBoardCanvas();

			setContentPane();
			
			
		}
	}*/


	class GameBoardCanvas extends JPanel {

		GameBoardCanvas(){
			addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {  
						int rowSelected = e.getY() / CELL_SIZE;
						int colSelected = e.getX() / CELL_SIZE;
						board.makeMove(rowSelected, colSelected);
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
			g.setColor(Color.LIGHT_GRAY);
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
			for (int row = 0; row < board.getgridSize(); ++row) {
				for (int col = 0; col < board.getgridSize(); ++col) {
					int x1 = col * CELL_SIZE + CELL_PADDING;
					int y1 = row * CELL_SIZE + CELL_PADDING;
					if (board.getCell(row,col) == Cell.CROSS) {
						g2d.setColor(Color.RED);
						int x2 = (col + 1) * CELL_SIZE - CELL_PADDING;
						int y2 = (row + 1) * CELL_SIZE - CELL_PADDING;
						g2d.setFont(myFont);
						g2d.drawString("S", x1, y2);
						//g2d.drawLine(x2, y1, x1, y2);
					} else if (board.getCell(row,col) == Cell.NOUGHT) {
						g2d.setColor(Color.BLUE);
						g2d.drawOval(x1, y1, SYMBOL_SIZE, SYMBOL_SIZE);
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