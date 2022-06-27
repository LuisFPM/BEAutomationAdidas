package data;

public class Category {
	 Integer id;
	 String name;
	
	public Category(Integer id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
