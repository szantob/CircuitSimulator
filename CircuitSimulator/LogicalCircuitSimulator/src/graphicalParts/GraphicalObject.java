package graphicalParts;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JPanel;

import controller.CircuitStateEnum;
import controller.ObjectContainer;
import graphics.CircuitWindow;
import graphics.GraficSettings;
import graphics.StateChangingColor;

public abstract class GraphicalObject extends JPanel implements StateChangingColor, Serializable {
	private static final long serialVersionUID = 1L;
	
	private int posX, posY, width, height;
	
	private ArrayList<GraphicalObjectPort> portList;
	private ObjectContainer container;
	public void setContainer(ObjectContainer container) {
		this.container=container;
	}
	public ObjectContainer getmyContainer() {
		return container;
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
	public void updateConnections() { //TODO
	}
	public void setChild(MovableGate movableGate) {
		mmovableComponent = movableGate;
	}
	
	protected Color color = Color.YELLOW;
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
	public GraphicalObjectPort addGraphicalObjectPort(int posX, int posY, side portSide, GraphicalObject bela) {
		GraphicalObjectPort tmp = new GraphicalObjectPort(posX, posY, portSide, bela);
		portList.add(tmp);
		return tmp;
	}
	public ArrayList<GraphicalObjectPort> getObjectPortList(){
		return portList;
	}
	public GraphicalObjectPort getPort(int indexOf) {
		// TODO Auto-generated method stub
		return null;
	}
}


class MouseMoveAdapter extends MouseAdapter{
	GraphicalObject homeObject;
	MouseMoveAdapter(GraphicalObject homeObject){
		this.homeObject=homeObject;
	}

}
