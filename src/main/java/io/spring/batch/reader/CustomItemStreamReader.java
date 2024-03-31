//package io.spring.batch.reader;
//
//import org.springframework.batch.item.*;
//
//import java.util.List;
//
//public class CustomItemStreamReader implements ItemStreamReader<String> {
//    private static final String INDEX_KEY = "index";
//    private final List<String> items;
//    private int index = -1;
//    private boolean restart = false;
//
//    public CustomItemStreamReader(List<String> items) {
//        this.items = items;
//        this.index = 0;
//    }
//
//    @Override
//    public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
//        String item = null;
//        if (this.index < this.items.size()) {
//            System.out.println("read = " + index);
//            item = this.items.get(index++);
//        }
//
//        if (this.index == 7 && !restart) {
//            throw new RuntimeException("Restart is required!");
//        }
//        return item;
//    }
//
//    @Override
//    public void open(ExecutionContext executionContext) throws ItemStreamException {
//        if (executionContext.containsKey(INDEX_KEY)) {
//            index = executionContext.getInt(INDEX_KEY);
//            this.restart = true;
//        } else {
//            index = 0;
//            executionContext.put(INDEX_KEY, index);
//        }
//    }
//
//    @Override
//    public void update(ExecutionContext executionContext) throws ItemStreamException {
//        executionContext.put(INDEX_KEY, index);
//    }
//
//    @Override
//    public void close() throws ItemStreamException {
//        ItemStreamReader.super.close();
//    }
//}
