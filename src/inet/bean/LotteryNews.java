/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package inet.bean;

/**
 *
 * @author iNET
 */
public class LotteryNews {
    private String thumbnail;
    private String linkseo;

    public String getLinkseo() {
        return linkseo;
    }

    public void setLinkseo(String linkseo) {
        this.linkseo = linkseo;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSiteId() {
        return siteId;
    }

    public void setSiteId(int siteId) {
        this.siteId = siteId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public String getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(String approvalDate) {
        this.approvalDate = approvalDate;
    }
    private int siteId;
    private String title;
    private String shortDesc;
    private int status;
    private int views;
    private String approvalDate;
    private String urlFriendly;
    private String content;

    public String getUrlFriendly() {
        return urlFriendly;
    }

    public void setUrlFriendly(String urlFriendly) {
        this.urlFriendly = urlFriendly;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getTitleSeo() {
        return titleSeo;
    }

    public void setTitleSeo(String titleSeo) {
        this.titleSeo = titleSeo;
    }

    public String getDescSeo() {
        return descSeo;
    }

    public void setDescSeo(String descSeo) {
        this.descSeo = descSeo;
    }

    public String getKeywordSeo() {
        return keywordSeo;
    }

    public void setKeywordSeo(String keywordSeo) {
        this.keywordSeo = keywordSeo;
    }

    public String getGenDate() {
        return genDate;
    }

    public void setGenDate(String genDate) {
        this.genDate = genDate;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
    private String tags;
    private String titleSeo;
    private String descSeo;
    private String keywordSeo;
    private String genDate;
    private String lastUpdate;
}
