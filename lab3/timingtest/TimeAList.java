package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeAList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeAListConstruction();
    }

    public static void timeAListConstruction() {
        // TODO: YOUR CODE HERE
        AList<Integer> Ns = new AList<>();
        AList<Double> times = new AList<>();
        AList<Integer> opCounts = new AList<>();

        int n = 1;
        for (int i = 0; i < 8; ++i){
            Ns.addLast(1000 * n);
            opCounts.addLast(1000 * n);
            Stopwatch sw = new Stopwatch();
            AList<Integer> MyList = new AList<>();
            for (int j = 0; j < n * 1000; ++j){
                MyList.addLast(0);
            }
            double timeInSeconds = sw.elapsedTime();
            times.addLast(timeInSeconds);
            n *= 2;
        }
        printTimingTable(Ns, times, opCounts);
    }
}
