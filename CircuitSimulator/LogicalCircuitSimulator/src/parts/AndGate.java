package parts;

import controller.CircuitState;

public class AndGate extends LogicalGate {
	public synchronized boolean Update() {
		CircuitObjectPort inA = portList.get(0);
		CircuitObjectPort inB = portList.get(1);
		CircuitObjectPort out = portList.get(2);
		if(inA.getState()==CircuitState.UNSTABLE||inB.getState()==CircuitState.UNSTABLE) {
			if(out.getState()==CircuitState.UNSTABLE) {
				return false;
			}else {
				this.state = CircuitState.UNSTABLE;
				out.setState(this, CircuitState.UNSTABLE);
				return true;
			}
		}else if(inA.getState()==CircuitState.HIGH&&inB.getState()==CircuitState.HIGH) {
			if(out.getState()==CircuitState.HIGH) {
				return false;
			}else {
				this.state = CircuitState.HIGH;
				out.setState(this, CircuitState.HIGH);
				return true;
			}
		}else {
			if(out.getState()==CircuitState.LOW) {
				return false;
			}else {
				this.state = CircuitState.LOW;
				out.setState(this, CircuitState.LOW);
				return true;
			}
		}
	}
}
