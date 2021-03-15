package providers.database;

import providers.database.impl.SqlDatabase;

public class DatabaseFactory {
    public static Database CreateDatabase(DatabaseServerType type) {
        switch (type) {
            case MY_SQL:
                return new SqlDatabase();

            case SQL_Server:
                return null;
        }
        return null;
    }
}
