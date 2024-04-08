//package io.spring.batch.async;
//
//import org.springframework.batch.core.ItemReadListener;
//
//public class CustomItemReadListener implements ItemReadListener<String> {
//
//    @Override
//    public void beforeRead() {
//        ItemReadListener.super.beforeRead();
//    }
//
//    @Override
//    public void afterRead(String item) {
//        System.out.println("Thread : " + Thread.currentThread().getName() + ", read item : " + item);
//    }
//
//    @Override
//    public void onReadError(Exception ex) {
//        ItemReadListener.super.onReadError(ex);
//    }
//}
