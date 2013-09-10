package com.algorithm.huffman;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class MinHeap<T> {
	private List<Comparable<T>> container;

	public MinHeap() {
		container = new LinkedList<Comparable<T>>();
	}

	@SuppressWarnings("unchecked")
	private int getTargetIndex(Comparable<T> e) {
		int index1 = 0;
		int index2 = container.size() - 1;
		int mid = (index1 + index2) / 2;

		while (index1 <= index2) {
			mid = (index1 + index2) / 2;
			Comparable<T> midt = container.get(mid);
			int cv = e.compareTo((T) midt);
			if (cv > 0) {
				index1 = mid + 1;
			} else if (cv < 0) {
				index2 = mid - 1;
			} else {
				break;
			}
		}

		return index1;

	}

	public void add(Comparable<T> e) {
		int index = getTargetIndex(e);
		container.add(index, e);
	}

	@SuppressWarnings("unchecked")
	public T pop() {
		if (container.isEmpty()) {
			return null;
		} else {
			return (T) container.remove(0);
		}
	}

	public int size() {
		return container.size();
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		for (Iterator<Comparable<T>> iterator = container.iterator(); iterator
				.hasNext();) {
			Comparable<T> item = iterator.next();
			sb.append(item.toString() + ",");
		}
		int len = sb.length();
		if (sb.charAt(len - 1) == ',') {
			sb.deleteCharAt(len - 1);
			sb.append(']');
		}
		return sb.toString();

	}

}
