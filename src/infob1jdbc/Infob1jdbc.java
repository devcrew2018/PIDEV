/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infob1jdbc;

import java.sql.SQLException;
import workshop.services.EventServices;

/**
 *
 * @author takoua
 */
public class Infob1jdbc {
    
    public static void main(String[] args) throws SQLException {
        
    
    
    
    EventServices ep = new EventServices ();
    
   int i = ep.nb_res("open day"); 
        System.out.println(i);
        
       
    
    
    
            }
}
