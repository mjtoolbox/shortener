package com.wsbc.shortener.url;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "urlshorten", schema = "public")
public class UrlShorten implements Serializable {

    public UrlShorten() {
    }

    public UrlShorten(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    @Id
    @Column(name = "url_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long urlId;

    @Column(name = "shortUrl")
    private String shortUrl;

    @Column(name = "originalUrl")
    private String originalUrl;

    // Remove this property as it can be driven from UrlLog
    @Column(name = "click")
    private int click;

    @Column(name = "createdby")
    private String createdBy;

    @CreationTimestamp
    @Column(name = "last_update")
    private Date last_updated;


    public void increaseClick(){
        this.click ++;
    }

    public long getUrlId() {
        return urlId;
    }

    public void setUrlId(long urlId) {
        this.urlId = urlId;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public int getClick() {
        return click;
    }

    public void setClick(int click) {
        this.click = click;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getLast_updated() {
        return last_updated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UrlShorten that = (UrlShorten) o;
        return urlId == that.urlId &&
                click == that.click &&
                Objects.equals(shortUrl, that.shortUrl) &&
                Objects.equals(originalUrl, that.originalUrl) &&
                Objects.equals(createdBy, that.createdBy) &&
                Objects.equals(last_updated, that.last_updated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(urlId, shortUrl, originalUrl, click, createdBy, last_updated);
    }

    @Override
    public String toString() {
        return "UrlShorten{" +
                "urlId=" + urlId +
                ", shortUrl='" + shortUrl + '\'' +
                ", originalUrl='" + originalUrl + '\'' +
                ", click=" + click +
                ", createdBy='" + createdBy + '\'' +
                ", last_updated=" + last_updated +
                '}';
    }
}
