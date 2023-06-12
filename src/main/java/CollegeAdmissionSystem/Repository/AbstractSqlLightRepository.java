package CollegeAdmissionSystem.Repository;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract  class AbstractSqlLightRepository
{

    protected   final Connection conn;
    public AbstractSqlLightRepository( Connection conn) {
        this.conn = conn;
    }
    public  interface IPrepareStatement
    {
        public PreparedStatement AdditionalPrepareStatement(PreparedStatement ps) throws SQLException;
    }
    public <T> List<T> query(String sql , Class<T>type ) throws Exception {
        return  preparedQuery(sql,null,type);
    }
    public <T> List<T> preparedQuery(String sql , IPrepareStatement  psHandler, Class<T>type ) throws Exception
    {
        List<T> results =new ArrayList<T>();
        Field[] fields=type.getFields();
        PreparedStatement ps = conn.prepareStatement(sql);
        if (psHandler!=null)ps=psHandler.AdditionalPrepareStatement(ps);

        var rs= ps.executeQuery();
        while (rs.next())
        {
            T data=type.getConstructor().newInstance();
            for (Field field : fields) {
                try {
                    Object object = rs.getObject(field.getName());
                    field.set(data, object);
                } catch ( SQLException e)
                {
                    //Cannot map some complex data  eg Lists of sub queries
                }
            }
            results.add(data);
        }
        return results;
    }


    public <T> List<T> preparedQuery(String sql , int  id, Class<T>type ) throws Exception {
        return preparedQuery(sql, new IPrepareStatement() {
            @Override
            public PreparedStatement AdditionalPrepareStatement(PreparedStatement ps) throws SQLException
            {
                ps.setInt(1,id);
                return ps;
                            }
        },type);

    }
}
