package BlackJack;

public class Msg {
    public static void showOpeningMsg() {
        System.out.println("*********************************");
        System.out.println("********  ブラックジャック  *******");
        System.out.println("*********************************");
        System.out.println();
        System.out.println("ゲーム開始！");
        System.out.println();
    }

    public static void showBeginGameMsg() {
        System.out.println("*********************************");
        System.out.println("カードを配ります。");
        System.out.println();
    }

    public static void hitOrStandMsg() {
        System.out.println("カードをもう一枚引きますか？ ( Y / N )");
    }

    public static void finishMsg() {
        System.out.println("終了しますか？ ( Y / N )");
    }

    public static void startAgain(){
        System.out.println("最初から開始します。");
        System.out.println();
    }

    public static void gameFinish(){
        System.out.print("終了します。");
    }


    public static void totalJudgeMsg(int win,int lose,int draw) {
        System.out.println("通算勝敗");
        System.out.println("勝ち：" + win + "回");
        System.out.println("負け：" + lose + "回");
        System.out.println("引分：" + draw + "回");
        System.out.println();
    }




}
