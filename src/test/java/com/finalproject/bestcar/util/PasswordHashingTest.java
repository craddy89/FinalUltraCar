package com.finalproject.bestcar.util;

import org.junit.Assert;
import org.junit.Test;

public class PasswordHashingTest {

    @Test
    public void hashingTest(){
        Assert.assertEquals(PasswordHashing.hash("123"), "A665A45920422F9D417E4867EFDC4FB8A04A1F3FFF1FA07E998E86F7F7A27AE3");
    }

}
