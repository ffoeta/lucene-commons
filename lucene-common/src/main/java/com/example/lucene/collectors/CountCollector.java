package com.example.lucene.collectors;

import org.apache.lucene.search.ScoreMode;

import java.util.Collections;
import java.util.List;

public class CountCollector extends BaseCollector {
  private int hits;

  public CountCollector() {
  }

  @Override
  public void collect(int doc) {
    hits++;
  }

  @Override
  public ScoreMode scoreMode() {
    return ScoreMode.COMPLETE_NO_SCORES;
  }

  @Override
  public long hitCount() {
    return hits;
  }

  @Override
  public List<Integer> hitList() {
    return Collections.emptyList();
  }
}
