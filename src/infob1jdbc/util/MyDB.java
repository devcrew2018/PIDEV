/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infob1jdbc.util;

import infob1jdbc.Infob1jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Asus
 */
public class MyDB {
      final String url="jdbc:mysql://localhost/infob1jdbc";
        final String login="root";
        final String password="";
        Connection connexion;
        static MyDB instance;
        private MyDB(){
            try {
            connexion = (com.mysql.jdbc.Connection) DriverManager.getConnection(url, login, password);
            System.out.println("Connexion etablie");
        } catch (SQLException ex) {
            Logger.getLogger(Infob1jdbc.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur de connection a la BD");
        }
        }
       public static MyDB getInstance(){
            if(instance == null)
                instance=new MyDB();
            return instance ;
        }
        public Connection getConnection(){
            return connexion ;
        }
}
