package BlackJack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;

public class BlackJack {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    private final CardStuck cardStuck = new CardStuck();

    //ディーラーのカードは17以上で判定される
    private int DEALER_CARD = 17;

    //ブラックジャック
    private int BLACK_JACK = 21;

    /**
     * プレーヤーの手札
     */
    private List<Card> playerCards;
    /**
     * ディーラーの手札
     */
    private List<Card> dealerCards;

    /**
     * 通算勝敗の記録
     */
    private int win = 0;
    private int lose = 0;
    private int draw = 0;


    /**
     * ゲーム開始
     */
    public void Begin() throws IOException {

        //開始メッセージ
        Msg.showOpeningMsg();

        while (true) {
            //カード配布のメッセージ
            Msg.showBeginGameMsg();
            //プレイヤー、ディーラーにカードを2枚ずつ配る
            deliveryPlayerCard();
            deliveryDealerCard();

            //テスト
           /* playerCards.set(0, new Card(34));
            playerCards.set(1, new Card(35));
            //playerCards.set(2, new Card(26));
            //playerCards.set(3, new Card(50));

            dealerCards.set(0, new Card(47));
            dealerCards.set(1, new Card(48));*/
            //dealerCards.set(2, new Card(38));
            //dealerCards.set(3, new Card(51));

            //カードをS1~S13,H1~H13,D1~D13,C1~C13の順で並び替える
            sortPlayerCard();
            sortDealerCard();

            //手持ちのカードを画面に表示する
            showPlayerCards();
            showDealerCards();


            //プレイヤーがカードを引くか選択する(ストップするかバーストするまで繰り返し)
            try {
                inputHitOrStand();
            } catch (IOException e) {
                e.printStackTrace();
            }

            //ディーラーがカードを引く
            dealersCardMin();

            //勝敗の表示
            showWinOrLose();

            //通算勝敗の表示
            Msg.totalJudgeMsg(win, lose, draw);

            //終了するかの確認
            Msg.finishMsg();

            //入力の受付
            String finish = reader.readLine();
            if (!finish.equals("Y")) {
                //最初から開始
                Msg.startAgain();
                //カード山の初期化
                resetCard();

            } else {
                //Yと入力→終了
                Msg.gameFinish();
                break;
            }
        }
    }

    //カードの山を初期化
    private void resetCard(){
        cardStuck.createNewStack();
    }

    /**
     * プレイヤーの手札にカードを2枚配る
     */
    private void deliveryPlayerCard() {
        playerCards = cardStuck.takeCards(2);
    }

    /**
     * ディーラーの手札にカードを2枚配る
     */
    private void deliveryDealerCard() {
        dealerCards = cardStuck.takeCards(2);
    }

    /**
     * プレイヤーのカードをS1~S13,H1~H13,D1~D13,C1~C13の順で並び替える
     */
    private void sortPlayerCard() {
        Collections.sort(playerCards);
    }

    /**
     * ディーラーのカードをS1~S13,H1~H13,D1~D13,C1~C13の順で並び替える
     */
    private void sortDealerCard() {
        Collections.sort(dealerCards);
    }

    /**
     * プレイヤーのカードを出力する
     */
    private void showPlayerCards() {
        System.out.print("プレイヤーカード：");
        for (int i = 0; i < playerCards.size(); i++) {
            System.out.print(playerCards.get(i).getDisplayString() + " ");
        }
        System.out.println();
        System.out.println();
    }

    /**
     * ディーラーのカードを出力する
     */
    private void showDealerCards() {
        System.out.print("ディーラーカード：");
        for (int i = 0; i < dealerCards.size(); i++) {
            if (i == 1) {
                System.out.print("* ");
            } else {
                System.out.print(dealerCards.get(i).getDisplayString() + " ");
            }
        }
        System.out.println();
        System.out.println();
    }

    /**
     * カードをヒットするかスタンドするか
     */
    public void inputHitOrStand() throws IOException {
        //プレイヤーがヒットかスタンドかを選択する
        while (true) {
            //メッセージ出力
            Msg.hitOrStandMsg();
            //入力の受付
            String choose = reader.readLine();
            if (!choose.equals("Y")) {
                //Y以外を入力→カードを引かない
                //while文を抜ける
                break;

            } else {
                //Y(N以外)を入力→カードをリストに追加、合計値が21以上ならバースト
                Card card = cardStuck.takeCard();
                playerCards.add(card);
                Hand playerHand = new Hand(playerCards);
                if (!playerHand.isBurst()) {
                    //プレイヤーの手持ちのカードを画面に表示する
                    showPlayerCards();
                } else if (playerHand.isBurst()) {
                    //バースト
                    System.out.println("バースト");
                    break;
                }
            }
        }
    }

    /**
     * ディーラーの手札が17以上になるまでカードを引く
     */
    public void dealersCardMin() {
        while (true) {
            Hand dealerHand = new Hand(dealerCards);

            //ディーラーがカードを引く(17以上になるまで)
            if (dealerHand.getHandPoint() < DEALER_CARD) {
                //カードを1枚引く
                Card card = cardStuck.takeCard();
                //リストに追加
                dealerCards.add(card);
            } else {
                break;
            }
        }
    }

    /**
     * 勝敗を表示する
     * バースト -> 数の大小 -> 枚数 の順
     * 結果に応じて変数をカウントアップする
     */
    public void showWinOrLose() {
        Hand playerHand = new Hand(playerCards);
        Hand dealerHand = new Hand(dealerCards);

        int playerPoint = playerHand.getHandPoint();
        int dealerPoint = dealerHand.getHandPoint();

        showPlayerCards();
        showDealerCards();

        //バースト
        //プレイヤーがバースト：ディーラーの勝ち
        if (playerHand.isBurst()) {
            System.out.println("負け");
            lose++;
            //ディーラーがバースト：プレイヤーの勝ち
        } else if (dealerHand.isBurst()) {
            System.out.println("勝ち");
            win++;
            //プレイヤーとディーラー両方がバースト：ディーラーの勝ち
        } else if (playerHand.isBurst() && dealerHand.isBurst()) {
            System.out.println("負け");
            lose++;

            //数の大小
            //プレイヤーの方が21に近い：プレイヤーの勝ち
        } else if (playerPoint > dealerPoint) {
            System.out.println("勝ち");
            win++;
            //ディーラーの方が21に近い：ディーラーの勝ち
        } else if (playerPoint < dealerPoint) {
            System.out.println("負け");
            lose++;

            //ブラックジャックではなく、同じ数字で同じ枚数：引き分け
        } else if (playerPoint != BLACK_JACK && playerCards.size() == dealerCards.size()){
            System.out.println("引き分け");
            draw++;
        }

        //両方がブラックジャックのときは枚数で判断
        //プレイヤーの方が枚数が少ない：プレイヤーの勝ち
        if (playerPoint == BLACK_JACK && dealerPoint == BLACK_JACK) {
            if (playerCards.size() < dealerCards.size()) {
                System.out.println("勝ち");
                win++;
                //ディーラーの方が枚数が少ない：ディーラーの勝ち
            } else if (playerCards.size() > dealerCards.size()) {
                System.out.println("負け");
                lose++;
            }
        }

            //最終の点数
            System.out.print("プレイヤー：" + playerPoint + " ");
            if (playerPoint > 21) {
                System.out.println("バースト");
            }
            System.out.println();

            System.out.print("ディーラー：" + dealerPoint + " ");
            if (dealerPoint > 21) {
                System.out.println("バースト");
            }
            System.out.println();


    }
}
