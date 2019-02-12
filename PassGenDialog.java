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

import java.util.List;
import java.security.SecureRandom;
import java.util.ArrayList;

class PassGenDialog extends JInternalFrame {
	
	private static PassGenDialog instance = null;
	
	private JTextField tf;
	private JButton btn;
	private JLabel lbl, lbl2;
	private JPanel upperPanel, lowerPanel;
	
	public static PassGenDialog getInstance() {
		if (instance == null) {
			instance = new PassGenDialog();
		}
		return instance;
	}
	
	private void genActionPerformed() {
		lbl2.setText("");
		int randInt;
		StringBuilder sb = new StringBuilder();
		List<Integer> l = new ArrayList();;
		
		for (int i = 33; i < 127; i++) {
			l.add(i);
		}
		// removes characters /, \ and "
		l.remove(new Integer(34));
		l.remove(new Integer(47));
		l.remove(new Integer(92));
		// randomize over the ASCII numbers and append respective char values into a string builder
		for (int i = 0; i < 10; i++) {
			randInt = l.get(new SecureRandom().nextInt(91));
			sb.append((char) randInt);
		}
		lbl2.setText(sb.toString());
	} // end genActionPerformed
	
	// private constructor
	private PassGenDialog() {
		// call constructor of JInternalFrame
		// Arguments: title, resizability, closability,
		// maximizability, and iconifiability
		super("Password Generator", false, true, false, false);
		tf = new JTextField(10);
		btn = new JButton("Generate");
		lbl = new JLabel("Password: ");
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
} // end class PassGenDialog