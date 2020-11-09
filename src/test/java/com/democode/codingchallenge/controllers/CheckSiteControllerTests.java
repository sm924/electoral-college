package com.democode.codingchallenge.controllers;

import com.democode.codingchallenge.models.Site;
import com.democode.codingchallenge.services.CheckSiteNoDbService;
import com.democode.codingchallenge.services.CheckSiteService;
import mockit.Expectations;
import mockit.Injectable;
import mockit.Tested;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


class CheckSiteControllerTests {

    @Tested
    private CheckSiteController checkSiteController;

    @Injectable
    private CheckSiteService checkSiteService;
    @Injectable
    private CheckSiteNoDbService checkSiteNoDbService;

    @Test
    void testForNonMalicious() {
        new Expectations() {
            {
                checkSiteService.getAllSites();
                result = getSites();
            }
        };
        Site testSite = checkSiteController.getInfo("bbc.co.uk", "someUrl");
        Assert.assertNotNull(testSite.getMalicious());
        Assert.assertFalse(testSite.getMalicious());
    }

    @Test
    void testForMaliciousSite() {
        new Expectations() {
            {
                checkSiteService.getAllSites();
                result = getSites();
            }
        };
        Site testSite = checkSiteController.getInfo("malware4u.com", "someUrl");
        Assert.assertNotNull(testSite.getMalicious());
        Assert.assertTrue(testSite.getMalicious());
    }

    @Test
    void testFindByUrl() {
        new Expectations() {
            {
                checkSiteService.getAllSites();
                result = getSites();
            }
        };
        Site testSite = checkSiteController.getInfo("somedomain", "www.bbc.co.uk");
        Assert.assertNotNull(testSite.getMalicious());
        Assert.assertFalse(testSite.getMalicious());
    }

    @Test
    void testFindByDomain() {
        new Expectations() {
            {
                checkSiteService.getAllSites();
                result = getSites();
            }
        };
        Site testSite = checkSiteController.getInfo("malware4u.com", "someUrl");
        Assert.assertNotNull(testSite.getMalicious());
        Assert.assertTrue(testSite.getMalicious());
    }

    @Test
    void testForUnknownSite() {
        new Expectations() {
            {
                checkSiteService.getAllSites();
                result = getSites();
            }
        };
        Site testSite = checkSiteController.getInfo("notintable.com", "someUrl");
        Assert.assertNull(testSite.getMalicious());
        Assert.assertNull(testSite.getRating());
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
