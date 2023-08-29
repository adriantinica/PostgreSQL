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

        // ITERATING lines and print;
       // rs.next();
        //System.out.println(rs.getInt(1));
        //System.out.println(rs.getString(2));
        //System.out.println(rs.getString(3));
        //System.out.println(rs.getDouble(4));
        //rs.next();
        //System.out.println(rs.getInt(1));
       // System.out.println(rs.getString(2));
        //System.out.println(rs.getString(3));
        //System.out.println(rs.getDouble(4));
        
        //HW1: Let's add new fields in our table (currencies):

        String sql = "INSERT INTO currencies (id, name, char_code, rate) VALUES (?, ?, ?, ?)";

          
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
            

        
        rs = st.executeQuery("SELECT * FROM currencies ");

        // ITERATING lines and print;
        while (rs.next()) {
        System.out.println(rs.getInt(1));
        System.out.println(rs.getString(2));
        System.out.println(rs.getString(3));
        System.out.println(rs.getDouble(4));
        }

        String setPrimaryKey = "ALTER TABLE currencies ADD PRIMARY KEY (id)";
        st.executeUpdate(setPrimaryKey);
        

        
       
    }
}
