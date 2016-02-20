package week2;

import duke.FileResource;
import org.junit.Before;
import org.junit.Test;

public class CodonAnalysisTest {

  FileResource resource;
  private CodonAnalysis codonAnalysis;

  @Before
  public void setUp() throws Exception {
    resource = new FileResource("data/dnaMystery2.txt");
    codonAnalysis = new CodonAnalysis();
  }

  @Test
  public void testCodon() throws Exception {
    String dna = resource.asString().trim().toUpperCase();
    for (CodonAnalysis.READING_FRAME reading_frame : CodonAnalysis.READING_FRAME.values()) {
      codonAnalysis.buildCodonMap(reading_frame, dna);
      System.out.println("In Frame " + reading_frame.getValue());
      codonAnalysis.printCodonCounts(1, 10);
    }


  }
}