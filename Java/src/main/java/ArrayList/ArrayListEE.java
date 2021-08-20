package ArrayList;

import java.util.*;

public class ArrayListEE {
    public static void main(String[] args) {
        Integer[] sizeArr = new Integer[] { 1000, 5000, 100000, 1000000 };
        for (int i = 0; i < sizeArr.length; i++) {
            System.out.println(
                    timeResOutput(
                            fillArrayList(sizeArr[i])
                                 )
                              );
        }
    }

    // Output sum methods execution time result as string
    public static String timeResOutput(ArrayList<Integer> sizedArray) {
        return  "Sum method time: " + timeData(0, sizedArray)
                + 
                "; Modify array method time: " + timeData(1, sizedArray)
                + 
                "; Reverse array method time: " + timeData(2, sizedArray);
    }

    // Fill arrayList with random integers between 0 - 99;
    public static ArrayList<Integer> fillArrayList(int numberOfInts) {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        Random rand = new Random();
        for (int i = 0; i <= numberOfInts - 1; i++) {
            int randomInt = rand.nextInt(100);
            arrayList.add(randomInt);
        }
        return arrayList;
    }

    // Multiply all elements in array list by 2
    public static ArrayList<Integer> modifyArrayList(ArrayList<Integer> arrayList) {
        for (int elem : arrayList) {
            elem = elem * 2;
        }
        return arrayList;
    }

    public static ArrayList<Integer> reverseArrayList(ArrayList<Integer> arrayList) {
        Collections.reverse(arrayList);
        return arrayList;
    }

    // Iterative method to calculate sum of integers in array list
    public static int sumOfArrayList(ArrayList<Integer> arrayList) {
        int sum = 0;
        for (int i = 0; i < arrayList.size(); i++)
            sum = sum + arrayList.get(i);
        return sum;
    }

    // Measure execution time of methods
    public static ArrayList<Double> executionTime(ArrayList<Integer> filledArrayList) {
        ArrayList<Double> timeArr = new ArrayList<Double>();

        double startTime1 = System.nanoTime();
        sumOfArrayList(filledArrayList);
        double stopTime1 = System.nanoTime();

        double startTime2 = System.nanoTime();
        modifyArrayList(filledArrayList);
        double stopTime2 = System.nanoTime();

        double startTime3 = System.nanoTime();
        reverseArrayList(filledArrayList);
        double stopTime3 = System.nanoTime();

        // Reverse Array method
        double finalTimeReverse = (startTime3 - stopTime3) * (-1);
        // Modify Array method execution time
        double finalTimeModify = (startTime2 - stopTime2) * (-1);
        // Interative sum method execution time
        double finalTimeSum = (startTime1 - stopTime1) * (-1);
        // Add time data to arrayList timeArr:
        // [min execution time, max execution time, average execution time]
        timeArr.add(finalTimeSum);
        timeArr.add(finalTimeModify);
        timeArr.add(finalTimeReverse);
        return timeArr;
    }

    // TypeOfMethod: 0 - iterative
    public static ArrayList<Double> timeData(int typeOfMethod, ArrayList<Integer> sizedArray) {
        ArrayList<Double> arrayListTime = new ArrayList<Double>();
        ArrayList<Double> resultTime = new ArrayList<Double>();
        double sum = 0;
        // Get execution time of chosen method 100 times and add to the arrayListTime
        for (int i = 0; i <= 100; i++) {
            double executionTime = executionTime(sizedArray).get(typeOfMethod);
            arrayListTime.add(executionTime);
        }
        // Calculate sum of all calculated time
        for (int i = 0; i < arrayListTime.size(); i++) {
            sum += arrayListTime.get(i);
        }
        // Sort array with time to get the smallest and the largest sum methods
        // execution time
        Collections.sort(arrayListTime);
        // min execution time
        double min = arrayListTime.get(0);
        // max execution time
        double max = arrayListTime.get(arrayListTime.size() - 1);
        // avg - average execution time
        double avg = sum / arrayListTime.size();
        // Add time data to the resultTime
        resultTime.add(min);
        resultTime.add(max);
        resultTime.add(avg);
        return resultTime;
    }
}
