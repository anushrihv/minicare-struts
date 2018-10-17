package com.minicare.dao;

import com.minicare.model.Member;
import com.minicare.model.Seeker;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Set;

public class SeekerDao {
    private static SeekerDao seekerDao;
    private PreparedStatement preparedStatement;

    private SeekerDao(){

    }

    static {
        seekerDao = new SeekerDao();
    }

    public static SeekerDao getInstance(){
        return seekerDao;
    }

//    public void insertSeeker(Seeker seekerModel) throws ClassNotFoundException, SQLException {
//            Connection connection = JDBCHelper.getConnection();
//            MemberDao memberDao = MemberDao.getInstance();
//            memberDao.insertMember(connection, seekerModel);
//
//            Set<Member> memberSet = memberDao.getMember(seekerModel.getEmail());
//            Iterator<Member> iterator = memberSet.iterator();
//            Member member = iterator.next();
//            int id = member.getMemberId();
//
//            String sql = "insert into seeker(MemberId,NumberOfChildren,SpouseName) values (?,?,?)";
//            preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setInt(1, id);
//            preparedStatement.setInt(2, seekerModel.getNumberOfChildren());
//            preparedStatement.setString(3, seekerModel.getSpouseName());
//            preparedStatement.executeUpdate();
//
//            try { preparedStatement.close(); } catch (Exception e) { /* ignored */ }
//            try { JDBCHelper.closeConnection(); } catch (Exception e) { /* ignored */ }
//    }

    public void insertSeeker(Seeker seeker){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(seeker);
        transaction.commit();
        session.close();
    }

    public Seeker getSeeker(int seekerId) throws ClassNotFoundException, SQLException{
        Seeker seeker = new Seeker();
        Connection connection = JDBCHelper.getConnection();
        String sql = "select MemberId,NumberOfChildren,SpouseName from seeker where MemberId=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,seekerId);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            seeker.setMemberId(resultSet.getInt("MemberId"));
            seeker.setNumberOfChildren(resultSet.getInt("NumberOfChildren"));
            seeker.setSpouseName(resultSet.getString("SpouseName"));
        }

        preparedStatement.close();
        connection.close();
        return seeker;
    }

//    public void editSeeker(Seeker seekerModel) throws ClassNotFoundException, SQLException{
//        Connection connection = JDBCHelper.getConnection();
//        String sql = "update seeker SET NumberOfChildren=? , SpouseName=? where MemberId=?";
//        PreparedStatement preparedStatement = connection.prepareStatement(sql);
//        preparedStatement.setInt(1,seekerModel.getNumberOfChildren());
//        preparedStatement.setString(2,seekerModel.getSpouseName());
//        preparedStatement.setInt(3,seekerModel.getMemberId());
//        preparedStatement.executeUpdate();
//
//        preparedStatement.close();
//        connection.close();
//    }

    public void editSeeker(Seeker seeker){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "update Seeker SET numberOfChildren = ? , spouseName = ? where memberId = ?";
        Query query = session.createQuery(hql);
        query.setInteger(0,seeker.getNumberOfChildren());
        query.setString(1,seeker.getSpouseName());
        query.setInteger(2,seeker.getMemberId());
        query.executeUpdate();
        transaction.commit();
        session.close();
    }
}
