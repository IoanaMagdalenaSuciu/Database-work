package start;



import Model.Client;

import java.awt.*;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * This class compute the header
 * @param <T>
 */
public class TableDAO<T> {
    private final Class<T> type;

    /**
     * Class constructor
     */
    public TableDAO(){
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    /**
     * Get the field of a class and create the header for the table
     * @return List of fields
     */
    public List<String> retrieveInfo() {
        String[] header = new String[3000];
        int i = 0;
        List<String> list = new ArrayList<String>();
        for (Field field : type.getDeclaredFields()) {
            try {
                header[i] = field.getName();
                list.add(field.getName());
                i++;
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
        return list;
    }





}
