package www.wtu.com.run.beans.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import www.wtu.com.run.beans.RunData;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "t_run_data".
*/
public class RunDataDao extends AbstractDao<RunData, Long> {

    public static final String TABLENAME = "t_run_data";

    /**
     * Properties of entity RunData.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Username = new Property(1, String.class, "username", false, "USERNAME");
        public final static Property Distance = new Property(2, String.class, "distance", false, "DISTANCE");
        public final static Property Bushu = new Property(3, String.class, "bushu", false, "BUSHU");
        public final static Property Time = new Property(4, String.class, "time", false, "time_sum");
        public final static Property Kll = new Property(5, String.class, "kll", false, "KLL");
        public final static Property Date = new Property(6, Long.class, "date", false, "create_date");
    }


    public RunDataDao(DaoConfig config) {
        super(config);
    }
    
    public RunDataDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"t_run_data\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"USERNAME\" TEXT," + // 1: username
                "\"DISTANCE\" TEXT," + // 2: distance
                "\"BUSHU\" TEXT," + // 3: bushu
                "\"time_sum\" TEXT," + // 4: time
                "\"KLL\" TEXT," + // 5: kll
                "\"create_date\" INTEGER);"); // 6: date
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"t_run_data\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, RunData entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String username = entity.getUsername();
        if (username != null) {
            stmt.bindString(2, username);
        }
 
        String distance = entity.getDistance();
        if (distance != null) {
            stmt.bindString(3, distance);
        }
 
        String bushu = entity.getBushu();
        if (bushu != null) {
            stmt.bindString(4, bushu);
        }
 
        String time = entity.getTime();
        if (time != null) {
            stmt.bindString(5, time);
        }
 
        String kll = entity.getKll();
        if (kll != null) {
            stmt.bindString(6, kll);
        }
 
        Long date = entity.getDate();
        if (date != null) {
            stmt.bindLong(7, date);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, RunData entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String username = entity.getUsername();
        if (username != null) {
            stmt.bindString(2, username);
        }
 
        String distance = entity.getDistance();
        if (distance != null) {
            stmt.bindString(3, distance);
        }
 
        String bushu = entity.getBushu();
        if (bushu != null) {
            stmt.bindString(4, bushu);
        }
 
        String time = entity.getTime();
        if (time != null) {
            stmt.bindString(5, time);
        }
 
        String kll = entity.getKll();
        if (kll != null) {
            stmt.bindString(6, kll);
        }
 
        Long date = entity.getDate();
        if (date != null) {
            stmt.bindLong(7, date);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public RunData readEntity(Cursor cursor, int offset) {
        RunData entity = new RunData( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // username
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // distance
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // bushu
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // time
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // kll
            cursor.isNull(offset + 6) ? null : cursor.getLong(offset + 6) // date
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, RunData entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setUsername(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setDistance(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setBushu(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setTime(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setKll(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setDate(cursor.isNull(offset + 6) ? null : cursor.getLong(offset + 6));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(RunData entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(RunData entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(RunData entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}