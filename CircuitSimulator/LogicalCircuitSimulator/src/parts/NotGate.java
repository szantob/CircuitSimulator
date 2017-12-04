package parts;

import controller.CircuitStateEnum;
import logicalParts.LogicalObject;

public class NotGate extends LogicalObject {
	private static final long serialVersionUID = 1L;
	public NotGate() {
		super(1, 1, 0);
	}
	public synchronized boolean Update() {
		switch(portList.get(0).getState()) {
		case UNSTABLE:
			if(state==CircuitStateEnum.UNSTABLE) {
				return false;
			}else {
				state=CircuitStateEnum.UNSTABLE;
				return true;
			}
		case HIGH:
			if(state==CircuitStateEnum.LOW) {
				return false;
			}else {
				state=CircuitStateEnum.LOW;
				return true;
			}
		case LOW:
			if(state==CircuitStateEnum.HIGH) {
				return false;
			}else {
				state=CircuitStateEnum.HIGH;
				return true;
			}
		default:
			throw new NullPointerException("No valid input state found");
		}
	}
}
