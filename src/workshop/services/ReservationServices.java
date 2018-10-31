/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package workshop.services;

import infob1jdbc.util.MyDB;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import workshop.entites.Event;
import workshop.entites.Reservation;

/**
 *
 * @author takoua
 */
public class ReservationServices {
     Connection connexion ;

    public ReservationServices() {
        connexion =MyDB.getInstance().getConnection();
    }

    
     public void ajouterReservation(Reservation r)throws SQLException{
        String req="INSERT INTO `reservation` (`eve`, `fullname`,`email`,`phone`) VALUES ('"+r.getEve()+"', '"+r.getFullname()+"', '"+r.getEmail()+"','"+r.getPhone_number()+"')";
       
                
                Statement stm= connexion.createStatement();
                stm.executeUpdate(req);
                
    }
  public int getNombre(String s) throws SQLException{
        int x=0;
        String req="SELECT * FROM stat WHERE etat='"+s+"'";
        Statement st=connexion.createStatement();
        ResultSet rst=st.executeQuery(req);
         while (rst.next()){
            x++;
        }
         return x;
        
    }
  public List<Reservation> getReservation(String r) throws SQLException
    { 
        List <Reservation> reservations= new ArrayList<>();
        String req="SELECT * FROM reservation where eve='"+r+"'";
        Statement ps=connexion.createStatement();
        //ps.setString(1,r);
        ResultSet rst=ps.executeQuery(req);
        while (rst.next()){
            Reservation c=new Reservation(rst.getString("eve"),rst.getString("fullname"),rst.getString("email"),rst.getInt("phone"));
            reservations.add(c);
        }
        return reservations;
    
    
}
    
}
