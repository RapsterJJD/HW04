import org.junit.Test;
import org.junit.After;
import java.lang.reflect.Field;
import org.junit.Assert;
import org.junit.Before;
import org.junit.rules.Timeout;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import javax.swing.*;
import java.io.*;
import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.lang.reflect.InvocationTargetException;
import java.util.UUID;

import static org.junit.Assert.*;

/**
 * A framework to run public test cases.
 *
 * <p>Purdue University -- CS18000 -- Summer 2022</p>
 *
 * @author Purdue CS
 * @version June 13, 2022
 */
public class RunLocalTest {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(TestCase.class);
        System.out.printf("Test Count: %d.\n", result.getRunCount());
        if (result.wasSuccessful()) {
            System.out.printf("Excellent - all local tests ran successfully.\n");
        } else {
            System.out.printf("Tests failed: %d.\n", result.getFailureCount());
            for (Failure failure : result.getFailures()) {
                System.out.println(failure.toString());
            }
        }
    }

    /**
     * A set of public test cases.
     *
     * <p>Purdue University -- CS18000 -- Summer 2022</p>
     *
     * @author Purdue CS
     * @version June 13, 2022
     */
    public static class TestCase {
        private final PrintStream originalOutput = System.out;
        private final InputStream originalSysin = System.in;

        @SuppressWarnings("FieldCanBeLocal")
        private ByteArrayInputStream testIn;

        @SuppressWarnings("FieldCanBeLocal")
        private ByteArrayOutputStream testOut;

        @Before
        public void outputStart() {
            testOut = new ByteArrayOutputStream();
            System.setOut(new PrintStream(testOut));
        }

        @After
        public void restoreInputAndOutput() {
            System.setIn(originalSysin);
            System.setOut(originalOutput);
        }

        private String getOutput() {
            return testOut.toString();
        }

        @SuppressWarnings("SameParameterValue")
        private void receiveInput(String str) {
            testIn = new ByteArrayInputStream(str.getBytes());
            System.setIn(testIn);
        }

        // Each of the correct outputs
        public static final String WELCOME_MESSAGE = "Welcome to the Food Recommender!";
        public static final String INITIAL_FOOD = "Do you want to have good food?";
        public static final String SPICY = "Do you like spicy food?";
        public static final String CHINESE = "Do you want to try spicy Chinese Food?";
        public static final String INDIAN = "Do you want to try spicy Indian Food?";
        public static final String ETHIOPIAN = "Do you want to try spicy Ethiopian food?";
        public static final String SEA_FOOD = "Do you like sea food?";
        public static final String SUSHI = "Do you like traditional sushi?";
        public static final String BEEF = "Do you like beef?";
        public static final String PORK = "Do you like pork?";
        public static final String LAMB = "Do you like lamb?";
        public static final String SOUP = "Do you want soup with beef?";
        public static final String GOODBYE_MESSAGE = "Thank you for using" +
                " the Food Recommender!";


        public static final String HOT_POT = "Sichuan hot pot is a great choice!";
        public static final String VINDALOO = "Vindaloo is a great choice!";
        public static final String DORO_WAT = "Doro wat is very good!";
        public static final String PENNE = "Penne all’arrabbiata is delicious!";
        public static final String SALMON_SUSHI = "Salmon sushi will fulfill you!";
        public static final String BBQ_SUSHI = "BBQ bacon sushi is a great combination of sushi and meat!";
        public static final String FRUIT_SALAD = "Sweet fruit salad is a great choice!";
        public static final String PORK_QUESADILLAS = "Pork quesadillas are very tasty!";
        public static final String LAMB_CHOPS = "Rosemary roast lamb chops are a great choice!";
        public static final String BIBIMBAP = "Beef and veggie bibimbap will fulfill you!";
        public static final String PHO_SOUP = "Vietnamese beef and noodle pho soup is very good!";


        @Test(timeout = 1000)
        public void testExpectedOne() {

            // Set the input
            String input = "No\n";

            // Pair the input with the expected result
            String expected = WELCOME_MESSAGE + System.lineSeparator() +
                    INITIAL_FOOD + System.lineSeparator() +
                    GOODBYE_MESSAGE + System.lineSeparator();

            // Runs the program with the input values
            receiveInput(input);
            FoodRecommender.main(new String[0]);

            // Retrieves the output from the program
            String output = getOutput();

            // Trims the output and verifies it is correct.
            expected = expected.replaceAll("\r\n", "\n");
            output = output.replaceAll("\r\n", "\n");
            assertEquals("Make sure you follow the flowchart and use the given strings for the result!",
                    expected.trim(), output.trim());
        }

        @Test(timeout = 1000)
        public void testExpectedTwo() {

            // Set the input
            String input = "Yes\nYes\nYes\n";

            // Pair the input with the expected result
            String expected = WELCOME_MESSAGE + System.lineSeparator() +
                    INITIAL_FOOD + System.lineSeparator() +
                    SPICY + System.lineSeparator() +
                    CHINESE + System.lineSeparator() +
                    HOT_POT + System.lineSeparator() +
                    GOODBYE_MESSAGE + System.lineSeparator();

            // Runs the program with the input values
            receiveInput(input);
            FoodRecommender.main(new String[0]);

            // Retrieves the output from the program
            String output = getOutput();

            // Trims the output and verifies it is correct.
            expected = expected.replaceAll("\r\n", "\n");
            output = output.replaceAll("\r\n", "\n");
            assertEquals("Make sure you follow the flowchart and use the given strings for the result!",
                    expected.trim(), output.trim());
        }


    }
}
