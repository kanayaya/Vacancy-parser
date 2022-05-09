package Utils;

import java.util.HashMap;

public class LayoutChanger {
    private static final String rusLayout    = "ё1234567890-=йцукенгшщзхъ\\фывапролджэячсмитьбю.Ё!\"№;%:?*()_+ЙЦУКЕНГШЩЗХЪ/ФЫВАПРОЛДЖЭЯЧСМИТЬБЮ,";
    private static final String qwertyLayout = "`1234567890-=qwertyuiop[]\\asdfghjkl;'zxcvbnm,./~!@#$%^&*()_+QWERTYUIOP{}|ASDFGHJKL:\"ZXCVBNM<>?";

    public static String changeLayout(String text) {
        String[] words = text.split(" ");
        String newText = "";
        for (String word: words) {
            if (isMadeWith(word, rusLayout)) {
                newText = newText.concat(" " + convertWithMap(word, makeMap(rusLayout, qwertyLayout)));
            } else if (isMadeWith(word, qwertyLayout)){
                newText = newText.concat(" " + convertWithMap(word, makeMap(qwertyLayout, rusLayout)));
            }
        }
        return newText;
    }
    private static HashMap<Character, Character> makeMap(String fromLayout, String toLayout) {
        HashMap<Character, Character> map = new HashMap<>();
        for (int i = 0; i < fromLayout.length(); i++) {
            map.put(fromLayout.toCharArray()[i], toLayout.toCharArray()[i]);
        }
        return map;
    }
    private static String convertWithMap(String text, HashMap<Character, Character> map) {
        String convertedWord = "";
        for (char symbol: text.toCharArray()) {
            String newSymbol = map.get(symbol).toString();
            convertedWord = convertedWord.concat(newSymbol);
        }
        return convertedWord;
    }
    private static boolean isMadeWith(String text, String layout) {
        for (char symbol: text.toCharArray()) {
            if (!isInLayout(symbol, layout)) return false;
        }
        return true;
    }
    private static boolean isInLayout(char symbol, String layout) {
        for (char key: layout.toCharArray()) {
            if (symbol==key) return true;
        }
        return false;
    }
}
