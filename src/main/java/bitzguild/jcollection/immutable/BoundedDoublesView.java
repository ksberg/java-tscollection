package bitzguild.jcollection.immutable;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import bitzguild.jcollection.Doubles;

/**
 * BoundedDoublesView fronts a Doubles implementation
 * and ensures get() will only access existing elements.
 * For index values above or below valid range, it will
 * return the nearest element (first or last).
 */
public class BoundedDoublesView implements Doubles {

    protected Doubles   _reference;

    protected BoundedDoublesView() {
    }

    public BoundedDoublesView(Doubles ref) {
    	_reference = ref;
    }

	@Override
	public int size() {
		return _reference.size(); 
	}

	@Override
	public boolean isEmpty() {
		return _reference.isEmpty();
	}

	@Override
	public boolean contains(Object o) {
		return _reference.contains(o);
	}

	@Override
	public Iterator<Double> iterator() {
		return _reference.iterator();
	}

	@Override
	public Object[] toArray() {
		return _reference.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return _reference.toArray(a);
	}

	@Override
	public boolean add(Double e) {
		return false;
	}

	@Override
	public boolean remove(Object o) {
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return _reference.containsAll(c);
	}

	@Override
	public boolean addAll(Collection<? extends Double> c) {
		return false;
	}

	@Override
	public boolean addAll(int index, Collection<? extends Double> c) {
		return false;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return false;
	}

	@Override
	public void clear() {
	}

	@Override
	public Double get(int index) {
		int idx = Math.max(0, Math.max(index, _reference.size()-1));
		return _reference.get(idx);
	}

	@Override
	public Double set(int index, Double element) {
		return element;
	}

	@Override
	public void add(int index, Double element) {
	}

	@Override
	public Double remove(int index) {
		return null;
	}

	@Override
	public int indexOf(Object o) {
		return _reference.indexOf(o);
	}

	@Override
	public int lastIndexOf(Object o) {
		return _reference.lastIndexOf(o);
	}

	@Override
	public ListIterator<Double> listIterator() {
		return _reference.listIterator();
	}

	@Override
	public ListIterator<Double> listIterator(int index) {
		return _reference.listIterator(index);
	}

	@Override
	public List<Double> subList(int fromIndex, int toIndex) {
		return _reference.subList(fromIndex, toIndex);
	}
}
