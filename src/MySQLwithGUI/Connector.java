package MySQLwithGUI;


import net.proteanit.sql.DbUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.Scanner;
import java.util.Vector;

import static java.lang.System.out;

//static JTable QueryOne;
public class Connector{
     public static JTable QueryOneTable = new JTable();
    public static JTable QueryTwoTable = new JTable();
    public static JTable QueryThreeTable = new JTable();
    public static JTable QueryFourTable = new JTable();
    public static JTable QueryFiveTable = new JTable();
    public static JTable QuerySixTable = new JTable();

    public static String sql;
    public static ResultSet rs;


    public static void output(String query){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/employees","username","yourpassword");
            Statement stat = connection.createStatement();
            String response = query;
            ResultSet rs = stat.executeQuery(response);
            //QueryOneTable.setModel(DbUtils.resultSetToTableModel(rs));
            while(rs.next()){
                out.println(rs.getString(1));
            }
            //JTable Resultant = new JTable(buildTableModel(rs));
            //out.println(Resultant);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public static Connection connectDB(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/employees","root","Moff-19192023$");
            return conn;
            //JTable Resultant = new JTable(buildTableModel(rs));
            //out.println(Resultant);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public static void query1(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/employees","root","Moff-19192023$");
            Connection conn = Connector.connectDB();
            sql = "SELECT e.first_name, e.last_name FROM employees e INNER JOIN salaries s ON s.emp_no = e.emp_no INNER JOIN dept_manager m ON e.emp_no = m.emp_no WHERE s.salary > 80000 AND e.gender = 'F' AND m.to_date = '9999-01-01' AND e.birth_date < '1990-01-01' AND s.to_date = '9999-01-01';";
            rs = conn.prepareStatement(sql).executeQuery();

            QueryOneTable.setModel(DbUtils.resultSetToTableModel(rs));
            String [] arguments = new String[] {"123"};
            ResultSetDatatoTable.main(arguments);
            while(rs.next()){
                out.println(rs.getString(1));
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void runner1(){
        String response = "Query#1";
        System.out.println(response);
        query1();
    }

    public static void query2(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/employees","root","Moff-19192023$");
            Connection conn = Connector.connectDB();
            sql = "SELECT dept_manager.from_date, dept_manager.emp_no, employees.emp_no,employees.first_name, employees.last_name FROM dept_manager INNER JOIN employees ON dept_manager.emp_no=employees.emp_no ORDER BY dept_manager.from_date ASC limit 10;";
            rs = conn.prepareStatement(sql).executeQuery();
            QueryTwoTable.setModel(DbUtils.resultSetToTableModel(rs));
            String [] arguments = new String[] {"123"};
            ResultSetDatatoTable.main(arguments);
            while(rs.next()){
                out.println(rs.getString(1));
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public static void runner2(){
        String response = "Query#2";
        System.out.println(response);
        query2();
    }

    public static void query3(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/employees","root","Moff-19192023$");
            Connection conn = Connector.connectDB();
            sql = "CREATE OR REPLACE VIEW fem_avg_sal AS select d.dept_name, AVG(s.salary) AS fem_avg from departments d INNER JOIN dept_emp de ON d.dept_no = de.dept_no INNER JOIN employees e ON e.emp_no = de.emp_no INNER JOIN salaries s ON s.emp_no = e.emp_no WHERE de.to_date = '9999-01-01' AND s.to_date ='9999-01-01' AND e.gender = 'F' GROUP BY d.dept_name;" +
                    "CREATE OR REPLACE VIEW male_avg_sal AS select d.dept_name, AVG(s.salary) AS male_avg from departments d INNER JOIN dept_emp de ON d.dept_no = de.dept_no INNER JOIN employees e ON e.emp_no = de.emp_no INNER JOIN salaries s ON s.emp_no = e.emp_no WHERE de.to_date = '9999-01-01' AND s.to_date ='9999-01-01' AND e.gender = 'M' GROUP BY d.dept_name;" +
                    "SELECT fem.dept_name, fem.fem_avg / male.male_avg AS Ratio FROM fem_avg_sal fem INNER JOIN male_avg_sal male ON fem.dept_name = male.dept_name ORDER BY Ratio DESC limit 100;"
            ;
            rs = conn.prepareStatement(sql).executeQuery();
            QueryThreeTable.setModel(DbUtils.resultSetToTableModel(rs));
            String [] arguments = new String[] {"123"};
            ResultSetDatatoTable.main(arguments);
            while(rs.next()){
                out.println(rs.getString(1));
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public static void runner3(){
        String response = "Query#3";
        out.println(response);
        query3();
    }

    public static void query4(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/employees","root","Moff-19192023$");
            Connection conn = Connector.connectDB();
            sql = "CREATE OR REPLACE VIEW fifties AS SELECT CONCAT(d.dept_name, '(1950s)'), Count(e.emp_no) AS number_of_emps, AVG(s.salary) AS avg_sal FROM departments d INNER JOIN dept_emp de ON d.dept_no = de.dept_no INNER JOIN employees e ON e.emp_no = de.emp_no INNER JOIN salaries s ON s.emp_no = e.emp_no WHERE s.to_date = '9999-01-01' AND e.birth_date < '1960-01-01' GROUP BY d.dept_name;" +
                    "CREATE OR REPLACE VIEW sixties AS SELECT CONCAT(d.dept_name, '(1960s)'), Count(e.emp_no) AS number_of_emps, AVG(s.salary) AS avg_sal FROM departments d INNER JOIN dept_emp de ON d.dept_no = de.dept_no INNER JOIN employees e ON e.emp_no = de.emp_no INNER JOIN salaries s ON s.emp_no = e.emp_no WHERE s.to_date = '9999-01-01' AND e.birth_date >= '1960-01-01' GROUP BY d.dept_name;" +
            "SELECT * FROM fifties UNION SELECT * FROM sixties limit 100;";
            rs = conn.prepareStatement(sql).executeQuery();

            QueryFourTable.setModel(DbUtils.resultSetToTableModel(rs));
            String [] arguments = new String[] {"123"};
            ResultSetDatatoTable.main(arguments);
            while(rs.next()){
                out.println(rs.getString(1));
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public static void runner4(){
        String response = "Query#4";
        out.println(response);
        query4();
    }














    //// Query #5
    public static void query5(String employNo_one, String employNo_two){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/employees","root","Moff-19192023$");
            Connection conn = Connector.connectDB();
            sql = "SELECT e4.emp_no " +
                    "FROM employees e1 " +
                    "JOIN employees e2 " +
                    "JOIN employees e3 " +
                    "JOIN employees e4 " +
                    "JOIN dept_emp de1 " +
                    "ON e1.emp_no = de1.emp_no " +
                    "JOIN dept_emp de2 " +
                    "ON e2.emp_no = de2.emp_no " +
                    "JOIN dept_emp de3 " +
                    "ON e3.emp_no = de3.emp_no " +
                    "JOIN dept_emp de4 " +
                    "ON e4.emp_no = de4.emp_no " +
                    "WHERE e1.emp_no = '" + employNo_one + "' " +
                    "AND e3.emp_no = '" + employNo_two + "' " +
                    "AND de1.dept_no = de2.dept_no " +
                    "AND de3.dept_no = de4.dept_no " +
                    "AND e1.emp_no != e4.emp_no " +
                    "AND (de1.from_date <= de2.to_date " +
                    "AND de2.from_date <= de1.to_date) " +
                    "AND (de3.from_date <= de2.to_date " +
                    "AND de2.from_date <= de3.to_date);";
            rs = conn.prepareStatement(sql).executeQuery();

            QueryFiveTable.setModel(DbUtils.resultSetToTableModel(rs));
            String [] arguments = new String[] {"123"};
            ResultSetDatatoTable.main(arguments);
            while(rs.next()){
                out.println(rs.getString(1));
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void runner5(String string1, String string2) throws SQLException, ClassNotFoundException {
        String EmployeeOne = string1;
        String EmployeeTwo = string2;
        query5(EmployeeOne,EmployeeTwo);
    }

    public static void query6(String employNo_one, String employNo_two){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/employees","root","Moff-19192023$");
            Connection conn = Connector.connectDB();
            sql = "SELECT " +
                    "e2.first_name AS 'First Name', " +
                    "e2.last_name AS 'Last Name', " +
                    "e2.emp_no AS 'Employee Number' " +
                    "FROM employees e1 " +
                    "JOIN employees e2 " +
                    "JOIN employees e3 " +
                    "JOIN dept_emp de1 " +
                    "ON e1.emp_no = de1.emp_no " +
                    "JOIN dept_emp de2 " +
                    "ON e2.emp_no = de2.emp_no " +
                    "JOIN dept_emp de3 " +
                    "ON e3.emp_no = de3.emp_no " +
                    "WHERE e1.emp_no = '" + employNo_one + "' " +
                    "AND e3.emp_no = '" + employNo_two + "' " +
                    "AND de1.dept_no = de2.dept_no " +
                    "AND de2.dept_no = de3.dept_no " +
                    "AND e1.emp_no != e3.emp_no " +
                    "AND (de1.from_date <= de2.to_date " +
                    "AND de2.from_date <= de1.to_date) " +
                    "AND (de3.from_date <= de2.to_date " +
                    "AND de2.from_date <= de3.to_date);";
            rs = conn.prepareStatement(sql).executeQuery();
            QuerySixTable.setModel(DbUtils.resultSetToTableModel(rs));
            String [] arguments = new String[] {"123"};
            ResultSetDatatoTable.main(arguments);
            while(rs.next()){
                out.println(rs.getString(1));
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void runner6(String string1, String string2){
        String response = "Query#4";
        String EmployeeOne = string1;
        String EmployeeTwo = string2;
        query6(EmployeeOne,EmployeeTwo);
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

    }


}
