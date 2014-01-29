package bitzguild.jcollection.transform;

import bitzguild.jcollection.Doubles;
import bitzguild.jcollection.mutable.DoublesArray;

public class DoublesToDoublesFunctionCache extends DoublesArray {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected Doubles           			_domain;
	protected DoublesToDoubleFunction		_function;
	protected int							_lookback;
	
	protected DoublesToDoublesFunctionCache() {
		// blocked
	}
	
	public DoublesToDoublesFunctionCache(DoublesToDoubleFunction function, Doubles domain, int lookback) {
		_lookback = Math.abs(lookback);
		_domain = domain;
		_function = function;
	}
	
	public int lookback() {	return _lookback; }
	public Doubles domain() { return _domain; }
	public DoublesToDoubleFunction function() { return _function; }
	
	/**
	 * 
	 * @param index int
	 */
	public Double get(int index) {
		if (this.size() < _domain.size()) {
			int domainSize = _domain.size();
			int ourSize = size();
			int icache = ourSize -1;
			int lookback = Math.min(_lookback, domainSize);
			int topend = Math.max(lookback, domainSize);
			Doubles range = this;
			
			while(++icache < lookback) {
//				System.out.println("icache = " + icache);
				this.add(_function.calculateFirst(_domain, range, icache, _lookback));
			}
			while(icache < topend) {
//				System.out.println("icache = " + icache);
				this.add(_function.calculateEntry(_domain, range, icache, _lookback));
				icache++;
			}
		}
		return super.get(index);
	}
}
