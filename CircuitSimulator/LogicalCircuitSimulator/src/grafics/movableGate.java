package grafics;

import javax.swing.JLabel;

import parts.LogicalGate;

public class movableGate extends movableComponent {
	private static final long serialVersionUID = 1L;
	movableComponentPort inA, inB, out;
	
	LogicalGate simObject;
	movableGate(){
		super(10,10,50,40);
		initialize();
	}
	movableGate(int posX, int posY, String lableStr){
		super(posX,posY,70,50);
		super.setChild(this);
		initialize();
		JLabel label = new JLabel(lableStr);
		label.setBounds(10, 18, 46, 14);
		add(label);
	}
	void initialize() {
		inA=new movableComponentPort(1, 1, side.LEFT,this);
		add(inA);
		inB=new movableComponentPort(1, 34, side.LEFT,this);
		add(inB);
		out=new movableComponentPort(48, 18, side.RIGHT,this);
		add(out);
	}
	
	public void updateConnections() {
		if(inA.getAttachedWire()!=null)inA.getAttachedWire().refresh(inA);
		if(inB.getAttachedWire()!=null)inB.getAttachedWire().refresh(inB);
		if(out.getAttachedWire()!=null)out.getAttachedWire().refresh(out);
	}

}
