package graphicalParts;
// TODO Moving bug
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

import controller.CircuitStateEnum;
import controller.PortMap;
import graphics.CircuitWindow;
import graphics.GraficSettings;

public class MovableWire extends GraphicalObject{
	private static final long serialVersionUID = 1L;
	
	int aPosX, aPosY, bPosX, bPosY;
	private GraphicalObjectPort aPort, bPort;
	private volatile Graphics2D g2;
	
	public static MovableWire attachMovableWireToPorts(GraphicalObjectPort portA, GraphicalObjectPort portB, PortMap portMap){
		//portMap.addGraphicalObjectPort(portA.getX(), portA.getY(), portA., bela)
		MovableWire wire = new MovableWire(portA.getConnectionX(), portA.getConnectionY(), portB.getConnectionX(), portB.getConnectionY(),portMap);
		wire.aPort=portMap.addVirtualGraphicalObjectPort(portA);
		wire.bPort=portMap.addVirtualGraphicalObjectPort(portB);
		portA.attachWire(wire);
		portB.attachWire(wire);
		CircuitWindow.workplace.add(wire);
		CircuitWindow.frame.repaint();
		CircuitWindow.frame.revalidate();
		return  wire;
	}
	
	public MovableWire(int aPosX, int aPosY, int bPosX, int bPosY, PortMap portMap){
		super(0, 0, GraficSettings.WORKPLACE_WIDTH, GraficSettings.WORKPLACE_HEIGHT, portMap);
		this.aPosX=aPosX;
		this.aPosY=aPosY;
		this.bPosX=bPosX;
		this.bPosY=bPosY;
		setBounds(0, 0, GraficSettings.WORKPLACE_WIDTH, GraficSettings.WORKPLACE_HEIGHT);
		setOpaque(false);
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g2 = (Graphics2D) g;
        Line2D lin = new Line2D.Float(aPosX, aPosY, bPosX, bPosY);
		g2.setColor(color);
		g2.setStroke(new BasicStroke(3f));
        g2.draw(lin);
	}
	public void refresh(GraphicalObjectPort port) {
		if(port == aPort) {
			aPosX=port.getConnectionX();
			aPosY=port.getConnectionY();
		}else if(port == bPort) {
			bPosX=port.getConnectionX();
			bPosY=port.getConnectionY();
		}else {
			////////////////////////////////////////////////////////// EXC
		} 
		
		CircuitWindow.frame.repaint();
		CircuitWindow.frame.revalidate();
	}
	public void setColorByState(CircuitStateEnum state) {
		switch(state) {
		case HIGH:
			color = Color.RED;
			break;
		case LOW:
			color = Color.BLUE;
			break;
		case UNSTABLE:
			color = Color.YELLOW;
			break;
		default:
			color = Color.YELLOW;
			break;
		}
		
		CircuitWindow.frame.repaint();
		CircuitWindow.frame.revalidate();
	}
	protected void moveToPos(int newPosX, int newPosY) {
	}
}
