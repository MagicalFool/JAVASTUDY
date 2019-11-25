public class TestClass {
    public static void main(String[] args) {
        new TestClass().aum(1);
    }

    public int aum(int i) {
        System.out.println(i++);
        if (i <1000){
            aum(i);
            return i;
        }else {
            return -1;
        }

    }
}
