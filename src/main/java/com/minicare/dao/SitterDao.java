package com.minicare.dao;

import com.minicare.model.*;
import com.minicare.model.Member;
import com.minicare.model.Sitter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Set;

public class SitterDao{
    private static SitterDao sitterDao;

    private SitterDao(){

    }

    static{
        sitterDao = new SitterDao();
    }

    public static SitterDao getInstance(){
        return sitterDao;
    }

    public void insertSitter(Sitter sitterModel) throws ClassNotFoundException, SQLException {
        Connection connection = JDBCHelper.getConnection();
        PreparedStatement preparedStatement;
        MemberDao memberDao = MemberDao.getInstance();
        memberDao.insertMember(connection,sitterModel);

        Set<Member> memberSet = memberDao.getMember(sitterModel.getEmail());
        Iterator<Member> iterator = memberSet.iterator();
        Member member = iterator.next();
        int id = member.getMemberId();

        String sql = "insert into sitter(MemberId,YearsOfExperience,ExpectedPay) values (?,?,?)";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,id);
        preparedStatement.setInt(2,sitterModel.getYearsOfExperience());
        preparedStatement.setDouble(3,sitterModel.getExpectedPay());
        preparedStatement.executeUpdate();

        try { preparedStatement.close(); } catch (Exception e) { /* ignored */ }
        try { connection.close(); } catch (Exception e) { /* ignored */ }
    }

    public void deleteSitter(int memberId) throws ClassNotFoundException, SQLException{
        Connection connection = JDBCHelper.getConnection();
        String sql ="update sitter SET Status=? where MemberId=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, Status.INACTIVE.name());
        preparedStatement.setInt(2,memberId);
        preparedStatement.executeUpdate();

        try { preparedStatement.close(); } catch (Exception e) { /* ignored */ }
        try { connection.close(); } catch (Exception e) { /* ignored */ }
    }

    public Sitter getSitter(int sitterId) throws ClassNotFoundException,SQLException{
        Sitter sitterModel = new Sitter();
        Connection connection = JDBCHelper.getConnection();
        String sql = "select MemberId,YearsOfExperience,ExpectedPay from sitter where MemberId=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,sitterId);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            sitterModel.setMemberId(resultSet.getInt("MemberId"));
            sitterModel.setYearsOfExperience(resultSet.getInt("YearsOfExperience"));
            sitterModel.setExpectedPay(resultSet.getDouble("ExpectedPay"));
        }

        preparedStatement.close();
        connection.close();
        return sitterModel;
    }

    public void editSitter(Sitter sitterModel) throws ClassNotFoundException, SQLException{
        Connection connection = JDBCHelper.getConnection();
        String sql = "update sitter SET YearsOfExperience=? , ExpectedPay=? where MemberId=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,sitterModel.getYearsOfExperience());
        preparedStatement.setDouble(2,sitterModel.getExpectedPay());
        preparedStatement.setInt(3,sitterModel.getMemberId());
        preparedStatement.executeUpdate();

        preparedStatement.close();
        connection.close();
    }
}
