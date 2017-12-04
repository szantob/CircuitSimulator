package graphicalParts;

import javax.swing.JLabel;

import parts.LogicalGate;

public class MovableGate extends GraphicalObject {
	private static final long serialVersionUID = 1L;
	//GraphicalObjectPort inA, inB, out;
	
	LogicalGate simObject;
	public MovableGate(int posX, int posY, String lableStr){
		super(posX,posY,70,50);
		initialize();
		JLabel label = new JLabel(lableStr);
		label.setBounds(10, 18, 46, 14);
		add(label);
	}
	void initialize() {
		//inA=portMap.addGraphicalObjectPort(1, 1, side.LEFT,this);
		add(portMap.addGraphicalObjectPort(1, 1, side.LEFT,this));
		//inB=new GraphicalObjectPort(1, 34, side.LEFT,this);
		add(portMap.addGraphicalObjectPort(1, 34, side.LEFT,this));
		//out=new GraphicalObjectPort(48, 18, side.RIGHT,this);
		add(portMap.addGraphicalObjectPort(48, 18, side.RIGHT,this));
	}
	
	public void updateConnections() {
		/*if(inA.getAttachedWire()!=null)inA.getAttachedWire().refresh(inA);
		if(inB.getAttachedWire()!=null)inB.getAttachedWire().refresh(inB);
		if(out.getAttachedWire()!=null)out.getAttachedWire().refresh(out);*/
		portMap.refreshAttachedWires();
	}

}
