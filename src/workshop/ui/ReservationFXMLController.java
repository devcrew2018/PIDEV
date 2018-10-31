/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package workshop.ui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import workshop.entites.Event;
import workshop.entites.Reservation;
import workshop.services.EventServices;
import workshop.services.ReservationServices;

/**
 * FXML Controller class
 *
 * @author takoua
 */
public class ReservationFXMLController implements Initializable {

    @FXML
    private JFXButton choisir;
    @FXML
    private JFXListView<?> lv2;
    @FXML
    private TableView<?> tablist;
    @FXML
    private TableColumn<?, ?> aaa;
    @FXML
    private TableColumn<?, ?> bbb;
    @FXML
    private TableColumn<?, ?> ccc;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
           try {
           EventServices cs=new EventServices();
           ArrayList<Event> events = (ArrayList <Event>) cs.getnomEvents();
            ObservableList obs= FXCollections.observableArrayList(events);
           
            
            lv2.setItems(obs); 
             //.setCellValueFactory(new PropertyValueFactory<>("titre"));
            
    }      
        catch (SQLException ex) {
            Logger.getLogger(ReservationFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
                    choisir.setOnAction(new EventHandler<ActionEvent>() {
             @Override
             public void handle(ActionEvent event) {
      //  (conf).setVisible(true);         
        //  (ann).setVisible(true);
         

                 ReservationServices cs=new ReservationServices();
                 try {
                      Event a= (Event) lv2.getSelectionModel().getSelectedItems().get(0);
                     
                        System.out.println(a.getTitre());
                     ArrayList<Reservation> reservations = (ArrayList <Reservation>) cs.getReservation(a.getTitre());
                                          
                     
                                ObservableList obs= FXCollections.observableArrayList(reservations);
            
           tablist.setItems(obs);
           aaa.setCellValueFactory(new PropertyValueFactory<>("fullname"));
            bbb.setCellValueFactory(new PropertyValueFactory<>("email"));
            ccc.setCellValueFactory(new PropertyValueFactory<>("phone_number"));
                     } catch (SQLException ex) {
                     Logger.getLogger(ReservationFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                 }
       
       
      
           
            
             }
    });
        
                            }
        // TODO
    }    
    

