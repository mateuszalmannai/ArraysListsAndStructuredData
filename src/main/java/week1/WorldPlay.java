package week1;

public class WorldPlay {

  public boolean isVowel(char ch) {
    boolean result = false;
    String vowels = "aeiou";
    if (vowels.indexOf(ch) != -1) {
      result = true;
    }
    return result;
  }

  public String replaceVowels(String phrase, char ch) {
    StringBuilder result = new StringBuilder(phrase);
    for (int i = 0; i < phrase.length(); i++) {
      if (isVowel(phrase.charAt(i))) {
        result.setCharAt(i, ch);
      }
    }
    return result.toString();
  }

  public String emphasize(String phrase, char ch) {
    StringBuilder result = new StringBuilder(phrase);
    for (int i = 0; i < phrase.length(); i++) {
      if (Character.toLowerCase(phrase.charAt(i)) == ch) {
        if (i % 2 == 0) {
          result.setCharAt(i, '*');
        } else {
          result.setCharAt(i, '+');
        }
      }
    }
    return result.toString();
  }
}
