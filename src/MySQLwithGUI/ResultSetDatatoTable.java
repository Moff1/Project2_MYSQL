package MySQLwithGUI;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import static MySQLwithGUI.Connector.QueryOneTable;

public class ResultSetDatatoTable extends javax.swing.JFrame {
    Connection conn = Connector.connectDB();
    String sql;
    ResultSet rs;

    static JPanel panel2;
    static JTable QueryOne;
    static JFrame frame2;

    static JScrollPane jScrollPane;


    public ResultSetDatatoTable() {

        try {
            TableTest();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void TableTest() throws SQLException {
        try {
            sql = "";
            rs = conn.prepareStatement(sql).executeQuery();
            QueryOne.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public static void main(String[] args){

        panel2 = new JPanel();
        frame2 = new JFrame("Outputs for Queries");

        panel2.add(QueryOneTable);

        frame2.add(panel2);

        frame2.setSize(600,600);
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame2.setVisible(true);
    }
}