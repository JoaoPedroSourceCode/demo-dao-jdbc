package model.dao;

import model.entities.Department;
import java.util.List;

public interface DepartmentDao {

    public void insert(Department department);

    public void update(Department department);

    public void deleteById(Integer id);

    public Integer findById(Integer id);

    List <Department> findAll();
}
