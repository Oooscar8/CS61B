package HW0;

public class exercise_3 {
    public static int max(int[] m) {
        int max = 0;
        for (int i=0; i < m.length; ++i){
            if (m[i] > max) 
                max = m[i];
        }
        if (max > 0)
            return max;
        return 0;
    }
    public static void main(String[] args) {
       int[] numbers = new int[]{9, 2, 15, 2, 22, 10, 6};
       System.out.println(max(numbers));      
    }
}
