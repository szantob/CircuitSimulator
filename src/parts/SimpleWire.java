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
}
