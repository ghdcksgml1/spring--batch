//package io.spring.batch.async;
//
//import org.springframework.batch.core.partition.support.Partitioner;
//import org.springframework.batch.item.ExecutionContext;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class ColumnRangePartitioner implements Partitioner {
//    @Override
//    public Map<String, ExecutionContext> partition(int gridSize) {
//        HashMap<String, ExecutionContext> result = new HashMap<>();
//
//        result.put("1", new ExecutionContext());
//
//        return result;
//    }
//}
