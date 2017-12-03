package graphicalParts;

import javax.swing.JLabel;

import parts.LogicalGate;

public class MovableGate extends MovableComponent {
	private static final long serialVersionUID = 1L;
	MovableComponentPort inA, inB, out;
	
	LogicalGate simObject;
	public MovableGate(int posX, int posY, String lableStr){
		super(posX,posY,70,50);
		super.setChild(this);
		initialize();
		JLabel label = new JLabel(lableStr);
		label.setBounds(10, 18, 46, 14);
		add(label);
	}
	void initialize() {
		inA=new MovableComponentPort(1, 1, side.LEFT,this);
		add(inA);
		inB=new MovableComponentPort(1, 34, side.LEFT,this);
		add(inB);
		out=new MovableComponentPort(48, 18, side.RIGHT,this);
		add(out);
	}
	
	public void updateConnections() {
		if(inA.getAttachedWire()!=null)inA.getAttachedWire().refresh(inA);
		if(inB.getAttachedWire()!=null)inB.getAttachedWire().refresh(inB);
		if(out.getAttachedWire()!=null)out.getAttachedWire().refresh(out);
	}

}
