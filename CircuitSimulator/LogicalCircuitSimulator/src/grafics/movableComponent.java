package grafics;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

public class movableComponent extends JPanel{
	private static final long serialVersionUID = 1L;
	int posX, posY, width, height;
	movableComponent mmovableComponent;
	movableComponent(){
    	initialize();
	}
	movableComponent(int posX, int posY, int width, int height){
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
	        	((movableGate)mmovableComponent).updateConnections();
            }
        });
		setLayout(null);
	}
	@SuppressWarnings("unused")
	private void updateConnections() {
	}
	public void setChild(movableGate movableGate) {
		mmovableComponent = movableGate;
	}
}
