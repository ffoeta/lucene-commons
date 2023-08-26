package com.example.runner.util;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class StoppableThread extends Thread {
  protected volatile boolean stopNow;

  public StoppableThread() {
    this.stopNow = false;
  }

  @PreDestroy
  public void stopNow() {
    stopNow = true;
    this.interrupt();
  }

  @PostConstruct
  public synchronized void start() {
    super.start();
  }
}
