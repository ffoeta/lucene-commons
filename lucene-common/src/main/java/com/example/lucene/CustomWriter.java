package com.example.lucene;

import com.example.lucene.exception.CustomIndexUserCreationException;
import com.example.lucene.exception.CustomIndexUserDisposalException;
import com.example.lucene.exception.CustomIndexUserOperationException;
import com.example.lucene.analyzer.IndexAnalyzer;
import com.example.lucene.model.IndexType;
import com.example.lucene.proto.CustomIndexUser;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

public class CustomWriter extends CustomIndexUser implements AutoCloseable {
  private static final Logger LOG = LoggerFactory.getLogger(CustomWriter.class);

  private IndexWriter indexWriter;

  private CustomWriter(IndexType indexType) throws IOException {
    super(indexType);
    this.indexWriter = new IndexWriter(directory, new IndexWriterConfig(IndexAnalyzer.getAnalyzer()));
  }
  public static CustomWriter buildWriterForIndex(IndexType indexType) throws CustomIndexUserCreationException {
    try {
      var writer = new CustomWriter(indexType);
      LOG.info("TAKEN INDEX {} FOR {}", indexType, writer.getClass().getSimpleName());
      return writer;
    } catch (IOException e) {
      LOG.error("FAILED TO INSTANTIATE WRITER FOR INDEX {}", indexType);
      throw new CustomIndexUserCreationException(e.getMessage());
    }
  }

  public void writeDocs(List<Document> documents) throws CustomIndexUserOperationException {
    try {
      indexWriter.addDocuments(documents);
      indexWriter.commit();
    } catch (IOException e) {
      LOG.error("FAILED TO WRITE DOCUMENTS OF SIZE {} TO INDEX {}", documents.size(), this.indexType);
      throw new CustomIndexUserOperationException(e.getMessage());
    }
  }

  public void writeDoc(Document document) throws CustomIndexUserOperationException {
    try {
      indexWriter.addDocument(document);
      indexWriter.commit();
    } catch (IOException e) {
      LOG.error("FAILED TO WRITE DOCUMENT {} TO INDEX {}", document, this.indexType);
      throw new CustomIndexUserOperationException(e.getMessage());
    }
  }

  public void deleteAllDocs() {
    try {
      indexWriter.deleteAll();
      indexWriter.commit();
    } catch (IOException e) {
      LOG.error("FAILED TO DELETE DOCUMENTS FOR INDEX {}", indexType);
      throw new CustomIndexUserOperationException(e.getMessage());
    }
  }

  @Override
  public void close() {
    try {
      indexWriter.close();
    } catch (IOException e) {
      LOG.error("FAILED TO CLOSE RESOURCE FOR INDEX {}", indexType);
      throw new CustomIndexUserDisposalException(e.getMessage());
    }
    LOG.info("RELEASED INDEX {} FROM {}", indexType, this.getClass().getSimpleName());
  }
}
