package core.dao;

import org.mongodb.morphia.dao.BasicDAO;

import core.model.BasicModel;
import core.util.DBWrapper;

public class BasicDao<ENTITY extends BasicModel, KEY> extends BasicDAO<ENTITY, KEY> {

    protected BasicDao() {
        super(DBWrapper.datastore);
    }

    public boolean exists(KEY id) {
        return (get(id) != null);
    }

}