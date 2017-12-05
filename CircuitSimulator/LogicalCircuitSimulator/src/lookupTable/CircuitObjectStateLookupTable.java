package lookupTable;

import java.util.ArrayList;


public class CircuitObjectStateLookupTable{
	ArrayList<CircuitStateArray> keyList;
	ArrayList<CircuitStateArray> valueList;

	private int keyLength;
	private int valueLength;
	
	boolean unstableSensitive;
	
	public CircuitObjectStateLookupTable(boolean unstableSensitive, int keyLength, int valueLength) {
		this.keyLength=keyLength;
		this.valueLength=valueLength;
		this.unstableSensitive=unstableSensitive;
		keyList = new ArrayList<CircuitStateArray>();
		valueList = new ArrayList<CircuitStateArray>();
	}
	public CircuitObjectStateLookupTable(int keyLength, int valueLength) {
		this(true,keyLength,valueLength);
	}
	
	public String toString() {
		String ret = new String();
		ArrayList<CircuitStateArray> keys = new ArrayList<CircuitStateArray>();
		for (int i = 0; i < keys.size(); i++) {
			CircuitStateArray key = (CircuitStateArray) keys.get(i);
		    ret+=key.toString();
		    ret+="|";
		    ret+=get(key).toString();
		    ret+=";";
		}
		return ret;
	}
	public String prettyPrint() {
		String ret = new String();
		ArrayList<CircuitStateArray> keys = new ArrayList<CircuitStateArray>();
		int rowLength =keys.get(0).size()+get(keys.get(0)).size()+1;
		ret+="+";
		for (int i = 0; i < rowLength; i++) ret+="-";
		ret+="+";
		ret+=System.lineSeparator();
		for (int i = 0; i < keys.size(); i++) {
			CircuitStateArray key = (CircuitStateArray) keys.get(i);
			ret+="|";
		    ret+=key.toString();
		    ret+="|";
		    ret+=get(key).toString();
			ret+="|";
		    ret+=System.lineSeparator();
		}
		ret+="+";
		for (int i = 0; i < rowLength; i++) ret+="-";
		ret+="+";
	    ret+=System.lineSeparator();
		return ret;
	}
	public int size() {
		return keyList.size();
	}
	public boolean isEmpty() {
		return keyList.isEmpty();
	}
	public boolean containsKey(Object key) {
		CircuitStateArray arr = (CircuitStateArray)key;
		if(arr.size()!=keyLength) return false;
		for(int i=0;i<keyList.size();i++) {
			if(keyList.equals(key)) return true;
		}
		return false;
	}
	public boolean containsValue(Object value) {
		CircuitStateArray arr = (CircuitStateArray)value;
		if(arr.size()!=keyLength) return false;
		for(int i=0;i<valueList.size();i++) {
			if(valueList.equals(value)) return true;
		}
		return false;
	}
	public CircuitStateArray get(Object key) {
		CircuitStateArray arr = (CircuitStateArray)key;
		if(arr.size()!=keyLength) return null;
		if(arr.hasUnstable()&&this.unstableSensitive) return valueList.get(0);
		for(int i=0;i<keyList.size();i++) {
			if(keyList.get(i).equals(key)) return valueList.get(i);
		}
		return null;
	}
	public CircuitStateArray put(CircuitStateArray key, CircuitStateArray value) {
		CircuitStateArray arr = (CircuitStateArray)key;
		if(arr.size()!=keyLength) return null;
		arr = (CircuitStateArray)value;
		if(arr.size()!=keyLength) return null;
		keyList.add(key);
		valueList.add(value);
		return null;
	}
	public CircuitStateArray remove(Object key) {
		CircuitStateArray arr = (CircuitStateArray)key;
		if(arr.size()!=keyLength) return null;
		valueList.remove(keyList.indexOf(key));
		keyList.remove(key);
		return null;
	}
	public void clear() {
		keyList.clear();
		valueList.clear();
	}
}
