package dao;

import beans.Costumer;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CostumersDAO {

    boolean isCostumerEmailExists(Costumer costumer);

    void addCostumer(Costumer costumer);

    void updateCostumer(Costumer costumer);

    void deleteCostumer(int costumerId);

    ArrayList<Costumer> getAllCostumers();

    Costumer getOneCostumer(int costumerId);

}
