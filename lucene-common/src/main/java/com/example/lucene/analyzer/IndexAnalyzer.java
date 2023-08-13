package com.example.lucene.analyzer;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.en.EnglishAnalyzer;

import java.io.IOException;

public class IndexAnalyzer {
  public static Analyzer getAnalyzer() throws IOException {
    return new EnglishAnalyzer();
  }
}
