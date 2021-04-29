package com.enjoy.xa;

import com.enjoy.domain.Order;
import com.enjoy.util.SnowflakeUtil;
import com.mysql.jdbc.jdbc2.optional.MysqlXADataSource;
import com.mysql.jdbc.jdbc2.optional.MysqlXid;

import javax.sql.XAConnection;
import javax.transaction.xa.XAException;
import javax.transaction.xa.XAResource;
import javax.transaction.xa.Xid;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;


public class XADemo {

    private static final String DB_3307 = "jdbc:mysql://192.168.1.100:3307/enjoy";
    private static final String DB_3317 = "jdbc:mysql://192.168.1.100:3317/enjoy";
    private final String connectionStr;
    private final String user;
    private final String password;
    private final Xid xid;

    public XADemo(String connectionStr, String user, String password) {
        this.connectionStr = connectionStr;
        this.user = user;
        this.password = password;
        Random random = new Random();
        byte[] gtrid = new byte[4];
        byte[] bqual = new byte[4];
        random.nextBytes(gtrid);
        random.nextBytes(bqual);
        xid = new MysqlXid(gtrid, bqual, 100);
    }

    public Xid getXid() {
        return xid;
    }

    public static void main(String[] args) {
        XADemo xaDemo = new XADemo(DB_3307, "root", "root");
        String sql_order = "insert into t_order(id,product,province,created) values(?,?,?,?)";
        Order order = new Order();
        order.setId(SnowflakeUtil.nextId());
        order.setProduct("Apparel");
        order.setProvince("T-Short");
        try {
            XAResource xaResource = xaDemo.getResouce(sql_order, order);
            // 第一阶段
            int result = xaResource.prepare(xaDemo.getXid());
            // 第二阶段
            if (XAResource.XA_OK == result) {
                xaResource.commit(xaDemo.getXid(), false);
            } else {
                xaResource.rollback(xaDemo.getXid());
            }
        } catch (XAException e) {
            e.printStackTrace();
        }
    }

    public XAResource getResouce(String sql, Order params) throws XAException {
        try {
            MysqlXADataSource dataSource = getDataSource(this.connectionStr, this.user, this.password);
            XAConnection xaConnection = dataSource.getXAConnection();
            Connection connection = xaConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            if (params != null) {
                preparedStatement.setLong(1, params.getId());
                preparedStatement.setString(2, params.getProduct());
                preparedStatement.setString(3, params.getProvince());
                preparedStatement.setDate(4, new Date(System.currentTimeMillis()));
            }
            XAResource xaResource = xaConnection.getXAResource();
            xaResource.start(xid, XAResource.TMNOFLAGS);
            int updateResult = preparedStatement.executeUpdate();
            xaResource.end(xid, XAResource.TMSUCCESS);
            return xaResource;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private MysqlXADataSource getDataSource(String connection, String user, String password) {
        MysqlXADataSource mysqlXADataSource = new MysqlXADataSource();
        try {
            mysqlXADataSource.setURL(connection);
            mysqlXADataSource.setUser(user);
            mysqlXADataSource.setPassword(password);
            mysqlXADataSource.setConnectTimeout(5000);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mysqlXADataSource;
    }
}
