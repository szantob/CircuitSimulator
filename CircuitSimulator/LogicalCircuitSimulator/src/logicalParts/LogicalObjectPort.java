package logicalParts;

import controller.CircuitStateEnum;

enum CircuitObjectPortDirection{
	INPUT,
	OUTPUT,
	INOUT
}

public class LogicalObjectPort{
	protected CircuitObjectPortDirection direction;
	protected LogicalObjectPort connectedPort;
	private LogicalObject connectedObject;
	private LogicalObject homeObject;
	private CircuitStateEnum state;
	private boolean isChanged; 
	
	public LogicalObjectPort(CircuitObjectPortDirection direction, LogicalObject homeObject) {
		this.direction=direction;
		this.homeObject=homeObject;
		this.isChanged=false;
		this.state=CircuitStateEnum.UNSTABLE;
	}
	public boolean Connect(LogicalObjectPort toPort) {
		if((direction == toPort.direction)&&direction!=CircuitObjectPortDirection.INOUT) return false;
		connectedPort = toPort;
		connectedObject = toPort.GetHomeObject();
		return false;
	}
	public boolean setState(LogicalObject whoSetted, CircuitStateEnum state) {
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
	public LogicalObject GetHomeObject() {
		return homeObject;
	}
	public LogicalObject GetConnectedObject() {
		return connectedObject;
	}
	public CircuitStateEnum getState() {
		return state;
	}
	public boolean isChanged() {
		return isChanged;
	}
}