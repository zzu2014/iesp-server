package server.dao;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * Created by TangFei on 2014/8/14.
 */
public class UnZipDAOImpl implements UnZipDAO {

    StringBuffer  jsonString = new StringBuffer();  //保存zip文件中联系人的信息

    @Override
    public String unzip_method(String path) {
        try {
            ZipFile zipFile = new ZipFile(path);
            Enumeration emu = zipFile.entries();
            while(emu.hasMoreElements()){    //遍历zip文件
                ZipEntry entry = (ZipEntry)emu.nextElement();
                BufferedReader br = new BufferedReader(new InputStreamReader(zipFile.getInputStream(entry),"UTF-8"));
                while (br.ready()) {
                    jsonString.append(br.readLine());
                }
                br.close();
            }
            zipFile.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new String(jsonString);
    }
}
