package com.wangzhihui.Dao;

import com.wangzhihui.model.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class UserDao  implements  IUserDao{

    QueryRunner sw = new QueryRunner();

    @Override
    public boolean saveUser(Connection con, User user) throws SQLException {
        String sql = "insert into usertable(username,password,email,gender,birth) values(?,?,?,?,?)";
        int i = sw.update(con,sql,new Object[]{user.getUsername(),user.getPassword(),user.getEmail(),user.getGender(),user.getBirthdate()});
        if (i != 0) {
            return true;
        }else return false;
    }

    @Override
    public int deleteUser(Connection con, User user) throws SQLException {
        String sql = "delete from usertable where id = ?";
        return  sw.update(con,sql,new BeanListHandler<>(User.class));
    }

    @Override
    public int updateUser(Connection con, User user) throws SQLException {
        String sql = "update usertable set name = ?,password = ?, email = ?, gender = ?, birth = ? where id = ?";
        //TODO 5.1 - write updUpdate sql where id =?
        //TODO 5.2 - create prepared ststement
        //TODO 5.3 - executeUpdate()
        //Todo 5.4 return int value
        return sw.update(con,sql,new Object[]{user.getUsername(),user.getPassword(),user.getEmail(),user.getGender(),user.getBirthdate(),user.getID()});
    }


    @Override
    public User findById(Connection con, Integer id) throws SQLException {
        String sql = "select * from usertable where id =  " + id;
        return  sw.query(con,sql,new BeanHandler<>(User.class));
    }

    @Override
    public User findByUsernamePassword(Connection con, String username, String password) throws SQLException {
        //use for login
        //select --- where username=? and password=?--- i will show you now
        String sql = "select id,username,password,email,gender,birthdate from usertable where username=? and password=?";
        PreparedStatement st = con.prepareStatement(sql);
        st.setString(1, username);
        st.setString(2, password);

        ResultSet rs = st.executeQuery();
        User user = null;
        if (rs.next()) {
            //get from rs and into user model
            user = new User();
            user.setID(rs.getInt("id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setEmail(rs.getString("email"));
            user.setGender(rs.getString("gender"));
            user.setBirthdate(rs.getDate("birthdate"));

            //done

            return user;
        }else {
            return  null;
        }
        //System.out.println(con);
        //String sql2 = "select * from usertable where name = '" + username + "' and password = '" + password + "'";
        // User user = qr.query(con, sql2, new BeanHandler<>(User.class));
        // System.out.println(user.toString());
        // return user;

    }

    @Override
    public List<User> findByUsername(Connection con, String username) throws SQLException {
        String sql = "select * from usertable where username = " + username;
        return  sw.query(sql,new BeanListHandler<>(User.class));
    }

    @Override
    public List<User> findByPassword(Connection con, String password) throws SQLException {
        String sql = "select * from usertable where password = " + password;
        return  sw.query(con,sql,new BeanListHandler<>(User.class));
    }

    @Override
    public List<User> findByEmail(Connection con, String email) throws SQLException {
        String sql = "select * from usertable where email = " + email;
        return  sw.query(con,sql,new BeanListHandler<>(User.class));
    }

    @Override
    public List<User> findByGender(Connection con, String gender) throws SQLException {
        String sql = "select * from usertable where gender = " + gender;
        return  sw.query(con,sql,new BeanListHandler<>(User.class));
    }

    @Override
    public List<User> findByBirthdate(Connection con, Date birthDate) throws SQLException {
        String sql = "select * from usertable where birthdate = " + birthDate;
        return  sw.query(con,sql,new BeanListHandler<>(User.class));
    }

    @Override
    public List<User> findAllUser(Connection con) throws SQLException {
        String sql = "select * from usertable";
        return sw.query(con,sql,new BeanListHandler<>(User.class));
    }


}
