package knowledgeTest.customUtil;

import knowledgeTest.components.util.CustomUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * JUnit test for the {@link knowledgeTest.components.util.utilImpl.CustomUtilImpl} class.
 * Testing custom util methods.
 * ApplicationContext will be loaded from "classpath:/application-context.xml"
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-context.xml"})
public class CustomUtilTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    private CustomUtil customUtil;

    /**
     * Testing getRandomNumbers() method
     */
    @Test
    public void test_getRandomNumbers() {

        Integer amount = 10;
        Integer maxRange = 40;

        // creating list with specified amount of random numbers
        ArrayList<Integer> list = customUtil.getRandomNumbers(amount, maxRange);

        assertEquals("FAIL - list size must be same,", (Object) amount, list.size());
    }

    /**
     * Testing getRandomNumbers() method
     */
    @Test
    public void test_getPatternMatch() {

        final String RU_ENG_PATTERN = "([a-zA-Zа-яА-Я\\s.-]+)*";

        String matchingString = "New Userфывааа";
        String notMatchingString = "123 #$%^&";

        assertTrue("Fail - string have to match,",
                customUtil.getPatternMatch(matchingString, RU_ENG_PATTERN));

        assertFalse("Fail - string don't have to match,",
                customUtil.getPatternMatch(notMatchingString, RU_ENG_PATTERN));
    }
}
