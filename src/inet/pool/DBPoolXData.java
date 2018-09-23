package inet.pool;

import java.io.*;
import java.util.*;
import inet.util.*;

public class DBPoolXData {

    private static final String fileName = "/inet/pool/dbpool.conf";

    //Thông tin 1 DB
    private Vector poolnames;
    private ConnectionParameter parameter;

    //Chứa tất cả các DB
    private static final Vector poolDataList = new Vector();

    public static Vector getPoolDataList() {
        return poolDataList;
    }

    public ConnectionParameter getParameter() {
        return parameter;
    }

    public void setParameter(ConnectionParameter parameter) {
        this.parameter = parameter;
    }

    public Vector getPoolnames() {
        return poolnames;
    }

    public void setPoolnames(Vector poolnames) {
        this.poolnames = poolnames;
    }

    @Override
    public String toString() {
        return getPoolnames() + " = " + parameter.toString();
    }
    private static boolean loaded = false;

    public static boolean isLoaded() {
        return loaded;
    }

    public static void loadData() {
        Vector v = new Vector();
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(DBPoolXData.class.getResourceAsStream(fileName)));
            String line = in.readLine();
            for (; line != null; line = in.readLine()) {
                line = line.trim();
                if ("".equals(line) || line.startsWith("#")) {
                    continue;
                }

                int idx = line.indexOf("=");
                if (idx <= 0) {
                    System.out.println("   Seperator '=' is NOT found.");
                    continue;
                }

                String name = line.substring(0, idx).trim();
                String value = line.substring(idx + 1).trim();

                if (name == null || name.equals("")
                        || value == null || value.equals("")) {
                    continue;
                }
                DBPoolXData data = new DBPoolXData();
                data.setPoolnames((Vector) StringTool.parseString(name, ","));
                ConnectionParameter parameter = new ConnectionParameter();

                Collection c = StringTool.parseString(value, ",");
                Iterator it = c.iterator();
                parameter.setDriver((String) it.next());
                parameter.setUrl((String) it.next());
                parameter.setUser((String) it.next());
                String password = (String) it.next();
                try {
                    parameter.setPassword(Encrypter.decrypt(password));
                } catch (Exception ex) {
                    password = "";
                    System.out.println("   DBPoolXData: invalid encrypted password = " + password);
                    ex.printStackTrace();
                }
                try {
                    parameter.setCount(Integer.parseInt((String) it.next()));
                } catch (Exception ex) {
                }
                //Count = 0 -> 10
                if (parameter.getCount() < 1) {
                    parameter.setCount(1);
                }
                if (parameter.getCount() > 10) {
                    parameter.setCount(10);
                }

                data.setParameter(parameter);
                System.out.println(data.toString());
                v.add(data);
            }
            loaded = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        poolDataList.clear();
        poolDataList.addAll(v);
    }
}

class ConnectionParameter {

    private String driver;
    private String url;
    private String user;
    private String password;//clear text
    private int count; //number of connections

    public void setDriver(String s) {
        this.driver = s;
    }

    public void setUrl(String s) {
        this.url = s;
    }

    public void setUser(String s) {
        this.user = s;
    }

    public void setPassword(String s) {
        this.password = s;
    }

    public void setCount(int s) {
        this.count = s;
    }

    public String getDriver() {
        return this.driver;
    }

    public String getUrl() {
        return this.url;
    }

    public String getUser() {
        return this.user;
    }

    public String getPassword() {
        return this.password;
    }

    public int getCount() {
        return this.count;
    }

    @Override
    public String toString() {
        return "<" + getDriver() + ">,<" + getUrl() + ">,<" + getUser() + ">,<***>,<" + getCount() + ">";
    }

    public static void main(String[] args) {
        System.out.println(Encrypter.encrypt("xoso##2015"));
    }
}
