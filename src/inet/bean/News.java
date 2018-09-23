/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inet.bean;

import java.math.BigDecimal;
import java.sql.Timestamp;

import inet.util.DaiCaThang;
import inet.util.DateProc;
import inet.util.UTF8Tool;

/**
 *
 * @author Huyen
 */
public class News {

    private BigDecimal id;
    private String title;
    private byte[] imageTitle;
    private String description;
    private String content;
    private String source;
    private String tag;
    private Timestamp publishDate;
    private int viewCounter=0;
    public static int XOSO_CAT_ID=290;
    private int status;
    private String createdUser;
    private String imageUrl;
    private String province;
    
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

	public String getProvince() {
    		if(this.province == null) {
    			this.province = "";
    		}
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(String createdUser) {
        this.createdUser = createdUser;
    }

    public String getUrl() {
        String utf = "";
//        UTF8Tool.coDau2KoDau(title).replaceFirst("\\?", "").replaceAll("\"", "").replaceAll(" ", "-").replaceAll("'", "").replaceAll("//", "").toLowerCase();
//        utf = utf.replaceAll("â", "a");
//        utf = utf.replaceAll("ô", "o");
//        utf = utf.replaceAll("ố", "o");
//        utf = utf.replaceAll("ộ", "o");
//        utf = utf.replaceAll("ồ", "o");
//        utf = utf.replaceAll("á", "a");
//        utf = utf.replaceAll("à", "a");
//        utf = utf.replaceAll("ạ", "a");
//        utf = utf.replaceAll("ã", "a");
        
        utf = DaiCaThang.toUrlFriendly(title.toLowerCase());
        return utf;
    }
    public int getViewCounter() {
        return viewCounter;
    }

    public void setViewCounter(int viewCounter) {
        this.viewCounter = viewCounter;
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

    public byte[] getImageTitle() {
        return imageTitle;
    }

    public void setImageTitle(byte[] imageTitle) {
        this.imageTitle = imageTitle;
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

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Timestamp getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Timestamp publishDate) {
        this.publishDate = publishDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
    public String getURLTitle(){
        return UTF8Tool.coDau2KoDau(title).replaceFirst("\\?", "").replaceAll("\"", "").replaceAll(" ", "-").replaceAll("'", "").replaceAll("//", "").toLowerCase();
    }
    public String getDatePost(){
        String sDatePost=null;
      
       
        if(DateProc.Timestamp2DDMMYYYY(publishDate).equals(DateProc.Timestamp2DDMMYYYY(DateProc.createTimestamp()))){
            sDatePost="Hôm nay,"+DateProc.Timestamp2HHMM(publishDate);
        }else{
            sDatePost=DateProc.Timestamp2DDMMYYYY(publishDate)+","+DateProc.Timestamp2HHMM(publishDate);
        }
        return sDatePost;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    
}
