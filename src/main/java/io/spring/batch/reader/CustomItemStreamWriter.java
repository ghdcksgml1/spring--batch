package io.spring.batch.reader;

import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.ItemStreamWriter;

public class CustomItemStreamWriter implements ItemStreamWriter<String> {
    @Override
    public void write(Chunk<? extends String> chunk) throws Exception {
        System.out.println("");
    }

    @Override
    public void open(ExecutionContext executionContext) throws ItemStreamException {
        System.out.println("open");
    }

    @Override
    public void update(ExecutionContext executionContext) throws ItemStreamException {
        System.out.println("update");
    }

    @Override
    public void close() throws ItemStreamException {
        System.out.println("close");
    }
}
