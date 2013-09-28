package knowledgeTest.customUtil;

import knowledgeTest.util.CustomUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * JUnit test for the {@link knowledgeTest.util.impl.CustomUtilImpl} class.
 * Testing custom util methods.
 * ApplicationContext will be loaded from "classpath:/application-context.xml"
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-context.xml"})
public class CustomUtilTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    private CustomUtil customUtil;

    @Test
    public void test_getRandomNumbers() {

        Integer amount = 10;
        Integer maxRange = 40;

        // creating list with specified amount of random numbers
        ArrayList<Integer> list = customUtil.getRandomNumbers(amount, maxRange);

        assertEquals("FAIL - list size must be same,", (Object) amount, list.size());
    }
}
