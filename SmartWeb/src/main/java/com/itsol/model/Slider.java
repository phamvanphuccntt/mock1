package com.itsol.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SLIDER")
public class Slider {
	@Id
	@Column(name = "SLIDER_ID")
	private int slideId;
	@Column(name = "IMAGE", nullable = false)
	private String image;
	@Column(name = "LINK", nullable = false)
	private String link;
	@Column(name = "RANK", nullable = false)
	private int rank;

	public int getSlideId() {
		return slideId;
	}

	public void setSlideId(int slideId) {
		this.slideId = slideId;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public Slider(int slideId, String image, String link, int rank) {
		super();
		this.slideId = slideId;
		this.image = image;
		this.link = link;
		this.rank = rank;
	}

	public Slider() {
		super();
	}

}
