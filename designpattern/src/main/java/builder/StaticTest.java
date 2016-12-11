package builder;

public class StaticTest {

    public static final int a;
    static {
        a = 3;
        System.out.println(123);
        StaticTest staticTest = new StaticTest();
        System.out.println(staticTest.instanceMethod());
    }

//    StaticTest() {
//        a = 3;
//    }

    public static int staticMethod() {
//        a = 3;
        return a;
    }

    public int instanceMethod() {
//       a = 3;
        return a;
    }

    public static void main(String[] args) {
        System.out.println(StaticTest.a);

        System.out.println(StaticTest.staticMethod());

        StaticTest staticTest = new StaticTest();
        System.out.println(staticTest.instanceMethod());
    }
}
