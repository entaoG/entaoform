package com.ysii.hadoop.kafka;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import java.util.Properties;

/**
 * kafka 生产者
 * @author yskkysll
 * @version 1.0
 */
public class ProducerDemo
{
    public static void main(String[] args) {
        Properties props = new Properties();
        //zk
        props.put("zk.connection","192.168.92.130:2181");
        //kafka broker
        props.put("metadata.broker.list","192.168.92.130:9092");

        //serialize
        props.put("serializer.class","kafka.serializer.StringEncoder");
        ProducerConfig config = new ProducerConfig(props);
        Producer<String, String> producer = new Producer<String, String>(config);

        //read socket
        for(int i = 1; i<=100000; i++){
            //Thread.sleep(50);
            producer.send(new KeyedMessage<String, String>("test","message："+i));
        }

    }
}
