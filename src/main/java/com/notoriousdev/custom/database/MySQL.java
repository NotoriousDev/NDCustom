package com.notoriousdev.custom.database;

import com.notoriousdev.custom.NDCustom;

import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQL
{

    private final NDCustom plugin;
    private String host;
    private String port;
    private String database;
    private String username;
    private String password;
    private Connection conn;

    public MySQL(NDCustom plugin)
    {
        this.plugin = plugin;
        this.host = plugin.getConfig().getString("");
        this.port = plugin.getConfig().getString("");
        this.database = plugin.getConfig().getString("");
        this.username = plugin.getConfig().getString("");
        this.password = plugin.getConfig().getString("");
    }

    private void init() throws SQLException, ClassNotFoundException
    {
        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection(String.format("jdbc:mysql://%s:%s/%s?autoReconnect=true", host, port, database), username, password);
    }

    public void kill() throws SQLException
    {
        if (conn != null)
        {
            conn.close();
            conn = null;
        }
    }

    public void register(String playername, String email, String password) throws SQLException, MalformedURLException, IOException
    {
//        String hash = plugin.utils.postPasswordHash(password);
//        String id;
//        PreparedStatement ps = conn.prepareStatement("SELECT password,user_email,user_id FROM phpbb_users WHERE username_clean=?;");
//        ps.setString(1, playername.toLowerCase());
//        ResultSet rs = ps.executeQuery();
//        String _password = rs.getString(1);
//        String _email = rs.getString(2);
//        id = String.valueOf(rs.getInt(3));
//        rs.close();
//        ps.close();
//        if (_password.equals(hash) && _email.equals(email)) {
//            conn.createStatement().executeUpdate(String.format("UPDATE phpbb_acl_users SET auth_role_id=2 WHERE user_id=%s", id));
//        } else {
//            plugin.getServer().getPlayer(playername).sendMessage("Error");
//        }
    }
}
