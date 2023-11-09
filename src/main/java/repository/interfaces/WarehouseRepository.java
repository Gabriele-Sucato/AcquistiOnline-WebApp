package main.java.repository;

import main.java.model.Warehouse;

public interface WarehouseRepository {
    Warehouse findWarehouseByArticleCode(String articleCode);

    void updateWarehouse(Warehouse warehouse);
}
