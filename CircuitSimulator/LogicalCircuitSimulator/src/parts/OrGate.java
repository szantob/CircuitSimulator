package parts;

import controller.CircuitStateEnum;
import controller.PortMap;
import logicalParts.LogicalObjectPort;

public class OrGate extends LogicalGate {
	private static final long serialVersionUID = 1L;

	public OrGate(PortMap portMap) {
		super(portMap);
	}

	public synchronized boolean Update() {
		LogicalObjectPort inA = portMap.getL(0);
		LogicalObjectPort inB = portMap.getL(1);
		LogicalObjectPort out = portMap.getL(2);
		if(inA.getState()==CircuitStateEnum.UNSTABLE||inB.getState()==CircuitStateEnum.UNSTABLE) {
			if(out.getState()==CircuitStateEnum.UNSTABLE) {
				return false;
			}else {
				this.state = CircuitStateEnum.UNSTABLE;
				out.setState(this, CircuitStateEnum.UNSTABLE);
				return true;
			}
		}else if(inA.getState()==CircuitStateEnum.HIGH||inB.getState()==CircuitStateEnum.HIGH) {
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