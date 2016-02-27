package week3;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class LogEntryTest {

  @Test
  public void testLogEntry() throws Exception {
    LogEntry logEntry = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
    System.out.println(logEntry);

    LogEntry logEntry1 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
    System.out.println(logEntry1);

  }
}