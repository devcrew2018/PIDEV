/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package workshop.ui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import workshop.entites.Event;
import workshop.entites.Reservation;
import workshop.services.EventServices;
import workshop.services.ReservationServices;

/**
 * FXML Controller class
 *
 * @author takoua
 */
public class MembreFXMLController implements Initializable {

    @FXML
    private JFXListView<Event> lv;
    @FXML
    private JFXButton pass;
    @FXML
    private TextArea ta;
    @FXML
    private JFXTextField fulln;
    @FXML
    private JFXTextField eml;
    @FXML
    private JFXTextField phnum;
    @FXML
    private JFXButton signup;
    @FXML
    private JFXTextField ti;
    @FXML
    private JFXButton select;
    @FXML
    private JFXButton pass2;
    @FXML
    private JFXButton pass3;
    @FXML
    private Label label;
    @FXML
    private WebView webview;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        (ta).setVisible(false);
         EventServices ps= new EventServices ();
        try {
        ArrayList<Event> events = (ArrayList<Event>)ps.getnomEvents();
            ObservableList obs = FXCollections.observableArrayList(events);
           
            
            lv.setItems(obs); 
             //.setCellValueFactory(new PropertyValueFactory<>("titre"));
            
    }      
        catch (SQLException ex) {
            Logger.getLogger(MembreFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    pass.setOnAction(new EventHandler<ActionEvent>() {
             @Override
             public void handle(ActionEvent event) {
        (ta).setVisible(true);

                 EventServices ps=new EventServices();
                 try {int a= lv.getSelectionModel().getSelectedIndex() ;
                     
                     ArrayList<Event> events = (ArrayList<Event>)ps.getEventsPlaceLibre();
                     Event e = events.get(a) ;
                     ta.setText(e.getDesc());
                     } catch (SQLException ex) {
                     Logger.getLogger(MembreFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                 }
             }        
        
         });
         pass2.setOnAction(new EventHandler<ActionEvent>() {
             @Override
             public void handle(ActionEvent event) {
        (ta).setVisible(true);

                 EventServices ps=new EventServices();
                 try {int a= lv.getSelectionModel().getSelectedIndex() ;
                     
                     ArrayList<Event> events = (ArrayList<Event>)ps.getEventsPlaceLibre();
                     Event e = events.get(a) ;
                     ta.setText(e.getDate());
                     } catch (SQLException ex) {
                     Logger.getLogger(MembreFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                 }
                 WebEngine engine=webview.getEngine(); 
    engine.load("https://www.youtube.com/");
    
             }        
        
         });
        
       
     select.setOnAction(new EventHandler<ActionEvent>() {
             @Override
             public void handle(ActionEvent event) {
      //  (conf).setVisible(true);         
        //  (ann).setVisible(true);
         

                 EventServices ps=new EventServices();
                  try {int a= lv.getSelectionModel().getSelectedIndex() ;
                     
                     ArrayList<Event> events = (ArrayList<Event>)ps.getEventsPlaceLibre();
                     Event e = events.get(a) ;
                     ti.setText(e.getTitre());
                     } catch (SQLException ex) {
                     Logger.getLogger(MembreFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                 }
       
       
      
           
            
             }
    });
     signup.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               
                    Integer i,j;
                    
                    j = Integer.parseInt(phnum.getText()) ;
                    Reservation r = new Reservation(ti.getText(),fulln.getText(),eml.getText(),j);
                    
                   
                   
                    int x =0;
                    if((r.getFullname().contains("  "))||(r.getEmail().contains("  ")))
       {
           x++;
                      label.setText("*** le nom et l'email de doivents pas contenir\n 2 espaces successives***");
       }
        if(r.getFullname().length()>0)
       {
            char liba=r.getFullname().charAt(0);
             if((liba<'A')||(liba>'Z'))
             { x++;
                                 (label).setText("*** Le nom complet doit commancer par une lettres\n majuscule***");}

       }
         
       for(j=1;j<r.getFullname().length();j++)
                  {
                      char lib=r.getFullname().charAt(j);
                   //  if(Character.isDigit(e.getLibelle().charAt(j)))
                   if(((lib<'A')||(lib>'Z')&&(lib<'a')||(lib>'z'))&&(lib!=' ')&&(lib!='-')&&(lib!='_')&&(lib!='é')&&(lib!='è'))
                   {  x++;
                                                                 (label).setText("*** verifiez votre Libelle***");}

                  }
                if(((fulln.getText().length()<1)||(eml.getText().length()<1)||(r.getEmail().length() <3)||(r.getFullname().length() <3)))
                {
                    (label).setText("***un ou plusieurs champs vides \nou trop court***");
                   x++;
                }
                if(x==0){
                     try {
                    
                    EventServices p= new EventServices();
                    ReservationServices ps= new ReservationServices();
                    int k = p.nb_res(ti.getText());
                    int g=p.nb_place(ti.getText());
                    int f = g-k ;
                    if(f>0)
                    {
                         ps.ajouterReservation(r);
                        
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Notification");
                        alert.setContentText("Participation succeded");
                        alert.showAndWait();
                        
                    }
                    else {
                        
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Notification");
                        alert.setContentText("evenement plein");
                        alert.showAndWait();
                        
                    }
                   
                        
                       
                        
                        
                        
                        
                        FXMLLoader loader= new  FXMLLoader(getClass().getResource("MembreFXML.fxml"));
                        Parent root;
                        try{ root=loader.load();
                        signup.getScene().setRoot(root);
                        
                        
                        
                        
                        
                        } catch (IOException ex) {
                            Logger.getLogger(CrudsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                    
                    
                    
                } catch (SQLException ex) {
                    Logger.getLogger(MembreFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    
                }
                
                
            } }
         
        }) ;
             
            
             
    }
   
   
    
    
       
         
   
       
    }
