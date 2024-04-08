//package io.spring.batch.async;
//
//import org.springframework.batch.core.ItemProcessListener;
//
//public class CustomItemProcessListener implements ItemProcessListener<String, String> {
//
//    @Override
//    public void beforeProcess(String item) {
//        ItemProcessListener.super.beforeProcess(item);
//    }
//
//    @Override
//    public void afterProcess(String item, String result) {
//        System.out.println("Thread : " + Thread.currentThread().getName() + ", process item : " + item);
//    }
//
//    @Override
//    public void onProcessError(String item, Exception e) {
//        ItemProcessListener.super.onProcessError(item, e);
//    }
//}
