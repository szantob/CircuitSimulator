package grafics;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class movableWire extends JPanel{
	private static final long serialVersionUID = 1L;
	int aPosX, aPosY, bPosX, bPosY;
	private movableComponentPort aPort, bPort;
	private Graphics2D g2;
	private JFrame frame;
	
	public static movableWire attachMovableWireToPorts(JFrame frame, movableComponentPort portA, movableComponentPort portB){
		movableWire wire = new movableWire(frame, portA.getConnectionX(), portA.getConnectionY(), portB.getConnectionX(), portB.getConnectionY());
		wire.aPort=portA;
		wire.bPort=portB;
		return  wire;
	}
	public movableWire(JFrame frame, int aPosX, int aPosY, int bPosX, int bPosY){
		this.frame = frame;
		this.aPosX=aPosX;
		this.aPosY=aPosY;
		this.bPosX=bPosX;
		this.bPosY=bPosY;
		setBounds(aPosX, aPosY, bPosX-aPosX, bPosY-aPosY+2);
		setOpaque(false);
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g2 = (Graphics2D) g;
        Line2D lin = new Line2D.Float(0, 1, bPosX-aPosX, bPosY-aPosY+1);
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

		frame.repaint();
		frame.revalidate();
	}
}
