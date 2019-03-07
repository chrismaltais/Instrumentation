import java.util.Random;
import java.util.Timer;

class TestFile {

	private static long startTime = 0;
	private static long endTime = 0;

    public static int[] populateArray(int size) {
		Random rand = new Random();
		int[] numList = new int[size];
		// Get numbers between 1 - 99999
		for (int i = 0; i < size; i ++) {
			int randomNumber = rand.nextInt(99999) + 1;
			numList[i] = randomNumber;
		}
		return numList;
    }
    
    public static void main(String[] args) {
		startTime = System.currentTimeMillis();

		// Define instrumentation object
//		Instrumentation instrumentation = Instrumentation.getInstance();

		// Activate instrumentation object:
//		instrumentation.activate(true);

//		startTime = System.currentTimeMillis();
		// Begin program test
//		instrumentation.startTiming("Main");

//		endTime = System.currentTimeMillis();
//		System.out.println("Overhead instrumentation START: " + (endTime - startTime));

    	// Instantiate all sorting algorithms

		BubbleSort2Algorithm bubbleSort = new BubbleSort2Algorithm();

		ExtraStorageMergeSortAlgorithm mergeSort = new ExtraStorageMergeSortAlgorithm();

		NaiveQuickSortAlgorithm quickSort = new NaiveQuickSortAlgorithm();

		SelectionSortAlgorithm selectionSort = new SelectionSortAlgorithm();

    	// Create a test array
    	int arraySize = 99999;
    	int[] initializeTestValues = populateArray(arraySize);
    	int[] testValues = initializeTestValues.clone();
    	
    	// Time the Bubble sort
		startTime = System.currentTimeMillis();
//    	instrumentation.startTiming("Bubble Sort");
		bubbleSort.sort(testValues);
//    	instrumentation.stopTiming("Bubble Sort");
		endTime = System.currentTimeMillis();
		System.out.println("Bubble sort: " + (endTime - startTime));

    	// Reinitialize array to be sorted
    	testValues = initializeTestValues.clone();
    	
//    	instrumentation.comment("This is a test comment");
    	
    	// Time the Merge Sort
		startTime = System.currentTimeMillis();
//    	instrumentation.startTiming("Merge Sort");
		mergeSort.sort(testValues);
//    	instrumentation.stopTiming("Merge Sort");
		endTime = System.currentTimeMillis();
		System.out.println("Merge sort: " + (endTime - startTime));

    	// Reinitialize array to be sorted
    	testValues = initializeTestValues.clone();
    	
    	// Time the Merge Sort
		startTime = System.currentTimeMillis();
//    	instrumentation.startTiming("Quick Sort");
		quickSort.sort(testValues);
//    	instrumentation.stopTiming("Quick Sort");
		endTime = System.currentTimeMillis();
		System.out.println("Quick sort: " + (endTime - startTime));

      	// Reinitialize array to be sorted
    	testValues = initializeTestValues.clone();
    	
    	// Time the Merge Sort
		startTime = System.currentTimeMillis();
//    	instrumentation.startTiming("Selection Sort");
		selectionSort.sort(testValues);
//    	instrumentation.stopTiming("Selection Sort");
		endTime = System.currentTimeMillis();
		System.out.println("Selection sort: " + (endTime - startTime));

//		startTime = System.currentTimeMillis();

//		instrumentation.stopTiming("Main");

//		endTime = System.currentTimeMillis();
//		System.out.println("Overhead instrumentation STOP: " + (endTime - startTime));

//    	instrumentation.dump();
		endTime = System.currentTimeMillis();
		System.out.println("Main: " + (endTime - startTime));
    }
}