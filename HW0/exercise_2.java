package HW0;

public class exercise_2 {
    public static int max(int[] m) {
        int max = 0, i = 0;
        while (i < m.length){
            if (m[i] > max) 
                max = m[i];
            ++i;
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
