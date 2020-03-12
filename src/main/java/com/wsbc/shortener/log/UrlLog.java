package com.wsbc.shortener.log;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "urllog", schema = "public")
public class UrlLog implements Serializable {

    @Id
    @Column(name = "url_log_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long url_log_id;

    @Column(name = "url_id")
    private long urlId;

    @Column(name = "shorturl")
    private String shortUrl;

    @Column(name = "last_accessed")
    private Date lastAccessed;

    public UrlLog() {
    }

    public UrlLog(long urlId, String shortUrl, Date lastAccessed){
        this.urlId = urlId;
        this.shortUrl = shortUrl;
        this.lastAccessed = lastAccessed;
    }

    public long getUrl_log_id() {
        return url_log_id;
    }

    public void setUrl_log_id(long url_log_id) {
        this.url_log_id = url_log_id;
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

    public Date getLastAccessed() {
        return lastAccessed;
    }

    public void setLastAccessed(Date lastAccessed) {
        this.lastAccessed = lastAccessed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UrlLog urlLog = (UrlLog) o;
        return url_log_id == urlLog.url_log_id &&
                urlId == urlLog.urlId &&
                Objects.equals(shortUrl, urlLog.shortUrl) &&
                Objects.equals(lastAccessed, urlLog.lastAccessed);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url_log_id, urlId, shortUrl, lastAccessed);
    }

    @Override
    public String toString() {
        return "UrlLog{" +
                "url_log_id=" + url_log_id +
                ", urlId=" + urlId +
                ", shortUrl='" + shortUrl + '\'' +
                ", lastAccessed=" + lastAccessed +
                '}';
    }
}
