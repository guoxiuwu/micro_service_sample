package com.ebao.gs.sp.rc.service.convert

import com.ebao.gs.sp.rc.common.utils.JSONUtils
import com.ebao.gs.sp.rc.model.UserAuth
import com.ebao.gs.sp.rc.model.UserProfile
import org.apache.ibatis.type.JdbcType
import org.apache.ibatis.type.TypeHandler

import java.sql.CallableStatement
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException

/**
 * Created by xiuwu.guo on 11/6/2017.
 */
class BasicObjectJPAConvert implements TypeHandler {

    @Override
    public void setParameter(PreparedStatement ps, int i, Object parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, JSONUtils.toJSON(parameter))
    }

    @Override
    public Object getResult(ResultSet rs, String columnName) throws SQLException {
        String json = rs.getString(columnName)
        return JSONUtils.parseMap(json)
    }

    @Override
    public Object getResult(ResultSet rs, int columnIndex) throws SQLException {
        String json = rs.getString(columnIndex)
        return JSONUtils.parseMap(json)
    }

    @Override
    public Object getResult(CallableStatement cs, int columnIndex) throws SQLException {
        String json = cs.getString(columnIndex)
        return JSONUtils.parseMap(json)
    }
}

// User
class UserProfileJPAConvert implements TypeHandler<UserProfile> {

    @Override
    void setParameter(PreparedStatement ps, int i, UserProfile parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, JSONUtils.toJSON(parameter))
    }

    @Override
    public UserProfile getResult(ResultSet rs, String columnName) throws SQLException {
        String json = rs.getString(columnName)
        return JSONUtils.fromJSON(json, UserProfile.class)
    }

    @Override
    public UserProfile getResult(ResultSet rs, int columnIndex) throws SQLException {
        String json = rs.getString(columnIndex)
        return JSONUtils.fromJSON(json, UserProfile.class)
    }

    @Override
    public UserProfile getResult(CallableStatement cs, int columnIndex) throws SQLException {
        String json = cs.getString(columnIndex)
        return JSONUtils.fromJSON(json, UserProfile.class)
    }
}
// User
public class UserAuthsJPAConverter implements TypeHandler<List<UserAuth>> {
    @Override
    public void setParameter(PreparedStatement ps, int i, List<UserAuth> parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, JSONUtils.toJSON(parameter))
    }

    @Override
    public List<UserAuth> getResult(ResultSet rs, String columnName) throws SQLException {
        String json = rs.getString(columnName)
        return JSONUtils.fromJSONAsList(json, UserAuth.class)
    }

    @Override
    public List<UserAuth> getResult(ResultSet rs, int columnIndex) throws SQLException {
        String json = rs.getString(columnIndex)
        return JSONUtils.fromJSONAsList(json, UserAuth.class)
    }

    @Override
    public List<UserAuth> getResult(CallableStatement cs, int columnIndex) throws SQLException {
        String json = cs.getString(columnIndex);
        return JSONUtils.fromJSONAsList(json, UserAuth.class)
    }
}



