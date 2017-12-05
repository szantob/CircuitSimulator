package graphics;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Line2D;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.MouseInputListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import controller.MouseStateMachine;
import main.Main;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JSlider;
import java.awt.Insets;


public class CircuitWindow {
	public static JFrame frame;
	public static JPanel workplace;
	
	public CircuitWindow() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 472);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new ProgramMenu();
		frame.setJMenuBar(menuBar);
		
		
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		
		JPanel background = new JPanel();
		frame.getContentPane().add(background, BorderLayout.CENTER);
		background.setLayout(null);
		background.setBackground(Color.BLACK);
		workplace = new movableBackground();
		workplace.setBounds(0,0,GraficSettings.WORKPLACE_WIDTH,GraficSettings.WORKPLACE_HEIGHT);
		background.add(workplace);

		
		JPanel panel_1 = new JPanel();
		frame.getContentPane().add(panel_1, BorderLayout.WEST);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{161, 0};
		gbl_panel_1.rowHeights = new int[]{332, 0, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(0, 0, 5, 0);
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.gridx = 0;
		gbc.gridy = 0;
		graphics.CircuitObjectTree circuitObjectTree = new CircuitObjectTree();
		panel_1.add(circuitObjectTree, gbc);	
		
		JSlider slider = new JSlider();
		slider.setBounds(0, 0, 55, 20);
		GridBagConstraints gbc_slider = new GridBagConstraints();
		gbc_slider.gridx = 0;
		gbc_slider.gridy = 1;
		panel_1.add(slider, gbc_slider);
	}
}

class SimSpeedListener implements ChangeListener {
	@Override
	public void stateChanged(ChangeEvent e) {
        JSlider source = (JSlider) e.getSource();
        int value = (int)source.getValue();
        Main.tokenContainer.reduceSimSpeed(value*10000);
	}
}

class movableBackground extends JPanel{
	private static final long serialVersionUID = 1L;
	int posX, posY;
	int width, height;
	
	private volatile Graphics2D g2;
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g2 = (Graphics2D) g;
		for(int i=0; i<width; i+=GraficSettings.GRID_SIZE) {
	        Line2D lin = new Line2D.Float(i, 0, i, height);
			g2.setColor(Color.GRAY);
			g2.setStroke(new BasicStroke(1f));
	        g2.draw(lin);
		}
		for(int i=0; i<height; i+=GraficSettings.GRID_SIZE) {
	        Line2D lin = new Line2D.Float(0, i, width, i);
			g2.setColor(Color.GRAY);
			g2.setStroke(new BasicStroke(1f));
	        g2.draw(lin);
		}
	}
	movableBackground(){
		this.posX=0;
		this.posY=0;
		this.width=GraficSettings.WORKPLACE_WIDTH;
		this.height=GraficSettings.WORKPLACE_HEIGHT;
		setBounds(posX-width, posX-height, 2*width, 2*height);
		initialize();
	}
	private void initialize() {
		setBackground(Color.LIGHT_GRAY);
		setLayout(null);
		addMouseListener(new MClickListener());
		addMouseMotionListener(new MMotionListener(this));
	}
}
class MClickListener implements MouseInputListener {

	JPanel mJPanel;
	
	public MClickListener() {
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		if(SwingUtilities.isLeftMouseButton(e)) {
			MouseStateMachine.backgroundLeftMouseEvent(e.getX(),e.getY());
		}else if(SwingUtilities.isRightMouseButton(e)) {
			MouseStateMachine.backgroundLeftMouseEvent(e.getX(),e.getY());
		}else if(SwingUtilities.isMiddleMouseButton(e)) {
			MouseStateMachine.backgroundMiddleMouseEvent(e.getX(),e.getY());
		}
	}
	@Override
	public void mousePressed(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
	@Override
	public void mouseDragged(MouseEvent e) {}
	@Override
	public void mouseMoved(MouseEvent e) {}
}

class MMotionListener implements MouseMotionListener{
	int lastMouseX = 0;
	int lastMouseY = 0;

	movableBackground mmovableBackground;
	public MMotionListener(movableBackground movableBackground) {
		mmovableBackground = movableBackground;
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		int tempX = e.getXOnScreen() - lastMouseX;
    	int tempY = e.getYOnScreen() - lastMouseY;
    	if(Math.abs(tempX)<50) mmovableBackground.posX+= tempX;
    	if(Math.abs(tempY)<50) mmovableBackground.posY+= tempY;
       	lastMouseX= e.getXOnScreen();
       	lastMouseY= e.getYOnScreen();
    	if(mmovableBackground.posX>0) mmovableBackground.posX=0;
    	if(mmovableBackground.posY>0) mmovableBackground.posY=0;
    	if(mmovableBackground.posX<(0-mmovableBackground.width-1500)) mmovableBackground.posX=0-mmovableBackground.width-1500;
    	if(mmovableBackground.posY<(0-mmovableBackground.height-1000)) mmovableBackground.posY=0-mmovableBackground.height-1000;
    	mmovableBackground.setLocation(mmovableBackground.posX,mmovableBackground.posY);
		//e.getX()+tempX,e.getY()+tempY
	}
	@Override
	public void mouseMoved(MouseEvent e) {}
}

class CircuitObjectTree extends JPanel{
	public static final String JSON_FILE="circuitParts.txt";
	private static final long serialVersionUID = 1L;
	private JTree tree;
	
	CircuitObjectTree(){
		initialize();
	}
	private void initialize() {
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Circuit Parts");
		DefaultMutableTreeNode io = new DefaultMutableTreeNode("IO");
		io.add(new DefaultMutableTreeNode("IN"));
		io.add(new DefaultMutableTreeNode("OUT"));
		root.add(io);
		DefaultMutableTreeNode gates2 = new DefaultMutableTreeNode("Logical Gates");
			gates2.add(new DefaultMutableTreeNode("NOT"));
			gates2.add(new DefaultMutableTreeNode("AND"));
			gates2.add(new DefaultMutableTreeNode("OR"));
			gates2.add(new DefaultMutableTreeNode("XOR"));
		root.add(gates2);
		DefaultMutableTreeNode ngates = new DefaultMutableTreeNode("Negated Logical Gates");
			ngates.add(new DefaultMutableTreeNode("NAND"));
			ngates.add(new DefaultMutableTreeNode("NOR"));
			ngates.add(new DefaultMutableTreeNode("XNOR"));
		root.add(ngates);
		tree = new JTree(root);
		JScrollPane scrollPane = new JScrollPane(tree);
		add(scrollPane);
		tree.addTreeSelectionListener(new TreeSelectionListener() {
	        public void valueChanged(TreeSelectionEvent evt) {
	        	DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode) evt.getNewLeadSelectionPath().getLastPathComponent();
	            MouseStateMachine.objectTreeMouseEvent(treeNode.toString());
	            tree.setExpandsSelectedPaths(false);
	        }
		});
	}
}