public class B {

    private A a;

    public B() {
        System.out.println("BBB创建完成,准备初始化...");
    }

    public A getA() {
        return a;
    }

    public void setA(A a) {
        this.a = a;
    }
}
