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
		initialize();
	}
	void initialize() {
		inA=new movableComponentPort(1, 1, side.LEFT);
		add(inA);
		inB=new movableComponentPort(1, 24, side.LEFT);
		add(inB);
		out=new movableComponentPort(31, 13, side.RIGHT);
		add(out);
	}
	@SuppressWarnings("unused")
	private void updateConnections() {
		inA.getAttachedWire().refresh(inA);
		inB.getAttachedWire().refresh(inB);
		out.getAttachedWire().refresh(out);
	}

}
