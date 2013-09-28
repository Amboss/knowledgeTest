package knowledgeTest.util;

import java.util.ArrayList;

/**
 * Interface specify custom utility methods
 */
public interface CustomUtil {

    /**
     *  get defined amount of random numbers from defined range
     *
     * @param amount - defined amount of random numbers
     * @param maxRange - maximum range of numbers
     * @return ArrayList with amount od numbers
     */
    public ArrayList<Integer> getRandomNumbers(Integer amount, Integer maxRange);
}
