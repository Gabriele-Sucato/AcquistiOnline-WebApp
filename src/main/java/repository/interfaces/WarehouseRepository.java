package main.java.repository.interfaces;

import main.java.model.Warehouse;

public interface WarehouseRepository {
    Warehouse findWarehouseByArticleCode(String articleCode);

    void updateWarehouse(Warehouse warehouse);
}
