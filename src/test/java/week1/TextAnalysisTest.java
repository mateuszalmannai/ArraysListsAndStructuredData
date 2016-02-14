package week1;

import duke.FileResource;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import static org.hamcrest.CoreMatchers.*;

public class TextAnalysisTest {

  private TextAnalysis textAnalysis;

  @Before
  public void setUp() throws Exception {
    textAnalysis = new TextAnalysis();
  }

  @Test
  public void testTextFingerPrint() throws Exception {
    textAnalysis.textFingerPrint("Lujyfwapvu huk zljbypaf hyl mbukhtluahs whyaz vm avkhf'z Pualyula.");
  }

  @Test
  public void testSimpleSimulate() throws Exception {
    textAnalysis.simpleSimulate(10000);
  }

  @Test
  public void testSimulate() throws Exception {
    textAnalysis.simulate(10);
  }

  @Test
  public void testCountShakespeare() throws Exception {
    textAnalysis.countShakespeare();
  }

}