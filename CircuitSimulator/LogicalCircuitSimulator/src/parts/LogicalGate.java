package parts;

import logicalParts.LogicalObject;

public class LogicalGate extends LogicalObject {
	private static final long serialVersionUID = 1L;

	public LogicalGate() {
		super(2,1,0); // 2 input 1 output
	}
}
