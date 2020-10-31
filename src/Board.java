
//**************Rivka Gozlan 206999476, Hodaya Ben-Haim 207401852***************
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class Board extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected Image img;

	public JPanel left=new JPanel();
	protected JLabel turn,redTurn,blueTurn,name,numWins,name1,name2,win1,win2;
	protected Border emptyBorder2;

	protected JPanel right=new JPanel();
	protected JButton[][] cmd;//The game buttons
	protected Icon redIcon;
	protected Icon blueIcon;
	protected int player;
	protected Border emptyBorder1;
	protected int numWin1,numWin2;//the number of the players wins

	/**
	 * Empty constructor
	 */
	public Board() {
		img = Toolkit.getDefaultToolkit().createImage("src/background game.jpg");

		initializeRight();//initialize right panel
		initializeLeft();//initialize left panel

		setLayout(new GridLayout(1, 2));
		add(left);
		add(right);
	}

	/**
	 * The method add to "cmd" action listener
	 * @param cmd- a JBottun
	 */
	protected void addListener(JButton cmd) {
		GameListener l=new GameListener();
		cmd.addActionListener(l);
	}

	/**
	 * The method initialize right panel
	 */
	protected void initializeRight() {
		//add empty border to the panel
		emptyBorder1=BorderFactory.createEmptyBorder(10, 0, 10, 10);
		right.setBorder(emptyBorder1);

		right.setPreferredSize(new Dimension(580, 580));//Sets a preferred panel size
		right.setLayout(new GridLayout(8,8,3,3));

		player=1;//The first player is 1

		//Initialization the game buttons
		cmd=new JButton[8][8];
		for (int i = 0; i < cmd.length; i++) 
			for (int j = 0; j < cmd[0].length; j++) { 
				cmd[i][j]=new JButton();
				cmd[i][j].setBackground(Color.WHITE);//Change the color of the game buttons
				right.add(cmd[i][j]);//add to panel
				addListener(cmd[i][j]);//create listener to the game button
			}
		//The game tools
		redIcon = new ImageIcon("src/red2.jpg");
		blueIcon = new ImageIcon("src/blue2.jpg");
		//Updating the first game tools
		cmd[4][4].setIcon(redIcon);
		cmd[3][3].setIcon(redIcon);
		cmd[4][3].setIcon(blueIcon);
		cmd[3][4].setIcon(blueIcon);

		numWin1=0;
		numWin2=0;

		right.setOpaque(false);//Makes the panel transparent
	}

	/**
	 * The method initialize left panel
	 */
	protected void initializeLeft() {
		left.setPreferredSize(new Dimension(580, 620));
		emptyBorder2=BorderFactory.createEmptyBorder(300, 22,120, 100);
		left.setBorder(emptyBorder2);

		left.setLayout(new GridLayout(3,3,50,5));

		turn=new JLabel("     Turn:");
		redTurn=new JLabel(new ImageIcon("src/red1.jpg"));
		blueTurn=new JLabel(new ImageIcon("src/blue1.jpg"));
		blueTurn.setVisible(false);
		name=new JLabel("Name:");
		name1=new JLabel("");
		name2=new JLabel("");
		numWins=new JLabel("Wins:");
		win1=new JLabel("   0");
		win2=new JLabel("   0");

		//Picks up the names the users have inserted and puts them into the names of the players in the game
		try {
			BufferedReader reader = new BufferedReader(new FileReader("src/name.txt"));
			name1.setText(reader.readLine());
			name2.setText(reader.readLine());
			reader.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		Font font=new Font("Comic Sans MS",Font.BOLD,17);
		Font font2=new Font("Comic Sans MS",Font.BOLD,22);
		name1.setFont(font);
		name2.setFont(font);
		turn.setFont(font2);
		name.setFont(font2);
		numWins.setFont(new Font("Comic Sans MS",Font.BOLD,20));
		win1.setFont(font);
		win2.setFont(font);

		left.add(turn);
		left.add(name);
		left.add(numWins);
		left.add(redTurn);
		left.add(name1);
		left.add(win1);
		left.add(blueTurn);
		left.add(name2);
		left.add(win2);

		left.setOpaque(false);//Makes the panel transparent
	}

	//create the background
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img,0,0,this);
	}
	/**
	 * 
	 * @return the current icon
	 */
	protected Icon getPlayerIcon() {
		if(player==1)
			return redIcon;
		return blueIcon;
	}
	/**
	 * 
	 * @return the enemy icon
	 */
	protected Icon getSecPlayerIcon() {
		if(player==1)
			return blueIcon;
		return redIcon;
	}

	protected class GameListener implements ActionListener {//Listener of the buttons
		/**
		 * The method change the player	
		 */
		protected void changePlayer() {
			if(player==1) {
				player=2;
				redTurn.setVisible(false);
				blueTurn.setVisible(true);
			}
			else {
				player=1;
				redTurn.setVisible(true);
				blueTurn.setVisible(false);
			}
		}
		//Checks whether there is a possibility to turn the game tools on the right and change
		protected void checkRight(int i,int j) {
			for (int j2 = j+1; j2 < 8; j2++) {
				if(cmd[i][j2].getIcon()==null)
					return;
				if(cmd[i][j2].getIcon()==getPlayerIcon()) { 
					changeRow(i,j+1,j2);
					break;
				}
			}
		}
		//Checks whether there is a possibility to turn the game tools on the left and change
		protected void checkLeft(int i,int j) {
			for (int j2 = j-1; j2 >= 0; j2--) {
				if(cmd[i][j2].getIcon()==null)
					return;
				if(cmd[i][j2].getIcon()==getPlayerIcon()) {
					changeRow(i,j2+1,j);
					break;
				}
			}
		}
		//Checks whether there is a possibility to turn the game tools on the line up and change
		protected void checkLineUp(int i,int j) {
			for (int i2 = i+1; i2 < 8; i2++) {
				if(cmd[i2][j].getIcon()==null)
					return;
				if(cmd[i2][j].getIcon()==getPlayerIcon()) {
					changeColumn(j,i+1,i2);
					break;
				}
			}
		}
		//Checks whether there is a possibility to turn the game tools on the line down and change
		protected void checkLineDown(int i,int j) {
			for (int i2 = i-1; i2 >= 0; i2--) {
				if(cmd[i2][j].getIcon()==null)
					return;
				if(cmd[i2][j].getIcon()==getPlayerIcon()) {
					changeColumn(j,i2+1,i);
					break;
				}
			}
		}
		//Checks whether there is a possibility to turn the game tools on the slant 1
		protected void checkSlant1(int i,int j) {
			for (int j2 = j+1,i2=i-1; j2 < 8&&i2>=0; j2++,i2--) {
				if(cmd[i2][j2].getIcon()==null)
					return;
				if(cmd[i2][j2].getIcon()==getPlayerIcon()) { 
					changeSlant(i-1,j+1,j2);
					break;
				}
			}
		}
		//Checks whether there is a possibility to turn the game tools on the slant 2
		protected void checkSlant2(int i,int j) {
			for (int j2 = j-1,i2=i+1; j2 >= 0&&i2 < 8; j2--,i2++) {
				if(cmd[i2][j2].getIcon()==null)
					return;
				if(cmd[i2][j2].getIcon()==getPlayerIcon()) { 
					changeSlant2(i+1,j-1,j2);
					break;
				}
			}
		}
		//Checks whether there is a possibility to turn the game tools on the slant 3
		protected void checkSlant3(int i,int j) {
			for (int j2 = j-1,i2=i-1; j2 >= 0 && i2 >= 0; j2--,i2--) {
				if(cmd[i2][j2].getIcon()==null)
					return;
				if(cmd[i2][j2].getIcon()==getPlayerIcon()) { 
					changeSlant3(i-1,j-1,j2);
					break;
				}
			}
		}
		//Checks whether there is a possibility to turn the game tools on the slant 4
		protected void checkSlant4(int i,int j) {
			for (int j2 = j+1,i2=i+1; j2 < 8 && i2 < 8; j2++,i2++) {
				if(cmd[i2][j2].getIcon()==null)
					return;
				if(cmd[i2][j2].getIcon()==getPlayerIcon()) { 
					changeSlant4(i+1,j+1,j2);
					break;
				}
			}
		}
		//change row
		protected void changeRow(int i,int firstJ,int lastJ) {
			for (int k = firstJ; k <lastJ; k++)
				cmd[i][k].setIcon(getPlayerIcon());
		}
		//change column
		protected void changeColumn(int j,int firstI,int lastI) {
			for (int k = firstI; k <lastI; k++)
				cmd[k][j].setIcon(getPlayerIcon());
		}
		//change slant 1
		protected void changeSlant(int i,int firstJ,int lastJ) {
			for (int k = firstJ; k <lastJ; k++,i--)
				cmd[i][k].setIcon(getPlayerIcon());
		}
		//change slant 2
		protected void changeSlant2(int i,int firstJ,int lastJ) {
			for (int k = firstJ; k >=lastJ; k--,i++) 
				cmd[i][k].setIcon(getPlayerIcon());
		}
		//change slant 3
		protected void changeSlant3(int i,int firstJ,int lastJ) {
			for (int k = firstJ; k >=lastJ; k--,i--)
				cmd[i][k].setIcon(getPlayerIcon());
		}
		//change slant 4
		protected void changeSlant4(int i,int firstJ,int lastJ) {
			for (int k = firstJ; k <lastJ; k++,i++)
				cmd[i][k].setIcon(getPlayerIcon());
		}
		/**
		 * @param i-row button index
		 * @param j-column button index
		 * @return true if this button is between two enemies-tools of the second color,else return false
		 */
		protected boolean isAroundEnemy(int i, int j) {
			if(j!=0 && j!=7 && cmd[i][j+1].getIcon()==getPlayerIcon() && cmd[i][j-1].getIcon()==getPlayerIcon())
				return true;//In case there is a need to lock

			if(i!=0 && i!=7 && cmd[i+1][j].getIcon()==getPlayerIcon() && cmd[i-1][j].getIcon()==getPlayerIcon())
				return true;//In case there is a need to lock

			if((i!=0 && j!=0 ) && (i!=7 && j!=7) && 
					(cmd[i+1][j+1].getIcon()==getPlayerIcon() && cmd[i-1][j-1].getIcon()==getPlayerIcon()||
					cmd[i-1][j+1].getIcon()==getPlayerIcon() && cmd[i+1][j-1].getIcon()==getPlayerIcon()))
				return true;//In case the slants are blocked

			return false;
		}

		/**
		 * The method checks whether to lock
		 */
		protected void lock() {
			for (int i = 0; i < cmd.length; i++) 
				for (int j = 0; j < 8; j++) 
					if(cmd[i][j].getIcon()==null)
						//Whether between enemies
						if(isAroundEnemy(i,j))
							cmd[i][j].setEnabled(false);//lock the buttons
		}
		/**
		 * The method open all the buttons
		 */
		protected void openLock() {
			for (int i = 0; i < cmd.length; i++) 
				for (int j = 0; j < cmd.length; j++) 
					cmd[i][j].setEnabled(true);
		}
		/**
		 * The system prepares the game board for a new game
		 */
		protected void clearsBoard() {
			//clear all the icons from the buttons game
			for (int i = 0; i < cmd.length; i++) 
				for (int j = 0; j < cmd.length; j++) 
					cmd[i][j].setIcon(null);
			//set the start icons
			cmd[4][4].setIcon(redIcon);
			cmd[3][3].setIcon(redIcon);
			cmd[4][3].setIcon(blueIcon);
			cmd[3][4].setIcon(blueIcon);

			//show the current turn
			redTurn.setVisible(true);
			blueTurn.setVisible(false);
			//set the first player
			player=1;
		}
		/**
		 * The method check if there is a winer ,who win and open the fit window
		 */
		protected void whoWin() {
			String winer="no winer";
			int win;
			int cntRed=0,cntBlue=0;
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					if(cmd[i][j].getIcon()==redIcon)
						cntRed++;
					else if(cmd[i][j].getIcon()==blueIcon)
						cntBlue++;
				}
			}
			if(cntBlue<cntRed) {
				win=1;
				numWin1++;
				win1.setText("   "+numWin1);
			}
			else if(cntBlue>cntRed) {
				win=2;
				numWin2++;
				win2.setText("   "+numWin2);
			}
			else//if no winer
				win=0;
			//read the winer name
			try {
				BufferedReader reader = new BufferedReader(new FileReader("src/name.txt"));
				for (int i = 0; i < win; i++) {
					winer=reader.readLine();
				}
				reader.close();
			} catch (IOException e1) {
				e1.printStackTrace();	
			}

			//open the win window
			new Win(winer);
			clearsBoard();
		}
		/**
		 * The method check if there is a winer ,who win and open the fit window
		 */
		protected void checkWin() {
			for (int i = 0; i < 8; i++) 
				for (int j = 0; j < 8; j++)
					if(cmd[i][j].getIcon()==null&&cmd[i][j].isEnabled()==true)
						return;
			whoWin();
		}

		public void actionPerformed(ActionEvent e) {
			for (int i = 0; i < cmd.length; i++) 
				for (int j = 0; j < cmd[0].length; j++) 
					if(e.getSource().equals(cmd[i][j])&&cmd[i][j].getIcon()==null){
						cmd[i][j].setIcon(getPlayerIcon());//set the right icon on the request icon
						//Checks whether there is a possibility to turn the game tools
						checkRight(i,j);
						checkLeft(i,j);
						checkLineUp(i,j);
						checkLineDown(i,j);
						checkSlant1(i,j);
						checkSlant2(i,j);
						checkSlant3(i,j);
						checkSlant4(i,j);

						openLock();
						lock();
						changePlayer();
						checkWin();
						break;

					}

		}
	}

}

