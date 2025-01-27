package model.dao;

import model.entities.Seller;
import java.io.Serializable;
import java.util.List;

public interface SellerDao  {

    public void insert(Seller seller);

    public void update(Seller Seller);

    public void deleteById(Integer id);

    public Seller findById(Integer id);

    List <Seller> findAll();
}
