package DAO;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import Connection.ConnectionFactory;

/**
 * Access data from database and performs operations on the tables
 * @param <T>
 */
public class AbstractDAO<T>  {
    /**
     * Logger
     */
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());
    /**
     * Class Type
     */
    private final Class<T> type;

    /**
     * Class constructor
     */
    public AbstractDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    }

    /**
     * Create Select Query
     * @param field The column according to which the selection will be made
     * @return Select Query
     */
    private String createSelectQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE " + field + " =?");
        return sb.toString();
    }

    /**
     * Create Delete Query
     * @param field The column according to which the deletion will be made
     * @return Delete Query
     */
    private String creatDeleteQuery(String field){
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE ");
        sb.append(" FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE " + field + " =?");
        return sb.toString();
    }

    /**
     * Select all rows in the table
     * @return List of data
     */
    public List<T> findAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM ");
        sb.append(type.getSimpleName());
        sb.append(";");
        String query = sb.toString();
        System.out.println(query);
        ResultSet resultSet = null;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
           return createObjects(resultSet);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }

        return null;
    }

    /**
     * Select the object with a specific id
     * @param id The id
     * @return The objects with specified id
     */

    public List<T> findById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("id");
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            return createObjects(resultSet);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * Select the object by name
     * @param name String
     * @return Object with specified name
     */
    public T findByName(String name) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("name");
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, name);
            resultSet = statement.executeQuery();
            return createObjects(resultSet).get(0);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        }catch (NullPointerException e) {
            return null;
        } finally
         {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);

        }
        return null;
    }

    /**
     * Creat a list of object form ResultSet
     * @param resultSet Result set
     * @return List of objects
     */
    private List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<T>();
        try {
            while (resultSet.next()) {
                T instance = type.newInstance();
                for (Field field : type.getDeclaredFields()) {
                    Object value = resultSet.getObject(field.getName());
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        if(!list.isEmpty()){
            return list;
        }else{
            return null;
        }
    }

    /**
     * Insert object into database
     * @param t Object to be inserted
     * @return An int
     */
    public int insert(T t) {
        Connection connection = null;
        PreparedStatement statement = null;
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO ");
        sb.append(type.getSimpleName());
        sb.append(" VALUES(");
        ResultSet resultSet = null;
        for(int i = 0; i < type.getDeclaredFields().length-1; i++){
            sb.append("?,");
        }
        sb.append("?);");
        String query = sb.toString();
        System.out.println(query);
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            int i = 1;
            for(Field f: t.getClass().getDeclaredFields()){
                PropertyDescriptor propertyDescriptor = new PropertyDescriptor(f.getName(),type);
                Method method =propertyDescriptor.getReadMethod();
                statement.setObject(i, method.invoke(t));
                i++;
               // System.out.println(statement);

            }
            int x = statement.executeUpdate();
            return x;
        } catch (SQLException | IntrospectionException | IllegalAccessException | InvocationTargetException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } finally {

            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return 0;


    }

    /**
     * Delete an object from database by id
     * @param id The id
     * @return The object that was deleted
     */
    public T delete (int id){
        Connection connection = null;
        PreparedStatement statement = null;
        String query = creatDeleteQuery("id");
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            System.out.println(statement);
            statement.executeUpdate();
        }catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:delete " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;

        }

    /**
     * Update an object in database
     * @param t The object to be updated
     * @return an int
     */
    public int update(T t) {
        Connection connection = null;
        PreparedStatement statement = null;
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE ");
        sb.append(type.getSimpleName());
        sb.append(" SET ");
        int i =0;
        Field[] fields = type.getDeclaredFields();
        for (Field field : type.getDeclaredFields()) {
            if(field.getName().equals("id")){
            }else if(i == type.getDeclaredFields().length-1){
                sb.append(field.getName()+"=?");
            }
            else{
                sb.append(field.getName()+"=?, ");
            }
            i++;
        }
        sb.append(" WHERE id= ?");
        sb.append(";");
        String query = sb.toString();
        System.out.println(query);
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            int j= 1;
            for(Field f: t.getClass().getDeclaredFields()){
                if(!f.getName().equals("id")){
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(f.getName(),type);
                    Method method =propertyDescriptor.getReadMethod();
                    statement.setObject(j, method.invoke(t));
                    j++;
                    System.out.println(statement);
                }else {
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(f.getName(), type);
                    Method method = propertyDescriptor.getReadMethod();
                    statement.setObject(t.getClass().getDeclaredFields().length,method.invoke(t));
                    //j++;
                }
            }
            return statement.executeUpdate();
        } catch (SQLException | IntrospectionException e) { LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) { e.printStackTrace(); } finally {

            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return 0;

    }
}
