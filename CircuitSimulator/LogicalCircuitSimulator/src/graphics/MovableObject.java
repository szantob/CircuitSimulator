package graphics;

import java.awt.Color;

import javax.swing.JPanel;

import parts.CircuitObject;

public class MovableObject extends JPanel implements StateChangingColor {
	private static final long serialVersionUID = 1L;
	protected Color color = Color.YELLOW;
	private CircuitObject connectedObject;
	public CircuitObject getConnectedObject() {
		return connectedObject;
	}
	public void connectObject(CircuitObject object) {
		this.connectedObject = object;
	}

}
