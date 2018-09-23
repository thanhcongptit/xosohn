package inet.bean;

import java.math.BigDecimal;
import java.sql.Timestamp;

import inet.util.DaiCaThang;

public class Dream {
	private BigDecimal id;
	private String title;
	private String description;
	private String content;
	private Timestamp publishDate;
	private String url;
	private String datePost;
	private int status;
	private String imageUrl;
	private String titleSeo;
	private String metaSeo;
	private String keyword;

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getTitleSeo() {
		return titleSeo;
	}

	public void setTitleSeo(String titleSeo) {
		this.titleSeo = titleSeo;
	}

	public String getMetaSeo() {
		return metaSeo;
	}

	public void setMetaSeo(String metaSeo) {
		this.metaSeo = metaSeo;
	}

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Timestamp publishDate) {
		this.publishDate = publishDate;
	}

	public String getUrl() {
		String utf = "";
		// UTF8Tool.coDau2KoDau(title).replaceFirst("\\?", "").replaceAll("\"",
		// "").replaceAll(" ", "-").replaceAll("'", "").replaceAll("//",
		// "").toLowerCase();
		// utf = utf.replaceAll("â", "a");
		// utf = utf.replaceAll("ô", "o");
		// utf = utf.replaceAll("ố", "o");
		// utf = utf.replaceAll("ộ", "o");
		// utf = utf.replaceAll("ồ", "o");
		// utf = utf.replaceAll("á", "a");
		// utf = utf.replaceAll("à", "a");
		// utf = utf.replaceAll("ạ", "a");
		// utf = utf.replaceAll("ã", "a");

		utf = DaiCaThang.toUrlFriendly(title.toLowerCase());
		return utf;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDatePost() {
		return datePost;
	}

	public void setDatePost(String datePost) {
		this.datePost = datePost;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
}
