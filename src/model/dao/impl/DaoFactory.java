package model.dao.impl;

import model.dao.SellerDao;

public class DaoFactory {
    public SellerDao createSellerDao() {
        return new SellerDaoJDBC();
    }
}
