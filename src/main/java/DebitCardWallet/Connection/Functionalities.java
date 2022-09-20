package DebitCardWallet.Connection;

import DebitCardWallet.Exceptions.AmountGreaterThanBalance;
import DebitCardWallet.Exceptions.BalanceNull;
import DebitCardWallet.supportClasses.Validation;

import java.sql.*;
import java.util.ArrayList;

public class Functionalities extends Validation {

    // These functions will only be called because the loggedIn is returning true.So, we won't give those cases

    private ArrayList<String> dataBaseToArrayList(String id){
        ArrayList<String> data = new ArrayList<>();
        try{
            Statement stmt=con.createStatement();
            String sql = "Select * from " + tableName + " where "+ ID +" = '"+ id +"'";
            ResultSet dataSet = stmt.executeQuery(sql);
            if(dataSet == null || !dataSet.next()){
                System.out.println("Result set is null");
                return data;
            }

            data.add(dataSet.getString(ID));
            data.add(dataSet.getString(NAME));
            data.add(dataSet.getString(PASSWORD));
            data.add(dataSet.getString(CARD));
            data.add(dataSet.getString(CVV));
            data.add(dataSet.getString(DATE_OF_EXPIRY));
            data.add(dataSet.getString(BALANCE));
        }
        catch (SQLException e){
            System.out.println("Caught Exception while saving data to arrayList");
            e.printStackTrace();
        }

        return data;
    }

    public String currentBalance(String id){
        ArrayList<String> data = dataBaseToArrayList(id);
        return data.get(6);
    }

    public String[] withDrawBalance(String id, Double withDraw){

        String[] result = new String[2];

        Double currBalance = Double.parseDouble(currentBalance(id));

        // if the balance is lower than or equal to zero

        try{
            if(currBalance <= 0){
                throw new BalanceNull();
            }
        }
        catch (BalanceNull e){
            result[0] = "false";
            result[1] = "The balance is already 0. SIR!";
            return result;
        }

        try{
            if(withDraw > currBalance){
                throw new AmountGreaterThanBalance();
            }

        }
        catch(AmountGreaterThanBalance e){
            result[0] = "false";
            result[1] = "The withdrawal amount is greater than the balance";
            return result;
        }

        currBalance-= withDraw;
        String newBalance = currBalance.toString();
        // updating to db
        updateBalanceToDb(id,newBalance);

        result[0] = "true";
        result[1] = "The amount of " + withDraw + " was withdrawal successfully. You new Balance is -: "+ currentBalance(id);
        return result;
    }

    public String depositBalance(String id, Double deposit){
        Double currBalance = Double.parseDouble(currentBalance(id));

        currBalance += deposit;
        String newBalance = currBalance.toString();
        updateBalanceToDb(id,newBalance);
        return "The amount of " + deposit.toString() + " was deposited successfully. You new Balance is -: "+ currentBalance(id);
    }

    private void updateBalanceToDb(String id, String balance){
        try {
            Statement stmt=con.createStatement();
            String sql = "UPDATE " + tableName + " SET " + BALANCE + " = " + balance + " where "+ ID +" = '" + id + "'";
            stmt.execute(sql);
        }catch (SQLException e){
            System.out.println("caught exception while updating balance");
            e.printStackTrace();
        }
    }

}
