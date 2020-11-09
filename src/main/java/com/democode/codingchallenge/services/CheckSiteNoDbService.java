package com.democode.codingchallenge.services;

import com.democode.codingchallenge.models.Site;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("checkSiteNoDbService")
public class CheckSiteNoDbService {

    public List<Site> getAllSites() {
        return getSites();
    }

    private List<Site> getSites() {
        List<Site> sites = new ArrayList<>();
        sites.add(setSite(1,"bbc.co.uk", "www.bbc.co.uk", 1, false));
        sites.add(setSite(2,"malware4u.com", "www.malware4u.com?verydodgy=true&containsnasties=true", 95, true));
        sites.add(setSite(3,"verynaughty.com", "www.verynaughty.com", 80, true));
        return sites;
    }

    private Site setSite(int id, String domain, String url, Integer rating, Boolean malicious) {
        Site site = new Site();
        site.setId(id);
        site.setDomain(domain);
        site.setUrl(url);
        site.setRating(rating);
        site.setMalicious(malicious);
        return site;
    }

}
