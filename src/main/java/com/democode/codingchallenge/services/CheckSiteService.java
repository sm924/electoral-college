package com.democode.codingchallenge.services;

import com.democode.codingchallenge.models.Site;
import com.democode.codingchallenge.repositories.SiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("checkSiteService")
public class CheckSiteService {

    @Autowired
    private SiteRepository siteRepository;

    public List<Site> getAllSites() {
        return siteRepository.findAll();
    }

}
