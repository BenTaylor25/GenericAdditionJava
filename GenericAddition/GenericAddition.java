package GenericAddition;

import java.util.ArrayList;
import java.util.Arrays;

import static GenericAddition.MyMaths.sum;

public class GenericAddition {
    public static void main(String[] args) {

        // Sum of ints.
        ArrayList<Integer> ints = new ArrayList<>(Arrays.asList(
            1, 2, 3, 4, 5
        ));

        double intSum = sum(ints);
        System.out.println(intSum);   // 15.0


        // Sum of doubles using the same method.
        ArrayList<Double> doubles = new ArrayList<>(Arrays.asList(
            1.5, 2.3, 4.2
        ));

        double doubleSum = sum(doubles);
        System.out.println(doubleSum);   // 8.0

    }
}
