package parts;

import controller.CircuitStateEnum;
import logicalParts.LogicalObjectPort;

public class AndGate extends LogicalGate {
	private static final long serialVersionUID = 1L;

	public synchronized boolean Update() {
		LogicalObjectPort inA = portList.get(0);
		LogicalObjectPort inB = portList.get(1);
		LogicalObjectPort out = portList.get(2);
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
