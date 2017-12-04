package controller;

import java.util.ArrayList;

import graphicalParts.GraphicalObject;
import graphicalParts.GraphicalObjectPort;
import graphicalParts.side;
import logicalParts.CircuitObjectPortDirection;
import logicalParts.LogicalObject;
import logicalParts.LogicalObjectPort;

public class PortMap {
	private ArrayList<GraphicalObjectPort> graphicalPortList;
	private ArrayList<LogicalObjectPort> logicalPortList;

	public PortMap() {
		graphicalPortList = new ArrayList<GraphicalObjectPort>();
		logicalPortList = new ArrayList<LogicalObjectPort>();
	}
	
	public GraphicalObjectPort addGraphicalObjectPort(int posX, int posY, side portSide, GraphicalObject bela) {
		GraphicalObjectPort tmp = new GraphicalObjectPort(posX, posY, portSide, bela);
		graphicalPortList.add(tmp);
		return tmp;
	}
	public LogicalObjectPort addLogicalObjectPort(CircuitObjectPortDirection direction, LogicalObject homeObject) {
		LogicalObjectPort tmp = new LogicalObjectPort(direction, homeObject); //TODO
		logicalPortList.add(tmp);
		return tmp;
	}
	public void refreshAttachedWires() {
		for(GraphicalObjectPort i : graphicalPortList) {
			if(i.getAttachedWire()!=null)i.getAttachedWire().refresh(i);
		}
	}
	public GraphicalObjectPort getG(int i) {
		return graphicalPortList.get(i);
	}
	public LogicalObjectPort getL(int i) {
		return logicalPortList.get(i);
	}
}
