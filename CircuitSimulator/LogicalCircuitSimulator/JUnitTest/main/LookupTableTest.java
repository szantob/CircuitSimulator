package main;

import org.junit.Assert;
import org.junit.Test;


import lookupTable.CircuitObjectStateLookupTable;
import lookupTable.CircuitStateArray;


public class LookupTableTest {
	CircuitObjectStateLookupTable lookupTable;
	
	@Test
	public void SearchTest() {
		lookupTable = new CircuitObjectStateLookupTable(2,1);
		CircuitStateArray value;
		CircuitStateArray expected;
		lookupTable.put(new CircuitStateArray("UU"), new CircuitStateArray("U"));
		lookupTable.put(new CircuitStateArray("LL"), new CircuitStateArray("L"));
		lookupTable.put(new CircuitStateArray("LH"), new CircuitStateArray("L"));
		lookupTable.put(new CircuitStateArray("HL"), new CircuitStateArray("L"));
		lookupTable.put(new CircuitStateArray("HH"), new CircuitStateArray("H"));
		
		value = new CircuitStateArray("H");
		//Assert.assertEquals(new CircuitStateArray("H"), lookupTable.get(new CircuitStateArray("HH")));
		
		expected = new CircuitStateArray("L");
		CircuitStateArray tmp = new CircuitStateArray("LL");
		value = lookupTable.get(tmp);
		boolean eq = expected.equals(value);
		Assert.assertEquals(true, eq);
		Assert.assertEquals(true, lookupTable.get(new CircuitStateArray("LH")).equals((CircuitStateArray)value));
		Assert.assertEquals(true, lookupTable.get(new CircuitStateArray("HL")).equals((CircuitStateArray)value));

		value = new CircuitStateArray("U");
		Assert.assertEquals(new CircuitStateArray("U"), lookupTable.get(new CircuitStateArray("UL")));
		Assert.assertEquals(new CircuitStateArray("U"), lookupTable.get(new CircuitStateArray("UH")));
		Assert.assertEquals(new CircuitStateArray("U"), lookupTable.get(new CircuitStateArray("UU")));
		Assert.assertEquals(new CircuitStateArray("U"), lookupTable.get(new CircuitStateArray("UL")));
		Assert.assertEquals(new CircuitStateArray("U"), lookupTable.get(new CircuitStateArray("UH")));
	}
}
