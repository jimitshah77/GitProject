import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.MasterNotRunningException;
import org.apache.hadoop.hbase.ZooKeeperConnectionException;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.util.Bytes;

public class HBaseDDL {
    public static void main(String args[]) throws MasterNotRunningException,
            ZooKeeperConnectionException,IOException{
        Configuration conf= HBaseConfiguration.create();

        HBaseAdmin admin = new HBaseAdmin(conf);
        HTableDescriptor des = new HTableDescriptor(Bytes.toBytes("employees"));

        HColumnDescriptor personalCF = new HColumnDescriptor("personal");
        des.addFamily(new HColumnDescriptor("personal"));

        HColumnDescriptor professionalCF = new HColumnDescriptor("professional");
        des.addFamily(new HColumnDescriptor("professional"));

        if(admin.tableExists("employees")){
            System.out.println("Table already Exists... Hence Disabling and deleting it.");
            admin.disableTable("employees");
            admin.deleteTable("employees");
            System.out.println("Table 'employees' DELETED.");
        }

        admin.createTable(des);
        System.out.println("Table 'employee' sucessfully CREATED");

    }
}