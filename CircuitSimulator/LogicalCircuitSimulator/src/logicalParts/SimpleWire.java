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
			try {
				graphicalUpdate();
			}catch(Exception e) {
				e.printStackTrace(System.err);
			}
			return true;
		}
	}
	public static SimpleWire attachSimpleWireToPorts(LogicalObjectPort portA, LogicalObjectPort portB, PortMap portMap) {
		if(portA==null||portB==null)return null;
		SimpleWire wire = new SimpleWire(portMap);
		switch(portA.direction) {
		case INPUT:
			LogicalObject.Connect(portA, wire.getPort(1));
			LogicalObject.Connect(wire.getPort(0), portB);
			break;
		case OUTPUT:
			LogicalObject.Connect(portA, wire.getPort(0));
			LogicalObject.Connect(wire.getPort(1), portB);
			break;
		case INOUT:
		default:
			LogicalObject.Connect(portA, wire.getPort(0));
			LogicalObject.Connect(wire.getPort(1), portB);
			break;
		}
		return wire;
	}
}
