package knowledgeTest.util.impl;

import knowledgeTest.util.CustomUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Class handel's custom utility methods
 */
@Component
public class CustomUtilImpl implements CustomUtil {

    protected static Logger logger = Logger.getLogger(CustomUtilImpl.class);

    /**
     * Get defined amount of random numbers from defined range
     *
     * @param amount   - defined amount of random numbers
     * @param maxRange - maximum range of numbers
     * @return ArrayList with amount of numbers
     */
    @Override
    public ArrayList<Integer> getRandomNumbers(Integer amount, Integer maxRange) {

        assert amount != null : "Amount not specified!";

        assert amount >= 1 : "Amount can't be less then 1!";

        assert maxRange != null : "MaxRange not specified!";

        assert maxRange >= 1 : "MaxRange can't be less then 1!";


        // define ArrayList to hold Integer objects
        ArrayList<Integer> arrayList = new ArrayList<>();

        for (int i = 0; i <= maxRange - 1; i++) {
            arrayList.add(i);
        }

        // shuffle list
        Collections.shuffle(arrayList);

        // adding defined amount of numbers to target list
        ArrayList<Integer> targetList = new ArrayList<>();
        for (int j = 0; j < amount; j++) {
            targetList.add(arrayList.get(j));
        }

        return targetList;
    }
}
