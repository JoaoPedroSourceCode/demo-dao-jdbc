package application;


import model.dao.SellerDao;
import model.dao.impl.DaoFactory;
import model.entities.Department;
import model.entities.Seller;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        System.out.println("------------------TEST - FindById------------------");
        SellerDao sellerDao = DaoFactory.createSellerDao ();

        Seller seller = sellerDao.findById(3);

        System.out.println(seller);

        System.out.println("\n------------------TEST - FindByDepartment------------------");

        Department department = new Department(2, null);

        List<Seller> find = sellerDao.findByDepartment(department);

        for (Seller obj : find) {
            System.out.println(obj);
        }

        System.out.println("\n------------------TEST - FindByAll------------------");

        find = sellerDao.findAll();

        for (Seller obj : find) {
            System.out.println(obj);
        }
    }
}