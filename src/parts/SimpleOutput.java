package parts;

import controller.CircuitState;

public class SimpleOutput extends CircuitObject{

	public SimpleOutput() {
		super(1, 0, 0);
		state=CircuitState.UNSTABLE;
	}
	public synchronized boolean Update(){
		if(portList.get(0).getState()!=state) {
			state = portList.get(0).getState();
			return true;
		}else {
			return false;
		}
	}
	public CircuitObjectPort getPort() {
		return portList.get(0);
	}
}