package student.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws SQLException{
        
        String url = "jdbc:postgresql://localhost/e_shop_java?user=postgres&password=Laser112&ssl=false";
        Connection conn = DriverManager.getConnection(url);

        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM currencies ");
       
        

        
        //HW1: Let's add new fields in our table (currencies):

        String sql = "INSERT INTO currencies (id, name, char_code, rate) VALUES (?, ?, ?, ?)" +
                     "ON CONFLICT (id) DO NOTHING";
          
            // Create a PreparedStatement and set the values for the placeholders
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            int id = 946;
            String currencyName = "Leu romanesc";
            String currencyCode = "RON";
            double exchangeRate = 3.9137;
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, currencyName);
            preparedStatement.setString(3, currencyCode);
            preparedStatement.setDouble(4, exchangeRate);
            
            // Execute the insert statement
            preparedStatement.executeUpdate();

            PreparedStatement preparedStatement1 = conn.prepareStatement(sql);
            int id1 = 643;
            currencyName = "Russian Ruble";
            currencyCode = "RUB";
            exchangeRate = 0.1865;
            preparedStatement1.setInt(1, id1);
            preparedStatement1.setString(2, currencyName);
            preparedStatement1.setString(3, currencyCode);
            preparedStatement1.setDouble(4, exchangeRate);
             

            // Execute the insert statement
            preparedStatement1.executeUpdate();
           
            


        
        rs = st.executeQuery("SELECT * FROM currencies ");
        // ITERATING lines and print;
        while (rs.next()) {
        System.out.println(rs.getInt(1));
        System.out.println(rs.getString(2));
        System.out.println(rs.getString(3));
        System.out.println(rs.getDouble(4));
        }

        String dropPrimaryKey = "ALTER TABLE currencies DROP CONSTRAINT IF EXISTS currencies_pkey";
        st.executeUpdate(dropPrimaryKey);

        // Then, add the new primary key
        String setPrimaryKey = "ALTER TABLE currencies ADD PRIMARY KEY (id)";
        st.executeUpdate(setPrimaryKey);


        

        String alterDelete ="DELETE FROM currencies WHERE id = 643 ";
        st.executeUpdate(alterDelete);
        System.out.println("after DELETE ALTERATION !!!");


        rs = st.executeQuery("SELECT * FROM currencies ");
        while (rs.next()) {
        System.out.println(rs.getInt(1));
        System.out.println(rs.getString(2));
        System.out.println(rs.getString(3));
        System.out.println(rs.getDouble(4));
        }

        String alterUpdate ="UPDATE currencies SET rate = 3.9069 WHERE id = 946";
        st.executeUpdate(alterUpdate);
        System.out.println("after 'rate' UPDATE !!!");

        
        rs = st.executeQuery("SELECT * FROM currencies ");
        while (rs.next()) {
        System.out.println(rs.getInt(1));
        System.out.println(rs.getString(2));
        System.out.println(rs.getString(3));
        System.out.println(rs.getDouble(4));
        }
        
        
        preparedStatement1.close();
        preparedStatement.close();
        st.close();
        conn.close();

        
       
    }
}

