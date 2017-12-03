package graphicalParts;

import javax.swing.JLabel;

import logicalParts.LogicalObject;
import logicalParts.SimpleInput;

public class MovableInput extends MovableComponent {
	private static final long serialVersionUID = 1L;
	MovableComponentPort out;
	
	private SimpleInput simObject;
	public MovableInput(int posX, int posY, String lableStr){
		super(posX,posY,50,30);
		//super.setChild(this);
		initialize();
		JLabel label = new JLabel("IN");
		label.setBounds(10, 13, 46, 14);
		add(label);
	}
	void initialize() {
		out=new MovableComponentPort(27, 8, side.RIGHT,this);
		add(out);
	}
	
	public void updateConnections() {
		if(out.getAttachedWire()!=null)out.getAttachedWire().refresh(out);
	}
	public SimpleInput getSimObject() {
		return simObject;
	}
	public void connectSimObject(LogicalObject simObject) {
		this.simObject = (SimpleInput)simObject;
	}

}
