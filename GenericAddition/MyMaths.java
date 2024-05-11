package GenericAddition;

import java.util.ArrayList;

public class MyMaths {

    public static double sum(ArrayList<? extends Number> numbers) {
        double sum = 0;

        for (Number number : numbers) {
            sum += number.doubleValue();
        }

        return sum;
    }

}
