package game;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ActionMaker extends JFrame {
	// Screen graphics
	private Image screenImage;
	private Graphics screenGraphics;

	// Image sources
	private ImageIcon exitButtonImage = new ImageIcon(Main.class.getResource("../images/ExitButton.png"));
	private ImageIcon exitButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/ExitButton_Entered.png"));
	private ImageIcon startButtonImage = new ImageIcon(Main.class.getResource("../images/StartButton.png"));
	private ImageIcon startButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/StartButton_Entered.png"));
	
	// Images
	private Image backgroundImage = new ImageIcon(Main.class.getResource("../images/TitleBackground.png")).getImage();
	
	// Labels
	private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("../images/MenuBar.png")));
	private JLabel player = new JLabel(new ImageIcon(Main.class.getResource("../images/Player.png")));
	
	// Buttons
	private JButton exitButton = new JButton(exitButtonImage);
	private JButton startButton = new JButton(startButtonImage);
	
	// Mouse
	private int mouseX;
	private int mouseY;
	
	// Player
	private int playerX;
	private int playerY = 21;
	private int minPlayerSpeed = 1;
	private int maxPlayerSpeed = 160;
	private int currentPlayerSpeed = minPlayerSpeed;
	private boolean increaseSpeed = true;
	
	// Constructor
	public ActionMaker() {
		
		// Setup window
		setUndecorated(true);
		setTitle("Action Maker");
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setBackground(new Color(0, 0, 0, 0));
		setLayout(null);

		// Exit button
		exitButton.setBounds(Main.SCREEN_WIDTH - 35, 0, 30, 30);
		exitButton.setBorderPainted(false);
		exitButton.setContentAreaFilled(false);
		exitButton.setFocusPainted(false);
		exitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				
				exitButton.setIcon(exitButtonEnteredImage);
				exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				
				exitButton.setIcon(exitButtonImage);
				exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				
				Sound buttonPressedSound = new Sound("Exit.mp3", false);
				
				buttonPressedSound.start();
				
				try
				{
					Thread.sleep(200);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				
				System.exit(0);
			}
		});

		add(exitButton);
		
		// Start button
		startButton.setBounds(100, 140, 480, 160);
		startButton.setBorderPainted(false);
		startButton.setContentAreaFilled(false);
		startButton.setFocusPainted(false);
		startButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				
				startButton.setIcon(startButtonEnteredImage);
				startButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				
				startButton.setIcon(startButtonImage);
				startButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				
				startButton.setVisible(false);
				
				backgroundImage = new ImageIcon(Main.class.getResource("../images/GameBackground.png")).getImage();
				
				// Setup game
				player.setVisible(true);
			}
		});
		
		add(startButton);
		
		// Menu bar
		menuBar.setBounds(0, 0, Main.SCREEN_WIDTH, 30);
		menuBar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				
				mouseX = e.getX();
				mouseY = e.getY();
			}
		});
		menuBar.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
				
				setLocation(x - mouseX, y - mouseY);
			}
		});
		
		add(menuBar);
		
		// Player
		player.setBounds(playerX, playerY, playerX + 160, playerY + 160);
		player.setVisible(false);
		
		add(player);
		
		// Keyboard
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				
				char keyChar = e.getKeyChar();
				
				switch (keyChar) {
				case 'W':
				case 'w':
					if (playerY - currentPlayerSpeed < 21) {
						playerY = 21;
					} else {
						playerY -= currentPlayerSpeed;
					}
					
					player.setBounds(playerX, playerY, playerX + 160, playerY + 160);
					
					currentPlayerSpeed += increaseSpeed ? 1 : -1;
					
					if (currentPlayerSpeed > maxPlayerSpeed && increaseSpeed) {
						increaseSpeed = false;
					} else if (currentPlayerSpeed < minPlayerSpeed & !increaseSpeed) {
						increaseSpeed = true;
					}
					
					break;
				case 'A':
				case 'a':
					if (playerX - currentPlayerSpeed < 0) {
						playerX = 0;
					} else {
						playerX -= currentPlayerSpeed;
					}
					
					player.setBounds(playerX, playerY, playerX + 160, playerY + 160);
					
					currentPlayerSpeed += increaseSpeed ? 1 : -1;
					
					if (currentPlayerSpeed > maxPlayerSpeed && increaseSpeed) {
						increaseSpeed = false;
					} else if (currentPlayerSpeed < minPlayerSpeed & !increaseSpeed) {
						increaseSpeed = true;
					}
					
					break;
				case 'S':
				case 's':
					if (playerY + currentPlayerSpeed >= Main.SCREEN_HEIGHT - 410) {
						playerY = Main.SCREEN_HEIGHT - 410;
					} else {
						playerY += currentPlayerSpeed;
					}
					
					player.setBounds(playerX, playerY, playerX + 160, playerY + 160);
					
					currentPlayerSpeed += increaseSpeed ? 1 : -1;
					
					if (currentPlayerSpeed > maxPlayerSpeed && increaseSpeed) {
						increaseSpeed = false;
					} else if (currentPlayerSpeed < minPlayerSpeed & !increaseSpeed) {
						increaseSpeed = true;
					}
					
					break;
				case 'D':
				case 'd':
					if (playerX + currentPlayerSpeed >= Main.SCREEN_WIDTH - 640) {
						playerX = Main.SCREEN_WIDTH - 640;
					} else {
						playerX += currentPlayerSpeed;
					}
					
					player.setBounds(playerX, playerY, playerX + 160, playerY + 160);
					
					currentPlayerSpeed += increaseSpeed ? 1 : -1;
					
					if (currentPlayerSpeed > maxPlayerSpeed && increaseSpeed) {
						increaseSpeed = false;
					} else if (currentPlayerSpeed < minPlayerSpeed & !increaseSpeed) {
						increaseSpeed = true;
					} else if (currentPlayerSpeed < minPlayerSpeed & !increaseSpeed) {
						increaseSpeed = true;
					}
					
					break;
				}
			}
		});
		
		// Play title background music
		Sound titleSound = new Sound("Start End.mp3", true);
		
		titleSound.start();
	}
	
	public void paint(Graphics g) {
		
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		
		screenGraphics = screenImage.getGraphics();
		
		drawScreen(screenGraphics);
		
		g.drawImage(screenImage, 0, 0, null);
	}
	
	public void drawScreen(Graphics g) {
		
		g.drawImage(backgroundImage, 0, 0, null);
		
		paintComponents(g);
		
		this.repaint();
	}
}
