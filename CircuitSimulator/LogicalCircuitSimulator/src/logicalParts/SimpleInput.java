package logicalParts;

import controller.CircuitStateEnum;
import controller.PortMap;

public class SimpleInput extends LogicalObject{
	private static final long serialVersionUID = 1L;
	public SimpleInput(PortMap portMap) {
		super(0, 1, 0, portMap);
		state=CircuitStateEnum.UNSTABLE;
	}
	public synchronized boolean Update(){/*
		if(portList.get(0).getState()!=state) {
			portList.get(0).setState(this, state);*/
		if(portMap.getL(0).getState()!=state) {
			portMap.getL(0).setState(this, state);
			return true;
		}else {
			return false;
		}
	}
	public void setState(CircuitStateEnum state) {
		this.state=state;
	}
	public void setState(String stateChar) {
		switch(stateChar) {
		case "H": state=CircuitStateEnum.HIGH;
		break;
		case "L": state=CircuitStateEnum.LOW;
		break;
		case "U": state=CircuitStateEnum.UNSTABLE;
		break;
		default: state=CircuitStateEnum.UNSTABLE;
		}
	}
	public LogicalObjectPort getPort() {
		//return portList.get(0);
		return portMap.getL(0);
	}
}
