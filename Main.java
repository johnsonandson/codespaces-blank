    
    public class Main{
    //     public static <T extends Comparable<T>>  

    //     boolean binarySearch(T[] data, int min, int max, T target){
    //         if (min>(max)){
    //             return false;
    //         }
    //         if (target.equals(data[(max+min)/2])){
    //             return true;
    //         }
    //         else if(target.compareTo(data[(max+min)/2])<0){
    //             return (binarySearch(data, min, (max+min)/2-1,target));
    //         }else{
    //             return (binarySearch(data, (min+max)/2+1,max,target));
    //         }

    // }
    public static boolean canFlowOff(int[][] map,int row, int col){
        boolean flow=false;
        if (row+1>map.length-1||row-1<0||col+1>map[0].length-1||col-1<0){
            return true;
        }
        if (map[row][col]>map[row][col+1]){
            flow= canFlowOff(map,row,col+1);
        }

        if (flow){
            return true;
        }
        if (map[row][col]>map[row][col-1]){
            flow= canFlowOff(map,row,col-1);
        }

        if (flow){
            return true;
        }
        if (map[row][col]>map[row+1][col]){
            flow= canFlowOff(map,row+1,col);
        }

        if (flow){
            return true;
        }
        if (map[row][col]>map[row-1][col]){
            flow= canFlowOff(map,row-1,col);
        }
        if (flow){
            return true;
        }
        return false;
    }
        public static void main(String[] args) {
            int[][] map = {
			{100, 99, 200, 200, 200, 200, 200, 200, 200, 200}, 
			{200, 98, 200, 200, 200, 200, 200, 200, 200, 200},
			{200, 97, 96, 200, 200, 200, 200, 200, 200, 200},
			{200, 200, 95, 200, 200, 200, 85, 84, 83, 200},
			{200, 200, 94, 93, 92, 200, 86, 200, 82, 200},
			{200, 150, 200, 200, 91, 200, 87, 200, 81, 200},
			{200, 200, 200, 200, 90, 89, 88, 200, 80, 200},
			{200, 150, 100, 200, 200, 200, 200, 200, 79, 200},
			{200, 200, 200, 200, 200, 200, 200, 200, 78, 77},
			{200, 98, 200, 200, 200, 200, 200, 200, 200, 76}};
            
            if (canFlowOff(map,1,3)){
                System.out.println("yep");
            }
            else{
                System.out.println("nah");
            }
        }
    }