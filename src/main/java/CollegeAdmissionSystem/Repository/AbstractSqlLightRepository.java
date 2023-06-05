package CollegeAdmissionSystem.Repository;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract  class AbstractSqlLightRepository
{

    protected   final Connection conn;
    public AbstractSqlLightRepository( Connection conn) {
        this.conn = conn;
    }
    public <T> List<T> query(String sql , Class<T>type ) throws Exception {
        List<T> results =new ArrayList<T>();
        Field[] fields=type.getFields();
        ResultSet rs= conn.createStatement().executeQuery(sql);
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
    public <T> List<T> parametrasedQuery(String sql , int  id, Class<T>type ) throws Exception {
        List<T> results =new ArrayList<T>();
        Field[] fields=type.getFields();


        //ResultSet rs= conn.createStatement().executeQuery(sql);
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1,id);
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
}
