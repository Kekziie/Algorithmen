public class Main
{
    //Static variables to count the number of comparisons
    private static int comparisonsOfMergeSort;
    private static int comparisonsOfQuickSort = 0;
    
    /* START OF TESTING */
    public static void main(String[] args) {
        java.util.Random random = new java.util.Random();
        
        java.util.Map mergeSortComparisons = new java.util.TreeMap<Integer,Integer>();
        java.util.Map quickSortComparisons = new java.util.TreeMap<Integer,Integer>();
        
        for (int arraySize = 100; arraySize <= 2000; arraySize = arraySize + 100)
        {
            //Create a new array with double values and fill it with random numbers.
            double[] doubleRandomArray = new double[arraySize];
            for (int i=0; i<doubleRandomArray.length; i++) {
                doubleRandomArray[i] = random.nextDouble();
            }
            
            //Sort with mergeSort
            double[] copy1 = java.util.Arrays.copyOf(doubleRandomArray, doubleRandomArray.length);
            mergeSort(copy1, 0, copy1.length-1);
            mergeSortComparisons.put(arraySize,comparisonsOfMergeSort);
            for (int i=0; i<copy1.length-1; i++)
            {
                if (copy1[i] > copy1[i+1])
                {
                    System.err.println("Error in mergeSort at position " + i);
                    break;
                }
            }
            
            //Sort with quickSort
            double[] copy2 = java.util.Arrays.copyOf(doubleRandomArray, doubleRandomArray.length);
            quickSort(copy2, 0, copy2.length-1);
            quickSortComparisons.put(arraySize,comparisonsOfQuickSort);
            for (int i=0; i<copy2.length-1; i++)
            {
                if (copy2[i] > copy2[i+1])
                {
                    System.err.println("Error in quickSort at position " + i);
                    break;
                }
            }
        }
        
        //Create a new array with integer values and fill it with random numbers.
        int[] intRandomArray = new int[1000];
        for (int i=0; i<intRandomArray.length; i++) {
            intRandomArray[i] = random.nextInt(100);
        }
        
        /* Comment these lines in if you have implemented Counting Sort.
         
         //Sort with countingSort
         countingSort(intRandomArray);
         for (int i=0; i<intRandomArray.length-1; i++)
         {
         if (intRandomArray[i] > intRandomArray[i+1])
         {
         System.err.println("Error in countingSort at position " + i);
         break;
         }
         }
         */
        System.out.println("If no errors have been printed, then you passed the test ");
        
        System.out.println("Merge Sort");
        mergeSortComparisons.forEach((key,value) -> System.out.println(key + ": " + value));
        
        System.out.println();
        
        System.out.println("Quick Sort");
        quickSortComparisons.forEach((key,value) -> System.out.println(key + ": " + value));
        
    }
    /* END OF TESTING */
    
    /* START OF MERGESORT */
    /**
     * Sorts an array with mergesort.
     * @param array - the array to be sorted.
     */
    public static void mergeSort(double[] array, int left, int right){
        comparisonsOfMergeSort = 0;
        if (left < right) {
            int mid = ((left + right - 1)/2);
            
            // sortiert beide Teile
            mergeSort(array, left, mid);
            mergeSort(array, (mid + 1), right);
            
            merge(array, left, mid, right);
        }
        
    }
    
    /**
     * zusammenfügen zweier Sub-Arrays
     */
    public static void merge(double[] array, int left, int mid, int right){
        
        // erzeugt Hilfs-Array
        double[] tempArray = new double[array.length];
        
        for(int i = left; i<= right; i++){
            tempArray[i] = array[i];
        }
        int indexLeft = left;
        int indexRight = mid + 1;
        int index = left;
        
        while(indexLeft <= mid && indexRight <= right){
            if(tempArray[indexLeft] <= tempArray[indexRight]){
                array[index] = tempArray[indexLeft];
                indexLeft++;
            } else {
                array[index] = tempArray[indexRight];
                indexRight++;
            }
            index++;
        }
        while(indexLeft <= mid){
            array[index] = tempArray[indexLeft];
            indexLeft++;
            index++;
        }
    }
    
    /* END OF MERGESORT */
    
    /* START OF QUICKSORT */
    /**
     * Sorts an array with quicksort.
     * @param array - the array to be sorted.
     */
    public static void quickSort(double[] array, int left, int right){
        
        int i = partition(array, left, right);
        
        if (left < (i-1)){
            
            quickSort(array, left, (i-1));
        }
        if(i < right){
            
            quickSort(array, i, right);
        }
    }
    
    
    public static int partition(double[] array, int left, int right){
        // Mittiges Element wird als Pivotelement gewählt
        double pivot = array[(left + right)/2];
        double a;
        int i = left;
        int j = right;
        
        
        while (i<=j) {
            // suche auf linker Seite Element größer als Pivot
            while (array[i] < pivot){
                i++;
                comparisonsOfQuickSort++;
                
            }
            // suche auf rechter Seite Element kleiner als Pivot
            while (array[j] > pivot){
                j--;
                comparisonsOfQuickSort++;
                
            }
            //Vertauschung, so dass Elemente kleiner Pivot links und Elemente größer Pivot rechts stehen
            if (i <= j){
                a = array[i];
                array[i] = array[j];
                array[j] = a;
                i++;
                j--;
                
            }
        }
        
        return i;
    }
    
    
    
    /* END OF QUICKSORT */
    
    /* START OF COUNTINGSORT */
    /**
     * Sorts an array of integers with countingsort.
     * @param array - the array to be sorted.
     */
    public static void countingSort(int[] array){
        // TODO: Implementation goes here.
        
        int max = array[0];
        
        for(int i = 0; i < array.length; i++){
            if(array[i] > max) {
                array[i] = max;
            }
        }
        
        int[] c = new int[max];
        
        for(int h = 0; h < c.length; h++){
            c[h] = 0;
        }
        
        for(int j = 1; j < array.length; j++){
            c[array[j]] = c[j] + c[j-1];
        }
        
        int[] r = new int[array.length];
        
        for(int k = r.length; k >= 1; k--){
            r[c[array[k]]] = array[k];
            c[array[k]] = c[array[k]] - 1;
        }
        
        array = r;
        
    }
    
    // TODO: Put additional methods for countingsort here.
    
    /* END OF COUNTINGSORT */
}
