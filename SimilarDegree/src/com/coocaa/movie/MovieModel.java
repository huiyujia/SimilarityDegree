package com.coocaa.movie;

import java.util.Date;

public class MovieModel {
    /**private int value=
    public void MovieModel(int a){
    }*/

    private Integer v_id;

    private String title;

    private String actor;

    private String director;

    private String anchorperson;

    private String area;

    private Integer classifyId;

    private String classifyName;

    private String publishCompany;

    private String productCompany;

    private String thumb;

    private String tv;

    private String scriptwriter;

    private String producer;

    private String year;

    private String releaseDate;

    private String classfication;

    private String score;

    private String focus;

    public void setExpired(Integer expired) {
        this.expired = expired;
    }

    public void setCategory_id(Integer category_id) {
        this.category_id = category_id;
    }

    private Integer expired;

    private String firstchars;

    private Integer totalSegment;

    private Byte isNew;

    private String videoFrom;

    private String thirdStr;

    private Integer sequence;

    private Integer temperature;

    private Date createdDate;

    private Date lastUpdateDate;

    private Integer category_id;

    private String category_name;

    private String publish_company;

    private String product_company;

    private String alias;

    private String release_date;

    private String praise;

    private String step;

    private String comment_count;

    private String browse_count;

    private String description;

    private int total_segment;

    private int is_new;

    private String video_from;

    private long foreign_id;

    private boolean is_pay;

    private Date created_date;

    private Date last_update_date;


    public int getIs_new() {
        return is_new;
    }

    public void setIs_new(int is_new) {
        this.is_new = is_new;
    }

    public String getVideo_from() {
        return video_from;
    }

    public void setVideo_from(String video_from) {
        this.video_from = video_from;
    }

    public long getForeign_id() {
        return foreign_id;
    }

    public void setForeign_id(long foreign_id) {
        this.foreign_id = foreign_id;
    }

    public boolean isIs_pay() {
        return is_pay;
    }

    public void setIs_pay(boolean is_pay) {
        this.is_pay = is_pay;
    }

    public Date getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }

    public Date getLast_update_date() {
        return last_update_date;
    }

    public void setLast_update_date(Date last_update_date) {
        this.last_update_date = last_update_date;
    }

    public int getTotal_segment() {
        return total_segment;
    }

    public void setTotal_segment(int total_segment) {
        this.total_segment = total_segment;
    }

    public Integer getExpired() {

        return expired;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getBrowse_count() {
        return browse_count;
    }

    public void setBrowse_count(String browse_count) {
        this.browse_count = browse_count;
    }

    public String getComment_count() {
        return comment_count;
    }

    public void setComment_count(String comment_count) {
        this.comment_count = comment_count;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }

    public String getPraise() {
        return praise;
    }

    public void setPraise(String praise) {
        this.praise = praise;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }


    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getProduct_company() {
        return product_company;
    }

    public void setProduct_company(String product_company) {
        this.product_company = product_company;
    }

    public Integer getV_id() {
        return v_id;
    }

    public void setV_id(Integer v_id) {
        this.v_id = v_id;
    }

    public String getPublish_company() {

        return publish_company;
    }

    public void setPublish_company(String publish_company) {
        this.publish_company = publish_company;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public Integer getvId() {
        return v_id;
    }


    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public void setvId(Integer vId) {
        this.v_id = vId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor == null ? null : actor.trim();
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director == null ? null : director.trim();
    }

    public String getAnchorperson() {
        return anchorperson;
    }

    public void setAnchorperson(String anchorperson) {
        this.anchorperson = anchorperson == null ? null : anchorperson.trim();
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

    public Integer getClassifyId() {
        return classifyId;
    }

    public void setClassifyId(Integer classifyId) {
        this.classifyId = classifyId;
    }

    public String getClassifyName() {
        return classifyName;
    }

    public void setClassifyName(String classifyName) {
        this.classifyName = classifyName == null ? null : classifyName.trim();
    }

    public String getPublishCompany() {
        return publishCompany;
    }

    public void setPublishCompany(String publishCompany) {
        this.publishCompany = publishCompany == null ? null : publishCompany.trim();
    }

    public String getProductCompany() {
        return productCompany;
    }

    public void setProductCompany(String productCompany) {
        this.productCompany = productCompany == null ? null : productCompany.trim();
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb == null ? null : thumb.trim();
    }

    public String getTv() {
        return tv;
    }

    public void setTv(String tv) {
        this.tv = tv == null ? null : tv.trim();
    }

    public String getScriptwriter() {
        return scriptwriter;
    }

    public void setScriptwriter(String scriptwriter) {
        this.scriptwriter = scriptwriter == null ? null : scriptwriter.trim();
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer == null ? null : producer.trim();
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year == null ? null : year.trim();
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate == null ? null : releaseDate.trim();
    }

    public String getClassfication() {
        return classfication;
    }

    public void setClassfication(String classfication) {
        this.classfication = classfication == null ? null : classfication.trim();
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score == null ? null : score.trim();
    }

    public String getFocus() {
        return focus;
    }

    public void setFocus(String focus) {
        this.focus = focus == null ? null : focus.trim();
    }


    public String getFirstchars() {
        return firstchars;
    }

    public void setFirstchars(String firstchars) {
        this.firstchars = firstchars == null ? null : firstchars.trim();
    }

    public Integer getTotalSegment() {
        return totalSegment;
    }

    public void setTotalSegment(Integer totalSegment) {
        this.totalSegment = totalSegment;
    }

    public Byte getIsNew() {
        return isNew;
    }

    public void setIsNew(Byte isNew) {
        this.isNew = isNew;
    }

    public String getVideoFrom() {
        return videoFrom;
    }

    public void setVideoFrom(String videoFrom) {
        this.videoFrom = videoFrom == null ? null : videoFrom.trim();
    }

    public String getThirdStr() {
        return thirdStr;
    }

    public void setThirdStr(String thirdStr) {
        this.thirdStr = thirdStr == null ? null : thirdStr.trim();
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public Integer getTemperature() {
        return temperature;
    }

    public void setTemperature(Integer temperature) {
        this.temperature = temperature;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }
}