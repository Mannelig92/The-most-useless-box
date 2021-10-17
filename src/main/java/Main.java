public class Main {
    static volatile boolean tumbler;
    final static int TIME_TO_OFF_TUMBLER = 1000;
    static int countOfTurning;
    final static int MAX_TURNS = 5;
    final static int TIME_TO_ON_TUMBLER = 1700;
    static Thread userThread = new Thread(null, Main::user, "Игрок");
    static Thread toyThread = new Thread(null, Main::toy, "Игрушка");

    public static void main(String[] args) {
        userThread.start();
        toyThread.start();
    }

    public static void user() {
        while (countOfTurning != MAX_TURNS) {
            if (!tumbler) {
                tumbler = true;
                System.out.println("Игрок включил тумблер");
                try {
                    Thread.sleep(TIME_TO_ON_TUMBLER);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            countOfTurning++;
        }
        userThread.interrupt();
    }

    public static void toy() {
        while (countOfTurning != MAX_TURNS) {
            if (tumbler) {
                tumbler = false;
                System.out.println("Игрушка выключила тумблер");
                try {
                    Thread.sleep(TIME_TO_OFF_TUMBLER);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        toyThread.interrupt();
    }
}