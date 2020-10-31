//**************Rivka Gozlan 206999476, Hodaya Ben-Haim 207401852***************
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

public class Instructions extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel instPanel;
	private JPanel tipPanel;
	private JLabel instructions;
	private JLabel tips;
	/**
	 * Empty constructor
	 */
	public Instructions() {
		setLayout(new GridLayout(1,1));
		initialize();
	}
	/**
	 * The method initialize the traits
	 */
	private void initialize() {
		instPanel=new JPanel();
		tipPanel=new JPanel();

		instructions=new JLabel(new ImageIcon("src/inst.jpg"));
		tips=new JLabel(new ImageIcon("src/tips.jpg"));

		instPanel.setLayout(new BorderLayout());
		tipPanel.setLayout(new BorderLayout());

		//Sets a preferred panel size
		instPanel.setPreferredSize(new Dimension(400, 200));
		tipPanel.setPreferredSize(new Dimension(400, 200));

		JScrollPane scrollPane = new JScrollPane(instructions);
		JScrollPane scrollPane2 = new JScrollPane(tips);

		instPanel.add(scrollPane, BorderLayout.CENTER);
		tipPanel.add(scrollPane2, BorderLayout.CENTER);

		//Adds the panels as bookmarks
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.addTab("Instructions", null, instPanel, "Reversi game instructions");
		tabbedPane.addTab("Tips", null, tipPanel, "Tips for victory");
		add(tabbedPane);
	}

}


