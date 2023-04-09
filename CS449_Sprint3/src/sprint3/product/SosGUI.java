package sprint3.product;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

import sprint3.product.Board.Cell;
import sprint3.product.Board.GameState;

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
	private JLabel bluePoints; 
	private JLabel redPoints; 
	private Container contentPane;

	private Board board;
	private SimpleGame simpGame = new SimpleGame();
	private GeneralGame genGame = new GeneralGame();
	
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
	
	public SosGUI(Board board) {
		this.board = board;
		board.resetGame();
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
		
		JPanel pointPane = new JPanel();
	
		gameStatusBar = new JLabel("    ");
		bluePoints = new JLabel("Blue Points: " + genGame.getBluePoints());
		redPoints = new JLabel("Red Points: " + genGame.getRedPoints());
		
		pointPane.add(gameStatusBar);
		pointPane.add(bluePoints);
		pointPane.add(redPoints);
		

		gameStatusBar.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 15));
		gameStatusBar.setBorder(BorderFactory.createEmptyBorder(2, 5, 4, 5));
		

		contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());
		contentPane.add(modePane, BorderLayout.PAGE_START);
		contentPane.add(bluePlayerPane, BorderLayout.LINE_START);
		contentPane.add(redPlayerPane, BorderLayout.LINE_END);
		contentPane.add(gameBoardCanvas, BorderLayout.CENTER);
		contentPane.add(pointPane, BorderLayout.PAGE_END); 	

	}


	class GameBoardCanvas extends JPanel{

		GameBoardCanvas(){
			addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {  
					if (board.getGameState() == GameState.PLAYING) {
						int rowSelected = e.getY() / CELL_SIZE;
						int colSelected = e.getX() / CELL_SIZE;
						
						if (board.getTurn() == "Blue") {
							if (sBlueMove.isSelected()) {
								board.makeMove(rowSelected, colSelected, 'S');
							} else if (oBlueMove.isSelected()) {
								board.makeMove(rowSelected, colSelected, 'O');
							}
						} else if (board.getTurn() == "Red") {
							if (sRedMove.isSelected()) {
								board.makeMove(rowSelected, colSelected, 'S');
							} else if (oRedMove.isSelected()) {
								board.makeMove(rowSelected, colSelected, 'O');
							}
						}
						if (board.getGameMode() == 0) {
							board.updateGameState(simpGame.hasSOS(board, board.getgridSize()));
						}
						else if (board.getGameMode() == 1) {
							if (genGame.hasSOS(board, board.getgridSize())) {
								if (board.getTurn() == "Red") {
									genGame.addBluePoints(1);
								}else if (board.getTurn() == "Blue") {
									genGame.addRedPoints(1);
								}
							}
							board.updateGameState(board.boardFull());
							if (board.boardFull()) {
								if (genGame.getBluePoints() > genGame.getRedPoints()) {
									board.setGameState(GameState.BLUE_WIN);
								} else if (genGame.getBluePoints() < genGame.getRedPoints()) {
									board.setGameState(GameState.RED_WIN);
								} else if (genGame.getBluePoints() == genGame.getRedPoints()) {
									board.setGameState(GameState.DRAW);
								}
							}
						}
						
					} 
					else {
						board.resetGame();
					}
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
			printStatusBar();
			drawLine(g);
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
					
					if ((board.getCell(rowSelected,colSelected) == Cell.ESS)) {
						g2d.setColor(Color.BLACK);
						int y2 = (rowSelected + 1) * CELL_SIZE - CELL_PADDING;
						g2d.setFont(myFont);
						g2d.drawString("S", x1, y2);
						
					}
							
					else if ((board.getCell(rowSelected,colSelected) == Cell.OH) || (board.getCell(rowSelected,colSelected) == Cell.USED)) {
						g2d.setColor(Color.BLACK);
						g2d.drawOval(x1, y1, SYMBOL_SIZE, SYMBOL_SIZE);
					}

				}
			}
		}
		
		private void drawLine(Graphics g) {
			Graphics2D g2d = (Graphics2D)g;
			if (board.getGameMode() == 0) {
				if (simpGame.hasSOS(board, board.getgridSize())) {
					g2d.drawLine(0, 100, 100, 100);
				}
			}
		}
		
		private void printStatusBar(){
			if (board.getGameState() == GameState.PLAYING) {
				gameStatusBar.setForeground(Color.BLACK);
				if (board.getGameMode() == 0) {
					if (board.getTurn() == "Blue") {
						gameStatusBar.setText("Blue's Turn");
					} else {
						gameStatusBar.setText("Red's Turn");
					}
				}
				else if (board.getGameMode() == 1) {
					if (board.getTurn() == "Blue") {
						gameStatusBar.setText("Blue's Turn");
					} else {
						gameStatusBar.setText("Red's Turn");
					}
					bluePoints = new JLabel("Blue Points: " + genGame.getBluePoints());
					redPoints = new JLabel("Red Points: " + genGame.getRedPoints());
				}
			} else if (board.getGameState() == GameState.DRAW) {
				gameStatusBar.setForeground(Color.RED);
				gameStatusBar.setText("It's a Draw! Click to play again.");
			} else if (board.getGameState() == GameState.BLUE_WIN) {
				gameStatusBar.setForeground(Color.RED);
				gameStatusBar.setText("Blue Won! Click to play again.");
			} else if (board.getGameState()== GameState.RED_WIN) {
				gameStatusBar.setForeground(Color.RED);
				gameStatusBar.setText("Red Won! Click to play again.");
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