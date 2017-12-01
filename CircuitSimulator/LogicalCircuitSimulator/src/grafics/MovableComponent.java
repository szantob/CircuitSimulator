package grafics;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import controller.CircuitStateEnum;

public class MovableComponent extends JPanel implements StateChangingColor{
	private static final long serialVersionUID = 1L;
	private int posX, posY, width, height;
	private Color color;
	
	MovableComponent mmovableComponent;
	MovableComponent(){
    	initialize();
	}
	MovableComponent(int posX, int posY, int width, int height){
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
}
