//**************Rivka Gozlan 206999476, Hodaya Ben-Haim 207401852***************
import java.awt.Toolkit;

import javax.swing.JFrame;

public class StartFrame extends  JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		//Open the StartGame window
		JFrame frame = new JFrame("Reversi");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		StartGame panel=new StartGame();
		frame.add(panel);
		frame.setSize(1300,700);
		frame.setLocationRelativeTo(null);//center the window
		frame.setResizable(false);//lock the possibility of change the size of the window
		frame.setIconImage(Toolkit.getDefaultToolkit().createImage("src/gameIcon.jpg"));
		frame.setVisible(true);
	}

}
