package com.wsbc.shortener.log;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@Table(name = "urllog", schema = "public")
public class UrlLog implements Serializable {

    @Id
    @Column(name = "url_log_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long url_log_id;

//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "url_id", nullable = false)
//    @OnDelete(action = OnDeleteAction.NO_ACTION)
//    @JsonIgnore
//    private UrlShorten urlShorten;

    @Column(name = "url_id")
    private long urlId;

    @Column(name = "shorturl")
    private String shortUrl;

    @Column(name = "last_accessed")
    private Date lastAccessed;


    public UrlLog(long urlId, String shortUrl, Date lastAccessed){
        this.urlId = urlId;
        this.shortUrl = shortUrl;
        this.lastAccessed = lastAccessed;
    }
}
