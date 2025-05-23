package com.shr4pnel.casino.util;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.shr4pnel.casino.base.Card;
import com.shr4pnel.casino.base.Player;
import com.shr4pnel.casino.slots.SlotsFruit;
import com.shr4pnel.casino.style.StyleManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class AsciiArt {
    private Map<String, Integer> suitToRow = new HashMap<>();
    private Map<String, Integer> cardTypeToColumn = new HashMap<>();

    private Map<Fruits, Integer> fruitIndex = new HashMap<>();

    public enum Fruits {
        DIAMOND,
        CHERRY,
        HORSESHOE,
        BELL,
        SEVEN,
        LEMON,
        PLUM,
        ORANGE,
        HEART
    }

    public AsciiArt() {
        suitToRow.put("hearts", 0);
        suitToRow.put("diamonds", 12);
        suitToRow.put("spades", 24);
        suitToRow.put("clubs", 36);

        cardTypeToColumn.put("Ace", 0);
        cardTypeToColumn.put("2", 15);
        cardTypeToColumn.put("3", 30);
        cardTypeToColumn.put("4", 45);
        cardTypeToColumn.put("5", 60);
        cardTypeToColumn.put("6", 75);
        cardTypeToColumn.put("7", 90);
        cardTypeToColumn.put("8", 105);
        cardTypeToColumn.put("9", 120);
        cardTypeToColumn.put("10", 135);
        cardTypeToColumn.put("Jack", 150);
        cardTypeToColumn.put("Queen", 165);
        cardTypeToColumn.put("King", 180);

        fruitIndex.put(Fruits.DIAMOND, 0);
        fruitIndex.put(Fruits.CHERRY, 1);
        fruitIndex.put(Fruits.SEVEN, 2);
        fruitIndex.put(Fruits.PLUM, 3);
        fruitIndex.put(Fruits.ORANGE, 4);
        fruitIndex.put(Fruits.LEMON, 5);
        fruitIndex.put(Fruits.BELL, 6);
        fruitIndex.put(Fruits.HEART, 7);
        fruitIndex.put(Fruits.HORSESHOE, 8);

    }

    private final String[] cigarette = {
            "                      ((\\",
            "(              _  ,-_  \\ \\",
            ")             / \\/  \\ \\ \\ \\",
            "(            /)| \\/\\ \\ \\| |",
            "`~()_______)___)\\ \\ \\ \\ \\ |",
            "            |)\\ )  `' | | |",
            "           /  /,          |",
            "           |  |          /",
            "           |  |         /",
            "           \\           /",
            "            \\         /",
            "             )       /",
            "            /       /",
            "           /       /",
            "                  /",
            "     KILL EM ALL 2009",
    };

    private final String cards = """
            /-------------\\/-------------\\/-------------\\/-------------\\/-------------\\/-------------\\/-------------\\/-------------\\/-------------\\/-------------\\/-------------\\/-------------\\/-------------\\
            |A            ||2            ||3            ||4            ||5            ||6            ||7            ||8            ||9            ||10           ||J            ||Q            ||K            |
            |             ||             ||             ||     <3      ||  <3     <3  ||  <3     <3  ||  <3     <3  ||  <3     <3  ||  <3     <3  ||  <3     <3  ||             ||             ||             |
            |             ||             ||     <3      ||             ||             ||             ||     <3      ||             ||             ||     <3      ||    _  _     ||    _  _     ||    _  _     |
            |  ."". ."".  ||     <3      ||             ||     <3      ||             ||             ||             ||  <3     <3  ||  <3     <3  ||  <3     <3  ||   ( `' )    ||   ( `' )    ||   ( `' )    |
            |  |   '   |  ||             ||     <3      ||             ||     <3      ||  <3     <3  ||  <3     <3  ||             ||     <3      ||             ||    `.,'     ||    `.,'     ||    `.,'     |
            |   \\     /   ||             ||             ||             ||             ||             ||             ||             ||             ||             ||    JACK     ||    QUEEN    ||    KING     |
            |    '. .'    ||     <3      ||     <3      ||     <3      ||             ||             ||             ||  <3     <3  ||  <3     <3  ||  <3     <3  ||             ||             ||             |
            |      '      ||             ||             ||             ||  <3     <3  ||  <3     <3  ||  <3     <3  ||             ||             ||     <3      ||             ||             ||             |
            |             ||             ||             ||     <3      ||             ||             ||             ||  <3     <3  ||  <3     <3  ||  <3     <3  ||             ||             ||             |
            |            A||            2||            3||            4||            5||            6||            7||            8||            9||           10||            J||            Q||            K|
            \\-------------/\\-------------/\\-------------/\\-------------/\\-------------/\\-------------/\\-------------/\\-------------/\\-------------/\\-------------/\\-------------/\\-------------/\\-------------/
            /-------------\\/-------------\\/-------------\\/-------------\\/-------------\\/-------------\\/-------------\\/-------------\\/-------------\\/-------------\\/-------------\\/-------------\\/-------------\\
            |A            ||2            ||3            ||4            ||5            ||6            ||7            ||8            ||9            ||10           ||J            ||Q            ||K            |
            |     /\\      ||             ||             ||     <>      ||  <>     <>  ||  <>     <>  ||  <>     <>  ||  <>     <>  ||  <>     <>  ||  <>     <>  ||             ||             ||             |
            |    /  \\     ||             ||     <>      ||             ||             ||             ||     <>      ||             ||             ||     <>      ||     /\\      ||     /\\      ||     /\\      |
            |   /    \\    ||     <>      ||             ||     <>      ||             ||             ||             ||  <>     <>  ||  <>     <>  ||  <>     <>  ||    <  >     ||    <  >     ||    <  >     |
            |  /      \\   ||             ||     <>      ||             ||     <>      ||  <>     <>  ||  <>     <>  ||             ||     <>      ||             ||     \\/      ||     \\/      ||     \\/      |
            |  \\      /   ||             ||             ||             ||             ||             ||             ||             ||             ||             ||    JACK     ||    QUEEN    ||    KING     |
            |   \\    /    ||     <>      ||     <>      ||     <>      ||             ||             ||             ||  <>     <>  ||  <>     <>  ||  <>     <>  ||             ||             ||             |
            |    \\  /     ||             ||             ||             ||  <>     <>  ||  <>     <>  ||  <>     <>  ||             ||             ||     <>      ||             ||             ||             |
            |     \\/      ||             ||             ||     <>      ||             ||             ||             ||  <>     <>  ||  <>     <>  ||  <>     <>  ||             ||             ||             |
            |            A||            2||            3||            4||            5||            6||            7||            8||            9||           10||            J||            Q||            K|
            \\-------------/\\-------------/\\-------------/\\-------------/\\-------------/\\-------------/\\-------------/\\-------------/\\-------------/\\-------------/\\-------------/\\-------------/\\-------------/
            /-------------\\/-------------\\/-------------\\/-------------\\/-------------\\/-------------\\/-------------\\/-------------\\/-------------\\/-------------\\/-------------\\/-------------\\/-------------\\
            |A            ||2            ||3            ||4            ||5            ||6            ||7            ||8            ||9            ||10           ||J            ||Q            ||K            |
            |             ||             ||             ||     {>      ||  {>     {>  ||  {>     {>  ||  {>     {>  ||  {>     {>  ||  {>     {>  ||  {>     {>  ||             ||             ||             |
            |             ||             ||     {>      ||             ||             ||             ||     {>      ||             ||             ||     {>      ||    ,'`.     ||    ,'`.     ||    ,'`.     |
            |     /\\      ||     {>      ||             ||     {>      ||             ||             ||             ||  {>     {>  ||  {>     {>  ||  {>     {>  ||   (_,._)    ||   (_,._)    ||   (_,._)    |
            |    /  \\     ||             ||     {>      ||             ||     {>      ||  {>     {>  ||  {>     {>  ||             ||     {>      ||             ||     /\\      ||     /\\      ||     /\\      |
            |   (_,._)    ||             ||             ||             ||             ||             ||             ||             ||             ||             ||    JACK     ||    QUEEN    ||    KING     |
            |     /\\      ||     {>      ||     {>      ||     {>      ||             ||             ||             ||  {>     {>  ||  {>     {>  ||  {>     {>  ||             ||             ||             |
            |             ||             ||             ||             ||  {>     {>  ||  {>     {>  ||  {>     {>  ||             ||             ||     {>      ||             ||             ||             |
            |             ||             ||             ||     {>      ||             ||             ||             ||  {>     {>  ||  {>     {>  ||  {>     {>  ||             ||             ||             |
            |            A||            2||            3||            4||            5||            6||            7||            8||            9||           10||            J||            Q||            K|
            \\-------------/\\-------------/\\-------------/\\-------------/\\-------------/\\-------------/\\-------------/\\-------------/\\-------------/\\-------------/\\-------------/\\-------------/\\-------------/
            /-------------\\/-------------\\/-------------\\/-------------\\/-------------\\/-------------\\/-------------\\/-------------\\/-------------\\/-------------\\/-------------\\/-------------\\/-------------\\
            |A            ||2            ||3            ||4            ||5            ||6            ||7            ||8            ||9            ||10           ||J            ||Q            ||K            |
            |             ||             ||             ||     -%      ||  -%     -%  ||  -%     -%  ||  -%     -%  ||  -%     -%  ||  -%     -%  ||  -%     -%  ||      _      ||      _      ||      _      |
            |     _-_     ||             ||     -%      ||             ||             ||             ||     -%      ||             ||             ||     -%      ||    _(_)_    ||    _(_)_    ||    _(_)_    |
            |   _/   \\_   ||     -%      ||             ||     -%      ||             ||             ||             ||  -%     -%  ||  -%     -%  ||  -%     -%  ||   (_)+(_)   ||   (_)+(_)   ||   (_)+(_)   |
            |  /  \\ /  \\  ||             ||     -%      ||             ||     -%      ||  -%     -%  ||  -%     -%  ||             ||     -%      ||             ||      |      ||      |      ||      |      |
            |  \\__/ \\__/  ||             ||             ||             ||             ||             ||             ||             ||             ||             ||    JACK     ||    QUEEN    ||    KING     |
            |     / \\     ||     -%      ||     -%      ||     -%      ||             ||             ||             ||  -%     -%  ||  -%     -%  ||  -%     -%  ||             ||             ||             |
            |    /___\\    ||             ||             ||             ||  -%     -%  ||  -%     -%  ||  -%     -%  ||             ||             ||     -%      ||             ||             ||             |
            |             ||             ||             ||     -%      ||             ||             ||             ||  -%     -%  ||  -%     -%  ||  -%     -%  ||             ||             ||             |
            |            A||            2||            3||            4||            5||            6||            7||            8||            9||           10||            J||            Q||            K|
            \\-------------/\\-------------/\\-------------/\\-------------/\\-------------/\\-------------/\\-------------/\\-------------/\\-------------/\\-------------/\\-------------/\\-------------/\\-------------/
            """;

    private final String cardBack = """
            /-------------\\
            |              |
            |              |
            |              |
            |              |
            |     :)       |
            |              |
            |              |
            |              |
            |              |
            |              |
            \\-------------/{EVENT=bjdeal_complete}
            """;
    private final String[] fruitIcon = {
            """

                     xxxxxxx
                    x       x
                    xxxxxxxxx
                     x     x
                      x   x
                       x x
                        x

                           """, """

                         x
                        x x
                       xx  x
                      x     x
                     xxx   xxx
                    xxxxx xxxxx
                     xxx   xxx

                            """, """

                    xxx xxxxx
                    xxxxxxxxx
                    xx   xxxx
                        xxx
                        xxxx
                        xxxx
                        xxxx

                             """, """

                    xx  xx
                     xxxx
                        xxx
                       xxxxx
                       xxxxxx
                        xxxxx
                          xx

                            """, """
                        x
                      xxxxx
                     xxxxxxx
                    xxxxxxxxx
                    xxxxxxxxx
                     xxxxxxx
                      xxxxx
                            """, """

                       xxxx
                     xxxxxx
                    xxxxxxx
                    xxxxxx
                    xxxx


                           """, """

                       xx
                      x  x
                     xxxxxx
                     x    x
                    xxxxxxxx
                    xxxxxxxx
                       xx

                            """, """

                     xx xx
                    x  x  x
                    x     x
                     x   x
                      xxx
                       x
                             """, """

                        xx
                      xxxxxx
                     xx    xx
                     xx    xx
                     xx    xx
                    xxxx  xxxx

                       """

    };

    public Label[] artAsTypingLabelArray() {
        ArrayList<Label> labelList = new ArrayList<>();
        for (String s : cigarette) {
            Label label = new Label(s, StyleManager.getSkin());
            labelList.add(label);
        }
        return labelList.toArray(Label[]::new);
    }

    // what a mess. someone else can document this. -t
    public String getCard(Card c) {
        StringBuilder sb = new StringBuilder();
        String type = c.getCardType();
        String suit = c.getSuit();

        int column = cardTypeToColumn.get(type);
        int row = suitToRow.get(suit);

        String[] lines = cards.split("\n");

        for (int i = row; i < row + 12; i++) {
            sb.append("{SPEED=15}");
            if (i < lines.length) {
                String line = lines[i];
                if (column + 15 <= line.length()) {
                    sb.append(line, column, column + 15);
                } else {
                    sb.append(line.substring(column));
                }
                sb.append("\n");
            }
        }
        sb.append("{RESET}");
        return sb.toString();
    }

    public String[] getCards(List<Card> hand) {
        List<String> playerCards = new ArrayList<>();
        for (Card c : hand) {
            playerCards.add(getCard(c));
        }
        return playerCards.toArray(String[]::new);
    }

    public String[] getCards(Player p) {
        List<Card> hand = p.getPlayerHand();
        List<String> playerCards = new ArrayList<>();
        for (Card c : hand) {
            playerCards.add(getCard(c));
        }
        return playerCards.toArray(String[]::new);
    }

    public String getCardBack() {
        return "{SPEED=15}" + cardBack + "{RESET}";
    }

    public String getFruit(Fruits fruit) {
        return "{SPEED=15}" + fruitIcon[fruitIndex.get(fruit)];
    }

    public String getFruit(Fruits fruit, String speed) {
        return "{SPEED=" + speed + "}" + fruitIcon[fruitIndex.get(fruit)];
    }

    public Fruits getRanFruit() {
        int ranNumb = ThreadLocalRandom.current().nextInt(Fruits.values().length);
        return Fruits.values()[ranNumb];
    }
}