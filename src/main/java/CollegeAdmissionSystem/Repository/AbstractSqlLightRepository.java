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
                field.set(data,rs.getObject(field.getName()));
            }
            results.add(data);
        }
        return results;
    }
   /* public <T,Param> List<T> parametrasedQuery(String sql , Param params, Class<T>type ) throws Exception {
        List<T> results =new ArrayList<T>();
        Field[] fields=type.getFields();


        //ResultSet rs= conn.createStatement().executeQuery(sql);
        PreparedStatement ps = conn.prepareStatement(sql);
        Field[] paramsFields=params.getClass().getDeclaredFields();

        for (Field paramsField : paramsFields)
        {
            try {
                if (paramsField.getType() == String.class) ps.setString(paramsField.getName(), paramsField.get(params));
                if (paramsField.getType() == int.class) ps.setInt(paramsField.getName(), paramsField.getInt(params));
               } catch (IllegalAccessException e)
                {throw new RuntimeException(e);}

        }
        var rs= ps.executeQuery();
        while (rs.next())
        {
            T data=type.getConstructor().newInstance();
            for (Field field : fields) {
                field.set(data,rs.getObject(field.getName()));
            }
            results.add(data);
        }
        return results;
    }*/
}
