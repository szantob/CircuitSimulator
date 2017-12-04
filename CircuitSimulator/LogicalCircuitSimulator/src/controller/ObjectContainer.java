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
public class ObjectContainer implements Serializable {
	private static final long serialVersionUID = 1L;
	private GraphicalObject graphicalComponent;
	private LogicalObject logicalComponent;
	ObjectContainer(GraphicalObject graphicalComponent, LogicalObject logicalComponent){
		this.graphicalComponent=graphicalComponent;
		this.logicalComponent=logicalComponent;
	}
	
	
	public static ObjectContainer newObjectContainer(int posX, int posY, String type) {
		ObjectContainer temp = new ObjectContainer(GraphicalObject.addGraphicalObject(posX, posY, type),
													LogicalObject.addCircuitObject(type));
		temp.graphicalComponent.setContainer(temp);
		/*temp.getGraphicalComponent().connectObject(temp.getLogicalComponent());
		temp.getLogicalComponent().connectObject(temp.getGraphicalComponent());
		temp.graphicalPortList = temp.getGraphicalComponent().getGraphicalObjectPortList();*/
		return temp;
	}
	public static ObjectContainer newObjectContainer(GraphicalObjectPort portA, GraphicalObjectPort portB) {
		MovableWire graphicalWire = MovableWire.attachMovableWireToPorts(portA, portB);
		SimpleWire logicalWire = new SimpleWire();
		ObjectContainer temp = new ObjectContainer(graphicalWire,logicalWire);
		//TODO
		return temp;
	}


	public GraphicalObject getGraphicalComponent() {
		return graphicalComponent;
	}
	public LogicalObject getLogicalComponent() {
		return logicalComponent;
	}




}
