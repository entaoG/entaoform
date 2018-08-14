package main.java.com.ysii.hadoop.flumekafka;

import org.apache.flume.Event;
import org.apache.flume.EventDeliveryException;
import org.apache.flume.api.RpcClient;
import org.apache.flume.api.RpcClientFactory;
import org.apache.flume.event.EventBuilder;

import java.nio.charset.Charset;

/**
 * kafka和flume连通
 * @author yskkysll
 * @version 1.0
 */
public class flumkafkaCn {
    public static void main(String[] args) {
        MyRpcClientFacade client = new MyRpcClientFacade();
        //Initialize client with the remote Flume agent's host and port
        client.init("192.168.92.130",41414);

        //send 10 events to the remote Flume agent.That agent should be
        //configured to listen with an AvroSource
        String smpleDate = "Hello Flume";
        for(int i = 0;i <100; i++){
            client.sendDataToFlume(smpleDate);
        }
        client.cleanUp();
    }
}

/**
 * rpcClient
 */
class MyRpcClientFacade{
    private String hostname;
    private RpcClient client;
    private int port;

    /**
     * init
     * @param hostname
     * @param port
     */
    public void init(String hostname, int port){
        //Setup the Rpc connection
        this.hostname = hostname;
        this.client = client;
        this.client = RpcClientFactory.getDefaultInstance(hostname, port);
    }

    /**
     * send data to Flume
     * @param data
     */
    public void sendDataToFlume(String data){
        //Ceatea a Flume Event object that encapsulates the sample data
        Event event = EventBuilder.withBody(data, Charset.forName("UTF-8"));

        //send the even
        try {
            client.append(event);
        } catch (EventDeliveryException e) {
            e.printStackTrace();
            //clean up and recreate the client
            client.close();
            client = null;
            client = RpcClientFactory.getDefaultInstance(hostname, port);
        }
    }

    public void cleanUp(){
        //Close the RPC connection
        client.close();
    }

}



