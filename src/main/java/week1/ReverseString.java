package week1;

public class ReverseString {

  /**
   * Must understand for loop with three parts
   * Separated with semi-colons
   * Initialization (happens once, before guard)
   * Guard, evaluated before loop body
   * Increment, executed after loop body
   * @param string
   * @return
   */
  public String reverseStringWithForLoop(String string) {
    String result = "";
    for (int i = 0; i < string.length(); i++) {
      result = string.charAt(i) + result;

    }
    return result;
  }

  public String reverseStringWithWhileLoop(String string) {
    String result = "";
    int i = 0;
    while (i < string.length()) {
      result = string.charAt(i) + result;
      i += 1;
    }
    return result;
  }
}
