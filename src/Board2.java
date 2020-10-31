
//**************Rivka Gozlan 206999476, Hodaya Ben-Haim 207401852***************
import java.awt.event.ActionEvent;

import javax.swing.JButton;

public class Board2 extends Board{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Empty constructor
	 */
	public Board2() {
		super();
	}

	/**
	 * The method add to "cmd" action listener
	 * @param cmd- a JBottun
	 */
	protected void addListener(JButton cmd) {
		Listener l=new Listener();
		cmd.addActionListener(l);
	}
	/**
	 * The method initialize right panel
	 */
	public void initializeRight() {
		super.initializeRight();

		//Locking the necessary buttons
		for (int i = 0; i < cmd.length; i++) 
			for (int j = 0; j < cmd.length; j++) 
				if(cmd[i][j].getIcon()==null&&!(i==4&&j==2||i==5&&j==3||i==2&&j==4||i==3&&j==5))
					cmd[i][j].setEnabled(false);
	}

	private class Listener extends GameListener {//Listener of the buttons
		/**
		 * 
		 * @param i
		 * @param j
		 * @return true if there is option to block
		 */
		private boolean canOverride(int i,int j) {
			boolean[][] flag=new boolean[8][2];//help array

			for (int j2 = j+1; j2 < 8; j2++) {
				if(!flag[0][0]&&cmd[i][j2].getIcon()==getSecPlayerIcon()) {
					flag[0][1]=true;
					flag[0][0]=true;
				}
				if(cmd[i][j2].getIcon()==null)
					flag[0][1]=true;
				if(cmd[i][j2].getIcon()==getSecPlayerIcon()&&!flag[0][1]) 
					return true;
				flag[0][0]=true;
			}

			for (int j2 = j-1; j2 >= 0; j2--) {
				if(!flag[1][0]&&cmd[i][j2].getIcon()==getSecPlayerIcon()) {
					flag[1][1]=true;
					flag[1][0]=true;
				}
				if(cmd[i][j2].getIcon()==null)
					flag[1][1]=true;
				if(cmd[i][j2].getIcon()==getSecPlayerIcon()&&!flag[1][1])
					return true;
				flag[1][0]=true;
			}

			for (int i2 = i+1; i2 < 8; i2++) {
				if(!flag[2][0]&&cmd[i2][j].getIcon()==getSecPlayerIcon()) {
					flag[2][1]=true;
					flag[2][0]=true;
				}
				if(cmd[i2][j].getIcon()==null)
					flag[2][1]=true;
				if(cmd[i2][j].getIcon()==getSecPlayerIcon()&&!flag[2][1]) 
					return true;
				flag[2][0]=true;
			}

			for (int i2 = i-1; i2 >= 0; i2--) {
				if(!flag[3][0]&&cmd[i2][j].getIcon()==getSecPlayerIcon()) {
					flag[3][1]=true;
					flag[3][0]=true;
				}
				if(cmd[i2][j].getIcon()==null)
					flag[3][1]=true;
				if(cmd[i2][j].getIcon()==getSecPlayerIcon()&&!flag[3][1]) 
					return true;
				flag[3][0]=true;
			}

			for (int j2 = j+1,i2=i-1; j2 < 8&&i2>=0; j2++,i2--) {
				if(!flag[4][0]&&cmd[i2][j2].getIcon()==getSecPlayerIcon()) {
					flag[4][1]=true;
					flag[4][0]=true;
				}
				if(cmd[i2][j2].getIcon()==null)
					flag[4][1]=true;
				if(cmd[i2][j2].getIcon()==getSecPlayerIcon()&&!flag[4][1])
					return true;
				flag[4][0]=true;
			}

			for (int j2 = j-1,i2=i+1; j2 >= 0&&i2 < 8; j2--,i2++) {
				if(!flag[5][0]&&cmd[i2][j2].getIcon()==getSecPlayerIcon()) {
					flag[5][1]=true;
					flag[5][0]=true;
				}
				if(cmd[i2][j2].getIcon()==null)
					flag[5][1]=true;
				if(cmd[i2][j2].getIcon()==getSecPlayerIcon()&&!flag[5][1]) 
					return true;
				flag[5][0]=true;
			}

			for (int j2 = j-1,i2=i-1; j2 >= 0 && i2 >= 0; j2--,i2--) {
				if(!flag[6][0]&&cmd[i2][j2].getIcon()==getSecPlayerIcon()) {
					flag[6][1]=true;
					flag[6][0]=true;
				}
				if(cmd[i2][j2].getIcon()==null)
					flag[6][1]=true;
				if(cmd[i2][j2].getIcon()==getSecPlayerIcon()&&!flag[6][1])
					return true;
				flag[6][0]=true;
			}	

			for (int j2 = j+1,i2=i+1; j2 < 8 && i2 < 8; j2++,i2++) {
				if(!flag[7][0]&&cmd[i2][j2].getIcon()==getSecPlayerIcon()) {
					flag[7][1]=true;
					flag[7][0]=true;
				}
				if(cmd[i2][j2].getIcon()==null)
					flag[7][1]=true;
				if(cmd[i2][j2].getIcon()==getSecPlayerIcon()&&!flag[7][1]) 
					return true;
				flag[7][0]=true;
			}
			return false;//in case can't block
		}
		/**
		 * The method checks whether to lock
		 */
		protected void lock() {
			for (int i = 0; i < cmd.length; i++) 
				for (int j = 0; j < 8; j++) 
					if(cmd[i][j].getIcon()==null)
						//Whether between enemies or that can not block
						if(isAroundEnemy(i,j)||!canOverride(i, j))
							cmd[i][j].setEnabled(false);//lock the buttons
		}

		/**
		 * The system prepares the game board for a new game
		 */
		protected void clearsBoard() {
			super.clearsBoard();
			//Locking the necessary buttons
			for (int i = 0; i < cmd.length; i++) 
				for (int j = 0; j < cmd.length; j++) { 
					if(cmd[i][j].getIcon()==null) 
						if(!((i==4&&j==2)||(i==5&&j==3)||(i==2&&j==4)||(i==3&&j==5)))
							cmd[i][j].setEnabled(false);
						else
							cmd[i][j].setEnabled(true);
				}
		}

		public void actionPerformed(ActionEvent e) {
			super.actionPerformed(e);
		}
	}
}

