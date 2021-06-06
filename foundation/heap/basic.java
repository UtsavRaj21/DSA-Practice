public class basic {

    public static int height(int[]arr ,int pi){
        if(pi>arr.length){
            return 0;
        }

        int lh=height(arr,2*pi+1);
        int rh=height(arr,2*pi+2);

        return Math.max(lh,rh) +1;
    }
    public static void main(String[] args) {
        int[] arr={0,1,2,3,4,5,6,7,8,9,10,11,12,13};
    }
}
