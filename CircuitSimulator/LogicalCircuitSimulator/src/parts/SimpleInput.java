package parts;

import controller.CircuitState;

public class SimpleInput extends CircuitObject{

	public SimpleInput() {
		super(0, 1, 0);
		state=CircuitState.UNSTABLE;
	}
	public synchronized boolean Update(){
		if(portList.get(0).getState()!=state) {
			portList.get(0).setState(this, state);
			return true;
		}else {
			return false;
		}
	}
	public void setState(CircuitState state) {
		this.state=state;
	}
	public void setState(String stateChar) {
		switch(stateChar) {
		case "H": state=CircuitState.HIGH;
		break;
		case "L": state=CircuitState.LOW;
		break;
		case "U": state=CircuitState.UNSTABLE;
		break;
		default: state=CircuitState.UNSTABLE;
		}
	}
	public CircuitObjectPort getPort() {
		return portList.get(0);
	}
}
