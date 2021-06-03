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
    public DefaultTableModel retrieveInfo(Object[][] listObj, JTable table){
        Object[] header = new Object[4];

        DefaultTableModel model = new DefaultTableModel();
        int i = 0;
        List<Object> list = new ArrayList<Object>();
        Object[][] data = new Object[1000][1000];
        for (Field field : type.getDeclaredFields()) {

            header[i] = field.getName();
            list.add(field.getName());
                i++;

        }

        model.setDataVector(listObj, header);
        return model;
    }





}
