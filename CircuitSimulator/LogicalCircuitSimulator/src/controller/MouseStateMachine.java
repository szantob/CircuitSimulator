package controller;

import graphics.MovableComponentPort;
import graphics.MovableWire;

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
			return;
		case "OUT":
			return;
		default:
			return;
		}
	}
	
	public static String backgroundMouseEvent() {
		if(state!=MouseState.GATE_SELECTED) return null;
		String tmp = selectedGate;
		state=MouseState.DEFAULT;
		selectedGate = null;
		return tmp;
	}
	
	public static void objectPortEvent(MovableComponentPort port) {
		switch(state) {
		case PORT_SELECTED:
			if(port.equals(selectedPort)) return;
			MovableWire.attachMovableWireToPorts(port, selectedPort);
			break;
		default:
			state = MouseState.PORT_SELECTED;
			break;
		
		}
	}
}
