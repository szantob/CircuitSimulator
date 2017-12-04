/**
 * 
 */
package controller;

import java.io.Serializable;
import graphicalParts.GraphicalObject;
import graphicalParts.GraphicalObjectPort;
import graphicalParts.MovableWire;
import logicalParts.LogicalObject;
import logicalParts.SimpleWire;

/**
 * @author Szanto Benedek David
 *
 */
public class ObjectContainer implements Serializable { //Serialize
	private static final long serialVersionUID = 1L;
	private GraphicalObject graphicalComponent;
	private LogicalObject logicalComponent;
	
	ObjectContainer(GraphicalObject graphicalComponent, LogicalObject logicalComponent){
		this.graphicalComponent=graphicalComponent;
		this.logicalComponent=logicalComponent;
	}
	
	public static ObjectContainer newObjectContainer(int posX, int posY, String type) {
		PortMap portMap = new PortMap();
		ObjectContainer temp = new ObjectContainer(GraphicalObject.addGraphicalObject(posX, posY, type, portMap),
													LogicalObject.addCircuitObject(type,portMap));
		temp.graphicalComponent.setContainer(temp);
		temp.logicalComponent.setContainer(temp);
		
		return temp;
	}
	public static ObjectContainer newObjectContainer(GraphicalObjectPort portA, GraphicalObjectPort portB) {
		PortMap portMapA = portA.getMovableComponent().getPortMap();
		PortMap portMapB = portB.getMovableComponent().getPortMap();
		
		PortMap portMap = new PortMap();
		MovableWire graphicalWire = MovableWire.attachMovableWireToPorts(portA, portB, portMap);
		SimpleWire logicalWire = SimpleWire.attachSimpleWireToPorts(portMapA.GtoL(portA), portMapB.GtoL(portB), portMap);
		ObjectContainer container = new ObjectContainer(graphicalWire,logicalWire);
		graphicalWire.setContainer(container);
		logicalWire.setContainer(container);
		return container;
	}


	public GraphicalObject getGraphicalComponent() {
		return graphicalComponent;
	}
	public LogicalObject getLogicalComponent() {
		return logicalComponent;
	}

	public void updateGraphicalComponent(CircuitStateEnum logicalLevel) {
		graphicalComponent.setColorByState(logicalLevel);
	}
	public void updateLogicalComponent(String logicalLevel) {
		logicalComponent.setState(logicalLevel);
		logicalComponent.tokenUpdate();	//TODO
	}
}
