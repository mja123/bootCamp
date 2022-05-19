package com.solvd.university.daos;

import com.solvd.university.daos.interfaces.IBaseDAO;
import com.solvd.university.daos.mySqlImplementation.BaseDAO;
import com.solvd.university.daos.mySqlImplementation.connectionPool.ConnectionPool;
import com.solvd.university.daos.mySqlImplementation.exceptions.ElementNotFoundException;
import com.solvd.university.model.Subject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.solvd.university.daos.mySqlImplementation.enums.EAttributesEntities.*;

public class SubjectDAO extends BaseDAO<Subject> {
    private static final Logger LOGGER = LogManager.getLogger(Subject.class);

    public SubjectDAO(String tableName, String className) {
        super(tableName, className);
    }

    @Override
    public Subject getEntityByID(Long id) throws ElementNotFoundException {
        Subject subject = null;
        ResultSet result = this.getResultSetById(id);
        System.out.println(result + " getEntitybyid");

        try {
            if (result.next()) {
                subject = this.convertResultSet(result);
            }
            return subject;
        } catch (SQLException e) {
            LOGGER.error(e);
        } finally {
            ConnectionPool.getInstance().goBackConnection(this.getConnection());
        }
        return subject;
    }

    private Subject convertResultSet (ResultSet resultSet) {
        Subject result = new Subject();
    System.out.println(resultSet + " convertResultSET");
        try {
            result.setId(resultSet.getLong(ID.getATTRIBUTE()));
            result.setBiannual(resultSet.getBoolean(BIANNUAL.getATTRIBUTE()));
            result.setName(resultSet.getString(NAME.getATTRIBUTE()));
            result.setYear(resultSet.getInt(YEAR.getATTRIBUTE()));
            result.setCreatedAt(resultSet.getDate(CREATED_AT.getATTRIBUTE()));
            result.setDeletedAt(resultSet.getDate(DELETED_AT.getATTRIBUTE()));
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return result;
    }

}
