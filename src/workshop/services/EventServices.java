/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package workshop.services;

import infob1jdbc.util.MyDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javax.swing.JOptionPane;
import static jdk.nashorn.internal.runtime.Debug.id;
import workshop.entites.Event;
import workshop.entites.Reservation;
import workshop.entites.Statistic;
import workshop.entites.User;

/**
 *
 * @author takoua
 */
public class EventServices {
        Connection connexion ;

    public EventServices() {
        connexion =MyDB.getInstance().getConnection();
    }

     public void ajouterEvent(Event e)throws SQLException{
        String req="INSERT INTO `events` (`titre`, `lieu`,`nb_p`,`coach`,`date`,`descp`) VALUES ('"+e.getTitre()+"', '"+e.getLieu()+"', '"+e.getNb_p()+"','"+e.getCoach()+"','"+e.getDate()+"','"+e.getDesc()+"')";
        //String req1="INSERT INTO 'Product' ('libelle' ,'nom')" +" values (p.getLib(),p.getDesc();";
                
                Statement stm= connexion.createStatement();
                stm.executeUpdate(req);
                
    }
      public boolean supprimerEvent(int ide) {
        try {
            String requete = "DELETE FROM events WHERE id=?";
            PreparedStatement pst = connexion.prepareStatement(requete);
            pst.setInt(1,ide);
            pst.executeUpdate();
            System.out.println("Votre event supprimé avec succées");
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }
     public boolean modifierEvent(Event e) {
         
        try {
            String requete = "UPDATE `events` SET `titre`=?,`lieu`=?,`nb_p`=?,`coach`=?,`date`=?,`descp`=? WHERE id=?";
            PreparedStatement pst = connexion.prepareStatement(requete);
           
           
            pst.setString(1, e.getTitre());
            pst.setString(2, e.getLieu());
             pst.setInt(3, e.getNb_p());
            pst.setString(4, e.getCoach());
            pst.setString(5, e.getDate());
            pst.setString(6, e.getDesc());
            pst.setInt(7, e.getIde());
            
        
            pst.executeUpdate();
             
            System.out.println("Votre Evenement modifié avec succées");
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            
        }
       
        return false;
    }
      
    public List<Event> getAllEvents() throws SQLException
    {   
        List <Event> events= new ArrayList<>();
        String req="SELECT * FROM events";
        Statement stm=connexion.createStatement();
        ResultSet rst=stm.executeQuery(req);
        while (rst.next()){
            Event e=new Event(rst.getInt("id"),rst.getString("titre"),rst.getString("lieu"),rst.getInt("nb_p"),rst.getString("coach"),rst.getString("date"),rst.getString("descp"));
            events.add(e);
        }
        return events;
    }
   public List<Event> getEvent(int a) throws SQLException
    {   
        List <Event> events= new ArrayList<>();
        String req="SELECT * FROM event where ide="+a;
        Statement stm=connexion.createStatement();
        ResultSet rst=stm.executeQuery(req);
        while (rst.next()){
            Event e=new Event(rst.getInt("ide"),rst.getString("titre"),rst.getString("lieu"),rst.getInt("nb_p"),rst.getString("coach"),rst.getString("date"),rst.getString("desc"));
            events.add(e);
        }
        return events;
    }
   public Event chercherEvent( int a) throws SQLException{
         String req="SELECT * FROM event where ide="+a;
         Event e = null;
        Statement stm=connexion.createStatement();
        ResultSet rst=stm.executeQuery(req);
        while (rst.next()){
           e =new Event(rst.getInt("ide"),rst.getString("titre"),rst.getString("lieu"),rst.getInt("nb_p"),rst.getString("coach"),rst.getString("date"),rst.getString("desc"));
         }
        return e;

}
    public List<Event> getnomEvents() throws SQLException
    {   
        List <Event> events= new ArrayList<>();
        String req="SELECT titre FROM events where nb_p>0  ";
        Statement stm=connexion.createStatement();
        ResultSet rst=stm.executeQuery(req);
        while (rst.next()){
           Event e=new Event(rst.getString("titre"));
        events.add(e);
    }
       return events;
      //return e.getTitre();
      
    }
  
     public List<String> gettitEvents() throws SQLException
    {   
        List <String> events= new ArrayList<>();
        String req="SELECT titre FROM events where nb_p>0  ";
        Statement stm=connexion.createStatement();
        ResultSet rst=stm.executeQuery(req);
        while (rst.next()){
           Event e=new Event(rst.getString("titre"));
        //events.add(e);
    }
       return events;
     // return e.getTitre();
      
    }
    
    
  
     public List<Event> getEventsPlaceLibre() throws SQLException
    {   
        List <Event> events= new ArrayList<>();
        String req="SELECT * FROM events where nb_p>0  ";
        Statement stm=connexion.createStatement();
        ResultSet rst=stm.executeQuery(req);
        while (rst.next()){
           Event e=new Event(rst.getString("titre"));
           e.setDesc(rst.getString("descp"));
             e.setDate(rst.getString("date"));
              e.setLieu(rst.getString("lieu"));
        events.add(e);
    }
       return events;
      //return e.getTitre();
      
    }
      public List<Reservation> getnomparticipants() throws SQLException
    {   
        List <Reservation> reser= new ArrayList<>();
        String req="SELECT eve FROM reservation  ";
        Statement stm=connexion.createStatement();
        ResultSet rst=stm.executeQuery(req);
        while (rst.next()){
           Reservation r=new Reservation(rst.getString("eve"));
        reser.add(r);
    }
       return reser;
      //return e.getTitre();
      
    }
  
      public List<Reservation> getParticipants() throws SQLException
    {   
        List <Reservation> reser= new ArrayList<>();
        String req="SELECT * FROM reservation  ";
        Statement stm=connexion.createStatement();
        ResultSet rst=stm.executeQuery(req);
        while (rst.next()){
           Reservation e=new Reservation(rst.getString("eve"));
           e.setFullname(rst.getString("fullname"));
             e.setEmail(rst.getString("email"));
            //  e.setPhone_number(rst.getString("phone"));
        reser.add(e);
    }
       return reser;
      //return e.getTitre();
      
    }
       public void diffuser( ) throws SQLException { 
        List <User> users= new ArrayList<>();
        String req="SELECT * FROM user   ";
        Statement stm=connexion.createStatement();
        ResultSet rst=stm.executeQuery(req);
        while (rst.next()){
           User u=new User(rst.getString("email"));
            System.out.println(u.getEmail()); 
            SendingMail sm =new SendingMail("new evenement ouvrir le site pour voir ", u.getEmail(), "new event") ;
       sm.envoyer();
       users.add(u);
    }  
    }
    
         
          public List<String> combobox() throws SQLException
    {   
         final ComboBox combo = new ComboBox();
        List <String> users= new ArrayList<>();
        String req="SELECT * FROM user  ";
        Statement stm=connexion.createStatement();
        ResultSet rst=stm.executeQuery(req);
        while (rst.next()){
         String e=new String(rst.getString("fullname"));
         // combo.setFullname(rst.getString("fullname"));
            System.out.println(e); 
            //combo.addItem("a","b");
       
       users.add(e);
    }
       return users;
      //return e.getTitre();
      
    }
           public void ajouterStatic(Statistic e)throws SQLException{
        String req="INSERT INTO `stat` (`evenement`, `etat`) VALUES ('"+e.getEvenement()+"', '"+e.getEtat()+"')";
      
                
                Statement stm= connexion.createStatement();
                stm.executeUpdate(req);
                
    }

   public int nb_res (String s) throws SQLException {
       int n =0  ;
   
          Statement stm=connexion.createStatement();
  
            
              String req1="SELECT * FROM reservation where eve='" +s+"'";
              
        ResultSet rst1=stm.executeQuery(req1);

      
        while( rst1.next() ){
         n++;

     
        }   
          return n ;   
}
   
   
  public int nb_place(String s) throws SQLException {
      int n =0  ;
   
          Statement stm=connexion.createStatement();
  
            
              String req1="SELECT * FROM events where titre='" +s+"'";
              
        ResultSet rst=stm.executeQuery(req1);

      
        while( rst.next() ){
          
          n=  rst.getInt(4);
     
        }   
          return n ;   
    

    
     
} 
        
        
        
}


     
    

