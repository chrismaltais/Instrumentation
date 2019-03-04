class TestFile {
    
    public static void main(String[] args) {
    	BubbleSort2Algorithm bubbleSort = new BubbleSort2Algorithm();
    	int[] testNumbers = {1, 2, 3, 9, 7, 4, 2, 5, 7, 3};
    	int[] sortedValues = new int[testNumbers.length];
    	bubbleSort.sort(testNumbers);
    	for (int num : testNumbers) {
    		System.out.println(num);
    	}
    }
}