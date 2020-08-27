import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

public class dmlDelete {
    public static void main(String args[])throws IOException {
        Configuration config = HBaseConfiguration.create();
        HTable table=new HTable(config, "employees");
        Delete del=new Delete(Bytes.toBytes("eid-101"));
        del.deleteColumn(Bytes.toBytes("personal"),Bytes.toBytes("name") );
        table.delete(del);
        System.out.println("Value Deleted");
        table.close();
    }
}
