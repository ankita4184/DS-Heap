import java.io.*;
import java.lang.*;
import java.util.*;

public class clinics{
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        int c = scan.nextInt();
        heap h = new heap(c);
        int clinic = scan.nextInt();
        for(int i = 0; i < c; i++){
            int pop = scan.nextInt();
            h.add(pop);
        }
        clinic = clinic - c;
        while(clinic != 0){
            city temp = h.max();
            temp.clinic++;
            temp.dens = (float)(temp.popu)/temp.clinic;
            h.push(temp);
            clinic--;
 //           System.out.println(clinic);
        }
        h.print();
    }
}

class city{
    int popu;
    int clinic;
    float dens;
    public city(){}
    public city(int p){
        popu = p;
        clinic = 1;
        dens = (float)popu;
    }
}

class heap{
    city arr[];
    int curr;
    public heap(){}
    public heap(int c){
        arr = new city[c + 1];
        curr = 1;
    }
    public void add(int pop){
        city temp = new city(pop);
        push(temp);
    }
    public void push(city temp){
        arr[curr] = temp;
        swim(curr);
        curr++;
    }
    public city max(){
        city temp = arr[1];
        swap(1, curr-1);
        curr--;
        sink(1);
        return temp;
    }
    private void swap(int a, int b){
        city t = arr[a];
        arr[a] = arr[b];
        arr[b] = t;
    }
    private void swim(int i){
        if(i <= 1) return;
        if(arr[i].dens > arr[i/2].dens){
            swap(i, i/2);
            swim(i/2);
        }
    }
    private void sink(int i){
        if(2*i >= curr) return;
        int min = i;
        if(arr[min].dens < arr[2*i].dens)
            min = 2*i;
        if(arr[min].dens < arr[2*i + 1].dens)
            min = 2*i + 1;
        if(min != i){
            swap(min, i);
            sink(min);
        }
    }
    public void print(){
        for(int i = 1; i < curr; i++)
            System.out.println(i+" "+arr[i].clinic+" "+arr[i].dens);
    }
}
           


