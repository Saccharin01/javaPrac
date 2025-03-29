package greeting;

import java.util.Scanner;


public class Main{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("사용자의 언어를 입력하세요. (ko,en,jp) : ");
        String userInput = scanner.nextLine();

        new Greeting(userInput);
    }
        }