package week2;

import java.util.HashMap;
import java.util.Map;

public class CodonAnalysis {
  private Map<String, Integer> codons;

  public enum READING_FRAME {
    ZERO(0), ONE(1), TWO(2);

    private int value;

    READING_FRAME(int value) {
      this.value = value;
    }

    public int getValue() {
      return value;
    }
  }


  public CodonAnalysis() {
    codons = new HashMap<>();
  }


  /**
   * Method builds a new map of codons mapped to their counts
   * from a string dna with the reading frame with the
   * position start (a value of 0, 1 or 2)
   *
   * @param start
   * @param dna
   */
  public void buildCodonMap(READING_FRAME start, String dna) {
    codons.clear();
    for (int i = start.value; i + 3 < dna.length(); i += 3) {
      String codon = dna.substring(i, i + 3);
      if (codons.containsKey(codon)) {
        codons.put(codon, codons.get(codon) + 1);
      } else {
        codons.put(codon, 1);
      }
    }
  }


  /**
   * Method returns the codon in a reading frame that has the
   * largest count. If there are several such codons it returns
   * any of them. This method assumes the HashMap of codons to counts
   * has already been built.
   *
   * @return
   */
  public String getMostCommonCodon() {
    int max = 0;
    String mostCommonCodon = "";
    for (String codon : codons.keySet()) {
      int count = codons.get(codon);
      if (count > max) {
        mostCommonCodon = codon;
        max = count;
      }
    }
    return mostCommonCodon;
  }


  /**
   * This method prints all the codons in the HashMap along with their
   * counts if their count is between start and end inclusive.
   *
   * @param start
   * @param end
   */
  public void printCodonCounts(int start, int end) {
    for (String codon : codons.keySet()) {
      int count = codons.get(codon);
      if (count >= start && count <= end) {
        System.out.println(codon + " " + count);
      }
    }
  }

  public int codonsSize() {
    return codons.size();
  }


}
