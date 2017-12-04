package controller;

import graphicalParts.GraphicalObjectPort;
import main.Main;

enum MouseState{
	DEFAULT,
	GATE_SELECTED,
	PORT_SELECTED
}

public class MouseStateMachine {
	private static String selectedGate;
	private static GraphicalObjectPort selectedPort;
	
	private static MouseState state = MouseState.DEFAULT;
	
	public static void objectTreeMouseEvent(String node) {
		state=MouseState.GATE_SELECTED;
		selectedGate = node;
	}
	
	public static boolean backgroundLeftMouseEvent(int posX, int posY) {
		if(state!=MouseState.GATE_SELECTED) return false;
		Main.partArray.addNewPart(posX, posY, selectedGate);
		state=MouseState.DEFAULT;
		selectedGate = null;
		return true;
	}
	public static void backgroundRightMouseEvent() {
		state = MouseState.DEFAULT;
		selectedGate = null;
		selectedPort = null;
	}
	
	public static void objectPortEvent(GraphicalObjectPort port) {
		switch(state) {
		case PORT_SELECTED:
			if(port.equals(selectedPort)) return;
			if(!PartArray.addNewPart(port,selectedPort)) return; //TODO
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
