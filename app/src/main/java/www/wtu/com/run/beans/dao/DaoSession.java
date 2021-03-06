package www.wtu.com.run.beans.dao;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import www.wtu.com.run.beans.ClimbData;
import www.wtu.com.run.beans.RunData;
import www.wtu.com.run.beans.RunInfo;

import www.wtu.com.run.beans.dao.ClimbDataDao;
import www.wtu.com.run.beans.dao.RunDataDao;
import www.wtu.com.run.beans.dao.RunInfoDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig climbDataDaoConfig;
    private final DaoConfig runDataDaoConfig;
    private final DaoConfig runInfoDaoConfig;

    private final ClimbDataDao climbDataDao;
    private final RunDataDao runDataDao;
    private final RunInfoDao runInfoDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        climbDataDaoConfig = daoConfigMap.get(ClimbDataDao.class).clone();
        climbDataDaoConfig.initIdentityScope(type);

        runDataDaoConfig = daoConfigMap.get(RunDataDao.class).clone();
        runDataDaoConfig.initIdentityScope(type);

        runInfoDaoConfig = daoConfigMap.get(RunInfoDao.class).clone();
        runInfoDaoConfig.initIdentityScope(type);

        climbDataDao = new ClimbDataDao(climbDataDaoConfig, this);
        runDataDao = new RunDataDao(runDataDaoConfig, this);
        runInfoDao = new RunInfoDao(runInfoDaoConfig, this);

        registerDao(ClimbData.class, climbDataDao);
        registerDao(RunData.class, runDataDao);
        registerDao(RunInfo.class, runInfoDao);
    }
    
    public void clear() {
        climbDataDaoConfig.clearIdentityScope();
        runDataDaoConfig.clearIdentityScope();
        runInfoDaoConfig.clearIdentityScope();
    }

    public ClimbDataDao getClimbDataDao() {
        return climbDataDao;
    }

    public RunDataDao getRunDataDao() {
        return runDataDao;
    }

    public RunInfoDao getRunInfoDao() {
        return runInfoDao;
    }

}
