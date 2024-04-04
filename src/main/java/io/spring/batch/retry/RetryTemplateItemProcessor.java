//package io.spring.batch.retry;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.batch.core.configuration.annotation.StepScope;
//import org.springframework.batch.item.ItemProcessor;
//import org.springframework.classify.BinaryExceptionClassifier;
//import org.springframework.classify.Classifier;
//import org.springframework.retry.support.DefaultRetryState;
//import org.springframework.retry.support.RetryTemplate;
//import org.springframework.stereotype.Component;
//
//@Component
//@StepScope
//@RequiredArgsConstructor
//public class RetryTemplateItemProcessor implements ItemProcessor<String, Customer> {
//
//    private final RetryTemplate retryTemplate;
//    private int cnt = 0;
//
//    @Override
//    public Customer process(String item) throws Exception {
//        Classifier<Throwable, Boolean> rollbackClassifier = new BinaryExceptionClassifier(true);
//
//        Customer customer = retryTemplate.execute(
//                context -> {
//                    if (item.equals("1") || item.equals("2")) {
//                        cnt++;
//                        System.out.println("failed cnt : " + cnt);
//                        throw new RetryableException();
//                    }
//
//                    return new Customer(item);
//                },
//                context -> {
//                    System.out.println("recovery logic");
//                    return new Customer(item);
//                },
//                new DefaultRetryState(item, rollbackClassifier)
//        );
//
//        return customer;
//    }
//}
