//bruteforce approach 
import java.util.*;
public class Solution {
    public static int majorityElement(int arr[]){
        
        int n= arr.length;
        //using nested loops for iteration and counting
        for(int i=0; i<= arr.length-1; i++){
            int count = 1;
            for (int j=i+1; j<arr.length ; j++){
                if(arr[i] == arr[j]){
                    count ++;
                }
            }
            if(count > n/2){
                return arr[i];
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        int arr[] = {3,3,4,2,4,4,2,4,4};
        int result = majorityElement(arr);
        System.out.println(result);
    }
    
}

