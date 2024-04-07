package io.spring.batch.async;

import org.springframework.batch.core.ItemWriteListener;
import org.springframework.batch.item.Chunk;

public class CustomItemWriteListener implements ItemWriteListener<String> {
    @Override
    public void beforeWrite(Chunk<? extends String> items) {
        ItemWriteListener.super.beforeWrite(items);
    }

    @Override
    public void afterWrite(Chunk<? extends String> items) {
        System.out.println("Thread : " + Thread.currentThread().getName() + ", write item : " + items.getItems().size());
    }

    @Override
    public void onWriteError(Exception exception, Chunk<? extends String> items) {
        ItemWriteListener.super.onWriteError(exception, items);
    }
}
