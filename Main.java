    
    public class Main{
	
	    public static boolean canFlowOff(int[][] map,int row, int col){
			if (map.length<=row||map[0].length<=col||row<0||col<0){
				return false;
			}
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
			if (canFlowOff(map,11,3)){
                System.out.println("yep");
            }
            else{
                System.out.println("nah");
            }
			if (canFlowOff(map,1,3)){
                System.out.println("yep");
            }
            else{
                System.out.println("nah");
            }
			map = {
			{100, 200, 200, 200, 200, 200, 200, 200, 200, 200}, 
			{200, 200, 200, 200, 200, 200, 200, 200, 200, 200},
			{200, 10, 200, 200, 200, 200, 200, 200, 200, 200},
			{200, 11, 10, 200, 200, 6, 85, 84, 83, 200},
			{200, 200, 14, 15, 59, 200, 86, 200, 82, 200},
			{200, 11, 12, 200, 200, 200, 87, 200, 81, 200},
			{200, 10, 200, 200, 90, 89, 88, 200, 200, 200},
			{200, 9, 8, 200, 200, 200, 200, 200, 200, 200},
			{200, 200, 7, 200, 200, 200, 200, 200, 200, 200},
			{200, 98, 6, 200, 200, 200, 200, 200, 200, 200}
			};

            if (canFlowOff(map,4,5)){
                System.out.println("yep");
            }
            else{
                System.out.println("nah");
            }
        }
    }
