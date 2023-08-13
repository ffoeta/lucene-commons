package com.example.lucene.proto;

import com.example.lucene.model.IndexType;
import com.example.lucene.utils.TypeResolver;
import org.apache.lucene.store.FSDirectory;

import java.io.File;
import java.io.IOException;

public abstract class CustomIndexUser implements AutoCloseable {
  protected IndexType indexType;
  protected FSDirectory directory;

  public CustomIndexUser(IndexType indexType) throws IOException {
    this.indexType = indexType;
    var indexPath = TypeResolver.getIndexPathForIndexType(this.indexType);
    this.directory = FSDirectory.open(new File(indexPath).toPath());
  }
}
