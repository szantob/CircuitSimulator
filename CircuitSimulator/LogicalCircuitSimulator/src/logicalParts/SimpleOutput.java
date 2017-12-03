package logicalParts;

import controller.CircuitStateEnum;

public class SimpleOutput extends LogicalObject{
	private static final long serialVersionUID = 1L;
	
	public SimpleOutput() {
		super(1, 0, 0);
		state=CircuitStateEnum.UNSTABLE;
	}
	public synchronized boolean Update(){
		if(portList.get(0).getState()!=state) {
			state = portList.get(0).getState();
			return true;
		}else {
			return false;
		}
	}
	public LogicalObjectPort getPort() {
		return portList.get(0);
	}
}