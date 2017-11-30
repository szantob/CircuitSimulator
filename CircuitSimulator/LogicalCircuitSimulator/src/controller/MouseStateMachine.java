package controller;

enum MouseState{
	GATE_SELECTED
}

public class MouseStateMachine {
	private MouseState state;
	public void objectTreeMouseEvent(String node) {
		state=MouseState.GATE_SELECTED;
	}
	
	public backgroundMouseEvent() {
		
	}
}
