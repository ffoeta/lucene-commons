package com.example.lucene;

import com.example.lucene.exception.CustomIndexUserCreationException;
import com.example.lucene.exception.CustomIndexUserDisposalException;
import com.example.lucene.model.IndexType;
import com.example.lucene.proto.CustomIndexUser;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class CustomReader extends CustomIndexUser implements AutoCloseable {
  private static final Logger LOG = LoggerFactory.getLogger(CustomReader.class);

  protected IndexReader indexReader;
  CustomReader(IndexType indexType) throws IOException {
    super(indexType);
    this.indexReader = DirectoryReader.open(directory);
  }
  public static CustomReader buildReaderForIndex(IndexType indexType) throws CustomIndexUserCreationException {
    try {
      var reader = new CustomReader(indexType);
      LOG.info("TAKEN INDEX {} FOR {}", indexType, reader.getClass().getSimpleName());
      return reader;
    } catch (IOException e) {
      LOG.error("FAILED TO INSTANTIATE WRITER FOR INDEX {}", indexType);
      throw new CustomIndexUserCreationException(e.getMessage());
    }
  }

  public IndexReader getIndexReader() {
    return indexReader;
  }

  public int readNDoc() {
    return indexReader.numDocs();
  }

  @Override
  public void close() {
    try {
      indexReader.close();
    } catch (IOException e) {
      LOG.warn("FAILED TO CLOSE RESOURCE FOR INDEX {}", indexType);
      throw new CustomIndexUserDisposalException(e.getMessage());
    }
    LOG.info("RELEASED INDEX {} FROM {}", indexType, this.getClass().getSimpleName());
  }
}
