import java.io.*;
import java.lang.*;
import java.util.*;

public class median{
    public static void main(String[] args){
        Heap h = new Heap(50);
        Scanner scan = new Scanner(System.in);
        Integer p;
        for(int i = 0; i < 10; i++){
            p = scan.nextInt();
            h.input(p);
            h.getMedian();
        }
    }
}

class Heap{
    int arr[];
    int arr1[];
    int curr;
    int curr1;
    int med;
    int max_size;
    public Heap(int size){ 
        arr = new int[size];
        curr = 1;
        arr1 = new int[size];
        curr1 = 1;
        med = -1;
        max_size = size;
    }
    public void pushM(int p){
        arr[curr] = p;
        curr++;
        swimM(curr);
    }
    private void swap(int a[], int c, int p){
        int temp;
        temp = a[c];
        a[c] = a[p];
        a[p] = temp;
    }
    private void swimM(int i){
        if(i <= 0) return;
        if(arr[i] > arr[i/2]){
            swap(arr,i,i/2);
            swimM(i/2);
        }
    }
    public void pushm(int p){
        arr1[curr1] = p;
        curr1++;
        swimm(curr1);
    }
    private void swimm(int i){
        if(i <= 0) return;
        if(arr1[i] < arr1[i/2]){
            swap(arr1, i, i/2);
            swimm(i/2);
        }
    }
    public void getMedian(){
        if(curr == curr1)
            med = (arr[curr-1] + arr1[curr1 - 1])/2;
        else if(curr > curr1)
            med = arr[curr-1];
        else
            med = arr1[curr1-1];
        System.out.println(med);
    }
    public int popM(){
        int temp = arr[0];
        swap(arr, 0, curr-1);
        curr--;
        sinkM(0);
        return temp;
    }
    public void sinkM(int i){
        int t = i;
        if(arr[t] < arr[(2*i)])
            t = 2*i;
        if(arr[t] < arr[(2*i) + 1])
            t = 2*i + 1;
        if(t != i){
            swap(arr, t, i);
            sinkM(t);
        }
    }
    public int popm(){
        int temp = arr1[0];
        swap(arr1, 0, curr1 - 1);
        curr1--;
        sinkm(0);
        return temp;
    }
    public void sinkm(int i){
        int t = i;
        if(arr1[t] > arr1[(2*i)])
            t = 2*i;
        if(arr1[t] > arr1[(2*i) + 1])
            t = 2*i + 1;
        if(t != i){
            swap(arr1, t, i);
            sinkm(t);
        }
    }
    public void input(int p){
        int temp;
        if(med == -1)
            pushM(p);
        else if(p < med)
            pushM(p);
        else
            pushm(p);
        if(Math.abs(curr - curr1) > 1){
            if(curr > curr1){
                temp = popM();
                swimm(temp);
            }
            else{
                temp = popm();
                swimM(temp);
            }
        }
    }
}
