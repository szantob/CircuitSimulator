package parts;

import controller.CircuitStateEnum;

public class AndGate extends LogicalGate {
	public synchronized boolean Update() {
		CircuitObjectPort inA = portList.get(0);
		CircuitObjectPort inB = portList.get(1);
		CircuitObjectPort out = portList.get(2);
		if(inA.getState()==CircuitStateEnum.UNSTABLE||inB.getState()==CircuitStateEnum.UNSTABLE) {
			if(out.getState()==CircuitStateEnum.UNSTABLE) {
				return false;
			}else {
				this.state = CircuitStateEnum.UNSTABLE;
				out.setState(this, CircuitStateEnum.UNSTABLE);
				return true;
			}
		}else if(inA.getState()==CircuitStateEnum.HIGH&&inB.getState()==CircuitStateEnum.HIGH) {
			if(out.getState()==CircuitStateEnum.HIGH) {
				return false;
			}else {
				this.state = CircuitStateEnum.HIGH;
				out.setState(this, CircuitStateEnum.HIGH);
				return true;
			}
		}else {
			if(out.getState()==CircuitStateEnum.LOW) {
				return false;
			}else {
				this.state = CircuitStateEnum.LOW;
				out.setState(this, CircuitStateEnum.LOW);
				return true;
			}
		}
	}
}
