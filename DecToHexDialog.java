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

class DecToHexDialog extends JInternalFrame {
	
	private static DecToHexDialog instance = null;
	
	private JTextField tf;
	private JButton btn;
	private JLabel lbl, lbl2;
	private JPanel upperPanel, lowerPanel;
	
	public static DecToHexDialog getInstance() {
		if (instance == null) {
			instance = new DecToHexDialog();
		}
		return instance;
	}
	
	private void convActionPerformed() {
		int input = 0;
		lbl2.setText("");
		try {
			input = Integer.parseInt(tf.getText());
			lbl2.setText(Integer.toHexString(input));
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(this, "Bad input! Try again.");
		}
	} // end convActionPerformed
	
	// private constructor
	private DecToHexDialog() {
		// call constructor of JInternalFrame
		// Arguments: title, resizability, closability,
		// maximizability, and iconifiability
		super("Dec To Hex", false, true, false, false);
		tf = new JTextField(10);
		btn = new JButton("Convert");
		lbl = new JLabel("Decimal To Hexadecimal: ");
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
				convActionPerformed();
			}
		});
		setBounds(25, 25, 250, 120);
		setLocation(190, 100);
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
	} // end private constructor
} // end class DecToHexDialog