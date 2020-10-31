//**************Rivka Gozlan 206999476, Hodaya Ben-Haim 207401852***************

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class Levels extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Image img;
	private Border emptyBorder;
	private JButton level1,level2;
	/**
	 * Empty constructor
	 */
	public Levels() {
		img = Toolkit.getDefaultToolkit().createImage("src/levels.jpg");
		emptyBorder=BorderFactory.createEmptyBorder(400,300,50,300);//create empty border
		setBorder(emptyBorder);
		setLayout(new GridLayout(2,1,6,6));

		//initialization the traits
		level1=new JButton("Easy");
		level2=new JButton("Classic");

		//add to this panel
		add(level1);
		add(level2);

		LevelsListener l=new LevelsListener();
		level1.addActionListener(l);
		level2.addActionListener(l);
		
		Font font=new Font("Comic Sans MS",Font.ITALIC,30);
		level1.setFont(font);
		level2.setFont(font);
		
		level1.setBackground(new Color(202, 255, 255));
		level2.setBackground(new Color(132, 214, 227));
	}
	//create the background 
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img,0,0,this);
	}

	private class LevelsListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {//open the fit game board
			//close all open windows
			java.awt.Window win[] = java.awt.Window.getWindows();
			for(int i=0;i<win.length;i++){ 
				win[i].dispose(); 
			}
			JFrame frame = new JFrame("Reversi");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setSize(1300,700);
			frame.setIconImage(Toolkit.getDefaultToolkit().createImage("src/gameIcon.jpg"));
			frame.setLocationRelativeTo(null);//center the window
			frame.setResizable(false);//lock the possibility of change the size of the window
			frame.setVisible(true);
			
			//in case the user press "level1"
			if(e.getSource().equals(level1)) {
				Board panel = new Board();
				frame.add(panel);
			}
			//in case the user press "level2"
			else if(e.getSource().equals(level2)) {
				Board2 panel = new Board2();
				frame.add(panel);
			}
		}
	}
}