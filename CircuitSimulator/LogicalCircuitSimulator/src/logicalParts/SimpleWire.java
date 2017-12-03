package logicalParts;

public class SimpleWire extends LogicalObject {
	private static final long serialVersionUID = 1L;
	
	public SimpleWire() {
		super(1, 1, 0);
	}
	public synchronized boolean Update() {
		if(portList.get(0).getState()==state) {
			return false;
		}else {
			state = portList.get(0).getState();
			portList.get(1).setState(this, getState());
			return true;
		}
	}
	public static SimpleWire attachSimpleWireToPorts(LogicalObjectPort portA, LogicalObjectPort portB) {
		if(portA==null||portB==null)return null;
		SimpleWire wire = new SimpleWire();
		LogicalObject.Connect(portA, wire.getPort(0));
		LogicalObject.Connect(portB, wire.getPort(1));
		return wire;
	}
}
