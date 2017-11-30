package grafics;

public class movableGate extends movableComponent {
	private static final long serialVersionUID = 1L;
	movableComponentPort inA, inB, out;
	movableGate(){
		super(10,10,50,40);
		initialize();
	}
	movableGate(int posX, int posY){
		super(posX,posY,50,40);
		super.setChild(this);
		initialize();
	}
	void initialize() {
		inA=new movableComponentPort(1, 1, side.LEFT,this);
		add(inA);
		inB=new movableComponentPort(1, 24, side.LEFT,this);
		add(inB);
		out=new movableComponentPort(31, 13, side.RIGHT,this);
		add(out);
	}
	
	public void updateConnections() {
		if(inA.getAttachedWire()!=null)inA.getAttachedWire().refresh(inA);
		if(inB.getAttachedWire()!=null)inB.getAttachedWire().refresh(inB);
		if(out.getAttachedWire()!=null)out.getAttachedWire().refresh(out);
	}

}
