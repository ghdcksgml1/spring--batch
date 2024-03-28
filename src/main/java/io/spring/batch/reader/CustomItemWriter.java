//package io.spring.batch.reader;
//
//import org.springframework.batch.item.Chunk;
//import org.springframework.batch.item.ItemWriter;
//
//public class CustomItemWriter implements ItemWriter<Customer> {
//    @Override
//    public void write(Chunk<? extends Customer> chunk) throws Exception {
//        chunk.getItems().forEach(System.out::println);
//        System.out.println("===chunked!===");
//    }
//}
