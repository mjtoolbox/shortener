package com.wsbc.shortener.log;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

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

    @Column(name = "url_id")
    private long url_id;

    @Column(name = "last_accessed")
    private Date last_accessed;

}
