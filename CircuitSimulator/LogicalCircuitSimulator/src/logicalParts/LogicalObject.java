package logicalParts;

import java.io.Serializable;
import java.util.ArrayList;
import controller.CircuitStateEnum;
import controller.ObjectContainer;
import controller.PortMap;
import graphicalParts.GraphicalObject;
import graphicalParts.GraphicalObjectPort;
import main.Main;
import parts.AndGate;
import parts.OrGate;
import parts.XorGate;

public abstract class LogicalObject implements Serializable { //SerializeOK
	private static final long serialVersionUID = 1L;
	protected ObjectContainer container; //SerializeOK
	public PortMap portMap;
	int I,O,IO;
	protected CircuitStateEnum state;
	private int sleepTime;
	
	private GraphicalObject connectedObject;
	
	public LogicalObject(int inputPortNumber, int outputPortNumber, int inoutPortNumber, PortMap portMap) {
		this.portMap = portMap;
		I=inputPortNumber;
		O=outputPortNumber;
		IO=inoutPortNumber;
		state = CircuitStateEnum.UNSTABLE;
		sleepTime = 100;
		for(int i=0; i<inputPortNumber;i++) {
			portMap.addLogicalObjectPort(CircuitObjectPortDirection.INPUT,this);
		}
		for(int i=0; i<outputPortNumber;i++) {
			portMap.addLogicalObjectPort(CircuitObjectPortDirection.OUTPUT,this);
		}
		for(int i=0; i<inoutPortNumber;i++) {
			portMap.addLogicalObjectPort(CircuitObjectPortDirection.INOUT,this);
		}
	}
	public void addTokensToOutputs(int timeToLive) {
		for(int i=I;i<I+O;i++) {
			Main.tokenContainer.addToken(portMap.getL(i).GetConnectedObject(), timeToLive);
		}
	}
	public static boolean Connect(LogicalObjectPort portA, LogicalObjectPort portB) {
		boolean aIsConnected, bIsConnected;
		aIsConnected = portA.Connect(portB);
		bIsConnected = portB.Connect(portA);
		if(aIsConnected&&bIsConnected) throw new RuntimeException();
		return true;
	}
	public synchronized boolean Update() {return false;}
	public void tokenUpdate() {}
	protected void graphicalUpdate() {
		container.updateGraphicalComponent(state);
	}
	public CircuitStateEnum getState() {
		return state;
	}
	public LogicalObjectPort getPort(int port) {
		return portMap.getL(port);
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
	public GraphicalObject getConnectedObject() {
		return connectedObject;
	}
	public void connectObject(GraphicalObject Object) {
		this.connectedObject = Object;
	}
	public static LogicalObject addCircuitObject(String name, PortMap portMap) {
		switch(name) {
		case "IN":
			return new SimpleInput(portMap);
		case "OUT":
			return new SimpleOutput(portMap);
		case "AND":
			return new AndGate(portMap);
		case "OR":
			return new OrGate(portMap);
		case "XOR":
			return new XorGate(portMap);
		default:
			return null; // TODO
		}
	}
	public ArrayList<GraphicalObjectPort> getObjectPortList() {
		return null;
	}
	public void setContainer(ObjectContainer temp) {
		container = temp;
	}
	public void setState(String stateChar) {
	}
}
