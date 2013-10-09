package com.design.patterns.FlyWeight;

/**
 *  A Flyweight Factory
 */
import java.util.*;

public class FontFactory  {
    @SuppressWarnings("rawtypes")
	private Hashtable charHashTable = new Hashtable();
    
    public FontFactory() {
    }

    @SuppressWarnings("unchecked")
	public Font GetFlyWeight(String s) {
        if(charHashTable.get(s) != null) {
            return (Font)charHashTable.get(s);
        } else {
            Font tmp = new ConcreteFont(s);
            charHashTable.put(s, tmp);
            return tmp;
        }
    }
    @SuppressWarnings("rawtypes")
	public Hashtable GetFactory() {
        return charHashTable;
    }
}