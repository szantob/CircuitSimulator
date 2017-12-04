package parts;

import controller.PortMap;
import logicalParts.LogicalObject;

public class LogicalGate extends LogicalObject {
	private static final long serialVersionUID = 1L;

	public LogicalGate(PortMap portMap) {
		super(2,1,0,portMap); // 2 input 1 output
	}
}
