package faizan.java.basics.generics.wildcard;

public class WithWildcard<E> {
	private E data;
	private int id;
	public WithWildcard() {
		data=null;
		id=0;
	}
	public WithWildcard(int id) {
		this.id = id;
	}
	public WithWildcard(int id,E data) {
		this.data = data;
		this.id = id;
	}
	public E getData() {
		return data;
	}
	public void setData(E data) {
		this.data = data;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String toString() {
		return id+"."+data;
	}
	public boolean isGreaterThan(WithWildcard<?> other) { // other object can be of unknown type but should be object of WithWildcard
		if(this.toString().length() > other.toString().length())
			return true;
		else {
			return false;
		}
	}
}
