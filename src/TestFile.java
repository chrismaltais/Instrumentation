import java.util.ArrayList;
import java.util.Random;

class TestFile {
	
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
    	// Define instrumentation object
    	Instrumentation instrumentation = Instrumentation.getInstance();
    	
    	// Activate instrumentation object:
    	instrumentation.activate(true);
    	
    	// Begin program test
    	instrumentation.startTiming("Main");
    	
    	// Instantiate all sorting algorithms
    	BubbleSort2Algorithm bubbleSort = new BubbleSort2Algorithm();
    	ExtraStorageMergeSortAlgorithm mergeSort = new ExtraStorageMergeSortAlgorithm();
    	NaiveQuickSortAlgorithm quickSort = new NaiveQuickSortAlgorithm();
    	SelectionSortAlgorithm selectionSort = new SelectionSortAlgorithm();
    	
    	// Create a test array
    	int arraySize = 5;
    	int[] initializeTestValues = populateArray(arraySize);
    	int[] testValues = initializeTestValues.clone();
    	
    	// Time the Bubble sort
    	instrumentation.startTiming("Bubble Sort");
    	bubbleSort.sort(testValues);
    	instrumentation.stopTiming("Bubble Sort");
    	
    	// Reinitialize array to be sorted
    	testValues = initializeTestValues.clone();
    	
    	instrumentation.comment("This is a test comment");
    	
    	// Time the Merge Sort
    	instrumentation.startTiming("Merge Sort");
    	mergeSort.sort(testValues);
    	instrumentation.stopTiming("Merge Sort");
    	
    	// Reinitialize array to be sorted
    	testValues = initializeTestValues.clone();
    	
    	// Time the Merge Sort
    	instrumentation.startTiming("Quick Sort");
    	quickSort.sort(testValues);
    	instrumentation.stopTiming("Quick Sort");
    	
      	// Reinitialize array to be sorted
    	testValues = initializeTestValues.clone();
    	
    	// Time the Merge Sort
    	instrumentation.startTiming("Selection Sort");
    	selectionSort.sort(testValues);
    	instrumentation.stopTiming("Selection Sort");
    	
    	instrumentation.stopTiming("Main");
    	
    }
}