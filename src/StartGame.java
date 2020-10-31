//**************Rivka Gozlan 206999476, Hodaya Ben-Haim 207401852***************
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class StartGame extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Image img;
	private JTextField namePlayer1,namePlayer2;
	private JButton start,Instructions;
	private Border emptyBorder;
	public StartGame() {
		img = Toolkit.getDefaultToolkit().createImage("src/background.jpg");
		emptyBorder=BorderFactory.createEmptyBorder(500, 95,60, 650);//create empty border
		setBorder(emptyBorder);
		setLayout(new GridLayout(2,2,6,6));

		//initialization the traits
		namePlayer1=new JTextField(" Your Name (player 1)");
		namePlayer2=new JTextField(" Your Name (player 2)");
		start=new JButton("start");
		Instructions=new JButton("Instructions");

		//add to this panel
		add(namePlayer1);
		add(namePlayer2);
		add(Instructions);
		add(start);

		//change the texts font
		Font font=new Font("Comic Sans MS",Font.ITALIC,20);
		namePlayer1.setFont(font);
		namePlayer2.setFont(font);
		start.setFont(font);
		Instructions.setFont(font);

		//add listener to the buttons
		StartListener l=new StartListener();
		start.addActionListener(l);
		Instructions.addActionListener(l);

		//delete the text from the textFild if the focus gained
		namePlayer2.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				if(namePlayer2.getText().equals(" Your Name (player 2)"))
					namePlayer2.setText("");
				if(namePlayer1.getText().equals(""))
					namePlayer1.setText(" Your Name (player 1)");
			}
		});

		namePlayer1.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				if(namePlayer1.getText().equals(" Your Name (player 1)"))
					namePlayer1.setText("");
				if(namePlayer2.getText().equals(""))
					namePlayer2.setText(" Your Name (player 2)");
			}
		});

	}
	//create the background 
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img,0,0,this);
	}

	private class StartListener implements ActionListener{
		/**
		 * The method write the names the users entered
		 * @throws IOException
		 */
		private void writeNames() throws IOException{
			BufferedWriter writer = new BufferedWriter(new FileWriter("src/name.txt"));
			writer.write(namePlayer1.getText()+"\n"+namePlayer2.getText());
			writer.close();
		}

		public void actionPerformed(ActionEvent e) {
			//in case the user press "start"
			if(e.getSource().equals(start)) {
				//in case the users entered any names
				if(!namePlayer1.getText().equals("") && !namePlayer2.getText().equals("") ) {
					//close all open windows
					java.awt.Window win[] = java.awt.Window.getWindows();
					for(int i=0;i<win.length;i++){ 
						win[i].dispose(); 
					}
					//save the names of the users
					try {
						writeNames();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					//open the game board
					JFrame frame = new JFrame("Reversi");
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//					GameBoard panel = new GameBoard();
//					Board2 panel = new Board2();
					Levels panel = new Levels();
					frame.add(panel);
					frame.setSize(1300,700);
					frame.setIconImage(Toolkit.getDefaultToolkit().createImage("src/gameIcon.jpg"));
					frame.setLocationRelativeTo(null);//center the window
					frame.setResizable(false);//lock the possibility of change the size of the window
					frame.setVisible(true);
				}
				else//In case it is missing name, the user get a massage
					JOptionPane.showMessageDialog(null,"Enter your names in the appropriate fields        ", "Please Note", JOptionPane.OK_CANCEL_OPTION, new ImageIcon("src/!.jpg"));

			}
			//in case the user press "Instructions"
			else if(e.getSource().equals(Instructions)){
				//open the Instructions window
				JFrame frame = new JFrame("Reversi");
				Instructions panel = new Instructions();
				frame.add(panel);
				frame.setSize(730,540);
				frame.setLocationRelativeTo(null);//center the window
				frame.setIconImage(Toolkit.getDefaultToolkit().createImage("src/gameIcon.jpg"));
				frame.setResizable(false);//lock the possibility of change the size of the window
				frame.setVisible(true);
			}
		}
	}
}