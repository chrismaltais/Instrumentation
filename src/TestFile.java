import java.util.Random;
import java.util.Timer;

class TestFile {

	private static long startTime = 0;
	private static long startMainTime = 0;
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
		startMainTime = System.currentTimeMillis();

		//Define instrumentation object
		Instrumentation instance = Instrumentation.getInstance();

		// Activate instrumentation object:
		instance.activate(true);

		startTime = System.currentTimeMillis();
		// Begin program test
		instance.startTiming("Main");

		endTime = System.currentTimeMillis();
		System.out.println("Overhead instrumentation START: " + (endTime - startTime));

    	// Instantiate all sorting algorithms

		BubbleSort2Algorithm bubbleSort = new BubbleSort2Algorithm();

		ExtraStorageMergeSortAlgorithm mergeSort = new ExtraStorageMergeSortAlgorithm();

		NaiveQuickSortAlgorithm quickSort = new NaiveQuickSortAlgorithm();

		SelectionSortAlgorithm selectionSort = new SelectionSortAlgorithm();

    	// Create a test array
    	int arraySize = 99999;

		startTime = System.currentTimeMillis();
    	int[] initializeTestValues = populateArray(arraySize);
		endTime = System.currentTimeMillis();
		System.out.println("populateArray(): " + (endTime - startTime));

    	int[] testValues = initializeTestValues.clone();
    	
    	// Time the Bubble sort
		startTime = System.currentTimeMillis();
		bubbleSort.sort(testValues);
		endTime = System.currentTimeMillis();
		System.out.println("Bubble sort: " + (endTime - startTime));

    	// Reinitialize array to be sorted
    	testValues = initializeTestValues.clone();
    	
    	instance.comment("This is a test comment");
    	
    	// Time the Merge Sort
		startTime = System.currentTimeMillis();
		mergeSort.sort(testValues);
		endTime = System.currentTimeMillis();
		System.out.println("Merge sort: " + (endTime - startTime));

    	// Reinitialize array to be sorted
    	testValues = initializeTestValues.clone();
    	
    	// Time the Merge Sort
		startTime = System.currentTimeMillis();
		quickSort.sort(testValues);
		endTime = System.currentTimeMillis();
		System.out.println("Quick sort: " + (endTime - startTime));

      	// Reinitialize array to be sorted
    	testValues = initializeTestValues.clone();
    	
    	// Time the Merge Sort
		startTime = System.currentTimeMillis();
		selectionSort.sort(testValues);
		endTime = System.currentTimeMillis();
		System.out.println("Selection sort: " + (endTime - startTime));

		startTime = System.currentTimeMillis();

		instance.stopTiming("Main");

		endTime = System.currentTimeMillis();
		System.out.println("Overhead instrumentation STOP: " + (endTime - startTime));

		endTime = System.currentTimeMillis();
		System.out.println("Main: " + (endTime - startMainTime));

    	instance.dump();
    }
}