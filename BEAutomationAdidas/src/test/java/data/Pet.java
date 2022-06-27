package data;

import java.util.ArrayList;

public class Pet {

	 Integer id;
	 Category category;
	 String name;
	 ArrayList<String> photoUrls;
	 ArrayList<Tag> tags;
	 String status;

	public Pet(Integer id, Category category, String name, ArrayList<String> photoUrls, ArrayList<Tag> tags,
			String status) {
		this.id = id;
		this.category = category;
		this.name = name;
		this.photoUrls = photoUrls;
		this.tags = tags;
		this.status = status;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<String> getPhotoUrls() {
		return this.photoUrls;
	}

	public void setPhotoUrls(ArrayList<String> photoUrls) {
		this.photoUrls = photoUrls;
	}

	public ArrayList<Tag> getTags() {
		return this.tags;
	}

	public void setTags(ArrayList<Tag> tags) {
		this.tags = tags;
	}

	public void addTag(Tag tag) {
		this.tags.add(tag);
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
