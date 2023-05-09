import java.sql.*;

public class Bank {
    private String name;
    public Bank(String name){
        this.name = name;
    }
    public void listAccounts(){
        Connection con = BankConnection.connect();
        Statement statement = null;
        try {
            statement = con.createStatement();
            String sql = "select * from accounts";
            ResultSet result = statement.executeQuery(sql);
            while (result.next()){
                System.out.println(result.getString(1)+" "+result.getString(2)+" "
                        +result.getString(3));
            }
            System.out.println();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public void openAccount(Account account){
        Connection con = BankConnection.connect();
        String sql = "insert into accounts values(?,?,?)";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, account.getNumber());
            preparedStatement.setString(2, account.getName());
            preparedStatement.setDouble(3, account.getBalance());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void closeAccount(int number){
        Connection con = BankConnection.connect();
        String sql = "delete  from accounts where accID = ?";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1,number);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void depositMoney(double amount,Account account){
        account.deposit(amount);
        Connection con = BankConnection.connect();
        String sql = "update accounts set accBalance = ? where accID = ? ";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setDouble(1,account.getBalance());
            preparedStatement.setInt(2,account.getNumber());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void withDrawMoney(Account account,double amount){
        account.withdraw(amount);
        Connection con = BankConnection.connect();
        String sql = "update accounts set accBalance = ? where accID = ? ";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setDouble(1,account.getBalance());
            preparedStatement.setInt(2,account.getNumber());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Account getAccount(int number) {
        Connection con = BankConnection.connect();
        String sql = "select * from accounts where accID = ?";
        Account account;
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, number);
            ResultSet result = preparedStatement.executeQuery();
            String accName = result.getString(2);
            double balance = result.getDouble(3);
            account = new Account(number, accName, balance);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return account;
    }
}
