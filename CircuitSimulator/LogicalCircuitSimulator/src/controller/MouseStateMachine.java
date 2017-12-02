package controller;

import graphics.MovableComponentPort;
import graphics.MovableWire;
import parts.CircuitObject;
import parts.CircuitObjectPort;
import parts.SimpleWire;

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
		switch(node) {
		case "AND":
		case "NAND":
		case "OR":
		case "NOR":
		case "XOR":
		case "XNOR":
			state=MouseState.GATE_SELECTED;
			selectedGate = node;
			return;
		case "NOT":
			return;
		case "IN":
			state=MouseState.GATE_SELECTED;
			selectedGate = node;
			return;
		case "OUT":
			return;
		default:
			return;
		}
	}
	
	public static String backgroundLeftMouseEvent() {
		if(state!=MouseState.GATE_SELECTED) return null;
		String tmp = selectedGate;
		state=MouseState.DEFAULT;
		selectedGate = null;
		return tmp;
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
			CircuitObjectPort logicalPort = null; //TODO
			CircuitObjectPort selectedLogicalPort = null; //TODO
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
}
