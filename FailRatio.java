import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

class FailRatio {

    static class Fail {
        public int stage;
        public double rate;

        Fail(int num, double ratio) {
            this.stage = num;
            this.rate = ratio;
        }
    }

    static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++)
            System.out.print(arr[i] + " ");
        System.out.println();
    }

    static Comparator comp = new Comparator<Fail>() {

        @Override
        public int compare(Fail o1, Fail o2) {

            if (o1.rate < o2.rate) {
                return 1;
            } else if (o1.rate > o2.rate) {
                return -1;
            } else {
                if (o1.stage > o2.stage) {
                    return 1;
                } else if (o1.stage < o2.stage) {
                    return -1;
                } else {
                    return 0;
                }
            }
        }

    };

    static int[] failRate(int size, int[] arr) {

        ArrayList<Fail> failList = new ArrayList<>();

        int[] tryings = new int[size + 1];
        for (int i = 0; i < arr.length; i++) {
            tryings[arr[i] - 1]++;
        }

        int total = 0;
        for (int i = 0; i < size + 1; i++) {
            total += tryings[i];
        }

        for (int i = 0; i < size; i++) {
            double rate = (double) tryings[i] / total;
            failList.add(new Fail(i + 1, rate));
            total = total - tryings[i];
        }
        Collections.sort(failList, comp);

        int i = 0;
        int[] answer = new int[size];
        for (Fail f : failList) {
            answer[i] = f.stage;
            i++;
        }
        return answer;

    }

    public static void main(String[] args) {

        int[] q1 = { 2, 1, 2, 6, 2, 4, 3, 3 };
        printArray(failRate(5, q1));
        int[] q2 = { 4, 4, 4, 4, 4 };
        printArray(failRate(4, q2));
    }
}