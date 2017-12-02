package parts;

public class SimpleWire extends CircuitObject {
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
	public static SimpleWire attachSimpleWireToPorts(CircuitObjectPort portA, CircuitObjectPort portB) {
		SimpleWire wire = new SimpleWire();
		CircuitObject.Connect(portA, wire.getPort(0));
		CircuitObject.Connect(portB, wire.getPort(1));
		return wire;
	}
}
