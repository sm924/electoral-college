package com.democode.codingchallenge.controllers;

import com.democode.codingchallenge.models.Site;
import com.democode.codingchallenge.services.CheckSiteNoDbService;
import com.democode.codingchallenge.services.CheckSiteService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * CheckSiteController
 * Author: Simon Mason
 *
 * This class contains the endpoints to retrieve data pertaining to a particular domain or URL.
 *
 * The standard endpoint requires a database to be present and the request should be in the format:
 *  GET /urlinfo/1/{domain}/{url}
 *
 * If there is no database present, the following request can be sent in to retrieve stubbed data:
 *  GET /urlinfo/1/nodb/{domain}/{url}
 *
 *  Both requests return a response in the form of a Site record including id, domain, url, threat rating and malicious flag.
 *
 */

@RestController
@RequestMapping("/urlinfo/1")
public class CheckSiteController {

    private final CheckSiteService checkSiteService;
    private final CheckSiteNoDbService checkSiteNoDbService;

    @org.springframework.beans.factory.annotation.Autowired
    public CheckSiteController(CheckSiteService checkSiteService, CheckSiteNoDbService checkSiteNoDbService) {
        this.checkSiteService = checkSiteService;
        this.checkSiteNoDbService = checkSiteNoDbService;
    }

    @GetMapping
    @RequestMapping(value = "{domain}/{url}")
    public Site getInfo(@PathVariable String domain, @PathVariable String url) {

        // Retrieve data from database via service
        List<Site> allSites = checkSiteService.getAllSites();
        return findSiteInfo(domain, url, allSites);
    }

    @GetMapping
    @RequestMapping(value = "/nodb/{domain}/{url}")
    public Site getInfoNoDb(@PathVariable String domain, @PathVariable String url) {

        // Retrieve data from stubbed service
        List<Site> allSites = checkSiteNoDbService.getAllSites();
        return findSiteInfo(domain, url, allSites);
    }

    private Site findSiteInfo(String domain, String url, List<Site> allSites) {

        // Decode url string to cater for escaped characters
        String urlDecoded = URLDecoder.decode(url, StandardCharsets.UTF_8);

        // Function to create unknown/blank site details
        Supplier<Site> siteSupplier = () -> {
            Site s = new Site();
            s.setDomain(domain);
            s.setUrl(urlDecoded);
            return s;
        };

        Optional<Site> opt = Optional.empty();
        if (!allSites.isEmpty()) {
            // Look for a match to the complete URL
            opt = allSites.stream().filter(s->s.getUrl().equals(urlDecoded) || s.getDomain().equals(domain)).findAny();
        }

        // Use the site record if found. Otherwise return a blank record.
        return opt.orElseGet(siteSupplier);
    }

}
