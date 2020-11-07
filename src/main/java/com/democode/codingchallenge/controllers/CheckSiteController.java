package com.democode.codingchallenge.controllers;

import com.democode.codingchallenge.models.Site;
import com.democode.codingchallenge.services.CheckSiteService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

@RestController
@RequestMapping("/urlinfo/1")
public class CheckSiteController {

    private final CheckSiteService checkSiteService;

    @org.springframework.beans.factory.annotation.Autowired
    public CheckSiteController(CheckSiteService checkSiteService) {
        this.checkSiteService = checkSiteService;
    }

    @GetMapping
    @RequestMapping(value = "{domain}/{url}")
    public Site getInfo(@PathVariable String domain, @PathVariable String url) {

        // Function to create unknown/blank site details
        Supplier<Site> siteSupplier = () -> {
            Site s = new Site();
            s.setDomain(domain);
            s.setUrl(url);
            return s;
        };

        Optional<Site> opt = Optional.empty();
        // Retrieve the list of sites
        List<Site> allSites = checkSiteService.getAllSites();
        if (allSites.size()>0) {
            // Look for a match to the complete URL
            opt = allSites.stream().filter(s->s.getUrl().equals(url)).findAny();
            if (opt.isEmpty()){
                // If not found, try to match on domain
                opt = allSites.stream().filter(s->s.getDomain().equals(domain)).findAny();
            }
        }

        // Use the site record if found. Otherwise return a blank record.
        Site site = (Site) opt.orElseGet(siteSupplier);
        return site;
    }

}
