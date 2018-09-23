package inet.bean;

import java.util.Date;

public class MetaGuide {
	private int id;
	private String title;
	private String meta;
	private String guide;
	private String footer;
	private String page;
	private String keyword;
	private int position;
	private Date lastUpdated;
	
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		if(title == null) {
			title = "";
		}
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMeta() {
		if(meta == null) {
			meta = "";
		}
		return meta;
	}
	public void setMeta(String meta) {
		this.meta = meta;
	}
	public String getGuide() {
		if(guide == null) {
			guide = "";
		}
		return guide;
	}
	public void setGuide(String guide) {
		this.guide = guide;
	}
	public String getFooter() {
		if(footer == null) {
			footer = "";
		}
		return footer;
	}
	public void setFooter(String footer) {
		this.footer = footer;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	public Date getLastUpdated() {
		return lastUpdated;
	}
	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
	public MetaGuide(int id, String title, String meta, String guide, String footer, String page, int position,
			Date lastUpdated) {
		super();
		this.id = id;
		this.title = title;
		this.meta = meta;
		this.guide = guide;
		this.footer = footer;
		this.page = page;
		this.position = position;
		this.lastUpdated = lastUpdated;
	}
	public MetaGuide() {
		super();
	}
	
}
