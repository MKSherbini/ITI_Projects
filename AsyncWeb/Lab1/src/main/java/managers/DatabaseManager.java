package managers;

import models.User;
import providers.database.*;

import java.rmi.RemoteException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    private static volatile DatabaseManager instance = null;
    private Database databaseInstance = null;

    private DatabaseManager() {
        if (instance != null)
            throw new RuntimeException("Use getInstance(), reflection is not allowed");
        databaseInstance = DatabaseFactory.CreateDatabase(DatabaseServerType.MY_SQL);
    }

    public static DatabaseManager getInstance() {
        if (instance == null) {
            synchronized (DatabaseManager.class) {
                if (instance == null) {
                    instance = new DatabaseManager();
                }
            }
        }
        return instance;
    }

    public Database getDatabaseInstance() {
        return databaseInstance;
    }
}
