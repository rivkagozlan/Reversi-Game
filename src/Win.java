//**************Rivka Gozlan 206999476, Hodaya Ben-Haim 207401852***************
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;
@SuppressWarnings("unchecked")

class Win extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel pMain1,pMain2,pname,pButton;
	private JLabel name;
	private JButton playAgain, exit;
	private Border emptyBorder1,emptyBorder2;

	private JTextArea winers;
	private  HashMap<String,Integer> allWiners; 

	public Win(String winerName) {	
		allWiners = new HashMap<>();

		//the right main panel
		pMain1=new JPanel();
		pMain1.setLayout(new GridLayout(3,1,0,10));
		emptyBorder1=BorderFactory.createEmptyBorder(320, 0, 0, 0);
		pMain1.setBorder(emptyBorder1);

		//the first panel
		pname=new JPanel();
		name=new JLabel(winerName);
		name.setFont(new Font("Comic Sans MS",Font.BOLD,24));
		pname.setOpaque(false);	
		pname.add(name);

		//the second panel
		pButton=new JPanel();
		playAgain=new JButton("Play again");
		Font font=new Font("Comic Sans MS",Font.ITALIC,17);
		playAgain.setFont(font);
		exit=new JButton("Exit");
		exit.setFont(font);
		pButton.add(playAgain);
		pButton.add(exit);
		pButton.setOpaque(false);	

		//add to the right main panel
		pMain1.add(pname);
		pMain1.add(pButton);
		pMain1.setOpaque(false);	

		//the left main panel
		pMain2=new JPanel();
		pMain2.setOpaque(false);
		emptyBorder2=BorderFactory.createEmptyBorder(40, 0, 0, 0);
		pMain2.setBorder(emptyBorder2);
		winers=new JTextArea();
		winers.setEditable(false);
		allWins(winerName);


		//add listener
		StartListener l=new StartListener();
		playAgain.addActionListener(l);
		exit.addActionListener(l);

		setSize(900,600);
		setVisible(true);
		setLocationRelativeTo(null);//center the window
		setResizable(false);//lock the possibility of change the size of the window
		setIconImage(Toolkit.getDefaultToolkit().createImage("src/gameIcon.jpg"));
		setAlwaysOnTop(true);//The window will be always in the front

		JLabel background=new JLabel(new ImageIcon("src/winBG2.jpg"));//background to this JFrame
		background.setLayout(new GridLayout(1,2));
		background.add(pMain2);
		background.add(pMain1);
		add(background);
	}
	/**
	 * The method reads the old winer names and there wins number to a hashMap and adds the current winner to the map
	 * and adds the data to a text area that shown on the window
	 * @param winerName- The current winer name
	 */
	private void allWins(String winerName){
		//reads the old winer names and there wins number to a temp hashMap
		HashMap<String,Integer> temp=new HashMap<>();
		ObjectInputStream obj1;
		try {
			obj1 = new ObjectInputStream(new FileInputStream("src/winers.txt"));
			temp = (HashMap<String,Integer>)obj1.readObject();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} 

		allWiners.putAll(temp);//put the 'temp' hashMap in the 'allWiners' hashMap

		if(allWiners.size()>=15) {//if 15 winers were saved- clear the map
			JOptionPane.showMessageDialog(null,"The winers list will be clear!        ", "Please Note", JOptionPane.OK_CANCEL_OPTION, new ImageIcon("src/!.jpg"));
			allWiners.clear();
		}

		//add the current win to the data of 'allWiners'
		boolean isExist=false;
		for (String key : allWiners.keySet()) {
			if(key.equals(winerName)) {
				allWiners.put(key, allWiners.get(key) + 1);
				isExist=true;
			}
		}

		if(!isExist && !winerName.equals("no winer"))
			allWiners.put(winerName, 1);
		//check what is the peak
		int max=0;
		for(Integer d : allWiners.values()) {
			if(d>max)
				max=d;
		}
		//add the data to a text area
		String tempWiners="\n\n\nachievements:\n\n",records="Records:\n\nThe current peak is "+max+"\nachieved:\n";
		for (String key : allWiners.keySet()) {
			if(allWiners.get(key).equals(max)) 
				records+="- "+key+"\n";

			tempWiners+="- "+key+" won "+allWiners.get(key)+" times\n";
		}
		winers.setText(records+tempWiners);
		winers.setFont(new Font("Comic Sans MS",Font.ITALIC,14));
		winers.setOpaque(false);
		pMain2.add(winers);

		//update the new data
		try {	
			ObjectOutputStream obj = new ObjectOutputStream(new FileOutputStream("src/winers.txt"));
			obj.writeObject(allWiners);
			obj.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		catch (IOException e2) {
			e2.printStackTrace();
		}
	}
	private class StartListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(e.getSource().equals(playAgain)) {
				dispose();
			}
			else if(e.getSource().equals(exit)) {
				java.awt.Window win[] = java.awt.Window.getWindows();
				for(int i=0;i<win.length;i++){ 
					win[i].dispose(); 
				}
			}
		}
	}
}

