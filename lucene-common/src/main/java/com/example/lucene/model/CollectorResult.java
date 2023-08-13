package com.example.lucene.model;

import java.util.List;

public record CollectorResult(long hits, List<Integer> hitIds) {
}
