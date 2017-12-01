package grafics;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

import javax.swing.JPanel;

public class movableWire extends JPanel{
	private static final long serialVersionUID = 1L;
	
	int aPosX, aPosY, bPosX, bPosY;
	private movableComponentPort aPort, bPort;
	private Graphics2D g2;
	
	public static movableWire attachMovableWireToPorts(movableComponentPort portA, movableComponentPort portB){
		movableWire wire = new movableWire(portA.getConnectionX(), portA.getConnectionY(), portB.getConnectionX(), portB.getConnectionY());
		wire.aPort=portA;
		wire.bPort=portB;
		portA.attachWire(wire);
		portB.attachWire(wire);
		return  wire;
	}
	
	public movableWire(int aPosX, int aPosY, int bPosX, int bPosY){
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
		g2.setColor(Color.BLACK);
		g2.setStroke(new BasicStroke(2f));
        g2.draw(lin);
	}
	public void refresh(movableComponentPort port) {
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
}
