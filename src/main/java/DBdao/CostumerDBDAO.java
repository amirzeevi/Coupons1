package DBdao;

import DB.DBmanager;
import DB.DBrunQuery;
import beans.Costumer;
import dao.CostumersDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;


public class CostumerDBDAO implements CostumersDAO {


    @Override
    public boolean isCostumerEmailExists(Costumer costumer) {
        Map<Integer, Object> values = Map.of(1, costumer.getEmail());
        ResultSet resultSet = DBrunQuery.getResultSet(DBmanager.IS_COSTUMER_EMAIL_EXISTS, values);
        assert resultSet != null;
        try {
            resultSet.next();
            return resultSet.getInt(1) == 1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public void addCostumer(Costumer costumer) {
        Map<Integer, Object> values = Map.of(1, costumer.getFirstName(),
                2, costumer.getLastName(),
                3, costumer.getEmail(),
                4, costumer.getPassword());
        DBrunQuery.runQuery(DBmanager.ADD_COSTUMER, values);
    }

    @Override
    public void updateCostumer(Costumer costumer) {
        Map<Integer, Object> values = Map.of(1, costumer.getFirstName(),
                2, costumer.getLastName(),
                3, costumer.getEmail(),
                4, costumer.getPassword(),
                5, costumer.getId());
        DBrunQuery.runQuery(DBmanager.UPDATE_COSTUMER, values);
    }

    @Override
    public void deleteCostumer(int costumerId) {
        Map<Integer, Object> map = Map.of(1, costumerId);
        DB.DBrunQuery.runQuery(DBmanager.DROP_COSTUMER, map);
    }

    @Override
    public ArrayList<Costumer> getAllCostumers() {
        ArrayList<Costumer> costumers = new ArrayList<>();
        ResultSet resultSet = DBrunQuery.getResultSet(DBmanager.GET_ALL_COSTUMERS);
        assert resultSet != null;
        while (true) {
            try {
                if (!resultSet.next()) break;
                costumers.add(resultSetToCostumer(resultSet));
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return costumers;
    }

    @Override
    public Costumer getOneCostumer(int costumerId) {
        Map<Integer, Object> map = Map.of(1, costumerId);
        ResultSet resultSet = DBrunQuery.getResultSet(DBmanager.GET_ONE_COSTUMER, map);
        assert resultSet != null;
        try {
            return resultSet.next() ? resultSetToCostumer(resultSet) : null;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    private static Costumer resultSetToCostumer(ResultSet resultSet) {
        Costumer costumer = new Costumer();
        try {
            costumer.setFirstName(resultSet.getString("first_name"));
            costumer.setLastName(resultSet.getString("last_name"));
            costumer.setEmail(resultSet.getString("email"));
            costumer.setPassword(resultSet.getString("password"));
            costumer.setId(resultSet.getInt("id"));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return costumer;

    }
}
