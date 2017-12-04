package graphicalParts;

import javax.swing.JLabel;

import controller.PortMap;
import parts.LogicalGate;

public class MovableGate extends GraphicalObject {
	private static final long serialVersionUID = 1L;
	
	LogicalGate simObject;
	public MovableGate(int posX, int posY, String lableStr, PortMap portMap){
		super(posX,posY,70,50,portMap);
		initialize();
		JLabel label = new JLabel(lableStr);
		label.setBounds(10, 18, 46, 14);
		add(label);
	}
	void initialize() {
		add(portMap.addGraphicalObjectPort(1, 1, side.LEFT,this));
		add(portMap.addGraphicalObjectPort(1, 34, side.LEFT,this));
		add(portMap.addGraphicalObjectPort(48, 18, side.RIGHT,this));
	}
	
	public void updateConnections() {
		portMap.refreshAttachedWires();
	}

}
