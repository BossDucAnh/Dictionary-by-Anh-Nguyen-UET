package sample.Supporter;

import java.sql.*;

public class ConnectDatabase {
    private Connection connection;
    private final String url ="jdbc:mysql://localhost:3306/data_dict?useUnicode=true&characterEncoding=UTF-8";//set up utf-8
    private final String user = "root";
    private final String password = "ducanhgiaosu99";
    private String myTable = "data_dict.av";
    public void setMyTable(String myTable){this.myTable = myTable;}

    public void getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("connect complete");
        }
        catch (ClassNotFoundException ex){
            System.out.println(ex.getMessage());
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }
    }
    public Trie getData(){
        Trie Mytrie = new Trie();
        String query = "SELECT * FROM " + myTable;// câu truy vấn
        ResultSet myResultset = null;//con trỏ đến hàng của table
        Statement statement;// thực hiện câu lệnh query
        try {
            //ResultSetMetaData resultSetMetaData = resultSet.getMetaData();// lấy dc tên số lượng cột và tên cột
            statement = connection.createStatement();//khởi tạo câu lệnh truy vấn
            myResultset = statement.executeQuery(query);//thực thi câu lệnh truy vấn
            while (myResultset.next()){
                // System.out.println(myResultset.getString(2));
                Mytrie.insert(myResultset.getString("word"));
            }
            return Mytrie;
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return null;
    }
    public String getMean(String word){
        ResultSet myresultSet = null;
        String temp = "";
        String query = "SELECT * FROM " + myTable + " where word = ? ";
        PreparedStatement preparedStatement = null;
        try{
            preparedStatement = connection.prepareStatement(query);//khởi tạo câu lệnh truy vấn
            preparedStatement.setString(1,word);//truyền word vào dấu hỏi chấm thứ nhất
            myresultSet = preparedStatement.executeQuery();//thực thi câu lệnh truy vấn
            if (myresultSet.next())
            temp = myresultSet.getString("html");
            return temp;
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return null;
    }
    public void insertWord(String word,String mean){
        // insert into data_infordict.av(word,html) VALUE("ducanh","deptrai");
        String query = "insert into " + myTable +"(word,html) VALUE(?,?)";
        PreparedStatement preparedStatement = null;
        try{
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,word);
            preparedStatement.setString(2,mean);
            if(preparedStatement.executeUpdate() > 0) System.out.println("complete");
            else System.out.println("error");
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }
    }
    public void deleteWord(String word){
        String query = "delete FROM " + myTable + " where word = ? ";
        PreparedStatement preparedStatement = null;
        try{
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,word);
            if(preparedStatement.executeUpdate() > 0) System.out.println("complete");
            else System.out.println("error");
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }
    }
    public void updateWord(String word,String mean){
        //UPDATE data_infordict.av SET html = "đẹp trai vãi nổi" where word = "duc anh dz";
        String query = "UPDATE  " + myTable + " SET html =  ? where word = ? ";
        PreparedStatement preparedStatement = null;
        try{
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,mean);
            preparedStatement.setString(2,word);
            if(preparedStatement.executeUpdate() > 0) System.out.println("complete");
            else System.out.println("error");
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }
    }
}
