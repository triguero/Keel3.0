/**
 * <p>
 * @author Written by Antonio Alejandro Tortosa (University of Granada)  15/10/2008
 * @author Modified by Xavi Sol� (La Salle, Ram�n Llull University - Barcelona) 03/12/2008
 * @version 1.1
 * @since JDK1.2
 * </p>
 */


package keel.Algorithms.Rule_Learning.PART;



public class Utilities {
/**
 * <p>
 * Collection of auxiliar methods.
 * </p>
 */
	
  /** The log of 2. */
  protected static double log2 = Math.log(2);

  /**
   * Mergesort algorithm for an array of long integers.
   * @param theArray long[] the Array to sort
   * @param nElems int size of theArray
   */
  public static void mergeSort(long[] theArray,int nElems){
    // provides workspace
    long[] workSpace = new long[nElems];
    recMergeSort(theArray, workSpace, 0, nElems-1);
  }

  //------------------------------PRIVATE METHODS--------------------------------------------------/
  static private void recMergeSort(long[] theArray,long[] workSpace, int lowerBound,int upperBound){
    if(lowerBound == upperBound)            // if range is 1,
      return;                              // no use sorting
    else
    {                                    // find midpoint
      int mid = (lowerBound+upperBound) / 2;
      // sort low half
      recMergeSort(theArray,workSpace, lowerBound, mid);
      // sort high half
      recMergeSort(theArray,workSpace, mid+1, upperBound);
      // merge them
      merge(theArray,workSpace, lowerBound, mid+1, upperBound);
    }  // end else
  }  // end recMergeSort()
  //-----------------------------------------------------------
  static private void merge(long[] theArray,long[] workSpace, int lowPtr,int highPtr, int upperBound){
    int j = 0;                             // workspace index
    int lowerBound = lowPtr;
    int mid = highPtr-1;
    int n = upperBound-lowerBound+1;       // # of items

    while(lowPtr <= mid && highPtr <= upperBound)
      if( theArray[lowPtr] < theArray[highPtr] )
        workSpace[j++] = theArray[lowPtr++];
      else
        workSpace[j++] = theArray[highPtr++];

    while(lowPtr <= mid)
      workSpace[j++] = theArray[lowPtr++];

    while(highPtr <= upperBound)
      workSpace[j++] = theArray[highPtr++];

    for(j=0; j<n; j++)
      theArray[lowerBound+j] = workSpace[j];
  }
  /*************************END OF THE FIRST METHOD*******************************/

  /**
   * Mergesort algorithm for an array of Pairs.
   * @param theArray Pair[] the Array to sort
   * @param nElems int size of theArray
   */
  public static void mergeSort(Pair[] theArray,int nElems){
    // provides workspace
    Pair[] workSpace = new Pair[nElems];
    recMergeSort(theArray, workSpace, 0, nElems-1);
  }
  //------------------------------PRIVATE METHODS--------------------------------------------------/
  static private void recMergeSort(Pair[] theArray,Pair[] workSpace, int lowerBound,int upperBound){
    if(lowerBound == upperBound)            // if range is 1,
      return;                              // no use sorting
    else
    {                                    // find midpoint
      int mid = (lowerBound+upperBound) / 2;
      // sort low half
      recMergeSort(theArray,workSpace, lowerBound, mid);
      // sort high half
      recMergeSort(theArray,workSpace, mid+1, upperBound);
      // merge them
      merge(theArray,workSpace, lowerBound, mid+1, upperBound);
    }  // end else
  }  // end recMergeSort()
  //-----------------------------------------------------------
  static private void merge(Pair[] theArray,Pair[] workSpace, int lowPtr,int highPtr, int upperBound){
      int j = 0;                             // workspace index
      int lowerBound = lowPtr;
      int mid = highPtr-1;
      int n = upperBound-lowerBound+1;       // # of items

      while(lowPtr <= mid && highPtr <= upperBound)
        if( theArray[lowPtr].value < theArray[highPtr].value )
          workSpace[j++] = theArray[lowPtr++];
        else
          workSpace[j++] = theArray[highPtr++];

      while(lowPtr <= mid)
        workSpace[j++] = theArray[lowPtr++];

      while(highPtr <= upperBound)
        workSpace[j++] = theArray[highPtr++];

      for(j=0; j<n; j++)
        theArray[lowerBound+j] = workSpace[j];
    }

    /*************************END OF THE FIRST METHOD*******************************/

    /**
     * Help method for computing entropy.
     * @param num the number
     * @return the entropy of the number
     */
    public static double logFunc(double num) {

      // Constant hard coded for efficiency reasons
      if (num < 1e-6)
        return 0;
      else
        return num*Math.log(num)/log2;
    }

}
