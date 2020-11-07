package com.democode.codingchallenge.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "sites")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Site {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String domain;
    private String url;
    private Integer threat_rating;
    private Boolean malicious;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getThreat_rating() {
        return threat_rating;
    }

    public void setThreat_rating(Integer threat_rating) {
        this.threat_rating = threat_rating;
    }

    public Boolean getMalicious() {
        return malicious;
    }

    public void setMalicious(Boolean malicious) {
        this.malicious = malicious;
    }

    @Override
    public String toString() {
        return "Domain: "+this.getDomain()+" URL: "+this.getUrl()+" Threat rating: "+this.getThreat_rating()+" Malicicous: "+this.getMalicious();
    }
}
