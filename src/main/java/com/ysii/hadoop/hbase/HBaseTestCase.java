package com.ysii.hadoop.hbase;



import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

/**
 * Hbase 例子
 * @author yskkysll
 * @version 1.1.0
 */
public class HBaseTestCase {

    //声明静态配置 HBaseConfiguration
    private static Configuration conf;
    static {
        conf = HBaseConfiguration.create();
        conf.set("hbase.rootdir", "hdfs://192.168.92.128:9000/hbase");
        conf.set("hbase.zookeeper.quorum", "192.168.92.128");
        conf.set("hbase.zookeeper.property.clientPort", "2181");
    }

    //创建一张表，通过HBaseAdmin HableDescriptor来创建
    public static void create(String tablename , String columuFamily) throws Exception{
        HBaseAdmin admin = new HBaseAdmin(conf);
       if(admin.tableExists(tablename)){
           System.out.println("table  Exists!");
           System.out.println(0);
       }else
       {
           HTableDescriptor tableDesc = new HTableDescriptor(tablename);
           tableDesc.addFamily(new HColumnDescriptor(columuFamily));
           admin.createTable(tableDesc);
           System.out.println("create table success!");
       }
    }

    //添加一条记录，通过HTable Put为已经存在的表来添加数据
    public static void put(String tablename,String row,String columnFamily, String column,
                           String data)throws Exception{
        HTable table = new HTable(conf, tablename);
        Put p1 = new Put(Bytes.toBytes(row));
        p1.add(Bytes.toBytes(columnFamily),Bytes.toBytes(column), Bytes.toBytes(data));
        table.put(p1);
        System.out.println("put"+row+","+columnFamily+":"+column+","+data+".");
    }

    //查询数据
    public static void get(String tablename, String row) throws IOException{
        HTable table = new HTable(conf,tablename);
        Get g = new Get(Bytes.toBytes(row));
        Result result = table.get(g);
        System.out.println("Get: "+ result);
    }

    //显示所有数据，通过HTable Scan来获取已有表的信息
    public static void scan(String tablename) throws Exception{
        HTable table = new HTable(conf, tablename);
        Scan s = new Scan();
        ResultScanner rs = table.getScanner(s);
        for(Result r:rs){
            System.out.println("Scan: "+ r);
        }
    }

    public static boolean delete(String tablename) throws IOException{
        HBaseAdmin admin = new HBaseAdmin(conf);
        if(admin.tableExists(tablename)){
            try{
                admin.disableTable(tablename);
                admin.deleteTable(tablename);
            }catch (Exception e){
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String tablename = "hbase_tb1";
        String columnFamily = "cf";

        try{
            //创建表
            //HBaseTestCase.create(tablename, columnFamily);
            //插入数据
            //HBaseTestCase.put(tablename, "row1", columnFamily , "cl1", "data");
            //查询数据
            //HBaseTestCase.get(tablename, "row1");
            //显示所有表数据
            //HBaseTestCase.scan(tablename);

            //删除表
            /*if(true == HBaseTestCase.delete(tablename)){
                System.out.println("Delete table:"+tablename+"success!");
            }*/
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
