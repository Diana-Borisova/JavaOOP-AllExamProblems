package restaurant.repositories;

import restaurant.entities.tables.interfaces.Table;
import restaurant.repositories.interfaces.TableRepository;

import java.util.*;

public class TableRepositoryImpl implements TableRepository<Table> {

    private final List<Table> tables;

    public TableRepositoryImpl() {
        tables = new ArrayList<>();
    }

    @Override
    public Collection<Table> getAllEntities() {
        return tables;
    }

    @Override
    public void add(Table table) {
        this.tables.add(table);
    }

    @Override
    public Table byNumber(int number) {
        return tables.stream().filter(t -> t.getTableNumber() == number).findFirst().orElse(null);
    }
}