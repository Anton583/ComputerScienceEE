package ArrayList;

import java.util.*;

public class ArrayListEE {
    public static void main(String[] args) {
        System.out.println(timeData(0) + ", " + timeData(1) + ", " + timeData(2));
    }

    // Fill arrayList with random integers between 0 - 99;
    public static ArrayList<Integer> fillArrayList() {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        Random rand = new Random();
        for (int i = 0; i <= 4999; i++) {
            int randomInt = rand.nextInt(100);
            arrayList.add(randomInt);
        }
        return arrayList;
    }

    // Iterative method to calculate sum of integers in array list
    public static int sumOfArrayList(ArrayList<Integer> arrayList) {
        int sum = 0;
        for (int i = 0; i < arrayList.size(); i++)
            sum = sum + arrayList.get(i);
        return sum;
    }

    // Recursive method to calculate sum of integers starting from the last index of
    // array list (Stack overflow - max 5680 elements in arrayList)
    public static int recursiveSumOfArr(ArrayList<Integer> arrayList, int arrEndIndex) {
        if (arrEndIndex == 0)
            return arrayList.get(arrEndIndex);
        return arrayList.get(arrEndIndex) + recursiveSumOfArr(arrayList, arrEndIndex - 1);
    }

    // Recursive method to calculate sum of integers starting from the first index
    // of array list using sublist (Stack overflow - max 9858 in arrayList)
    public static int recursiveSumOfArr2(List<Integer> arrayList, int startIndex) {
        if (startIndex == arrayList.size() - 1)
            return arrayList.get(startIndex);
        return arrayList.get(startIndex) + recursiveSumOfArr2(arrayList.subList(0, arrayList.size()), startIndex + 1);

    }

    // Measure execution time of array list sum methods
    public static ArrayList<Double> executionTime() {
        var filledArrayList = fillArrayList();
        ArrayList<Double> timeArr = new ArrayList<Double>();

        double startTime1 = System.nanoTime();
        sumOfArrayList(filledArrayList);
        double stopTime1 = System.nanoTime();

        double startTime2 = System.nanoTime();
        recursiveSumOfArr(filledArrayList, filledArrayList.size() - 1);
        double stopTime2 = System.nanoTime();

        double startTime3 = System.nanoTime();
        recursiveSumOfArr2(filledArrayList, 0);
        double stopTime3 = System.nanoTime();

        // Interative sum method execution time
        double finalTimeIterative = (startTime1 - stopTime1) * (-1);
        // Recursion sum method execution time
        double finalTimeRecursion = (startTime2 - stopTime2) * (-1);
        // Recursion sum sublist method execution time
        double finalTimeRecursion2 = (startTime3 - stopTime3) * (-1);

        // Add time data to arrayList timeArr:
        // [min execution time, max execution time, average execution time]
        timeArr.add(finalTimeIterative);
        timeArr.add(finalTimeRecursion);
        timeArr.add(finalTimeRecursion2);

        return timeArr;
    }

    // TypeOfMethod: 0 - iterative, 1 - recursion using last index, 2 - recursion
    // using sublist
    public static ArrayList<Double> timeData(int typeOfMethod) {
        ArrayList<Double> arrayListTime = new ArrayList<Double>();
        ArrayList<Double> resultTime = new ArrayList<Double>();
        double sum = 0;
        // Get execution time of chosen method 100 times and add to the arrayListTime
        for (int i = 0; i <= 100; i++) {
            double executionTime = executionTime().get(typeOfMethod);
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
