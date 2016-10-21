import org.junit.Test;

import java.util.Random;

/**
 * Created by yroslav on 10/21/16.
 */
public class Sorting {

    @Test/*(expected = NullPointerException.class)*/
    public void showResult(){
        int[] unsortedArray = new Random().ints(300, 0, 100).toArray();
        int[] result = getLargest(unsortedArray, 10);

        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + " ");
        }
    }

    //Это ваш метод
    public int[] getLargest(int[] array, int k){
        recQuickSort(0,array.length-1,array);
        return getMaxElements(k, array);
    }

    private void recQuickSort(int left, int right,int[] theArray){
        int size = right - left + 1;

        if(size<=3){
            manualSort(left, right, theArray);
        }else{
            int median = median(left, right, theArray);
            int partition = partitionIt(left, right, median, theArray);
            recQuickSort(left, partition - 1, theArray);
            recQuickSort(partition + 1, right, theArray);
        }
    }

    private int median(int left, int right, int[] theArray){
        int center = (left + right) / 2;

        if(theArray[left]>theArray[center])
            swap(left, center,theArray);

        if(theArray[left]>theArray[right])
            swap(left, right, theArray);

        if(theArray[center]>theArray[right])
            swap(center, right, theArray);

        swap(center, right - 1, theArray);

        return theArray[right - 1];
    }

    private int partitionIt(int left, int right, int pivot,int[] theArray)
    {
        int leftPtr = left;
        int rightPtr = right - 1;
        while(true)
        {
            while(theArray[++leftPtr] < pivot);

            while(theArray[--rightPtr] > pivot);

            if(leftPtr >= rightPtr)
                break;
            else
                swap(leftPtr, rightPtr,theArray);
        }
        swap(leftPtr, right-1,theArray);
        return leftPtr;
    }

    private void swap(int index1, int index2,int[] theArray)
    {
        int temp;
        temp = theArray[index1];
        theArray[index1] = theArray[index2];
        theArray[index2] = temp;
    }

    private void manualSort(int left, int right, int[] theArray){
        int size = right - left + 1;

        if(size <= 1)
            return;

        if(size == 2){
            if (theArray[left] >theArray[right])
                swap(left, right,theArray);
            return;
        }

        else
        {
            if (theArray[left] > theArray[right - 1])
                swap(left, right - 1, theArray);

            if (theArray[left] > theArray[right])
                swap(left, right, theArray);

            if (theArray[right - 1] > theArray[right])
                swap(right - 1, right, theArray);
        }
    }

    public int[] getMaxElements(int quantity, int[] theArray) throws NullPointerException{
        if(isValidQuantity(quantity,theArray)){

            int[] maxElement = new int[quantity];
            int length = theArray.length-1;

            for (int i = 0; i < quantity ; i++) {
                maxElement[i] = theArray[length-i];
            }

            return maxElement;
        }
        else {
            System.out.println("The value of quantity doesn't valid");
            return null;
        }
    }

    public boolean isValidQuantity(int quantity, int[] theArray){
        return (quantity >= 0) && (quantity <= theArray.length);
    }
}
