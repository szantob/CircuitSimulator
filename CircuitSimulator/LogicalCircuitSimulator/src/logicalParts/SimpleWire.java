package logicalParts;

import controller.PortMap;

public class SimpleWire extends LogicalObject {
	private static final long serialVersionUID = 1L;
	
	public SimpleWire(PortMap portMap) {
		super(1, 1, 0, portMap);
	}
	public synchronized boolean Update() {
		if(portMap.getL(0).getState()==state) {
			return false;
		}else {
			state = portMap.getL(0).getState();
			portMap.getL(1).setState(this, getState());
			graphicalUpdate();
			return true;
		}
	}
	public static SimpleWire attachSimpleWireToPorts(LogicalObjectPort portA, LogicalObjectPort portB, PortMap portMap) {
		if(portA==null||portB==null)return null;
		SimpleWire wire = new SimpleWire(portMap);
		LogicalObject.Connect(portA, wire.getPort(0));
		LogicalObject.Connect(portB, wire.getPort(1));
		return wire;
	}
}
