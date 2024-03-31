package io.spring.batch.retry;

import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;

public class SkipItemWriter implements ItemWriter<String> {

    private int cnt = 0;

    @Override
    public void write(Chunk<? extends String> chunk) throws Exception {
        for (String item : chunk.getItems()) {
            if (item.equals("-12")) {
                throw new SkippableException("Write failed cnt : " + cnt);
            } else {
                System.out.println("ItemWriter : " + item);
            }
        }
    }
}
