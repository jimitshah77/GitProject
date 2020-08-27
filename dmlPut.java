import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
        import org.apache.hadoop.hbase.HBaseConfiguration;
        import org.apache.hadoop.hbase.MasterNotRunningException;
        import org.apache.hadoop.hbase.ZooKeeperConnectionException;
        import org.apache.hadoop.hbase.client.HTable;
        import org.apache.hadoop.hbase.client.Put;
        import org.apache.hadoop.hbase.util.Bytes;

public class dmlPut {
    public static void main(String args[]) throws MasterNotRunningException,
            ZooKeeperConnectionException,IOException{
        Configuration conf= HBaseConfiguration.create();
        try{
            HTable table=new HTable(conf,"employees");

            Put put1= new Put(Bytes.toBytes("eid-100"));
            put1.add(Bytes.toBytes("personal"),Bytes.toBytes("name"),Bytes.toBytes("Shivam"));
            put1.add(Bytes.toBytes("professional"),Bytes.toBytes("experience"),Bytes.toBytes("4"));

            Put put2= new Put(Bytes.toBytes("eid-101"));
            put2.add(Bytes.toBytes("personal"),Bytes.toBytes("name"),Bytes.toBytes("Mitu"));
            put2.add(Bytes.toBytes("professional"),Bytes.toBytes("experience"),Bytes.toBytes("7"));

/*
            List<Put> putList= new ArrayList<Put>();
            putList.add(put1);
            putList.add(put2);
            System.out.println("Contents added.");
*/


            table.put(put1);
            table.put(put2);
            System.out.println("Contents added.");

        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

}