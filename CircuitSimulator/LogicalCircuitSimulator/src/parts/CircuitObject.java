package parts;

import java.util.ArrayList;
import controller.CircuitState;
import controller.TokenContainer;



enum CircuitObjectPortDirection{
	INPUT,
	OUTPUT,
	INOUT
}

class CircuitObjectPort{
	protected CircuitObjectPortDirection direction;
	protected CircuitObjectPort connectedPort;
	private CircuitObject connectedObject;
	private CircuitObject homeObject;
	private CircuitState state;
	private boolean isChanged; 
	
	public CircuitObjectPort(CircuitObjectPortDirection direction, CircuitObject homeObject) {
		this.direction=direction;
		this.homeObject=homeObject;
		this.isChanged=false;
		this.state=CircuitState.UNSTABLE;
	}
	public boolean Connect(CircuitObjectPort toPort) {
		if((direction == toPort.direction)&&direction!=CircuitObjectPortDirection.INOUT) return false;
		connectedPort = toPort;
		connectedObject = toPort.GetHomeObject();
		return false;
	}
	boolean setState(CircuitObject whoSetted, CircuitState state) {
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
	public CircuitState getState() {
		return state;
	}
	public boolean isChanged() {
		return isChanged;
	}
}


public class CircuitObject {
	protected ArrayList<CircuitObjectPort> portList;
	int I,O,IO;
	protected CircuitState state;
	private int sleepTime;
	
	public CircuitObject(int inputPortNumber, int outputPortNumber, int inoutPortNumber) {
		I=inputPortNumber;
		O=outputPortNumber;
		IO=inoutPortNumber;
		state = CircuitState.UNSTABLE;
		sleepTime = 100;
		portList = new ArrayList<CircuitObjectPort>();
		for(int i=0; i<inputPortNumber;i++) {
			portList.add(new CircuitObjectPort(CircuitObjectPortDirection.INPUT,this));
		}
		for(int i=0; i<outputPortNumber;i++) {
			portList.add(new CircuitObjectPort(CircuitObjectPortDirection.OUTPUT,this));
		}
		for(int i=0; i<inoutPortNumber;i++) {
			portList.add(new CircuitObjectPort(CircuitObjectPortDirection.INOUT,this));
		}
	}
	public void addTokensToOutputs(TokenContainer container, int timeToLive) {
		for(int i=I;i<i+O;i++) {
			container.addToken(portList.get(i).GetConnectedObject(), timeToLive);
		}
	}
	public static boolean Connect(CircuitObjectPort portA, CircuitObjectPort portB) {
		if(portA.Connect(portB)&&portB.Connect(portA)) throw new RuntimeException();
		return false;
	}
	public synchronized boolean Update() {
		return false;
	}
	public CircuitState getState() {
		return state;
	}
	public CircuitObjectPort getPort(int port) {
		return portList.get(port);
	}
	/**
	 * @return the sleepTime
	 */
	public int getSleepTime() {
		return sleepTime;
	}
	public String toString() {
		switch(state) {
		case HIGH: return "H";
		case LOW: return "L";
		case UNSTABLE: return "U";
		default: return "U";
		}
	}
}
