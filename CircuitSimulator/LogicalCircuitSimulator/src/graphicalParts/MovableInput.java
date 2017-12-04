package graphicalParts;

import javax.swing.JLabel;

import controller.PortMap;
import logicalParts.LogicalObject;
import logicalParts.SimpleInput;

public class MovableInput extends GraphicalObject {
	private static final long serialVersionUID = 1L;
	
	private SimpleInput simObject;
	public MovableInput(int posX, int posY, String lableStr, PortMap portMap){
		super(posX,posY,50,30, portMap);
		initialize();
		JLabel label = new JLabel("IN");
		label.setBounds(10, 13, 46, 14);
		add(label);
	}
	void initialize() {
		add(portMap.addGraphicalObjectPort(27, 8, side.RIGHT,this));
	}
	
	public void updateConnections() {
		portMap.refreshAttachedWires();
	}
	public SimpleInput getSimObject() {
		return simObject;
	}
	public void connectSimObject(LogicalObject simObject) {
		this.simObject = (SimpleInput)simObject;
	}

}
