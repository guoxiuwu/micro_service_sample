package com.ebao.gs.sp.rc.dao

import com.ebao.gs.sp.rc.common.model.BaseEntity

/**
 * Created by xiuwu.guo on 11/6/2017.
 *
 * if dao interface extends baseDao,the it can operate any entity object
 */
public interface BaseDao {

    public <T extends BaseEntity> T save(T entity)

    public <T extends BaseEntity> int delete(T entity)

    public <T extends BaseEntity> T findOne(Class<T> entityClass, Serializable id)

    public <T extends BaseEntity> T load(Class<T> entityClass, Serializable id)

}
