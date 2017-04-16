package com.runningsnail.androiddemo.gson;

import java.util.List;

import com.android.volley.Response.Listener;

public class Book {
	private String publisher;
	private String title;
	private String summary;
	private String price;
	private Rating rating;
	private List<Tag> tags;
	private Image images;

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public Rating getRating() {
		return rating;
	}

	public void setRating(Rating rating) {
		this.rating = rating;
	}

	

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	public Image getImages() {
		return images;
	}

	public void setImages(Image images) {
		this.images = images;
	}

}
