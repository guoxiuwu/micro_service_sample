package com.ebao.gs.sp.rc.dao.impl

import com.ebao.gs.sp.rc.common.basic.AppContext
import com.ebao.gs.sp.rc.common.exception.buis.DataValidityException
import com.ebao.gs.sp.rc.common.exception.sys.DataResultsException
import com.ebao.gs.sp.rc.common.exception.sys.EntityNotFoundException
import com.ebao.gs.sp.rc.common.exception.sys.InvalidEntityException
import com.ebao.gs.sp.rc.common.exception.sys.OptimisticLockException
import com.ebao.gs.sp.rc.common.exception.sys.PersistenceException
import com.ebao.gs.sp.rc.common.model.BaseAuditEntity
import com.ebao.gs.sp.rc.common.model.BaseEntity
import com.ebao.gs.sp.rc.common.model.EntityDataAware
import com.ebao.gs.sp.rc.common.utils.AnnotationValidator
import com.ebao.gs.sp.rc.common.utils.Func
import com.ebao.gs.sp.rc.common.utils.JSONUtils
import com.ebao.gs.sp.rc.dao.BaseDao
import org.apache.commons.collections.CollectionUtils
import org.mybatis.spring.SqlSessionTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate

/**
 * Created by xiuwu.guo on 11/6/2017.
 */
class BaseDaoImpl implements BaseDao {

    @Autowired
    protected SqlSessionTemplate sqlSessionTemplate
    @Autowired
    protected JdbcTemplate jdbcTemplate

    public <T extends BaseEntity> T save(T entity) {
        if (!(entity instanceof BaseEntity)) {
            throw new InvalidEntityException("${entity.class.name} is not an entity")
        }
        String repoClassName = getRepoClassName(entity.class)
        def id = ((BaseEntity) entity).getPK()
        //data integrity checking
        checkDataIntegrity(entity)

        if (id == null || !exists(entity.class, id)) {
            //insert
            if (entity instanceof BaseAuditEntity) {
                BaseAuditEntity ent = (BaseAuditEntity) entity
                ent.insertTime = Func.systemData()
                ent.insertUserId = AppContext.instance.loginContext().currentUser.userId
                ent.updateTime = ent.insertTime
                ent.updateUserId = ent.insertUserId
            }
            if (entity instanceof EntityDataAware) {
                EntityDataAware ent = (EntityDataAware) entity
                ent.ver = 0
            }

            int cnt = sqlSessionTemplate.insert("${repoClassName}.insert", entity)
            if (cnt != 1) {
                throw new PersistenceException("Encounterred an error when saving entity [${entity.class.name}], " +
                        "entity data: ${JSONUtils.toJSON(entity)}")
            }
        } else {
            //update
            if (entity instanceof BaseAuditEntity) {
                BaseAuditEntity ent = (BaseAuditEntity) entity
                if (ent.insertTime == null) {
                    ent.insertTime = Func.systemData()
                }
                if (ent.insertUserId == null) {
                    ent.insertUserId = AppContext.instance.loginContext().user.userId
                }
                ent.updateTime = Func.systemData()
                ent.updateUserId = AppContext.instance.loginContext().user.userId
            }
            String stmt = "${repoClassName}.update"
            if (entity instanceof EntityDataAware) {
                EntityDataAware ent = (EntityDataAware) entity
                int cnt = sqlSessionTemplate.update(stmt, ent)
                if (cnt != 1) {
                    throw new OptimisticLockException("Entity [${entity.class.name} : ${entity.PK}] with version [${ent.ver}] was deleted or updated")
                }
                ent.ver = ent.ver + 1
            } else {
                sqlSessionTemplate.update(stmt, entity)
            }
        }
        return entity
    }


    private static <T extends BaseEntity> void checkDataIntegrity(T t) {
        List<String> errors = AnnotationValidator.validate(t)
        if (CollectionUtils.isNotEmpty(errors)) {
            throw new DataValidityException(errors.first())
        }
    }

    public <T extends BaseEntity> int delete(T entity) {
        if (!(entity instanceof BaseEntity)) {
            throw new InvalidEntityException("${entity.class.name} is not an entity")
        }
        String repoClassName = getRepoClassName(entity.class)

        String stmt = "${repoClassName}.delete"
        def id = ((BaseEntity) entity).getPK()
        if (id == null) {
            throw new EntityNotFoundException("Cannot delete an unsaved entity")
        } else {
            return sqlSessionTemplate.delete(stmt, id)
        }
    }

    public <T extends BaseEntity> T findOne(Class<T> entityClass, Serializable id) {
        String repoClassName = getRepoClassName(entityClass)
        String stmt = "${repoClassName}.findOne"
        if (id == null) {
            throw new EntityNotFoundException("Cannot find an unsaved entity")
        } else {
            return sqlSessionTemplate.selectOne(stmt, id)
        }
    }

    public <T extends BaseEntity> T load(Class<T> entityClass, Serializable id) {
        T entity = findOne(entityClass, id)
        if (!entity) {
            String entityName = entityClass.name.substring(entityClass.name.lastIndexOf(".") + 1)
            throw new EntityNotFoundException("Cannot find an entity [${entityName}] with ID [${id}]")
        }
        return entity
    }

    private static String getRepoClassName(Class entityClass) {
        String repoClassName = "${entityClass.name}Repository".toString().replaceAll(".model", ".dao.repo")
        try {
            Class.forName(repoClassName)
        } catch (ClassNotFoundException e) {
            throw new InvalidEntityException("Repository not found: ${repoClassName}'")
        }
        if (!BaseEntity.class.isAssignableFrom(entityClass)) {
            throw new InvalidEntityException("${repoClassName} is not an entity")
        }
        return repoClassName
    }

    public boolean exists(Class entityClass, Serializable id) {
        String repoClassName = getRepoClassName(entityClass)
        String stmt = "${repoClassName}.exists"
        if (id == null) {
            throw new EntityNotFoundException("Cannot find an unsaved entity")
        } else {
            def cnt = sqlSessionTemplate.selectOne(stmt, id)
            if (cnt != null && cnt instanceof Number) {
                if (cnt == 0 || cnt == 1) {
                    return cnt == 1
                } else {
                    throw new DataResultsException("Expected 0 or 1 return from '${repoClassName}.exists' with ID [${id}] but got '${cnt}'")
                }
            } else {
                throw new DataResultsException("Expected 0 or 1 return from '${repoClassName}.exists' with ID [${id}] but got '${cnt}'")
            }
        }
    }
}
