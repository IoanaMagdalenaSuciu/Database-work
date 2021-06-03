package start;



import Model.Client;
import Model.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Iterator;
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
    public DefaultTableModel retrieveInfo(List<T> listObj){
        Object[] header = new Object[4];
        Object[][] result = new Object[listObj.size()][];
        int i = 0;
        DefaultTableModel model = new DefaultTableModel();
         i = 0;
        List<Object> list = new ArrayList<Object>();
        for (Field field : type.getDeclaredFields()) {

            header[i] = field.getName();
            list.add(field.getName());
                i++;
        }

        i = 0;
        Iterator<T> iterator = listObj.iterator();
        while(iterator.hasNext()){
            T value = iterator.next();
            List<Object> objectList = new ArrayList<Object>();
            for(Field field: value.getClass().getDeclaredFields()){
                field.setAccessible(true);
                try {
                    objectList.add(field.get(value));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            result[i] = objectList.toArray();
            i++;
        }
        model.setDataVector(result, header);
        return model;
    }





}
