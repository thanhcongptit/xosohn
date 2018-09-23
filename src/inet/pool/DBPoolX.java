package inet.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.NoSuchElementException;

import inet.util.Logger;

public class DBPoolX {

    private static final Logger logger = new Logger("DBPoolX");
    public final static int MAX_CONNECTIONS = 5; //max connections per pool

    private static final Map poolTable = new Hashtable(); //name (String) - pool (LinkedList)
    private static final Map dataTable = new Hashtable(); //name (String) - data (DBPoolXData)
    private static final Map dbpoolTable = new Hashtable(); //name (String) - DBPool (DBPoolX)

    private LinkedList pool = null;
    private DBPoolXData data = null;
    private String name = null;

    public String getName() {
        return name;
    }

    static {//Make connections
        if (!DBPoolXData.isLoaded()) {
            DBPoolXData.loadData();
        }
        buildPools();
    }

    private DBPoolX(String name, LinkedList pool, DBPoolXData data) {
        this.name = name;
        this.pool = pool;
        this.data = data;
    }

    public static DBPoolX getInstance(String poolName) throws DBPoolNotFoundException {
        DBPoolX poolX = (DBPoolX) dbpoolTable.get(poolName);
        if (poolX == null) {
            throw new DBPoolNotFoundException("Pool not found: " + poolName);
        }

        return poolX;
    }

    private static void buildPools() {
        DBPoolXData data = null;
        for (int i = 0; i < DBPoolXData.getPoolDataList().size(); i++) {
            data = (DBPoolXData) DBPoolXData.getPoolDataList().get(i);

            if (data != null) {
                buildPool(data);
            }
        }
    }

    private static void buildPool(DBPoolXData data) {
        try {
            //Táº¡o sá»‘ connection Ä‘áº¿n 1 DB --> Ä‘Æ°a vÃ o 1 pool
            LinkedList pool = new LinkedList();
            Connection conn = null;
            for (int j = 0; j < data.getParameter().getCount(); j++) {
                conn = makeDBConnection(data.getParameter());
                if (conn != null) {
                    pool.addLast(conn);
                }
            }

            String poolName = null;
            for (int j = 0; j < data.getPoolnames().size(); j++) {
                poolName = (String) data.getPoolnames().get(j);
                poolTable.put(poolName, pool);
                dataTable.put(poolName, data);
                dbpoolTable.put(poolName, new DBPoolX(poolName, pool, data));
            }
        } catch (SQLException ex) {
            logger.error("Error: " + ex.getMessage());
            logger.log("Khong noi dc voi database roi !");
        }

    }

    // Remove and close all connections in pool
    public static void releaseAll() {
        logger.log("Closing connections in ALL pools... ");

        String poolName = null;
        DBPoolX dbPoolX = null;
        for (Iterator it = dbpoolTable.keySet().iterator(); it.hasNext();) {
            poolName = (String) it.next();
            dbPoolX = (DBPoolX) dbpoolTable.get(poolName);
            dbPoolX.release();
        }
    }

    private java.sql.Connection conn = null;

    public synchronized Connection getConnection() {
        conn = null;
        while (conn == null) {
            try {
                synchronized (pool) {
                    try {
                        if (pool.size() > 0) {
                            conn = (java.sql.Connection) pool.removeFirst();
                        }
                    } catch (NoSuchElementException ex) {
                        ex.printStackTrace();
                    }
                }
                if (conn == null) {
                    conn = makeDBConnection(this.data.getParameter());
//                    System.out.println("get connecttion");
//                    conn = MySQLAccess.getConnection();
//                    
//                    if(conn == null) {
//                        System.out.println("fail connection");
//                        
//                    } else {
//                        System.out.println("connection ok");
//                    }
                }

            } catch (Exception ex) {
                logger.error("getConnection: " + ex.getMessage());
                ex.printStackTrace();
                try {
                    Thread.sleep(100);
                } catch (Exception ex2) {
                }
            } finally {
                try {
                    if (conn != null) {
                        conn.setAutoCommit(true);
                    } else {
                        Thread.sleep(1000);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        return conn;
    }

    public void putConnection(java.sql.Connection conn) {
        try {

            // Ignore closed connection
            if (conn == null || conn.isClosed()) {
                logger.log("putConnection: conn is null or closed: " + conn);
                return;
            }

            synchronized (pool) {
                if (pool.size() >= MAX_CONNECTIONS) {
                    conn.close();
                    return;
                }
                pool.addLast(conn);
                pool.notify();
            }
        } catch (SQLException ex) {
        }
    }

    // Remove and close all connections in pool
    public void release() {
        logger.log("Closing connections in pool " + getName());
        synchronized (pool) {
            for (int i = 0; i < pool.size(); i++) {
                conn = (Connection) pool.removeFirst();
                if (conn == null) {
                    continue;
                }

                try {
                    conn.close();
                } catch (Exception e) {
                    logger.error("release: Cannot close connection! (maybe closed?)");
                }
            }
            pool.clear();
        }
    }

    public int size() {
        synchronized (pool) {
            return pool.size();
        }
    }

    public boolean isEmpty() {
        synchronized (pool) {
            return pool.isEmpty();
        }
    }

    @Override
    public void finalize() {
        logger.log("Pool " + getName() + " called finalize()");
        release();
    }

    //--------------------------------------------------------------------------
    private static Connection makeDBConnection(ConnectionParameter param) throws SQLException {
        Connection conn = null;
        try {
            Class.forName(param.getDriver());
            conn = DriverManager.getConnection(param.getUrl(), param.getUser(), param.getPassword());
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return conn;
    }

    public static void load() {
    }

    public void releaseConnection(Connection conn, PreparedStatement preStmt) {
        try {
            if (preStmt != null) {
                preStmt.close();
            }

        } catch (SQLException e) {
        	e.printStackTrace();
        }
        
        try {
        	if (conn == null || conn.isClosed()) {
                return;
            }

            synchronized (pool) {
                if (pool.size() >= MAX_CONNECTIONS) {
                    conn.close();
                    return;
                }
                pool.addLast(conn);
                pool.notify();
            }
        } catch (Exception e) {
        	e.printStackTrace();
        }
    }

    public void releaseConnection(Connection conn, PreparedStatement preStmt, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
        	e.printStackTrace();
        }

        releaseConnection(conn, preStmt);
    }

    public void releaseConnection(Connection conn, PreparedStatement preStmt, Statement stmt, ResultSet rs) {
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException e) {
        	e.printStackTrace();
        }

        releaseConnection(conn, preStmt, rs);
    }
}
