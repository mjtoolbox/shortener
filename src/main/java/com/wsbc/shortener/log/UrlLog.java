package com.wsbc.shortener.log;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wsbc.shortener.url.UrlShorten;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@Table(name = "urllog", schema = "public")
public class UrlLog implements Serializable {

    @Id
    @Column(name = "url_log_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long url_log_id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "url_id", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JsonIgnore
    private UrlShorten urlShorten;

    @Column(name = "url_id", insertable = false, updatable = false)
    private long urlId;

    @Column(name = "last_accessed")
    private Date last_accessed;

}
