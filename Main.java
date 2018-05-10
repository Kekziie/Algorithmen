/*Qi Ma, Efstratios Baxivanelis, Kim Thuong Ngo*/

public class Main {

	/* START OF TESTING */
	public static int[] emptyArray     = {};  // empty
	public static int[] singletonArray = {1}; // = (1)
	public static int[] generalArray   = new int[333];
	
	public static void setUpGeneralArray() {
		for (int i=0; i<generalArray.length; i++) {
			generalArray[i] = 3*i;
		}
	}
	
	public static void searchInArray(int[] array, int key) {
		int linearResult   = linearSearch(array, key);
		int binaryResult   = binarySearch(array, key);
		int interpolResult = interpolationSearch(array, key);
		
		if (linearResult != -1) {
			System.out.println("Linear search: " + key + " found in position " + linearResult);
		}
		else {
			System.out.println("Linear search: " + key + " not found in array");
		}
		
		if (binaryResult != -1) {
			System.out.println("Binary search: " + key + " found in position " + binaryResult);
		}
		else {
			System.out.println("Binary search: " + key + " not found in array");
		}
		
		if (interpolResult != -1) {
			System.out.println("Interpolation search: " + key + " found in position " + interpolResult);
		}
		else {
			System.out.println("Interpolation search: " + key + " not found in array");
		}
		System.out.println();
	}
	
    public static void main(String[] args) {
    	setUpGeneralArray();
    	
    	// the keys to search
    	int[] keys = {-10000, -1000, -333, -100, -2, -1, 0, 1, 2, 100, 333, 1000, 10000};
		
		// test all keys
		for (int i=0; i < keys.length; i++) {
			System.out.println("Searching for key " + keys[i] + " in the first array:"); 
			searchInArray(emptyArray, keys[i]);
			System.out.println("Searching for key " + keys[i] + " in the second array:");
			searchInArray(singletonArray, keys[i]);
			System.out.println("Searching for key " + keys[i] + " in the third array:");
			searchInArray(generalArray, keys[i]);
			System.out.println();
		}
    }
    /* END OF TESTING */
      
    
    /**
     * Implementation of linear search. Return the index of key in the array.
     * Return -1 if key is not contained in the array.
     * @param array
     * @param key
     * @return
     */
    
    public static int linearSearch(int[] array, int key){
    	//Lineare Suche (jedes Element wird besucht und verglichen):
        for(int i = 0; i <= array.length - 1; i++){
        	//Element gefunden:
        	if(key == array[i]){
        		return i;
        	}
        }
        //Falls Element nicht in Array wird -1 zurückgegeben:
        return -1;
    }
    
    
    /**
     * Implementation of binary search. Return the index of key in the array.
     * Return -1 if key is not contained in the array.
     * @param array
     * @param key
     * @return
     */
    public static int binarySearch(int[] array, int key){
    	//Initialisierung benötigter Werte:
        int high = array.length - 1;
        int low = 0;
        int next = 0;
        //Schleifenbeginn:
        while(high >= low){
        	//Berechnung der mittleren Position im Array:
        	next = (int)Math.ceil((high - low)/2)+low;
        	//Vergleich mit mittlerer Position:
        	if(key == array[next]){
        		//Element wurde an Position Next gefunden:
        		return next;
        	}
        	else if(key < array[next]){
        		//Suche weiter in der linken Hälfte des Arrays:
        		high = next - 1;
        	}
        	else{
        		//Suche weiter in der rechten Hälfte des Arrays:
        		low = next + 1;
        	}
        }
        //Element nicht im Array:
        return -1;
    }
    
    
    /**
     * Implementation of interpolation search. Return the index of key in the array.
     * Return -1 if key is not contained in the array.
     * @param array
     * @param key
     * @return
     */
    public static int interpolationSearch(int[] array, int key){
    	//Initialisierung benötigter Werte:
    	int high = array.length - 2;
        int low = 1;
        int next = 0;
        int n = array.length;
        
        //Spezialfälle werden eigen behandelt
        //(Array leer, Key größer als letztes Array Element bzw. kleiner als erstes Element -> Element kann nicht in Array sein):
        if(array.length == 0 || key < array[0] || key > array[array.length - 1]){
        	return -1;
        }
        //Für Array.length = 1 gibt es keinen Sinn zu suchen, man kann direkt vergleichen:
        else if(array.length == 1){
        	if(key == array[0]){
        		return 0;
        	}
        	else{
        		return -1;
        	}
        }
        //Für ein gültiges Array mit mehr als einem Element:
        while(high-low > 1){
        	
        	if(high > array.length - 2){
        		high = array.length - 2;
        	}
        	
        	//Berechung des "Sprunges" zu Position Next im Array:
        	next = (int)Math.ceil(((key-array[low - 1])/(array[high + 1]-array[low - 1]))*(high - low + 1)) + (low + 1);
        	
        	n = high-low+1;
        	
        	//Element an Position Next gefunden:
        	if(key == array[next]){
        		return next;
        	}
        	else if(key > array[next]){
        		//Sprung zu weit:
        		int i = 2;
        		while(key >= array[next+(i - 1)*(int)Math.sqrt(n)]){
        			//Element kann nicht im Array vorhanden sein:
        			if(next+(i - 1)*(int)Math.sqrt(n) >= array.length){
        				return -1;
        			}
        			i++;
        		}
        		//Neue Grenzen gefunden:
        		low = next + (i - 2)*(int)Math.sqrt(n);
        		high = next + (i - 1)*(int)Math.sqrt(n);
        	}
        	else{
        		//Setze noch ungetestete Position High als neue Grenze:
        		high = next;
        	}
        }
        
        return -1;
    }

}