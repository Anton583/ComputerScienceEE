package ArrayList;

import java.util.*;

public class ArrayListEE {
    public static void main(String[] args) {
        Integer[] sizedArr = new Integer[] { 1000, 5000, 100000, 1000000 };
        // execute both methods a large number of times before output the result
        for (int i = 1000000; i >= 0; i--){
            timeData(0, fillArrayList(100), 100);
            timeData(1, fillArrayList(100), 100);
        }
        for (int i = 0; i < sizedArr.length; i++) {
            System.out.println("Sum method time: " + timeData(0, fillArrayList(sizedArr[i]), sizedArr[i]) +
            "; Iterate over a single value method: " + timeData(1, fillArrayList(sizedArr[i]), sizedArr[i]) + ";");
        }
    }

    // increase value by the provided number of iterations
    public static int increaseValue(int numOfIterations) {
        int val = 0;
            while (numOfIterations > 0) {
                val += 1;
                numOfIterations -= 1;
                }
            return val;
}

    // fill arrayList with random integers between 0 - 99;
    public static ArrayList<Integer> fillArrayList(int numberOfInts) {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        Random rand = new Random();
        for (int i = 0; i <= numberOfInts - 1; i++) {
            int randomInt = rand.nextInt(100);
            arrayList.add(randomInt);
        }
        return arrayList;
    }

    // iterative method to calculate sum of integers in array list
    public static int sumOfArrayList(ArrayList<Integer> arrayList) {
        int sum = 0;
        for (int i = 0; i < arrayList.size(); i++)
            sum = sum + arrayList.get(i);
        return sum;
    }

    // Measure execution time of methods
    public static ArrayList<Long> executionTime(ArrayList<Integer> filledArrayList, int numOfIterations) {
        ArrayList<Long> timeArr = new ArrayList<Long>();
        // execution time of increaseVal method
        long startTime = System.nanoTime();
        increaseValue(numOfIterations);
        long stopTime = System.nanoTime();
        long finalTimeSingleVal = (startTime - stopTime) * (-1);

        // execution time of sumArrayList method
        long startTime1 = System.nanoTime();
        sumOfArrayList(filledArrayList);
        long stopTime1 = System.nanoTime();
        long finalTimeSum = (startTime1 - stopTime1) * (-1);
        // Add time data to arrayList timeArr:
        // [min execution time, max execution time, average execution time, standard deviation]
        timeArr.add(finalTimeSum);
        timeArr.add(finalTimeSingleVal);
        return timeArr;
    }

    // TypeOfMethod: 0 - sumArrayList method execution time
    // TypeOfMethod: 1 - increaseValue method execution time
    public static ArrayList<Long> timeData(int typeOfMethod, ArrayList<Integer> sizedArray, int numOfIterations) {
        ArrayList<Long> arrayListTime = new ArrayList<Long>();
        ArrayList<Long> resultTime = new ArrayList<Long>();
        long sum = 0l;
        // Get execution time of chosen method 100 times and add to the arrayListTime
        for (int i = 0; i <= 100; i++) {
            long executionTime = executionTime(sizedArray, numOfIterations).get(typeOfMethod);
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
        long min = arrayListTime.get(0);
        // max execution time
        long max = arrayListTime.get(arrayListTime.size() - 1);
        // avg - average execution time
        long avg = sum / arrayListTime.size();
        // standard deviation
        long stdDev = 0l;
        for(long num : arrayListTime) {
            stdDev += Math.pow(num - avg, 2);
        }
        // Add time data to the resultTime
        resultTime.add(min);
        resultTime.add(max);
        resultTime.add(avg);
        resultTime.add((long)Math.sqrt(stdDev/arrayListTime.size()));
        return resultTime;
    }
}
