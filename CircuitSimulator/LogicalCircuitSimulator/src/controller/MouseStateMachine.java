package controller;

import graphicalParts.MovableComponent;
import graphicalParts.MovableComponentPort;
import graphicalParts.MovableWire;
import logicalParts.LogicalObject;
import logicalParts.LogicalObjectPort;
import logicalParts.SimpleWire;

enum MouseState{
	DEFAULT,
	GATE_SELECTED,
	PORT_SELECTED
}

public class MouseStateMachine {
	private static String selectedGate;
	private static MovableComponentPort selectedPort;
	
	private static MouseState state = MouseState.DEFAULT;
	
	public static void objectTreeMouseEvent(String node) {
		state=MouseState.GATE_SELECTED;
		selectedGate = node;
	}
	
	public static boolean backgroundLeftMouseEvent(int posX, int posY) {
		if(state!=MouseState.GATE_SELECTED) return false;
		PartArray.addNewPart(MovableComponent.addGraphicalObject(posX, posY, selectedGate), LogicalObject.addCircuitObject(selectedGate));
		state=MouseState.DEFAULT;
		selectedGate = null;
		return true;
	}
	public static void backgroundRightMouseEvent() {
		state = MouseState.DEFAULT;
		selectedGate = null;
		selectedPort = null;
	}
	
	public static void objectPortEvent(MovableComponentPort port) {
		switch(state) {
		case PORT_SELECTED:
			if(port.equals(selectedPort)) return;
			LogicalObjectPort logicalPort = null; //TODO
			LogicalObjectPort selectedLogicalPort = null; //TODO
			PartArray.addNewPart(	MovableWire.attachMovableWireToPorts(port, selectedPort),
									SimpleWire.attachSimpleWireToPorts(logicalPort,selectedLogicalPort));
			state = MouseState.DEFAULT;
			selectedPort = null;
			break;
		default:
			state = MouseState.PORT_SELECTED;
			selectedPort = port;
			break;
		// TODO foglalkozni az osszes esettel
		}
	}

	public static void backgroundMiddleMouseEvent(int x, int y) {
		// TODO Auto-generated method stub
		
	}
}
