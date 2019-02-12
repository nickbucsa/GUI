// abstract window toolkit
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Cursor;

// awt events
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

// swing
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSplitPane;
import javax.swing.JScrollPane;
import javax.swing.JDesktopPane;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.JInternalFrame;
import javax.swing.SwingUtilities;

// swing event
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

// swing tree
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.JTree;

public class GUIApp {
	
	private JFrame frame;
	private JDesktopPane desktop;
	private JScrollPane scrollPane;
	private JLabel statusLabel;
	private JPanel panel, labelPanel;
	private JMenuBar menuBar;
	private JMenu fileMenu, helpMenu;
	private JSplitPane splitPane;
	private JMenuItem exitItem, aboutItem;
	private JTree tree;

	// constructor
	public GUIApp() {
		initComponents();
		statusLabel.setText("Initialization complete.");
	}
	
	private void setLookAndFeel() {
		try {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void buildDesktop() {
		desktop = new JDesktopPane()
		{
			@Override
			protected void paintComponent(Graphics g)
			{
				ImageIcon icon = new ImageIcon("images/csun_logo.png");
				Image image = icon.getImage();
				int x=0, y=0;
				double imageWidth = image.getWidth(null);
				double imageHeight = image.getHeight(null);
				double screenWidth = getWidth();
				double screenHeight = getHeight();
				if (screenWidth != 0) {
					x = (int)screenWidth / 2 - (int)imageWidth / 2;
				}
				if (screenHeight != 0) {
					y = (int)screenHeight / 2 - (int)imageHeight / 2;
				}
				g.drawImage(image, x, y, this);
			}
		};
	}
	
	private void buildMenu() {
		menuBar = new JMenuBar();
		fileMenu = new JMenu("File");
		helpMenu = new JMenu("Help");
		exitItem = new JMenuItem("Exit");
		aboutItem = new JMenuItem("About");
		fileMenu.add(exitItem);
		helpMenu.add(aboutItem);
		menuBar.add(fileMenu);
		menuBar.add(helpMenu);
	}
	
	public void exitActionPerformed() {
		frame.dispose();
	}
	
	public void aboutActionPerformed() {
		JOptionPane.showMessageDialog(null, "Thanks for using my app!");
	}
	
	private void addMenuListeners() {
		exitItem.addActionListener(
			new java.awt.event.ActionListener() {
				public void actionPerformed(ActionEvent e) {
					exitActionPerformed();
				}
			}
		);
		aboutItem.addActionListener(
			new java.awt.event.ActionListener() {
				public void actionPerformed(ActionEvent e) {
					aboutActionPerformed();
				}
			}
		);
	}
	
	private void buildPanel() {
		panel = new JPanel();
		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		scrollPane = new JScrollPane();
		labelPanel = new JPanel();
		statusLabel = new JLabel();
		
		scrollPane.getViewport().add(tree);
		
		statusLabel.setBorder(BorderFactory.createLoweredBevelBorder());
		statusLabel.setMinimumSize(new Dimension(0, 18));
		statusLabel.setPreferredSize(new Dimension(0, 18));
		
		splitPane.setOneTouchExpandable(true);
		splitPane.setDividerLocation(200);
		splitPane.setContinuousLayout(true);
		splitPane.add(desktop, JSplitPane.RIGHT);
		splitPane.add(scrollPane, JSplitPane.LEFT);
		
		panel.setLayout(new BorderLayout());
		panel.add(splitPane, BorderLayout.CENTER);
		
		labelPanel.setLayout(new BorderLayout());
		labelPanel.add(statusLabel, BorderLayout.CENTER);
	}
	
	private void buildFrame() {
		// create a new frame and show it
		frame.setDefaultLookAndFeelDecorated(true);
		frame = new JFrame("My GUI Application");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("images/csun.gif"));
		// add label panel
		frame.getContentPane().add(labelPanel, BorderLayout.SOUTH);
		// add main panel
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		// add menu bar
		frame.setJMenuBar(menuBar);
		frame.setSize(1280,800);
		frame.setVisible(true);
	}
	
	private void buildTree() {
		// create data for the tree
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Tools");
		DefaultMutableTreeNode alg = new DefaultMutableTreeNode("Algorithms");
		DefaultMutableTreeNode odd = new DefaultMutableTreeNode("Odd");
		DefaultMutableTreeNode io = new DefaultMutableTreeNode("IO");
		DefaultMutableTreeNode fileInfo = new DefaultMutableTreeNode("File Info");
		DefaultMutableTreeNode weightOnMoon = new DefaultMutableTreeNode("Weight On Moon");
		DefaultMutableTreeNode milesToMeters = new DefaultMutableTreeNode("Miles To Meters");
		DefaultMutableTreeNode milesToKm = new DefaultMutableTreeNode("Miles To Kilometers");
		DefaultMutableTreeNode decToHex = new DefaultMutableTreeNode("Dec To Hex");
		DefaultMutableTreeNode decToBinary = new DefaultMutableTreeNode("Dec To Binary");
		DefaultMutableTreeNode phNrGen = new DefaultMutableTreeNode("Phone Nr Gen");
		DefaultMutableTreeNode passGen = new DefaultMutableTreeNode("Password Gen");
		alg.add(odd);
		alg.add(weightOnMoon);
		alg.add(milesToMeters);
		alg.add(milesToKm);
		alg.add(decToHex);
		alg.add(decToBinary);
		alg.add(phNrGen);
		alg.add(passGen);
		io.add(fileInfo);
		root.add(alg);
		root.add(io);
		// create a new tree
		DefaultTreeModel treeModel = new DefaultTreeModel(root);
		tree = new JTree(treeModel);
	}
	
	private void addTreeListeners() {
		tree.addMouseListener(
			new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					int selRow = tree.getRowForLocation(e.getX(), e.getY());
					if(selRow != -1) {
						treeClicked();
					}
				}
			}
		);
	}
	
	private void treeClicked() {
		// get the last selected tree node
		DefaultMutableTreeNode node = (DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
		// if the node is a leaf (no children, keep going)
		if (node != null && node.isLeaf()) {
			statusLabel.setText(node.toString() + " clicked.");
			if (node.toString().equals("Odd")) {
				OddDialog od = OddDialog.getInstance();
				if (!od.isVisible()) {
					od.setVisible(true);
					desktop.add(od);
				}
			}
			else if (node.toString().equals("File Info")) {
				ReadDialog rd = ReadDialog.getInstance();
				if (!rd.isVisible()) {
					rd.setVisible(true);
					desktop.add(rd);
				}
			} else if (node.toString().equals("Weight On Moon")) {
				WeightOnMoonDialog wom = WeightOnMoonDialog.getInstance();
				if (!wom.isVisible()) {
					wom.setVisible(true);
					desktop.add(wom);
				}
			} else if (node.toString().equals("Miles To Meters")) {
				MilesToMetersDialog mtm = MilesToMetersDialog.getInstance();
				if (!mtm.isVisible()) {
					mtm.setVisible(true);
					desktop.add(mtm);
				}
			} else if (node.toString().equals("Miles To Kilometers")) {
				MilesToKmDialog mtk = MilesToKmDialog.getInstance();
				if (!mtk.isVisible()) {
					mtk.setVisible(true);
					desktop.add(mtk);
				}
			} else if (node.toString().equals("Dec To Hex")) {
				DecToHexDialog dth = DecToHexDialog.getInstance();
				if (!dth.isVisible()) {
					dth.setVisible(true);
					desktop.add(dth);
				}
			} else if (node.toString().equals("Dec To Binary")) {
				DecToBinaryDialog dtb = DecToBinaryDialog.getInstance();
				if (!dtb.isVisible()) {
					dtb.setVisible(true);
					desktop.add(dtb);
				}
			}  else if (node.toString().equals("Phone Nr Gen")) {
				PhNrGenDialog png = PhNrGenDialog.getInstance();
				if (!png.isVisible()) {
					png.setVisible(true);
					desktop.add(png);
				}
			}  else if (node.toString().equals("Password Gen")) {
				PassGenDialog pass = PassGenDialog.getInstance();
				if (!pass.isVisible()) {
					pass.setVisible(true);
					desktop.add(pass);
				}
			}
		} // end if isleaf
	} // end treeClicked
	
	private void initComponents() {
		setLookAndFeel();
		buildDesktop();
		buildTree();
		addTreeListeners();
		buildMenu();
		addMenuListeners();
		buildPanel();
		buildFrame();
	}
}
