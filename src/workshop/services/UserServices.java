/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package workshop.services;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import java.time.LocalDate;
import workshop.entites.User;
/**
 *
 * @author SabriMarz
 */
public class UserServices {

    private Connection cnx;

    /* public void diffuser( ) throws SQLException { 
        List <User> users= new ArrayList<>();
        String req="SELECT * FROM user   ";
        Statement stm=cnx.createStatement();
        ResultSet rst=stm.executeQuery(req);
        while (rst.next()){
           User u=new User(rst.getString("email"));
            System.out.println(u.getEmail()); 
            SendingMail sm =new SendingMail("new evenement ouvrir le site pour voir ", u.getEmail(), "new event") ;
       sm.envoyer();
       users.add(u);
    }
    } */


}

   
