package logicalParts;

import controller.CircuitStateEnum;
import controller.PortMap;

public class SimpleOutput extends LogicalObject{
	private static final long serialVersionUID = 1L;
	
	public SimpleOutput(PortMap portMap) {
		super(1, 0, 0, portMap);
		state=CircuitStateEnum.UNSTABLE;
	}
	public synchronized boolean Update(){
		if(portMap.getL(0).getState()!=state) {
			state = portMap.getL(0).getState();
			return true;
		}else {
			return false;
		}
	}
	public LogicalObjectPort getPort() {
		return portMap.getL(0);
	}
}