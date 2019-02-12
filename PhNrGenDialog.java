import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

import java.awt.FlowLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

class PhNrGenDialog extends JInternalFrame {
	
	private static PhNrGenDialog instance = null;
	
	private JTextField tf;
	private JButton btn;
	private JLabel lbl, lbl2;
	private JPanel upperPanel, lowerPanel;
	
	public static PhNrGenDialog getInstance() {
		if (instance == null) {
			instance = new PhNrGenDialog();
		}
		return instance;
	}
	
	private void genActionPerformed() {
		int input = 0;
		int gen1 = 0;
		int gen2 = 0;
		lbl2.setText("");
		try {
			input = Integer.parseInt(tf.getText());
			if (input > 100 && input < 999) {
				gen1 = 100 + (int)(Math.random() * 900);
				gen2 = 1000 + (int)(Math.random() * 9000);
				lbl2.setText(String.valueOf(input) +"."+ String.valueOf(gen1) +"."+ String.valueOf(gen2));
			} else {
				JOptionPane.showMessageDialog(this, "That doesn't look like an area code! Try again.");
			}
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(this, "Bad area code! Try again.");
		}
	} // end genActionPerformed
	
	// private constructor
	private PhNrGenDialog() {
		// call constructor of JInternalFrame
		// Arguments: title, resizability, closability,
		// maximizability, and iconifiability
		super("Phone Nr Generator", false, true, false, false);
		tf = new JTextField(10);
		btn = new JButton("Generate");
		lbl = new JLabel("Phone number: ");
		lbl2 = new JLabel();
		upperPanel = new JPanel();
		lowerPanel = new JPanel();
		upperPanel.setLayout(new FlowLayout());
		upperPanel.setLayout(new FlowLayout());
		upperPanel.add(tf);
		upperPanel.add(btn);
		lowerPanel.add(lbl);
		lowerPanel.add(lbl2);
		add(upperPanel, BorderLayout.NORTH);
		add(lowerPanel, BorderLayout.SOUTH);
		// add button listener
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				genActionPerformed();
			}
		});
		setBounds(25, 25, 250, 120);
		setLocation(250, 100);
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
	} // end private constructor
} // end class PhNrGenDialog