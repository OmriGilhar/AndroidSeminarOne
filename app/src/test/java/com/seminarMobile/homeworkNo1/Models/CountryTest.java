package com.seminarMobile.homeworkNo1.Models;

import org.junit.Assert;
import org.junit.Test;

public class CountryTest {
    private Country c = new Country();

    @Test
    public void getEnglishName() {
        this.c.setEnglishName("Israel");
        Assert.assertEquals("Israel", c.getEnglishName());
    }

    @Test
    public void getNativeName() {
        this.c.setNativeName("ישראל");
        Assert.assertEquals("ישראל", c.getNativeName());
    }
}