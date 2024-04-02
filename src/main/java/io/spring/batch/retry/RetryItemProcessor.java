package io.spring.batch.retry;

import org.springframework.batch.item.ItemProcessor;

public class RetryItemProcessor implements ItemProcessor<String, String> {

    private int cnt = 0;
    @Override
    public String process(String item) throws Exception {
        cnt++;
        if (item.equals("2") || item.equals("3")) {
            System.out.println("item = " + item + ", cnt = " + cnt);
            throw new RetryableException();
        }
        return item + cnt;
    }
}
