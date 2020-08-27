import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

public class dmlScan{
    public static void main(String args[]) throws IOException {
        Configuration config = HBaseConfiguration.create();
        HTable table = new HTable(config,"employees");
        Scan scan = new Scan();
        scan.addColumn(Bytes.toBytes("personal"), Bytes.toBytes("name"));
        scan.setStartRow(Bytes.toBytes("eid-001"));
        ResultScanner result = table.getScanner(scan);
        for(Result res:result){
            byte[] val;
            val = res.getValue(Bytes.toBytes("personal"), Bytes.toBytes("name"));
            System.out.println("Row-value:"+Bytes.toString(val));
        }
        table.close();
    }
}
