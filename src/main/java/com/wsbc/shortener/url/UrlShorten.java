package com.wsbc.shortener.url;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wsbc.shortener.log.UrlLog;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Table(name = "urlshorten", schema = "public")
public class UrlShorten implements Serializable {


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
    @Setter(AccessLevel.NONE)
    private Date last_updated;

//    @OneToMany(mappedBy = "urlShorten", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JsonIgnore
//    private Set<UrlLog> urlLogs = new HashSet<UrlLog>();

    public void increaseClick(){
        this.click ++;
    }

}
