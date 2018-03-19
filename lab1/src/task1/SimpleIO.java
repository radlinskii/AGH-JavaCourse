package task1;

import java.util.Scanner;

public class SimpleIO {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String s = scan.next();
        int i = scan.nextInt();
        System.out.printf("%s , %d Written\n",s,i);
    }

}
