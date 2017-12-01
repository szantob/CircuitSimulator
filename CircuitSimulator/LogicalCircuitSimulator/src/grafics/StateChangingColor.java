package grafics;

import controller.CircuitStateEnum;

public interface StateChangingColor {
	public default void setColorByState(CircuitStateEnum state) {
	}
}
