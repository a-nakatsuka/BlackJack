package BlackJack;

import java.util.ArrayList;
import java.util.List;

public class Card implements Comparable<Card> {
    /**
     * カードマークの画面表示文字列格納リスト
     * S:0 H:1 H:2 C:3
     */
    private static final List<String> CARD_MARK = new ArrayList<String>() {{
        // S,H,D,Cの文字列を設定
        add("S");
        add("H");
        add("D");
        add("C");
    }};

    /**
     * 同一マークのカード枚数 1~13
     */
    public static final int CARD_NUMBER = 13;

    /**
     * 全カード枚数
     */
    public static final int CARD_ALL_NUMBER = 52;


    /**
     * 0~51の番号をもらってS1~C13を返す
     *
     * @param cardIndex 0~51の番号
     * @return S1~C13の文字列
     */
    public static String convertCardIndexToDisplayString(int cardIndex) {
        //カードマーク部分のIndexを取得
        int markIndex = getCardMarkNumber(cardIndex);
        //カードの番号部分を取得
        int cardNumber = getCardNumber(cardIndex);
        String dispNumber = "";
        dispNumber = String.valueOf((cardNumber));

        //1:A 11:J 12:Q 13:K
        //他の数字は型を変換してそのまま出力
        if (cardNumber == 1) {
            dispNumber = "A";
        } else if (cardNumber == 2 || cardNumber == 3 || cardNumber == 4 || cardNumber == 5 || cardNumber == 6 || cardNumber == 7 || cardNumber == 8 || cardNumber == 9 || cardNumber == 10) {
            dispNumber = String.valueOf(cardNumber);
        } else if (cardNumber == 11) {
            dispNumber = "J";
        } else if (cardNumber == 12) {
            dispNumber = "Q";
        } else if (cardNumber == 13) {
            dispNumber = "K";

        }

        // 画面表示用文字列を返す
        return CARD_MARK.get(markIndex) + dispNumber;
    }


    /**
     * 0~51の数字を受け取り、S,H,D,Cのマーク文字列に変換する
     *
     * @param cardIndex 0~51の数字
     * @return S, H, D, Cのマークに対応する数値
     */
    public static int getCardMarkNumber(int cardIndex) {
        // カードマークを表す数字(0~3)を戻り値として返す
        return cardIndex / CARD_NUMBER;
    }


    /**
     * 0~51の数字を受け取り、1~13のカード番号に変換する
     *
     * @param cardIndex 0~51の数字
     * @return 1~13
     */
    public static int getCardNumber(int cardIndex) {
        // カード番号を表す数値を戻り値として返す
        return cardIndex % CARD_NUMBER + 1;
    }


    private int _cardIndex;
    private int _cardMark;
    private int _cardNumber;

    /**
     * カードIndex(0~51)の取得
     *
     * @return 0~51の数値
     */
    public int get_cardIndex() {
        return _cardIndex;
    }

    /**
     * @return S, H, D, Cの文字列
     */
    public int get_cardMark() {
        return _cardMark;
    }

    /**
     * @return 1~13のカード番号
     */
    public int get_cardNumber() {
        return _cardNumber;
    }

    /**
     * 画面に表示するカード文字列
     *
     * @return 例：S2 H13
     */
    public String getDisplayString() {
        return Card.convertCardIndexToDisplayString(_cardIndex);
    }

    /**
     * コンストラクタ
     *
     * @param cardIndex 0~51の数字
     */
    public Card(int cardIndex) {
        this._cardIndex = -1;
        this._cardIndex = cardIndex;
        _cardMark = -1;
        _cardMark = Card.getCardMarkNumber(cardIndex);
        _cardNumber = -1;
        _cardNumber = Card.getCardNumber(cardIndex);
    }

    @Override
    public int compareTo(Card o) {
        return this.get_cardIndex() - o.get_cardIndex();
    }
}
