package lookupTable;

import java.util.ArrayList;

import controller.CircuitState;

public class CircuitStateArray extends ArrayList<CircuitState>{
	private static final long serialVersionUID = 1L;
	private final int size;

	public CircuitStateArray(String str){
		super(str.length());
		size=str.length();
		for(int i=0;i<size;i++) {
			switch(str.charAt(i)) {
			case 'U':
				add(CircuitState.UNSTABLE);
			break;
			case 'H':
				add(CircuitState.HIGH);
			break;
			case 'L':
				add(CircuitState.LOW);
			break;
			case 'X':
				add(CircuitState.XDONTCARE);
			break;
			default:
				throw new RuntimeException("Invalid input");
			}
		}
	}
	public String toString(){
		String returnStr = new String("");
		for(int i=0;i<size();i++) {
			switch(get(i)) {
			case HIGH:
				returnStr+="H";
				break;
			case LOW:
				returnStr+="L";
				break;
			case UNSTABLE:
				returnStr+="U";
				break;
			case XDONTCARE:
				returnStr+="X";
				break;
			default:
				throw new RuntimeException("Invalid data");
			}
		}
		return returnStr;
	}
	public boolean equals(Object o){
		CircuitStateArray array = (CircuitStateArray)o;
		if(size()!=array.size()) return false;
		for(int i=0;i<size;i++) {
			switch(get(i)) {
			case HIGH:
				if(array.get(i)!=CircuitState.HIGH&&array.get(i)!=CircuitState.XDONTCARE) return false;
			case LOW:
				if(array.get(i)!=CircuitState.LOW&&array.get(i)!=CircuitState.XDONTCARE) return false;
			case UNSTABLE:
				if(array.get(i)!=CircuitState.UNSTABLE/*&&array.get(i)!=CircuitState.XDONTCARE*/) return false;
			case XDONTCARE:
				break;
			default:
				throw new RuntimeException("Invalid data");
			}
		}
		return true;
	}
}
