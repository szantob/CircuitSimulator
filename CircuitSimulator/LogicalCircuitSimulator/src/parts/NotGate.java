package parts;

import controller.CircuitState;

public class NotGate extends CircuitObject {
	public NotGate() {
		super(1, 1, 0);
	}
	public synchronized boolean Update() {
		switch(portList.get(0).getState()) {
		case UNSTABLE:
			if(state==CircuitState.UNSTABLE) {
				return false;
			}else {
				state=CircuitState.UNSTABLE;
				return true;
			}
		case HIGH:
			if(state==CircuitState.LOW) {
				return false;
			}else {
				state=CircuitState.LOW;
				return true;
			}
		case LOW:
			if(state==CircuitState.HIGH) {
				return false;
			}else {
				state=CircuitState.HIGH;
				return true;
			}
		default:
			throw new NullPointerException("No valid input state found");
		}
	}
}
