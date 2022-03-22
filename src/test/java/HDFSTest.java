import com.elon33.netdisc.model.HdfsDAO;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.mapred.JobConf;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HDFSTest {
    /**
     * HDFS API测试
     */
    @Test
    public void testHDFS() {
        JobConf conf = HdfsDAO.config();
        HdfsDAO hdfs = new HdfsDAO(conf);

        try {
            System.out.println(conf.get("fs.defaultFS"));
            hdfs.mkdirs("/root");
            hdfs.copyFile("./pom.xml", "/root/pom.xml");
            hdfs.ls("/root");
            hdfs.download("/root/pom.xml", "./target/pom.xml");
            hdfs.rmr("/root/pom.xml");

            hdfs.copyFile("./pom.xml", "/root/pom.xml");
            hdfs.copyFile("./README.md", "/root/README.md");
        } catch (Exception e) {
            System.out.println("执行失败!");
            e.printStackTrace();
            return;
        }
        System.out.println("执行成功!");
    }

    @Test
    public void Listfile() {
        JobConf conf = HdfsDAO.config();
        HdfsDAO hdfs = new HdfsDAO(conf);
        try {
            System.out.println(conf.get("fs.defaultFS"));
            FileStatus[] list = hdfs.ls("/root");
            ArrayList<String> ans = new ArrayList<String>();
            for (int i = 0; i < list.length; i++) {

                ans.add((list[i].isDirectory() ? "目录" : "文件"));
                ans.add(list[i].isDirectory() ? "-":String.valueOf(list[i].getLen() / 1024));
            }
            System.out.println(ans);
        } catch (Exception e) {
            System.out.println("执行失败!");
            e.printStackTrace();
            return;
        }
        System.out.println("执行成功!");
    }
}
