package DebitCardWallet.supportClasses;

import DebitCardWallet.Connection.DataBase;
import DebitCardWallet.Exceptions.IncorrectIdException;
import DebitCardWallet.Exceptions.IncorrectPassWordException;
import org.apache.commons.lang3.StringUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

public class Validation extends DataBase {

    // the validation class is completely tested and working more than fine

    // if id exist return true
    public boolean ifIdExist(String id){
        try{
            Statement stmt = con.createStatement();
            String sql = "Select "+ ID +" from " + tableName + " where "+ ID +" = '"+id+"'";
            ResultSet a = stmt.executeQuery(sql);
            if(!a.next()){
                return false;
            }
        }
        catch (SQLException e){
            System.out.println("Caught SQLException during validation");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean ifCardExist(String card){
        try{
            Statement stmt = con.createStatement();
            String sql = "Select "+ CARD +" from " + tableName + " where "+ CARD +" = '"+card+"'";
            ResultSet a = stmt.executeQuery(sql);
            if(!a.next()){
                return false;
            }
        }
        catch (SQLException e){
            System.out.println("Caught SQLException during validation");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    // if user exist return true
    public String checkIfTrueUser(String id, String password) {
        ResultSet a;
        String result = "";

        try{
            Statement stmt=con.createStatement();
            String sql = "Select "+ PASSWORD +" from " + tableName + " where "+ ID +" = '" + id + "'";
            a = stmt.executeQuery(sql);
        }catch (SQLException e){
            System.out.println("caught SQLException during pre-verification of user");
            return result;
        }
        try{
            if(a == null || !a.next()){
                result = id + " is an invalid user id";
                throw new IncorrectIdException();
            }

            String passwordFromDB = a.getString(PASSWORD);
            if (!passwordFromDB.equals(password)) {
                result = "Wrong password of the corresponding " + id;
                throw new IncorrectPassWordException();
            }

        return result;
        }
        catch (SQLException | IncorrectPassWordException | IncorrectIdException e){
            System.out.println("caught SQLException during verification of user");
            return result;
        }
    }

    public boolean isOnlyNumber(String string){
        return !StringUtils.isNumeric(string);
    }

    /* returns true if the cvv is of 4 in length and only contains number */
    public String cvvLength(String cvv){
        String res = "";

        if(isOnlyNumber(cvv)){
//            result[0] = "false";
            res = "Cvv is not a number";
            return res;
        }

        if(cvv.length() != 3){
            res = "CVV is of 3 length";
            return res;
        }

        return res;
    }

    /* returns true if the card is of 12 in length and only contains number */

    public String cardLength(String card){
        String result = "";
        if(card.length() != 12){
            result = "Card number cannot be less or more than 12 digits";
            return result;
        }
        if(isOnlyNumber(card)){
            result = "Card number contains number 0-9";
            return result;
        }
        if(ifCardExist(card)){
            result = "Card already owned by another owner";
            return result;
        }
        return result;
    }

    /* returns true if the balance is of max 10 in length and only contains number */
    public String balanceLength(String balance){

        String result = "";

        if(balance.length()>10){
            result = "Respected, Ambani from wallMart. Enter correct balance";
            return result;
        }
        if(isOnlyNumber(balance)){
            result = "Balance value is not a number";
            return result;
        }

        return result;
    }

    public String dateCheck(String date){
        String result = "";

        if(date.length() != 5 || date.charAt(2) != '/') {
            System.out.println("Please enter the format of -: mm/yy. Like - 07/22");
            result = "Please enter the format of -: mm/yy";
            return result;
        }

        String month = date.substring(0,2);
        String year = date.substring(3,5);
        int mm;
        int yy;

        mm = Integer.parseInt(month);
        yy = Integer.parseInt(year);
        // to get current date + 1900, but we want 2 digits of year so -2000 + -1 for to check for previous year

        int prevYear = Calendar.getInstance().get(Calendar.YEAR) - 2001;
        int prevMonth = Calendar.getInstance().get(Calendar.MONTH);

        if(isOnlyNumber(month) || isOnlyNumber(year)){
            result = "Enter a valid date. Please enter the format of -: mm/yy";
            return result;
        }

        else if(mm > 12 || mm < 1){
            result = "Enter the correct month";
            return result;
        }

        else if(prevYear >= yy){
            result = "Card Expired";
            return  result;
        }

        else if(yy == prevYear + 1 && prevMonth >= mm){
            result = "Card Expired";
            return result;
        } else {
            return result;
        }
    }

    /* returns true if the id doesn't exist in the database */
    public String idCheck(String id){
        String result = "";
        if(id.length()>5){
            result = "Enter only 5 alphanumeric characters for ID";
            return result;
        }
        else if(ifIdExist(id)){
            result = "Id already exist!";
            return result;
        }

        return result;
    }

}
