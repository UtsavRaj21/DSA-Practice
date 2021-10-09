import java.util.*;
public class basic4 {
    //853. Car Fleet
    public class vPair implements Comparable<vPair>{
        int pos = 0 ;
        int speed = 0;
        double t =0;

        public vPair(int pos,int speed,double t){
            this.pos = pos;
            this.speed = speed;
            this.t = t;

        }

        public int compareTo(vPair o){
            return this.pos - o.pos;
        }


    }
    
    public int carFleet(int target, int[] position, int[] speed) {
        int n = speed.length;
        vPair[] data = new vPair[n];

        for(int i = 0 ; i <n; i++){
            data[i] = new vPair(position[i], speed[i], (target - position[i])/speed[i]);
        }

        Arrays.sort(data);
        int count =1;
        double time = data[n-1].t;

        for(int i = n-2; i >= n ;i--){
            if(time < data[i].t){
                count++;
                time =  data[i].t;
            }
        }
        return count;
    }

    //=================================================================================================================
    public static void main(String[] args) {
        
    }
}
