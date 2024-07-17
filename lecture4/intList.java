package Lecture4;

public class intList {
        public int first;
        public intList rest;

        public intList(int f, intList r) {
            first = f;
            rest = r;
        }

        /** Returns an IntList identical to L, but with all values incremented by x using recursion.*/
        /**Values in L not change!*/
         public static intList incrList(intList L, int x) {
            intList list = new intList(L.first, L.rest);
            return intListHelper(list, x, 0);
        }

        public static intList intListHelper(intList original, int x, int index){
             if (original.size() == 1){
                 return new intList(original.first + x, null);
             }
             return new intList(original.first + x, intListHelper(original.rest, x, index + 1));
        }

        /**Returns an IntList identical to L, but with all values incremented by x using recursion*/
        /**not using 'new'*/
        public static intList dincrList(intList L, int x){
            intList Q = L;
            dicrListHelper(Q, x, 0);
            return Q;
        }

        public static void dicrListHelper(intList original, int x, int index){
            if (original.size() == 1){
                original.first = original.first + x;
                return;
            }
            original.first = original.first + x;
            dicrListHelper(original.rest, x, index + 1);
        }

        /** Return the size of the list using... recursion! */
        public int size() {
            if (rest == null) {
                return 1;
            }
            return 1 + this.rest.size();
        }

        /** Return the size of the list using no recursion! */
        public int iterativeSize() {
            intList p = this;
            int totalSize = 0;
            while (p != null) {
                totalSize += 1;
                p = p.rest;
            }
            return totalSize;
        }

        /** Returns the ith item of this IntList. */
        public int get(int i) {
            if (i == 0) {
                return first;
            }
            return rest.get(i - 1);
        }

        public static void main(String[] args) {
            intList L = new intList(15, null);
            L = new intList(10, L);
            L = new intList(5, L);

            intList L1 = new intList(15, null);
            L1 = new intList(10, L1);


            intList incrL = intList.incrList(L, 1);
            for (int i = 0; i < incrL.size(); i++){
                System.out.println(incrL.get(i));
            }

            intList incrL1 = intList.incrList(L1, 1);
            for (int i = 0; i < incrL1.size(); i++){
                System.out.println(incrL1.get(i));
            }
        }
}
