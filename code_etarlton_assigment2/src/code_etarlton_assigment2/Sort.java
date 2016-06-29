package code_etarlton_assigment2;
import java.awt.Color;

public class Sort {

	/** Task: Sorts the first n objects in an array into ascending order using selection sort method.
     * @param p a DrawingPanel object
     * @param n an integer > 0 
     */
    public void selectionSort(DrawingPanel p, int n)
    {   
        for (int index = 0; index < n - 1; index++)
        {
            int indexOfNextSmallest = getIndexOfSmallest(p.array, index, n-1);
            swap(p, index, indexOfNextSmallest); 
            // Assertion: a[0] <= a[1] <= . . . <= a[index] <= all other a[i]
            
           p.paintImmediately(0,0,900,600);
           try{Thread.sleep(100);}
        catch(InterruptedException ex){}
            } // end for loop
        
    } // end selectionSort
    

    /** Task: Finds the index of the smallest value in an array.
     * @param a an array of int objects
     * @param first an integer >= 0 that is the index of the first
     * array element to consider
     * @param last an integer >= 0 that is the index of the last
     * array element to consider
     * @return the index of the smallest value among
     * a[first], a[first+1], . . . , a[last] 
     */
    private int getIndexOfSmallest(int[] a, int first, int last)
    {
        int min = a[first];
        int indexOfMin = first;
        for (int index = first+1; index <= last; index++)
        {
            if (a[index] > min)
            {
                min = a[index];
                indexOfMin = index;
                // Assertion: min is the smallest of a[first] through a[index].
            } // end if
        } // end for
        return indexOfMin;
    } // end getIndexOfSmallest
    
    
    /** Task: Swaps the array elements a[i] and a[j] and swaps c[i] and c[j].
     * @param p a DrawingPanel object
     * @param i an integer >= 0 and < a.length
     * @param j an integer >= 0 and < a.length 
     */
    private void swap(DrawingPanel p, int i, int j)
    {
        int temp = p.array[i];
        p.array[i] = p.array[j];
        p.array[j] = temp;
        
        Color ctemp = p.colorArray[i];
        p.colorArray[i] = p.colorArray[j];
        p.colorArray[j] = ctemp;
    } // end swap
    
    /**
     * Task: Sorts the first n objects in an array into ascending order using merge sort method.
     * @param low lower index of the array
     * @param high higher index of the array
     * @param p DrawingPanel object
     * @param ctemp array of Color objects, this is the temporary container array for the color objects to be sorted
     * @param temp array of int objects, this is the temporary container array for the int objects to be sorted
     */
    public void mergeSort(int low, int high, DrawingPanel p, Color[] ctemp, int[] temp)
    {
    	if(low < high)
    	{
    		int mid = (low + high) / 2;
    		mergeSort(low, mid, p, ctemp, temp); //sorts left side of array
    		mergeSort((mid+1), high, p, ctemp, temp); //sorts right side of array
    		merge(low, high, p, ctemp, temp);//merges the halves of the array
    	}
    }
    
    /**
     * Task: merges two halves of the original array
     * @param low lower index of the array
     * @param high higher index of the array
     * @param p DrawingPanel object
     * @param ctemp array of Color objects, this is the temporary container array for the color objects to be sorted
     * @param temp array of int objects, this is the temporary container array for the int objects to be sorted
     */
    private void merge(int low, int high, DrawingPanel p, Color[] ctemp, int[] temp)
    {
    	int mid = (low + high) / 2;
    	int lowIndex = low;
    	int resultIndex = low;
    	int highIndex = mid + 1;
    	
    	while((lowIndex <= mid) && (highIndex <= high))
    	{
    		if(p.array[lowIndex] >= p.array[highIndex])
    		{
    			temp[resultIndex] = p.array[lowIndex];
    			ctemp[resultIndex] = p.colorArray[lowIndex];
    			lowIndex++;
    		}
    		else
    		{
    			temp[resultIndex] = p.array[highIndex];
    			ctemp[resultIndex] = p.colorArray[highIndex];
    			highIndex++;
    		}
    		resultIndex++;
    	}
    	
    	for(int k = lowIndex; k <= mid; k++)
    	{
    		temp[resultIndex] = p.array[k];
    		ctemp[resultIndex] = p.colorArray[k];
    		resultIndex++;
    	}
    	
    	for(int k = highIndex; k <= high; k++)
    	{
    		temp[resultIndex] = p.array[k];
    		ctemp[resultIndex] = p.colorArray[k];
    		resultIndex++;
    	}
    	
    	//outputs the lines
    	for(int k = low; k <= high; k++)
    	{
    		p.array[k] = temp[k];
    		p.colorArray[k] = ctemp[k];
    		 p.paintImmediately(0,0,900,600);
             try{Thread.sleep(20);}
          catch(InterruptedException ex){}
    	}
    }
 }
