package logicalParts;

import java.io.Serializable;
import java.util.ArrayList;
import controller.CircuitStateEnum;
import controller.PortMap;
import controller.TokenContainer;
import graphicalParts.GraphicalObject;
import graphicalParts.GraphicalObjectPort;
import parts.AndGate;
import parts.OrGate;
import parts.XorGate;

public abstract class LogicalObject implements Serializable {
	private static final long serialVersionUID = 1L;
	//protected ArrayList<LogicalObjectPort> portList;
	public PortMap portMap;
	int I,O,IO;
	protected CircuitStateEnum state;
	private int sleepTime;
	
	private GraphicalObject connectedObject;
	
	public LogicalObject(int inputPortNumber, int outputPortNumber, int inoutPortNumber, PortMap portMap) {
		I=inputPortNumber;
		O=outputPortNumber;
		IO=inoutPortNumber;
		state = CircuitStateEnum.UNSTABLE;
		sleepTime = 100;
		//portList = new ArrayList<LogicalObjectPort>();
		for(int i=0; i<inputPortNumber;i++) {
			portMap.addLogicalObjectPort(CircuitObjectPortDirection.INPUT,this);
			//portList.add(new LogicalObjectPort(CircuitObjectPortDirection.INPUT,this));
		}
		for(int i=0; i<outputPortNumber;i++) {
			//portList.add(new LogicalObjectPort(CircuitObjectPortDirection.OUTPUT,this));
			portMap.addLogicalObjectPort(CircuitObjectPortDirection.OUTPUT,this);
		}
		for(int i=0; i<inoutPortNumber;i++) {
			//portList.add(new LogicalObjectPort(CircuitObjectPortDirection.INOUT,this));
			portMap.addLogicalObjectPort(CircuitObjectPortDirection.INOUT,this);
		}
	}
	public void addTokensToOutputs(TokenContainer container, int timeToLive) {
		for(int i=I;i<i+O;i++) {
			//container.addToken(portList.get(i).GetConnectedObject(), timeToLive);
			container.addToken(portMap.getL(i).GetConnectedObject(), timeToLive);
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
		//return portList.get(port);
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
		// TODO Auto-generated method stub
		return null;
	}
}
