package graphicalParts;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JRadioButton;

import controller.MouseStateMachine;

enum side{
	LEFT,
	RIGHT;
}

public class MovableComponentPort extends JPanel {
	private static final long serialVersionUID = 1L;
	private MovableComponent bela;
	private JRadioButton button;
	private side portSide;
	private MovableWire attachedWire;
	public MovableComponentPort(int posX, int posY, side portSide, MovableComponent bela){
		this.bela=bela;
		this.portSide=portSide;
		setBounds(posX,posY,22,15);
		setLayout(null);
		button=new JRadioButton();
		//button.setEnabled(false);
		button.setBounds(1, 1, 22, 15);
		button.addActionListener(new myActionListener(this));
		add(button);
	}
	public int getConnectionX() {
		if(portSide==side.LEFT) {
			return 0+getX()+getParent().getX();
		}else {
			return 22+getX()+getParent().getX();
		}
	}
	public int getConnectionY() {
		return 6+getY()+getParent().getY();
	}
	public void attachWire(MovableWire wire){
		if(wire == null) {
			
		}else {
			attachedWire = wire;
			/////////////////////////////////////
		}
	}
	public void updatePos() {
		////////////////////
	}
	public MovableWire getAttachedWire() {
		return attachedWire;
	}
	public MovableComponent getMovableComponent() {
		return bela;
	}
}

class myActionListener implements ActionListener{
	MovableComponentPort port;
	myActionListener(MovableComponentPort port){
		this.port=port;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		JRadioButton button = (JRadioButton)e.getSource();
		button.isSelected();
		MouseStateMachine.objectPortEvent(port);
	}
	
}
