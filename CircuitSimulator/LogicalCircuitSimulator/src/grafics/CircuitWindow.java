package grafics;

import javax.annotation.Resources;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.BorderLayout;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import main.ProgramResources;

import javax.swing.event.MouseInputListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

class movableBackground extends JPanel{
	private static final long serialVersionUID = 1L;
	private ProgramResources resource;
	int posX, posY;
	int width, height;
	movableBackground(){
		initialize();
	}
	movableBackground(ProgramResources resources){
		this.resource=resources;
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
		addMouseListener(new MClickListener(this));
		addMouseMotionListener(new MMotionListener(this));
	}
	
}


class CircuitObjectTree extends JPanel{
	public static final String JSON_FILE="circuitParts.txt";
	private static final long serialVersionUID = 1L;
	private ProgramResources resources;
	private JTree tree;
	
	CircuitObjectTree(ProgramResources resources){
		this.resources=resources;
		initialize();
	}
	private void initialize() {
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Circuit Parts");
		DefaultMutableTreeNode wires = new DefaultMutableTreeNode("Wires");
		wires.add(new DefaultMutableTreeNode("Simple Wire"));
		root.add(wires);
			DefaultMutableTreeNode gates = new DefaultMutableTreeNode("Logical Gates");
			gates.add(new DefaultMutableTreeNode("NOT"));
				DefaultMutableTreeNode gates2 = new DefaultMutableTreeNode("Logical Gates");
				gates2.add(new DefaultMutableTreeNode("AND"));
				gates2.add(new DefaultMutableTreeNode("OR"));
				gates2.add(new DefaultMutableTreeNode("XOR"));
			gates.add(gates2);
				DefaultMutableTreeNode ngates = new DefaultMutableTreeNode("Negated Logical Gates");
				ngates.add(new DefaultMutableTreeNode("NAND"));
				ngates.add(new DefaultMutableTreeNode("NOR"));
				ngates.add(new DefaultMutableTreeNode("XNOR"));
			gates.add(ngates);
		root.add(gates);
		tree = new JTree(root);
		JScrollPane scrollPane = new JScrollPane(tree);
		add(scrollPane);
		tree.addTreeSelectionListener(new TreeSelectionListener() {
	        public void valueChanged(TreeSelectionEvent evt) {
	        	String node = evt.getNewLeadSelectionPath().getLastPathComponent().toString();
	            resources.mouseSM.objectTreeMouseEvent(node);
	        }
		});
	}
}

public class CircuitWindow {
	private ProgramResources resources;
	public JFrame frame;
	public CircuitWindow(ProgramResources resource) {
		this.resources=resource;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel workfield = new JPanel();
		frame.getContentPane().add(workfield, BorderLayout.CENTER);
		workfield.setLayout(null);
		workfield.setBackground(Color.BLACK);
		
		
		JPanel background = new movableBackground(resources);
		background.setBounds(0,0,GraficSettings.WORKPLACE_WIDTH,GraficSettings.WORKPLACE_HEIGHT);
		workfield.add(background);

		movableGate gate1 = new movableGate(80,80);
		background.add(gate1);
		movableGate gate2 = new movableGate(240,80);
		background.add(gate2);
		

		movableWire wire = movableWire.attachMovableWireToPorts(frame, gate1.out, gate2.inB);
		background.add(wire);
				
		
		JPanel panel_1 = new JPanel();
		frame.getContentPane().add(panel_1, BorderLayout.WEST);
		
		
		panel_1.add(new CircuitObjectTree(resources));	
	}
}
class MClickListener implements MouseInputListener {

	JPanel mJPanel;
	
	public MClickListener(movableBackground movableBackground) {
		mJPanel = movableBackground;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		
		mJPanel.add(new movableGate(e.getX(),e.getY()));
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseDragged(MouseEvent e) {
    	
		
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
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
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}