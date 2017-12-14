import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        double a [] = {1,2};
        InsertToTable dc = (w,b,c) -> {
            w[0]=b;
            w[1]=c;
        };

        dc.assign(a,4,45);
        System.out.println(a[0] +  " "+a[1]);

        List<String> oo = new ArrayList<>();
        oo.add("pierwszy ");
        oo.add("drugi ");
        System.out.println(oo);
        oo.replaceAll(p->p.toUpperCase());
        System.out.println(oo);

    }
}
