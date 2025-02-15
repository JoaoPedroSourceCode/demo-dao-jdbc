package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.DepartmentDao;
import model.entities.Department;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DepartmentDaoJDBC implements DepartmentDao {
    private String Name;
    private Integer id;

    private Connection conn = null;

    public DepartmentDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Department department) {
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement(
                    "INSERT INTO department "
                            + "(Name) "
                            + "VALUES "
                            + "(?) ", PreparedStatement.RETURN_GENERATED_KEYS);

            st.setString(1, department.getName());

            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    department.setId(id);
                }
                DB.closeResultSet(rs);
            } else {
                throw new DbException("No department inserted");
            }
        } catch (SQLException se) {
            throw new DbException(se.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void update(Department department) {
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement(
                    "UPDATE department "
                            + "SET Name = ? "
                            + "WHERE Id = ? ");

            st.setString(1, department.getName());
            st.setInt(2, department.getId());

            int rowsAffected = st.executeUpdate();

            if (rowsAffected == 0) {
                throw new DbException("No rows updated");
            }
        } catch (SQLException se) {
            throw new DbException(se.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void deleteById(Integer id) {
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement("DELETE FROM department WHERE Id = ?");

            st.setInt(1, id);
            int rowsAffected = st.executeUpdate();

            if (rowsAffected == 0) {
                throw new DbException("Failed at deleting department");
            }

        } catch (SQLException se) {
            throw new DbException(se.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    public Department instantiateDepartment(ResultSet rs) throws SQLException {
        Department dep = new Department();
        dep.setName(rs.getString("Name"));
        dep.setId(rs.getInt("Id"));
        return dep;
    }

    @Override
    public Department findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT * FROM department WHERE Id = ?");

            st.setInt(1, id);
            rs = st.executeQuery();

            if (rs.next()) {
                Department dep = instantiateDepartment(rs);
                return dep;
            }

            return null;
        }

        catch (SQLException se) {
            throw new DbException(se.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public List<Department> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;

        List<Department> departmentList = new ArrayList<>();

        Map<Integer, Department> departmentMap = new HashMap<>();

        try {
            st = conn.prepareStatement(
                    "SELECT * FROM department " +
                            "ORDER BY Id ");

            rs = st.executeQuery();

            while (rs.next()) {

                Department dep = departmentMap.get(rs.getInt("Id"));

                if (dep == null) {
                    dep = instantiateDepartment(rs);
                    departmentMap.put(rs.getInt("Id"), dep);
                    departmentList.add(dep);
                }
            }

            return departmentList;
        }

        catch (SQLException se) {
            throw new DbException(se.getMessage());
        }

        finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }
}



