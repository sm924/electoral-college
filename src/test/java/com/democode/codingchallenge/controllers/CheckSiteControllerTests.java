package com.democode.codingchallenge.controllers;

import com.democode.codingchallenge.models.Site;
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
    private CheckSiteService    checkSiteService;

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
    void testForUnknownSite() {
        new Expectations() {
            {
                checkSiteService.getAllSites();
                result = getSites();
            }
        };
        Site testSite = checkSiteController.getInfo("notintable.com", "someUrl");
        Assert.assertNull(testSite.getMalicious());
        Assert.assertNull(testSite.getThreat_rating());
    }

    private List<Site> getSites() {
        List<Site> sites = new ArrayList<>();
        Site site1 = new Site();
        site1.setId(1L);
        site1.setDomain("bbc.co.uk");
        site1.setUrl("http://www.bbc.co.uk/sport/football");
        site1.setThreat_rating(1);
        site1.setMalicious(false);
        Site site2 = new Site();
        site2.setId(2L);
        site2.setDomain("malware4u.com");
        site2.setUrl("http://malware4u.com/dodgy/");
        site2.setThreat_rating(-1);
        site2.setMalicious(true);
        Site site3 = new Site();
        site3.setId(3L);
        site3.setDomain("verynaughty.com");
        site3.setUrl("http://verynaughty.com/its-a-wrongun");
        site3.setThreat_rating(-1);
        site3.setMalicious(true);
        sites.add(site1);
        sites.add(site2);
        sites.add(site3);
        return sites;
    }

}
