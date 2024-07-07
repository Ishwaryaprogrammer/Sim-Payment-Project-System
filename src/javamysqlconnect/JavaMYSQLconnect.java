
package javamysqlconnect;


import java.sql.*;
public class JavaMYSQLconnect {
        
 
            }
 
        

/*
   package javamysqlconnect;
 
    import java.sql.*;
 
    public class JavaMYSQLconnect {
 
        public static void main(String[] args) {
            try {
                  
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/spps", "root", "kalaivani");//Establishing connection
                System.out.println("Connected With the database successfully");
                //Creating PreparedStatement Object
                PreparedStatement preparedStatement =connection.prepareStatement("insert into student values(?,?,?,?)");
 
/* PreparedStatement preparedStatement =connection.prepareStatement("update Student set CITY=? where ROLLNO=?");*//*
                //Setting values for each parameter
                preparedStatement.setString(1,"1");
                preparedStatement.setString(2,"Mehtab");
                preparedStatement.setString(3,"Computer");
                preparedStatement.setString(4,"Ranchi");
 
                //Executing Query
                preparedStatement.executeUpdate();
                System.out.println("Data inserted Successfully");
 
 
        } catch (SQLException e) {
 
                System.out.println("Error while connecting to the database");
 
                        }
                    }
 
                }

*/

/*package javamysqlconnect;
 
    import java.sql.*;
 
    public class JavaMySQLConnect {
 
        public static void main(String[] args) {
            try {
                  
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentdb", "root", "root");//Establishing connection
                System.out.println("Connected With the database successfully");
                //Using SQL SELECT Query
                PreparedStatement preparedStatement =connection.prepareStatement("select * from student");
 
                //Creating Java ResultSet object
                ResultSet resultSet = preparedStatement.executeQuery();
                
                //Getting Results
                  while(resultSet.next()){
                            String rollNo=resultSet.getString("ROLLNO");
                            String name=resultSet.getString("STUDNAME");
                            String dept=resultSet.getString("DEPT");
                            String city=resultSet.getString("CITY");
                //Printing Results
                            System.out.println("Roll no = "+rollNo);
                            System.out.println("Name = "+name);
                            System.out.println("Department = "+dept);
                            System.out.println("City = "+city);
        }
              
 
 
        } catch (SQLException e) {
 
                System.out.println("Error while connecting to the database");
 
                        }
                    }
 
                }*/
