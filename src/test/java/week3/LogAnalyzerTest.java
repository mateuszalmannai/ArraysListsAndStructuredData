package week3;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

public class LogAnalyzerTest {

  private LogAnalyzer analyzer;

  @Before
  public void setUp() throws Exception {
    analyzer = new LogAnalyzer();

  }

  @Test
  public void testLogAnalyzer() throws Exception {
    analyzer.readFile("weblog1_log");
    analyzer.printAll();
    System.out.println("There are " + analyzer.countUniqueIps() + " unique IPs");
    System.out.println();
    analyzer.printAllHigherThanNum(400);
  }

  @Test
  public void testCountUniqueIpsQuiz() throws Exception {
    analyzer.readFile("weblog2_log");
    int uniqueIps = analyzer.countUniqueIps();
    assertThat(uniqueIps, is(45));
  }

  @Test
  public void testUniqueIPVisitsInOneDay() throws Exception {
    analyzer.readFile("weblog-short_log");
    List<String> ipAddressesSep14 = analyzer.uniqueIPVisitsInOneDay("Sep 14");
    assertThat(ipAddressesSep14.size(), is(2));

    List<String> ipAddressesSep30 = analyzer.uniqueIPVisitsInOneDay("Sep 30");
    assertThat(ipAddressesSep30.size(), is(3));
  }

  @Test
  public void testUniqueIPVisitsInOneDayQuiz() throws Exception {
    analyzer.readFile("weblog1_log");
    List<String> uniqueIPVisitsInOneDay = analyzer.uniqueIPVisitsInOneDay("Mar 24");
    System.out.println(uniqueIPVisitsInOneDay.size());
  }

  @Test
  public void testUniqueIPVisitsInOneDayQuiz2() throws Exception {
    analyzer.readFile("weblog2_log");
    List<String> uniqueIPVisitsInOneDay = analyzer.uniqueIPVisitsInOneDay("Sep 27");
    assertThat(uniqueIPVisitsInOneDay.size(), is(8));
  }

  @Test
  public void testCountUniqueIpsInRange() throws Exception {
    analyzer.readFile("short-test_log");
    int uniqueIPsInOneRange = analyzer.countUniqueIPsInRange(200, 299);
    assertThat(uniqueIPsInOneRange, is(4));
    int uniqueIPsInAnotherRange = analyzer.countUniqueIPsInRange(300, 399);
    assertThat(uniqueIPsInAnotherRange, is(2));
  }

  @Test
  public void testCountUniqueIpsInRangeQuiz() throws Exception {
    analyzer.readFile("weblog1_log");
    int uniqueIPsInRange = analyzer.countUniqueIPsInRange(200, 299);
    System.out.println(uniqueIPsInRange);
  }

  @Test
  public void testCountUniqueIpsInRangeQuiz2() throws Exception {
    analyzer.readFile("weblog2_log");
    int uniqueIPsInRange = analyzer.countUniqueIPsInRange(200, 299);
    assertThat(uniqueIPsInRange, is(40));
  }

  @Test
  public void testCountVisitsPerIP() throws Exception {
    analyzer.readFile("short-test_log");
    Map<String, Integer> ipCounts = analyzer.countVisitsPerIP();
    System.out.println(ipCounts);
    System.out.println("Unique ips: " + ipCounts.size());
  }

  @Test
  public void testMostNumberVisitsByIP() throws Exception {
    analyzer.readFile("weblog3-short_log");
    int mostNumberVisitsByIP = analyzer.mostNumberVisitsByIP(analyzer.countVisitsPerIP());
    assertThat(mostNumberVisitsByIP, is(3));
  }

  @Test
  public void testMostNumberVisitsByIPQuiz() throws Exception {
    analyzer.readFile("weblog1_log");
    int mostNumberVisitsByIP = analyzer.mostNumberVisitsByIP(analyzer.countVisitsPerIP());
    assertThat(mostNumberVisitsByIP, is(133));
  }

  @Test
  public void testMostNumberVisitsByIPQuiz2() throws Exception {
    analyzer.readFile("weblog2_log");
    int mostNumberVisitsByIP = analyzer.mostNumberVisitsByIP(analyzer.countVisitsPerIP());
    assertThat(mostNumberVisitsByIP, is(63));
  }

  @Test
  public void testIpsMostVisits() throws Exception {
    analyzer.readFile("weblog3-short_log");
    List<String> iPsMostVisits = analyzer.iPsMostVisits(analyzer.countVisitsPerIP());
    assertThat(iPsMostVisits.size(), is(2));
    System.out.println(iPsMostVisits);
  }

  @Test
  public void testIpsMostVisitsQuiz() throws Exception {
    analyzer.readFile("weblog1_log");
    List<String> iPsMostVisits = analyzer.iPsMostVisits(analyzer.countVisitsPerIP());
    assertThat(iPsMostVisits.size(), is(1));
    System.out.println(iPsMostVisits);
  }

  @Test
  public void testIpsMostVisitsQuiz2() throws Exception {
    analyzer.readFile("weblog2_log");
    List<String> iPsMostVisits = analyzer.iPsMostVisits(analyzer.countVisitsPerIP());
    assertThat(iPsMostVisits.size(), is(1));
    assertThat(iPsMostVisits.get(0), is("188.162.84.63"));
  }

  @Test
  public void testIpsForDays() throws Exception {
    analyzer.readFile("weblog3-short_log");
    Map<String, List<String>> iPsForDays = analyzer.iPsForDays();
    System.out.println(iPsForDays);
  }

  @Test
  public void testDayWithMostIPVisits() throws Exception {
    analyzer.readFile("weblog3-short_log");
    String dayWithMostIPVisits = analyzer.dayWithMostIPVisits(analyzer.iPsForDays());
    assertThat(dayWithMostIPVisits, is("Sep 30"));
  }

  @Test
  public void testDayWithMostIPVisitsQuiz() throws Exception {
    analyzer.readFile("weblog1_log");
    String dayWithMostIPVisits = analyzer.dayWithMostIPVisits(analyzer.iPsForDays());
    assertThat(dayWithMostIPVisits, is("Mar 24"));
  }

  @Test
  public void testDayWithMostIPVisitsQuiz2() throws Exception {
    analyzer.readFile("weblog2_log");
    String dayWithMostIPVisits = analyzer.dayWithMostIPVisits(analyzer.iPsForDays());
    assertThat(dayWithMostIPVisits, is("Sep 24"));
  }

  @Test
  public void testIpsWithMostVisitsOnDay() throws Exception {
    analyzer.readFile("weblog3-short_log");
    Map<String, List<String>> map = analyzer.iPsForDays();
    List<String> list = analyzer.iPsWithMostVisitsOnDay(map, "Sep 30");
    System.out.println(list);
    assertThat(list.size(), is(2));
  }

  @Test
  public void testIpsWithMostVisitsOnDayQuiz() throws Exception {
    analyzer.readFile("weblog1_log");
    Map<String, List<String>> map = analyzer.iPsForDays();
    List<String> list = analyzer.iPsWithMostVisitsOnDay(map, "Mar 17");
    System.out.println(list);
    assertThat(list.size(), is(1));
  }

  @Test
  public void testIpsWithMostVisitsOnDayQuiz2() throws Exception {
    analyzer.readFile("weblog2_log");
    Map<String, List<String>> map = analyzer.iPsForDays();
    List<String> list = analyzer.iPsWithMostVisitsOnDay(map, "Sep 30");
    System.out.println(list);
    assertThat(list.size(), is(2));
  }
}