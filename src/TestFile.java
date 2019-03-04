import java.util.ArrayList;
import java.util.Random;

class TestFile {
	
    public static int[] populateArray(int size) {
		Random rand = new Random();
		int[] numList = new int[size];
		// Get numbers between 1 - 99999
		for (int i = 0; i < size; i ++) {
			int randomNumber = rand.nextInt(99999) + 1;
			System.out.println(randomNumber);
			numList[i] = randomNumber;
		}
		return numList;
    }
    
    public static void main(String[] args) {
    	BubbleSort2Algorithm bubbleSort = new BubbleSort2Algorithm();
    	ExtraStorageMergeSortAlgorithm mergeSort = new ExtraStorageMergeSortAlgorithm();
    	NaiveQuickSortAlgorithm quickSort = new NaiveQuickSortAlgorithm();
    	SelectionSortAlgorithm selectionSort = new SelectionSortAlgorithm();
    	
    	int arraySize = 25;
    	int[] testValues = populateArray(arraySize);
    	int[] copyTestValues = testValues.clone();
    	
    	System.out.println(copyTestValues[0]);
    	System.out.println("copy ^, original DOWN");
    	System.out.println(testValues[0]);
    	
    	bubbleSort.sort(testValues);
    	System.out.println("--------------------");
    	for (int num : testValues) {
    		System.out.println(num);
    	}
    	
    }
}