package com.minicare.dao;

import com.minicare.model.MemberModel;
import com.minicare.model.SeekerModel;
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

    public void insertMember(Connection connection, MemberModel memberModel) throws SQLException{

            PreparedStatement preparedStatement;

            String sql = "insert into member(FirstName,LastName,PhoneNumber,EmailAddress,Type,Address,Password) values(?,?,?,?,?,?,?)";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, memberModel.getFirstName());
            preparedStatement.setString(2, memberModel.getLastName());
            preparedStatement.setLong(3, memberModel.getPhoneNumber());
            preparedStatement.setString(4, memberModel.getEmail());
            preparedStatement.setString(5, memberModel.getType().name());
            preparedStatement.setString(6, memberModel.getAddress());
            preparedStatement.setString(7, memberModel.getPassword());
            preparedStatement.executeUpdate();

    }

    public Set<MemberModel> getMember(String email) throws SQLException,ClassNotFoundException {
        Connection connection = JDBCHelper.getConnection();
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        Set<MemberModel> memberModelSet = new HashSet<MemberModel>();
        String sql = "select * from member where EmailAddress = ? and Status=?";

        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, email);
        preparedStatement.setString(2,Status.ACTIVE.name());
        resultSet = preparedStatement.executeQuery();
        while(true){
            boolean contains = resultSet.next();
            if(contains){
                MemberModel memberModel = new MemberModel();
                memberModel.setMemberId(resultSet.getInt("Id"));
                memberModel.setFirstName(resultSet.getString("FirstName"));
                memberModel.setLastName(resultSet.getString("LastName"));
                memberModel.setPhoneNumber(resultSet.getLong("PhoneNumber"));
                memberModel.setEmail(resultSet.getString("EmailAddress"));
                memberModel.setType(Type.valueOf(resultSet.getString("Type")));
                memberModel.setAddress(resultSet.getString("Address"));
                memberModel.setStatus(Status.valueOf(resultSet.getString("Status")));
                memberModel.setPassword(resultSet.getString("Password"));
                memberModelSet.add(memberModel);
            }else{
                break;
            }
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
        return memberModelSet;
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

    public void editMember(MemberModel memberModel) throws NamingException,SQLException{
        Connection connection = JNDIHelper.getJNDIConnection();
        String sql ="update member SET FirstName=? , LastName=? , PhoneNumber=?  , Address=? " +
                "where Id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,memberModel.getFirstName());
        preparedStatement.setString(2,memberModel.getLastName());
        preparedStatement.setLong(3,memberModel.getPhoneNumber());
        preparedStatement.setString(4,memberModel.getAddress());
        preparedStatement.setInt(5,memberModel.getMemberId());
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

    public Set<MemberModel> getAllMembers() throws NamingException,SQLException{
        Set<MemberModel> memberModelSet = new HashSet<>();
        Connection connection = JNDIHelper.getJNDIConnection();
        String sql = "select * from member where status=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,Status.ACTIVE.name());
        ResultSet resultSet = preparedStatement.executeQuery();
        while(true){
            boolean contains = resultSet.next();
            if(contains){
                MemberModel memberModel = new MemberModel();
                memberModel.setMemberId(resultSet.getInt("Id"));
                memberModel.setFirstName(resultSet.getString("FirstName"));
                memberModel.setLastName(resultSet.getString("LastName"));
                memberModel.setPhoneNumber(resultSet.getLong("PhoneNumber"));
                memberModel.setEmail(resultSet.getString("EmailAddress"));
                memberModel.setType(Type.valueOf(resultSet.getString("Type")));
                memberModel.setAddress(resultSet.getString("Address"));
                memberModel.setStatus(Status.ACTIVE);
                memberModelSet.add(memberModel);
            }else{
                break;
            }
        }
        return memberModelSet;
    }

    public Set<MemberModel> searchMember(String email) throws NamingException,SQLException{
        Set<MemberModel> memberModelSet = new HashSet<>();
        Connection connection = JNDIHelper.getJNDIConnection();
        String sql = "select * from member where EmailAddress like ? and status=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,"%"+email+"%");
        preparedStatement.setString(2,Status.ACTIVE.name());
        ResultSet resultSet = preparedStatement.executeQuery();
        while(true){
            boolean contains = resultSet.next();
            if(contains){
                MemberModel memberModel = new MemberModel();
                memberModel.setMemberId(resultSet.getInt("Id"));
                memberModel.setFirstName(resultSet.getString("FirstName"));
                memberModel.setLastName(resultSet.getString("LastName"));
                memberModel.setPhoneNumber(resultSet.getLong("PhoneNumber"));
                memberModel.setEmail(resultSet.getString("EmailAddress"));
                memberModel.setType(Type.valueOf(resultSet.getString("Type")));
                memberModel.setAddress(resultSet.getString("Address"));
                memberModel.setStatus(Status.ACTIVE);
                memberModelSet.add(memberModel);
            }else{
                break;
            }
        }
        return memberModelSet;
    }
}
