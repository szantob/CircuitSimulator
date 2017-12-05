package graphicalParts;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

import javax.swing.JComboBox;
import controller.PortMap;
import logicalParts.LogicalObject;
import logicalParts.SimpleInput;

public class MovableInput extends GraphicalObject {
	private static final long serialVersionUID = 1L;
	private LogicalLevelBox logicalLevelBox;
	
	private SimpleInput simObject;
	public MovableInput(int posX, int posY, String lableStr, PortMap portMap){
		super(posX,posY,61,30, portMap);
		add(portMap.addGraphicalObjectPort(34, 8, side.RIGHT,this));
	}
	public void initialize() {
		super.initialize();
		this.logicalLevelBox = new LogicalLevelBox();
		add(this.logicalLevelBox);
		this.logicalLevelBox.addActionListener(new ComboBoxActionListener(this));
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
class ComboBoxActionListener implements ActionListener, Serializable {
	private static final long serialVersionUID = 1L;
	MovableInput homeObject;
	ComboBoxActionListener(MovableInput home){
		homeObject = home;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		LogicalLevelBox cb = (LogicalLevelBox)e.getSource();
        String logicalLevelStr = (String)cb.getSelectedItem();
        try {
        	homeObject.getHomeContainer().updateLogicalComponent(logicalLevelStr);
        }catch(Exception exc) {
        	//TODO
        }
	}
}

class LogicalLevelBox extends JComboBox<String>{
	private static final long serialVersionUID = 1L;
	LogicalLevelBox(){
		super(new String[] {"H", "L"});
		setSelectedIndex(0);
		setBounds(5,5, 20, 20);
	}
}
