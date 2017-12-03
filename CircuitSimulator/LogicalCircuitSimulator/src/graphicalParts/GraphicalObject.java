package graphicalParts;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Serializable;

import javax.swing.JPanel;

import controller.CircuitStateEnum;
import graphics.CircuitWindow;
import graphics.GraficSettings;
import graphics.StateChangingColor;
import logicalParts.LogicalObject;

public abstract class GraphicalObject extends JPanel implements StateChangingColor, Serializable {
	private static final long serialVersionUID = 1L;
	private int posX, posY, width, height;
	
	protected Color color = Color.YELLOW;
	private LogicalObject connectedObject;
	
	public LogicalObject getConnectedObject() {
		return connectedObject;
	}
	public void connectObject(LogicalObject object) {
		this.connectedObject = object;
	}

	
	GraphicalObject mmovableComponent;
	GraphicalObject(){
    	initialize();
	}
	GraphicalObject(int posX, int posY, int width, int height){
    	this.posX=posX;
    	this.posY=posY;
    	this.width=width;
    	this.height=height;
    	setBounds(posX-width/2, posY-height/2, width, height);
    	initialize();
	}
	private void initialize() {
		setBackground(Color.YELLOW);
		addMouseMotionListener(new MouseAdapter(){
            public void mouseDragged(MouseEvent E)
            {
            	posX=E.getX()+getX();
               	posY=E.getY()+getY();
               	if(posX<0) posX=0;
	        	if(posY<0) posY=0;
	        	if(posX>2*GraficSettings.WORKPLACE_WIDTH) posX=2*GraficSettings.WORKPLACE_WIDTH;
	        	if(posY>2*GraficSettings.WORKPLACE_HEIGHT) posY=2*GraficSettings.WORKPLACE_HEIGHT;
	        	setLocation(posX-width/2,posY-height/2);
	        	((MovableGate)mmovableComponent).updateConnections();
            }
        });
		setLayout(null);
	}
	@SuppressWarnings("unused")
	private void updateConnections() {
	}
	public void setChild(MovableGate movableGate) {
		mmovableComponent = movableGate;
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
		setBackground(color);
	}
	public static GraphicalObject addGraphicalObject(int posX, int posY, String label) {
		GraphicalObject tmp;
		switch(label) {
		case "IN":
			tmp = new MovableInput(posX, posY, label);
			break;
		default:
			tmp = new MovableGate(posX, posY, label);
			break;
		}
		CircuitWindow.workplace.add(tmp);
		CircuitWindow.frame.repaint();
		CircuitWindow.frame.revalidate();
		return tmp;
	}
}
