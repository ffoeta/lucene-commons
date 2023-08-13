package com.example.lucene.collectors;

import org.apache.lucene.search.ScoreMode;

import java.util.ArrayList;
import java.util.List;

public class DefaultCollector extends BaseCollector {
  private final List<Integer> hitList = new ArrayList<>();

  public DefaultCollector() {
    super();
  }

  @Override
  public void collect(int doc) {
    hitList.add(doc);
  }

  @Override
  public ScoreMode scoreMode() {
    return ScoreMode.COMPLETE_NO_SCORES;
  }

  @Override
  public long hitCount() {
    return hitList.size();
  }

  @Override
  public List<Integer> hitList() {
    return hitList;
  }
}