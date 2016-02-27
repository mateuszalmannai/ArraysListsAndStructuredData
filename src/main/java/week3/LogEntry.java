package week3;

import java.util.Date;

public class LogEntry {
  private String ipAddress;
  private Date accessTime;
  private String request;
  private int statusCode;
  private int bytesReturned;

  public LogEntry(String ipAddress, Date accessTime, String request, int statusCode, int bytesReturned) {
    this.ipAddress = ipAddress;
    this.accessTime = accessTime;
    this.request = request;
    this.statusCode = statusCode;
    this.bytesReturned = bytesReturned;
  }

  public String getIpAddress() {
    return ipAddress;
  }

  public Date getAccessTime() {
    return accessTime;
  }

  public String getRequest() {
    return request;
  }

  public int getStatusCode() {
    return statusCode;
  }

  public int getBytesReturned() {
    return bytesReturned;
  }

  public void setIpAddress(String ipAddress) {
    this.ipAddress = ipAddress;
  }

  public void setAccessTime(Date accessTime) {
    this.accessTime = accessTime;
  }

  public void setRequest(String request) {
    this.request = request;
  }

  public void setStatusCode(int statusCode) {
    this.statusCode = statusCode;
  }

  public void setBytesReturned(int bytesReturned) {
    this.bytesReturned = bytesReturned;
  }

  @Override
  public String toString() {
    return "LogEntry{" +
      "ipAddress='" + ipAddress + '\'' +
      ", accessTime=" + accessTime +
      ", request='" + request + '\'' +
      ", statusCode=" + statusCode +
      ", bytesReturned=" + bytesReturned +
      '}';
  }
}
