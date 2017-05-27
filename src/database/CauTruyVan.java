/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

/**
 *
 * @author phamh
 */
public class CauTruyVan {
    
    // chọn tất cả các record
    public static String selectedStatement(String tableName){
        return "select * FROM " + tableName;
    }
}
