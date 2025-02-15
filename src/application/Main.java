package application;

import db.DB;
import model.dao.SellerDao;
import model.dao.impl.DaoFactory;
import model.entities.Department;
import model.entities.Seller;

import java.text.ParseException;
import java.util.List;
import java.text.SimpleDateFormat;

public class Main {
    public static void main(String[] args) {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

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

        System.out.println("\n------------------TEST - Insert ------------------");

        try {
            Seller alex = new Seller(2000.0, sdf.parse("20/01/2000"), "alexcoeuss@gmail.com", "Alexcoeus", null, department);
            sellerDao.insert(alex);
        }

        catch (ParseException pe) {
            System.out.println("Error: " + pe.getMessage());
        }

        System.out.println("Success");

        System.out.println("\n------------------TEST - Update ------------------");

        seller = sellerDao.findById(1);
        seller.setName("Marta Wayne");
        sellerDao.update(seller);

        System.out.println("Done");

        System.out.println("\n------------------TEST - DeleteById ------------------");

        sellerDao.deleteById(54);

        DB.closeConnection();
    }
}