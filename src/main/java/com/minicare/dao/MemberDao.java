package com.minicare.dao;

import com.minicare.model.Member;
import com.minicare.model.Status;
import com.minicare.model.Type;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class MemberDao {
    private static MemberDao memberDao;

    private MemberDao() {

    }

    static {
        memberDao = new MemberDao();
    }

    public static MemberDao getInstance() {
        return memberDao;
    }

    public void insertMember(Connection connection, Member member) throws SQLException{

            PreparedStatement preparedStatement;

            String sql = "insert into member(FirstName,LastName,PhoneNumber,EmailAddress,Type,Address,Password) values(?,?,?,?,?,?,?)";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, member.getFirstName());
            preparedStatement.setString(2, member.getLastName());
            preparedStatement.setLong(3, member.getPhoneNumber());
            preparedStatement.setString(4, member.getEmail());
            preparedStatement.setString(5, member.getType().name());
            preparedStatement.setString(6, member.getAddress());
            preparedStatement.setString(7, member.getPassword());
            preparedStatement.executeUpdate();

    }

    public Set<Member> getMember(String email) throws SQLException,ClassNotFoundException {
        Connection connection = JDBCHelper.getConnection();
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        Set<Member> memberSet = new HashSet<Member>();
        String sql = "select * from member where EmailAddress = ? and Status=?";

        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, email);
        preparedStatement.setString(2,Status.ACTIVE.name());
        resultSet = preparedStatement.executeQuery();
        while(true){
            boolean contains = resultSet.next();
            if(contains){
                Member member = new Member();
                member.setMemberId(resultSet.getInt("Id"));
                member.setFirstName(resultSet.getString("FirstName"));
                member.setLastName(resultSet.getString("LastName"));
                member.setPhoneNumber(resultSet.getLong("PhoneNumber"));
                member.setEmail(resultSet.getString("EmailAddress"));
                member.setType(Type.valueOf(resultSet.getString("Type")));
                member.setAddress(resultSet.getString("Address"));
                member.setStatus(Status.valueOf(resultSet.getString("Status")));
                member.setPassword(resultSet.getString("Password"));
                memberSet.add(member);
            }else{
                break;
            }
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
        return memberSet;
    }

    public void deleteMember(int memberId) throws NamingException,SQLException{
        Connection connection = JNDIHelper.getJNDIConnection();
        String sql = "update member SET Status=? where Id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, Status.INACTIVE.name());
        preparedStatement.setInt(2,memberId);
        preparedStatement.executeUpdate();

        try { preparedStatement.close(); } catch (Exception e) { /* ignored */ }
        try { connection.close(); } catch (Exception e) { /* ignored */ }
    }

    public void editMember(Member member) throws NamingException,SQLException{
        Connection connection = JNDIHelper.getJNDIConnection();
        String sql ="update member SET FirstName=? , LastName=? , PhoneNumber=?  , Address=? " +
                "where Id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, member.getFirstName());
        preparedStatement.setString(2, member.getLastName());
        preparedStatement.setLong(3, member.getPhoneNumber());
        preparedStatement.setString(4, member.getAddress());
        preparedStatement.setInt(5, member.getMemberId());
        preparedStatement.executeUpdate();

        preparedStatement.close();
        connection.close();
    }

    public void updatePassword(int memberId , String newPassword)  throws NamingException,SQLException{
        Connection connection = JNDIHelper.getJNDIConnection();
        String sql = "update member SET password = ? where Id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,newPassword);
        preparedStatement.setInt(2,memberId);
        preparedStatement.executeUpdate();

        preparedStatement.close();
        connection.close();
    }

    public Set<Member> getAllMembers() throws NamingException,SQLException{
        Set<Member> memberSet = new HashSet<>();
        Connection connection = JNDIHelper.getJNDIConnection();
        String sql = "select * from member where status=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,Status.ACTIVE.name());
        ResultSet resultSet = preparedStatement.executeQuery();
        while(true){
            boolean contains = resultSet.next();
            if(contains){
                Member member = new Member();
                member.setMemberId(resultSet.getInt("Id"));
                member.setFirstName(resultSet.getString("FirstName"));
                member.setLastName(resultSet.getString("LastName"));
                member.setPhoneNumber(resultSet.getLong("PhoneNumber"));
                member.setEmail(resultSet.getString("EmailAddress"));
                member.setType(Type.valueOf(resultSet.getString("Type")));
                member.setAddress(resultSet.getString("Address"));
                member.setStatus(Status.ACTIVE);
                memberSet.add(member);
            }else{
                break;
            }
        }
        return memberSet;
    }

    public Set<Member> searchMember(String email) throws NamingException,SQLException{
        Set<Member> memberSet = new HashSet<>();
        Connection connection = JNDIHelper.getJNDIConnection();
        String sql = "select * from member where EmailAddress like ? and status=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,"%"+email+"%");
        preparedStatement.setString(2,Status.ACTIVE.name());
        ResultSet resultSet = preparedStatement.executeQuery();
        while(true){
            boolean contains = resultSet.next();
            if(contains){
                Member member = new Member();
                member.setMemberId(resultSet.getInt("Id"));
                member.setFirstName(resultSet.getString("FirstName"));
                member.setLastName(resultSet.getString("LastName"));
                member.setPhoneNumber(resultSet.getLong("PhoneNumber"));
                member.setEmail(resultSet.getString("EmailAddress"));
                member.setType(Type.valueOf(resultSet.getString("Type")));
                member.setAddress(resultSet.getString("Address"));
                member.setStatus(Status.ACTIVE);
                memberSet.add(member);
            }else{
                break;
            }
        }
        return memberSet;
    }
}
