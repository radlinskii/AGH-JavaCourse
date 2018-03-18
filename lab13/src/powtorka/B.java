package powtorka;

class B extends A {
    B(){
        super(1);
        f();
    }

    void f(){
        System.out.println("B:");
    }

    A a = new A(2);
}
