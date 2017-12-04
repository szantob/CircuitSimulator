package graphicalParts;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import controller.PortMap;
import logicalParts.LogicalObject;
import logicalParts.SimpleInput;

public class MovableInput extends GraphicalObject {
	private static final long serialVersionUID = 1L;
	
	private SimpleInput simObject;
	public MovableInput(int posX, int posY, String lableStr, PortMap portMap){
		super(posX,posY,61,30, portMap);
		initialize();
	}
	void initialize() {
		String lvl[] = {"H", "L"};
		JComboBox<String> jComboBox = new JComboBox<String>(lvl);
		JComboBox<String> logicalLevelBox = jComboBox;
		logicalLevelBox.setSelectedIndex(0);
		logicalLevelBox.setBounds(5,5, 20, 20);
		logicalLevelBox.addActionListener(new ComboBoxActionListener(this));
		add(logicalLevelBox);
		add(portMap.addGraphicalObjectPort(34, 8, side.RIGHT,this));
	}
	
	public void updateConnections() {
		portMap.refreshAttachedWires();
	}
	public SimpleInput getSimObject() {
		return simObject;
	}
	public void connectSimObject(LogicalObject simObject) {
		this.simObject = (SimpleInput)simObject;
	}

}
class ComboBoxActionListener implements ActionListener {
	MovableInput homeObject;
	ComboBoxActionListener(MovableInput home){
		homeObject = home;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
        JComboBox<String> cb = (JComboBox<String>)e.getSource();
        String logicalLevelStr = (String)cb.getSelectedItem();
        try {
        	homeObject.getHomeContainer().updateLogicalComponent(logicalLevelStr);
        }catch(Exception exc) {
        	//TODO
        }
	}
}
