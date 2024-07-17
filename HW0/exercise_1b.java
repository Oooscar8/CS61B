package HW0;

public class exercise_1b {
    public static void drawTriangle(int N) {
        int col = 0;
        int row = 0;
        int SIZE = N;
        while (row < SIZE){
            while (col <= row) {
                System.out.print("*");
                col = col + 1;
            }
            System.out.println();
            row = row + 1;
            col = 0;
        }
    }
    public static void main(String[] args) {
        drawTriangle(10); // draws a triangle with 10 rows
    }
}
