import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Thread_Yield {

    public static void main(String[] args) throws InterruptedException {
        List<Vo> list = new ArrayList<>(202);
        Runnable runnable = () -> {
            for (int i = 0; i <= 100; i++) {
                Vo vo = new Vo(System.nanoTime(), Thread.currentThread().getName(), i);
                list.add(vo);
                if ( i % 20 == 0 ) {
                    Thread.yield();
                }
            }
        };

        new Thread(runnable, "线程A").start();
        new Thread(runnable, "线程B").start();

        Thread.sleep(3000);
        list.sort(new Order());
        for (Vo vo : list) {
            System.out.println(vo.toString());
        }
    }

    private static class Order implements Comparator<Vo> {

        @Override
        public int compare(Vo o1, Vo o2) {
            return o1.getNanoTime().compareTo(o2.getNanoTime());
        }
    }

    private static class Vo {
        Long nanoTime;
        String name;
        int index;

        public Vo() {}

        public Vo(Long nanoTime, String name, int index) {
            this.nanoTime = nanoTime;
            this.name = name;
            this.index = index;
        }

        @Override
        public String toString() {
            return this.nanoTime+ "--" +this.name+ "--" +this.index;
        }

        public Long getNanoTime() {
            return nanoTime;
        }

        public void setNanoTime(Long nanoTime) {
            this.nanoTime = nanoTime;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }
    }
}
