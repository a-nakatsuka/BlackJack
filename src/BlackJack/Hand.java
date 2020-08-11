package BlackJack;


import java.util.List;

public class Hand {

    private final List<Card> _Cards;

    private int _point = 0;
    private boolean _isBurst = false;

    /**
     * コンストラクタ
     */
    public Hand(List<Card> cards) {
        _Cards = cards;
        judgeNumber();
    }

    /**
     * リストに入ったカードの数字を足していく
     */
    public int getHandPoint() {
        //リスト内の数字を足す
        return _point;
    }

    /**
     * バーストの判定
     */
    public boolean isBurst() {
        return _isBurst;
    }

    /**
     * Aを1とするか11とするかの判定
     */
    public void judgeNumber() {
        //1枚目のA以外を合計
        int sum = 0;
        //フラグを立てる(Aがあればtrue)
        boolean ace = false;
        for (int i = 0; i < _Cards.size(); i++) {
            //1枚目のカードで、かつAのとき
            if (ace == false && _Cards.get(i).get_cardNumber() == 1) {
                //フラグを立てて加算しない
                ace = true;
            } else {
                //A以外を加算
                int point = _Cards.get(i).get_cardNumber();
                if (point > 10) {
                    point = 10;
                }
                sum += point;
            }
        }

        if (ace == true) {
            //1枚目がA,2枚目もAだった時
            int sum1 = sum + 1;
            int sum11 = sum + 11;

            //2枚目が1でバースト、11でバーストのとき
            if (sum1 > 21 && sum11 > 21) {
                //数が小さい方
                sum = sum1;

                //2枚目が1でバーストせず、11でバーストのとき
            } else if (sum1 < 22 && sum11 > 21) {
                //1の方
                sum = sum1;

                //2枚目が1でバーストせず、11でもバーストしないとき
            } else {
                //数が大きい(21に近い)方
                sum = sum11;
            }
        }
        _point = sum;

        if (_point < 22) {
            _isBurst = false;
        } else {
            //バーストしてたらtrue
            _isBurst = true;

        }


    }

}

