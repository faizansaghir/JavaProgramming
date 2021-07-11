package faizan.java.basics.generics.general;

public class General<E> {
	private E data;
	private int id;
	public General() {
		data=null;
		id=0;
	}
	public General(int id) {
		this.id = id;
	}
	public General(int id,E data) {
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
		return id+". "+data;
	}
}
