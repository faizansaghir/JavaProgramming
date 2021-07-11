package faizan.java.basics.generics.wildcard;

public class WithoutWildcard<E> {
	private E data;
	private int id;
	public WithoutWildcard() {
		data=null;
		id=0;
	}
	public WithoutWildcard(int id) {
		this.id = id;
	}
	public WithoutWildcard(int id,E data) {
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
	public boolean isGreaterThan(WithoutWildcard<E> other) {
		if(this.toString().length() > other.toString().length())
			return true;
		else {
			return false;
		}
	}
}
