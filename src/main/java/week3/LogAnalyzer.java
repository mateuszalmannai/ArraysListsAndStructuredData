package week3;

import duke.FileResource;

import java.text.SimpleDateFormat;
import java.util.*;

public class LogAnalyzer {
  private List<LogEntry> records;


  public LogAnalyzer() {
    records = new ArrayList<>();
  }

  public void readFile(String fileName) {
    FileResource fileResource = new FileResource("weblogdata/" + fileName);
    for (String line : fileResource.lines()) {
      LogEntry logEntry = WebLogParser.parseEntry(line);
      records.add(logEntry);
    }
  }

  public int countUniqueIps() {
    List<String> uniqueIps = new ArrayList<>();
    for (LogEntry record : records) {
      String ipAddress = record.getIpAddress();
      if (!uniqueIps.contains(ipAddress)) {
        uniqueIps.add(ipAddress);
      }
    }
    return uniqueIps.size();
  }

  public void printAllHigherThanNum(int num) {
    for (LogEntry record : records) {
      if (record.getStatusCode() > num) {
        System.out.println(record);
      }
    }
  }

  public void printAll() {
    for (LogEntry record : records) {
      System.out.println(record);
    }
  }

  public List<String> uniqueIPVisitsInOneDay(String someday) {
    List<String> uniqueIps = new ArrayList<>();
    for (LogEntry record : records) {
      String accessTime = record.getAccessTime().toString();
      if (accessTime.substring(4,10).equals(someday) && !uniqueIps.contains(record.getIpAddress())) {
        uniqueIps.add(record.getIpAddress());
      }
    }
    return uniqueIps;
  }

  public int countUniqueIPsInRange(int low, int high) {
    List<String> uniqueIps = new ArrayList<>();
    for (LogEntry record : records) {
      int statusCode = record.getStatusCode();
      if (statusCode >= low && statusCode <= high && !uniqueIps.contains(record.getIpAddress())) {
        uniqueIps.add(record.getIpAddress());
      }
    }
    return uniqueIps.size();
  }


  /**
   * Returns a map of ip addresses against number of times
   * the address appears in the records
   */
  public Map<String, Integer> countVisitsPerIP() {
    Map<String, Integer> counts = new HashMap<>();
    for (LogEntry logEntry : records) {
      String ipAddress = logEntry.getIpAddress();
      if (!counts.containsKey(ipAddress)) {
        counts.put(ipAddress, 1);
      } else {
        counts.put(ipAddress, counts.get(ipAddress) + 1);
      }
    }
    return counts;
  }

  /**
   * Returns the maximum number of visits by a single
   * IP address.
   */
  public int mostNumberVisitsByIP(Map<String, Integer> map) {
    int max = 0;
    for (Map.Entry<String, Integer> entry : map.entrySet()) {
      if (entry.getValue() > max) {
        max = entry.getValue();
      }
    }
    return max;
  }

  /**
   * Returns a list of IP addresses that have the maximum number
   * of visits.
   */
  public List<String> iPsMostVisits(Map<String, Integer> map) {
    List<String> maxVisits = new ArrayList<>();
    int max = mostNumberVisitsByIP(map);
    for (Map.Entry<String, Integer> entry : map.entrySet()) {
      if (entry.getValue() == max) {
        maxVisits.add(entry.getKey());
      }
    }
    return maxVisits;
  }

  /**
   * Returns a map that maps days from web logs to a list
   * of IP addresses that occureed on that day
   * (including repeated IP adddresses)
   * @return
   */
  public Map<String, List<String>> iPsForDays() {
    Map<String, List<String>> ipsForDay = new HashMap<>();
    for (LogEntry record : records) {
      Date accessTime = record.getAccessTime();
      String ipAddress = record.getIpAddress();
      String formattedDate = new SimpleDateFormat("MMM dd").format(accessTime);
      List<String> ips = ipsForDay.containsKey(formattedDate) ? ipsForDay.get(formattedDate) : new ArrayList<>();
      ips.add(ipAddress);
      ipsForDay.put(formattedDate, ips);
    }
    return ipsForDay;
  }

  /**
   * Returns the day that has the most IP address visits.
   */
  public String dayWithMostIPVisits(Map<String, List<String>> map) {
    String result = "";
    int max = 0;
    for (Map.Entry<String, List<String>> entry : map.entrySet()) {
      if (entry.getValue().size() > max) {
        result = entry.getKey();
        max = entry.getValue().size();
      }
    }
    return result;
  }

  /**
   * Returns a list of IP addresses that had the most accesses on a given day
   */
  public List<String> iPsWithMostVisitsOnDay(Map<String, List<String>> map, String day) {
    // List of ips for given day
    List<String> ipsOnDay = map.get(day);

    // create Map to hold counts of ip addresses
    Map<String, Integer> counts = new HashMap<>();

    // populate the map

    for (String ip : ipsOnDay) {
      if (!counts.containsKey(ip)) {
        counts.put(ip, 1);
      } else {
        counts.put(ip, counts.get(ip) + 1);
      }
    }

    return iPsMostVisits(counts);
  }

}
