package BlackJack;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        BlackJack bj = new BlackJack();
        try {
            bj.Begin();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

