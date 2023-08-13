package com.example.lucene.utils;

import com.example.lucene.model.IndexType;

import static com.example.lucene.config.IndexConfig.BOOK_INDEX_PATH;
import static com.example.lucene.config.IndexConfig.OTHER_INDEX_PATH;

public class TypeResolver {
  public static String getIndexPathForIndexType(IndexType indexType) {
    if (IndexType.BOOK.equals(indexType)) {
      return BOOK_INDEX_PATH;
    } else if (IndexType.OTHER.equals(indexType)) {
      return OTHER_INDEX_PATH;
    } else {
      throw new RuntimeException();
    }
  }
}
