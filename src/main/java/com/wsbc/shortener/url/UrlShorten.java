package com.wsbc.shortener.url;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@Table(name = "urlshorten", schema = "public")
public class UrlShorten implements Serializable {

    @Id
    @Column(name = "url_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long url_id;

    @Column(name = "shortUrl")
    private String shortUrl;

    @Column(name = "originalUrl")
    private String originalUrl;

    @Column(name = "click")
    private int click;

    @Column(name = "createdby")
    private String createdBy;

    @CreationTimestamp
    @Column(name = "last_update")
    @Setter(AccessLevel.NONE)
    private Date last_updated;

    public UrlShorten(){
    }
}
