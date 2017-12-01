package grafics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JRadioButton;

import controller.MouseStateMachine;

enum side{
	LEFT,
	RIGHT;
}

public class movableComponentPort extends JPanel {
	private static final long serialVersionUID = 1L;
	private movableComponent bela;
	private JRadioButton button;
	private side portSide;
	private movableWire attachedWire;
	public movableComponentPort(int posX, int posY, side portSide, movableComponent bela){
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
	public void attachWire(movableWire wire){
		if(wire == null) {
			
		}else {
			attachedWire = wire;
			/////////////////////////////////////
		}
	}
	public void updatePos() {
		////////////////////
	}
	public movableWire getAttachedWire() {
		return attachedWire;
	}
	public movableComponent getMovableComponent() {
		return bela;
	}
}

class myActionListener implements ActionListener{
	movableComponentPort port;
	myActionListener(movableComponentPort port){
		this.port=port;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		JRadioButton button = (JRadioButton)e.getSource();
		button.isSelected();
		MouseStateMachine.objectPortEvent(port);
	}
	
}
