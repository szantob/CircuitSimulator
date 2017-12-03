package logicalParts;

import java.util.ArrayList;
import controller.CircuitStateEnum;
import controller.TokenContainer;
import graphicalParts.MovableComponent;
import parts.AndGate;
import parts.OrGate;
import parts.XorGate;

public abstract class LogicalObject {
	protected ArrayList<LogicalObjectPort> portList;
	int I,O,IO;
	protected CircuitStateEnum state;
	private int sleepTime;
	
	private MovableComponent connectedObject;
	
	public LogicalObject(int inputPortNumber, int outputPortNumber, int inoutPortNumber) {
		I=inputPortNumber;
		O=outputPortNumber;
		IO=inoutPortNumber;
		state = CircuitStateEnum.UNSTABLE;
		sleepTime = 100;
		portList = new ArrayList<LogicalObjectPort>();
		for(int i=0; i<inputPortNumber;i++) {
			portList.add(new LogicalObjectPort(CircuitObjectPortDirection.INPUT,this));
		}
		for(int i=0; i<outputPortNumber;i++) {
			portList.add(new LogicalObjectPort(CircuitObjectPortDirection.OUTPUT,this));
		}
		for(int i=0; i<inoutPortNumber;i++) {
			portList.add(new LogicalObjectPort(CircuitObjectPortDirection.INOUT,this));
		}
	}
	public void addTokensToOutputs(TokenContainer container, int timeToLive) {
		for(int i=I;i<i+O;i++) {
			container.addToken(portList.get(i).GetConnectedObject(), timeToLive);
		}
	}
	public static boolean Connect(LogicalObjectPort portA, LogicalObjectPort portB) {
		if(portA.Connect(portB)&&portB.Connect(portA)) throw new RuntimeException();
		return false;
	}
	public synchronized boolean Update() {
		return false;
	}
	public CircuitStateEnum getState() {
		return state;
	}
	public LogicalObjectPort getPort(int port) {
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
	public MovableComponent getConnectedObject() {
		return connectedObject;
	}
	public void connectObject(MovableComponent Object) {
		this.connectedObject = Object;
	}
	public static LogicalObject addCircuitObject(String name) {
		switch(name) {
		case "IN":
			return new SimpleInput();
		case "OUT":
			return new SimpleOutput();
		case "AND":
			return new AndGate();
		case "OR":
			return new OrGate();
		case "XOR":
			return new XorGate();
		default:
			return null; // TODO
		}
	}
}
