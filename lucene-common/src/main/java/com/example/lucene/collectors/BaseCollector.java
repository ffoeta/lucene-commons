package com.example.lucene.collectors;

import org.apache.lucene.search.ScoreMode;
import org.apache.lucene.search.SimpleCollector;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class BaseCollector extends SimpleCollector {

  public BaseCollector() {
  }

  public long hitCount() {
    return 0l;
  }

  public List<Integer> hitList() {
    return Collections.emptyList();
  }

  @Override
  public void collect(int i) throws IOException {
  }

  @Override
  public ScoreMode scoreMode() {
    return ScoreMode.COMPLETE;
  }
}
