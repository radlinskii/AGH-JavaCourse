package task3;

import java.util.Scanner;

public class Problem610A {
    public static void main(String[] args){

        Scanner scan = new Scanner(System.in);
        int stickLength = scan.nextInt();
        System.out.printf("%d Written\n",stickLength);

        int count = 0;
        if(stickLength % 2 == 0){
            for (int i = 1; i <= stickLength / 4; i++) {
                count++;
            }
        }
        if(stickLength % 4 == 0)
            count--;
        System.out.println(count);
    }
}
