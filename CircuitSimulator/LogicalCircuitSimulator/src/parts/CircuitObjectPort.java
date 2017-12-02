package parts;

import controller.CircuitStateEnum;

enum CircuitObjectPortDirection{
	INPUT,
	OUTPUT,
	INOUT
}

public class CircuitObjectPort{
	protected CircuitObjectPortDirection direction;
	protected CircuitObjectPort connectedPort;
	private CircuitObject connectedObject;
	private CircuitObject homeObject;
	private CircuitStateEnum state;
	private boolean isChanged; 
	
	public CircuitObjectPort(CircuitObjectPortDirection direction, CircuitObject homeObject) {
		this.direction=direction;
		this.homeObject=homeObject;
		this.isChanged=false;
		this.state=CircuitStateEnum.UNSTABLE;
	}
	public boolean Connect(CircuitObjectPort toPort) {
		if((direction == toPort.direction)&&direction!=CircuitObjectPortDirection.INOUT) return false;
		connectedPort = toPort;
		connectedObject = toPort.GetHomeObject();
		return false;
	}
	boolean setState(CircuitObject whoSetted, CircuitStateEnum state) {
		if((whoSetted == homeObject)&&(direction == CircuitObjectPortDirection.INPUT)||
			(whoSetted == connectedObject)&&(direction == CircuitObjectPortDirection.OUTPUT)) return false; /////////////
		if(this.state == state) return false;
		this.state = state;
		if(direction == CircuitObjectPortDirection.INPUT) {
			isChanged = true;
		}else {
			if(connectedPort != null) {
				connectedPort.setState(homeObject, state);
			}else {
				return false;
			}
		}
		return true;	
	}
	public CircuitObject GetHomeObject() {
		return homeObject;
	}
	public CircuitObject GetConnectedObject() {
		return connectedObject;
	}
	public CircuitStateEnum getState() {
		return state;
	}
	public boolean isChanged() {
		return isChanged;
	}
}
