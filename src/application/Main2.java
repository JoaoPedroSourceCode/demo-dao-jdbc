package application;

import model.dao.DepartmentDao;
import model.dao.impl.DaoFactory;
import model.entities.Department;

public class Main2 {

    public static void main(String[] args) {

        Department department = new Department(null, "Cloud Services");

        DepartmentDao departmentDao = DaoFactory.createDepartmentDao();

        System.out.println("------------------TEST - Insert------------------");

        departmentDao.insert(department);

        System.out.println("------------------TEST - Update------------------");

        Department dep = department;
        dep.setName("AI Agents");
        departmentDao.update(dep);

        System.out.println("------------------TEST - DeleteById------------------");

        departmentDao.deleteById(8);

        System.out.println("------------------TEST - FindById------------------");

        System.out.println(departmentDao.findById(11));

        System.out.println("------------------TEST - FindAll------------------");

        System.out.println(departmentDao.findAll());
    }
}
