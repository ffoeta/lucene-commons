package com.example.kafka.model;

public class IndexMessage extends KafkaMessage {
  private Integer id;

  public IndexMessage() {
  }

  private IndexMessage(Integer id) {
    this.id = id;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public static MessageBuilder builder() {
    return new MessageBuilder();
  }

  public static class MessageBuilder {
    private Integer id;
    private MessageBuilder() {
    }

    public MessageBuilder withId(Integer id) {
      this.id = id;
      return this;
    }

    public IndexMessage build() {
      return new IndexMessage(this.id);
    }
  }
}
