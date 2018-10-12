package edu.sdsu.cs.datastructures;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    private static final int DEFAULT_TEST_SIZE = 100000;

    IMap<String, Integer> sut;

    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    /**
     * Unit test notes from lecture
     */
    private void checkInitCorrectly(){
        assertTrue(sut.isEmpty());
        assertEquals(0,sut.size());

        for(String key: sut.keyset())
            fail(String.format("you lose, you get nothing, you stole {}",key));
        for (Integer value: sut.values())
            fail(value.toString());
    }

    /**
     * Unit test notes from lecture
     * @throws Exception
     */
    public void addItems_sizeCorrect()throws Exception{
        for(int i = 0; i < DEFAULT_TEST_SIZE; i++)
            sut.add(String.format("{:06}",i), i*DEFAULT_TEST_SIZE);

        assertEquals(DEFAULT_TEST_SIZE,sut.size());
    }

}
