import java.io.*;
import java.lang.*;
import java.util.*;

public class os{
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        Integer pid;
        Heap h = new Heap(5);
        Integer pri;
        for(int i = 0; i < 5; i++){
            pid = scan.nextInt();
            pri = scan.nextInt();
            h.input(pid, pri);
        }
        h.print();
    }
}

class Heap{
    Process p[];
    int curr;
    public Heap(){}
    public Heap(int i){
        p = new Process[i+1];
        curr = 1;
    }
    public void input(int pid, int pri){
        Process t = new Process(pid, pri);
        p[curr] = t;
        swim(curr);
        curr++;
    }
    private void swim(int i){
        if(i <= 1) return;
        if(p[i].pri < p[i/2].pri){
            swap(i, i/2);
            swim(i/2);
        }
    }
    private void swap(int c, int a){
        Process temp = p[c];
        p[c] = p[a];
        p[a] = temp;
    }
    public void print(){
        while(curr >= 1){
            for(int i = 1; i < curr; i++)
                System.out.print(p[i].pri+" ");
            System.out.println(" "+pop());
        }
    }
    private int pop(){
        int temp = p[1].pri;
        curr--;
        swap(1, curr);
        sink(1);
        return temp;
    }
    private void sink(int i){
        int min = i;
        if(i*2 >= curr) return;
        if(p[min].pri > p[i*2].pri)
            min = i*2;
        if((2*i + 1) < curr && p[min].pri > p[i*2 + 1].pri)
            min = i*2 + 1;
        if(min != i){
            swap(min, i);
            sink(min);
        }
    }
}

class Process{
    int pri;
    int pid;
    public Process(int pid, int pri){
        this.pri = pri;
        this.pid = pid;
    }
}
         
