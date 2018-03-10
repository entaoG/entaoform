package com.ysii.hadoop.kafka;

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.message.MessageAndMetadata;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * kafka 消费者
 * @author yskkysll
 * @version 1.0
 */
public class ConsumerDemo {

    private static final String topic = "test";
    private static final Integer threads = 1;

    public static void main(String[] args) {
        Properties props = new Properties();
        //zk
        props.put("zookeeper.connect","192.168.92.130:2181");
        //group
        props.put("group.id","group1");
        //offset
        props.put("auto.offset.reset","smallest");
        ConsumerConfig config = new ConsumerConfig(props);
        ConsumerConnector consumer = Consumer.createJavaConsumerConnector(config);
        Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
        topicCountMap.put(topic ,1);
        Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumer.createMessageStreams(topicCountMap);
        List<KafkaStream<byte[], byte[]>> streams = consumerMap.get(topic);

        for(final KafkaStream<byte[],byte[]> kafkaStream : streams){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for(MessageAndMetadata<byte[],byte[]> mm : kafkaStream){
                        String msg = new String(mm.message());
                        System.out.println(msg);
                    }
                }
            }).start();

        }

    }
}
