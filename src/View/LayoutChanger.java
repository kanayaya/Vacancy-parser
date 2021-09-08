package View;

import java.util.HashMap;

public class LayoutChanger {
    private static final String rusLayout    = "ё1234567890-=йцукенгшщзхъ\\фывапролджэячсмитьбю.Ё!\"№;%:?*()_+ЙЦУКЕНГШЩЗХЪ/ФЫВАПРОЛДЖЭЯЧСМИТЬБЮ,";
    private static final String qwertyLayout = "`1234567890-=qwertyuiop[]\\asdfghjkl;'zxcvbnm,./~!@#$%^&*()_+QWERTYUIOP{}|ASDFGHJKL:\"ZXCVBNM<>?";

    public static String changeLayout(String... texts) {
        String newText = "";
        for (String text: texts) {
            if (isMadeWith(text, rusLayout)) {
                newText = newText.concat(" " + convertWithMap(text, makeMap(rusLayout, qwertyLayout)));
            } else if (isMadeWith(text, qwertyLayout)){
                newText = newText.concat(" " + convertWithMap(text, makeMap(qwertyLayout, rusLayout)));
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
