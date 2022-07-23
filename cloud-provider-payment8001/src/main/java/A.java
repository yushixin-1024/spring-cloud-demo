public class A {

    private B b;

    public A() {
        System.out.println("AAA创建完成,准备初始化...");
    }

    public B getB() {
        return b;
    }

    public void setB(B b) {
        this.b = b;
    }
}
