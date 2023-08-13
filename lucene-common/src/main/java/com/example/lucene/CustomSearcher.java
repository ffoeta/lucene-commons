package com.example.lucene;

import com.example.lucene.collectors.BaseCollector;
import com.example.lucene.exception.CustomIndexUserCreationException;
import com.example.lucene.exception.CustomIndexUserOperationException;
import com.example.lucene.model.CollectorResult;
import com.example.lucene.model.IndexType;
import org.apache.lucene.facet.FacetsCollector;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

public class CustomSearcher extends CustomReader {
  private static final Logger LOG = LoggerFactory.getLogger(CustomSearcher.class);

  private IndexSearcher indexSearcher;
  private CustomSearcher(IndexType indexType) throws IOException {
    super(indexType);
    indexSearcher = new IndexSearcher(indexReader);
  }
  public static CustomSearcher buildReaderForIndex(IndexType indexType) throws CustomIndexUserCreationException {
    try {
      var searcher = new CustomSearcher(indexType);
      LOG.info("TAKEN INDEX {} FOR {}", indexType, searcher.getClass().getSimpleName());
      return searcher;
    } catch (IOException e) {
      LOG.error("FAILED TO INSTANTIATE WRITER FOR INDEX {}", indexType);
      throw new CustomIndexUserCreationException(e.getMessage());
    }
  }

  public CollectorResult search(Query query, BaseCollector collector) {
    long hits;
    List<Integer> hitList;

    try {
      indexSearcher.search(query, collector);
      hits = collector.hitCount();
      hitList = collector.hitList();
    } catch (IOException e) {
      LOG.error("FAILED TO SEARCH");
      throw new CustomIndexUserOperationException(e.getMessage());
    }

    return new CollectorResult(hits, hitList);
  }

  public List<FacetsCollector.MatchingDocs> search(Query query, FacetsCollector collector) {
    try {
      indexSearcher.search(query, collector);
      return collector.getMatchingDocs();
    } catch (IOException e) {
      LOG.error("FAILED TO SEARCH");
      throw new CustomIndexUserOperationException(e.getMessage());
    }
  }
}
