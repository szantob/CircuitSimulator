package lookupTable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Set;


public class CircuitObjectStateLookupTable implements Map<CircuitStateArray, CircuitStateArray>{
	ArrayList<CircuitStateArray> keyList = new ArrayList<CircuitStateArray>();
	ArrayList<CircuitStateArray> valueList = new ArrayList<CircuitStateArray>();
	
	public String toString() {
		String ret = new String();
		ArrayList<CircuitStateArray> keys = new ArrayList<CircuitStateArray>(keySet());
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
		ArrayList<CircuitStateArray> keys = new ArrayList<CircuitStateArray>(keySet());
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
	@Override
	public int size() {
		return keyList.size();
	}
	@Override
	public boolean isEmpty() {
		return keyList.isEmpty();
	}
	@Override
	public boolean containsKey(Object key) {
		for(int i=0;i<keyList.size();i++) {
			if(keyList.equals(key)) return true;
		}
		return false;
	}
	@Override
	public boolean containsValue(Object value) {
		for(int i=0;i<valueList.size();i++) {
			if(valueList.equals(value)) return true;
		}
		return false;
	}
	@Override
	public CircuitStateArray get(Object key) {
		for(int i=0;i<keyList.size();i++) {
			if(keyList.get(i).equals(key)) return valueList.get(i);
		}
		return null;
	}
	@Override
	public CircuitStateArray put(CircuitStateArray key, CircuitStateArray value) {
		keyList.add(key);
		valueList.add(value);
		return null;
	}
	@Override
	public CircuitStateArray remove(Object key) {
		valueList.remove(keyList.indexOf(key));
		keyList.remove(key);
		return null;
	}
	@Override
	public void putAll(Map<? extends CircuitStateArray, ? extends CircuitStateArray> m) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void clear() {
		keyList.clear();
		valueList.clear();
	}
	@Override
	public Set<CircuitStateArray> keySet() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Collection<CircuitStateArray> values() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Set<Entry<CircuitStateArray, CircuitStateArray>> entrySet() {
		// TODO Auto-generated method stub
		return null;
	}
}
