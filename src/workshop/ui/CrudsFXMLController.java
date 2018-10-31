/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package workshop.ui;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.teknikindustries.bulksms.SMS;
import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;
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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import static jdk.nashorn.internal.runtime.Debug.id;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.data.category.DefaultCategoryDataset;
import workshop.entites.Event;
import workshop.entites.User;
import workshop.services.EventServices;
import workshop.services.ReservationServices;

/**
 * FXML Controller class
 *
 * @author takoua
 */
public class CrudsFXMLController implements Initializable {

    @FXML
    private JFXTextField Tit;
    @FXML
    private JFXTextField L;
    @FXML
    private JFXTextField np;
    @FXML
    private JFXComboBox<String> ch;
    @FXML
    private JFXDatePicker dte;
    @FXML
    private JFXTextArea dsc;
    @FXML
    private JFXButton ajout;
    @FXML
    private JFXButton conf;
    @FXML
    private JFXButton ann;
    @FXML
    private Label lab;
    @FXML
    private TableView<?> tabreq;
    @FXML
    private TableColumn<Event, Integer> aa;
    @FXML
    private TableColumn<?, ?> bb;
    @FXML
    private TableColumn<?, ?> cc;
    @FXML
    private TableColumn<?, ?> dd;
    @FXML
    private TableColumn<?, ?> ee;
    @FXML
    private TableColumn<?, ?> ff;
    @FXML
    private TableColumn<?, ?> gg;
    @FXML
    private JFXButton val;
    @FXML
    private JFXTextField tf_id;
    @FXML
    private AnchorPane parent_hbox;
    @FXML
    private Button ref;
    @FXML
    private JFXButton staat;
    @FXML
    private JFXButton pdf;
 
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
          
        
         EventServices ps= new EventServices ();
           try {
          ArrayList<String> users = (ArrayList<String>)  ps.combobox();
            ObservableList obs = FXCollections.observableArrayList(users);
           
            
            ch.setItems(obs); 
            
            // TODO
        } catch (SQLException ex) {
            Logger.getLogger(CrudsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        try {
            ArrayList<Event> events = (ArrayList<Event>)ps.getAllEvents();
            ObservableList obs = FXCollections.observableArrayList(events);
            tabreq.setItems(obs); 
            
            aa.setCellValueFactory(new PropertyValueFactory<>("ide"));
            bb.setCellValueFactory(new PropertyValueFactory<>("titre"));
            cc.setCellValueFactory(new PropertyValueFactory<>("lieu"));
            dd.setCellValueFactory(new PropertyValueFactory<>("nb_p"));
            ee.setCellValueFactory(new PropertyValueFactory<>("coach"));
            ff.setCellValueFactory(new PropertyValueFactory<>("date"));
            gg.setCellValueFactory(new PropertyValueFactory<>("desc"));
             (tf_id).setVisible(false);
              (aa).setVisible(false);
            
        } catch (SQLException ex) {
            Logger.getLogger(CrudsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        
                ajout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Integer i,j;
                
                j = Integer.parseInt(np.getText()) ;
               Event e = new Event (Tit.getText(),L.getText(),j,ch.getSelectionModel().getSelectedItem(),dte.getValue().toString(),dsc.getText());
             
              int x =0;
                    if((e.getTitre().contains("  "))||((e.getLieu()).contains("  ")))
       {
           x++;
                      lab.setText("*** Titre et Lieu de doivents pas contenir\n 2 espaces successives***");
       }
       
     
                if(((Tit.getText().length()<1)||(L.getText().length()<1)||(e.getTitre().length() <3)||(e.getLieu().length() <3)))
                {
                    (lab).setText("***un ou plusieurs champs vides \nou trop court***");
                   x++;
                }
             
                 if(x==0){       
               
                EventServices ps= new EventServices();
                try {
                    ps.ajouterEvent(e);
                  
             
                    
                        FXMLLoader loader= new  FXMLLoader(getClass().getResource("crudsFXML.fxml"));
                    Parent root;
                    try{ root=loader.load();
                         ajout.getScene().setRoot(root);
                       
                         
                         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                         alert.setTitle("Notification");
                         alert.setContentText("Event successfully recorded");
                         alert.showAndWait();
                           ps.diffuser();
                           
                           SMS smsTut = new SMS();
                           smsTut.SendSMS("takoua", "riahitakwa", "nouv event", "+21622546750", "https://bulksms.vsms.net/eapi/submission/send_sms/2/2.0");
                           
                           System.out.println("msg envoyé ");
                    } catch (IOException ex) {
                        Logger.getLogger(CrudsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }  
                    
                } catch (SQLException ex) {
                    Logger.getLogger(CrudsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    
                }
                 }
            }
                 
        }) ;
                
        
        
       val.setOnAction(new EventHandler<ActionEvent>() {
             @Override
             public void handle(ActionEvent event) {
         (conf).setVisible(true);         
          (ann).setVisible(true);
         

                 EventServices ps=new EventServices();
        Event e=(Event) tabreq.getSelectionModel().getSelectedItem();
        String t = String.valueOf(e.getIde());
       
       String k = String.valueOf(e.getNb_p());
           tf_id.setText(t);
            Tit.setText(e.getTitre());
            L.setText(e.getLieu());
            np.setText(k);
           ch.setPromptText(e.getCoach());
           //dte.setPromptText(e.getDate());
           DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
            LocalDate localDate = LocalDate.parse(e.getDate(),formatter);
            dte.setValue(localDate);
            
            dsc.setText(e.getDesc());
                 System.out.println(e.getIde());
            
             }
    });
       
       
            conf.setOnAction(new EventHandler<ActionEvent>() {
             @Override
             public void handle(ActionEvent event) {
                  
                 int j ;
               int b= Integer.parseInt(tf_id.getText());
                  j = Integer.parseInt(np.getText()) ;
                 Event e=new Event(b,Tit.getText(),L.getText(), j,ch.getSelectionModel().getSelectedItem(),dte.getValue().toString(),dsc.getText());
                // if((np.getText().length()<2)||(L.getText().length()<1)||(Tit.getText().length()<1)||(dsc.getText().length()<1)){
                  // lab.setText("champs vide !");
                 //}
                   // else{
                  EventServices ps=new EventServices();
                  Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            
                         alert.setTitle("Notification");
                         alert.setContentText("Are u sure you want to modify Event ?");
                         Optional<ButtonType> result = alert.showAndWait();
ButtonType button = result.orElse(ButtonType.CANCEL);

if (button == ButtonType.OK) {
if(ps.modifierEvent(e))
{
    
    alert.setTitle("Notification");
                         alert.setContentText("Event successfully modified ");
                          alert.showAndWait();
}
else
{
    Alert alerta = new Alert(Alert.AlertType.ERROR);
            
                         alerta.setTitle("Notification");
                         alerta.setContentText("Evènement enregistrée avec succés");
                          alerta.showAndWait();
                          };

}

            
                  
                  FXMLLoader loader=new FXMLLoader(getClass().getResource("crudsFXML.fxml"));
         Parent root;
        try {
            root= loader.load();
            conf.getScene().setRoot(root);
            
        } catch (IOException ex) {
            Logger.getLogger(CrudsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        } 
            }}
       
    
            
            );
            
            
            
              ann.setOnAction(new EventHandler<ActionEvent>() {
             @Override
             public void handle(ActionEvent event) {
                  
                 int j ;
               int b= Integer.parseInt(tf_id.getText());
                  j = Integer.parseInt(np.getText()) ;
                 Event e=new Event(b,Tit.getText(),L.getText(), j,ch.getSelectionModel().getSelectedItem(),dte.getValue().toString(),dsc.getText());
                  EventServices ps=new EventServices();
                  Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            
                         alert.setTitle("Notification");
                         alert.setContentText("Are you sure you want to delete Event ?");
                         Optional<ButtonType> result = alert.showAndWait();
ButtonType button = result.orElse(ButtonType.CANCEL);

if (button == ButtonType.OK) {
if(ps.supprimerEvent(b))
{
    alert.setTitle("Notification");
                         alert.setContentText("Event successfully deleted ");
                          alert.showAndWait();
}
else
{
    Alert alerta = new Alert(Alert.AlertType.ERROR);
            
                         alerta.setTitle("Notification");
                         alerta.setContentText("Error");
                          alerta.showAndWait();
};

}


                  
                  FXMLLoader loader=new FXMLLoader(getClass().getResource("crudsFXML.fxml"));
         Parent root;
        try {
            root= loader.load();
            ann.getScene().setRoot(root);
            
        } catch (IOException ex) {
            Logger.getLogger(CrudsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        } 
            }
       
    }
            
            );
               staat.setOnAction(new EventHandler<ActionEvent>() {
             @Override
             public void handle(ActionEvent event) {
               ReservationServices s=new ReservationServices();
               int a=0;int b=0;int c=0;
                 try {
              a=s.getNombre("succés");
               b=  s.getNombre("moyen");
              c=  s.getNombre("echec");
                     System.out.println(a);
                     System.out.println(b);
                     System.out.println(c);
                 } catch (SQLException ex) {
                     Logger.getLogger(CrudsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                 }
                 DefaultCategoryDataset dataset=new DefaultCategoryDataset();
                         dataset.setValue(a, "stat", "succés");
                         dataset.setValue(b, "stat", "moyen");
                         dataset.setValue(c, "stat", "echec");
                 JFreeChart chart=ChartFactory.createBarChart3D("etat d'evenment ", "etat", "nombres", dataset);
                 chart.setBackgroundPaint(Color.WHITE);
                 chart.getTitle().setPaint(Color.ORANGE);
                 CategoryPlot p=chart.getCategoryPlot();
                 p.setRangeGridlinePaint(Color.BLUE);
                 ChartFrame frame=new ChartFrame("bar d evenements",chart);
                 frame.setVisible(true);
                 frame.setSize(450,350);
                         
;
             
             
             
             
             }
    });
               pdf.setOnAction(new EventHandler<ActionEvent>() {
           @Override
            public void handle(ActionEvent event) {
          
                String ad="C:\\Users\\takoua\\Desktop\\ads.pdf";
                Document doc=new Document();
               try {
                   PdfWriter.getInstance(doc, new FileOutputStream(ad));
               } catch (FileNotFoundException ex) {
                   Logger.getLogger(CrudsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
               } catch (DocumentException ex) {
                   Logger.getLogger(CrudsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
               }
               doc.open();
               try {
                    doc.add(new Paragraph("ADFitness"));
                    doc.add(new Paragraph("Rapport des Evènements"));
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
                    LocalDateTime now = LocalDateTime.now();  
                    String d=dtf.format(now);
                    doc.add(new Paragraph("Date: "+d));
                    doc.add(new Paragraph(" "));
                    doc.add(new Paragraph(" Gerant responsable : " ));
                    doc.add(new Paragraph(" "));
                    PdfPTable table = new PdfPTable(5);
                    PdfPCell c1=new PdfPCell(new Phrase("Titre"));
                    table.addCell(c1);
                    c1=new PdfPCell(new Phrase("Lieu"));
                    table.addCell(c1);
                     c1=new PdfPCell(new Phrase("Nombre de place"));
                    table.addCell(c1);
                    c1=new PdfPCell(new Phrase("Coach"));
                    table.addCell(c1);
                    c1=new PdfPCell(new Phrase("Date"));
                    table.addCell(c1);
                    c1=new PdfPCell(new Phrase("Desc"));
                    table.addCell(c1);
                    // table.setHeaderRows(0);
                    EventServices s=new  EventServices();
                    ArrayList<Event> e =(ArrayList<Event>)s.getAllEvents();
                    for(int i=0;i<e.size();i++)
                    {
                        String n=e.get(i).getTitre();
                        table.addCell(n);
                        
                        String r=e.get(i).getLieu();
                        table.addCell(r);
                        
                     
                       int da=e.get(i).getNb_p();
                        
                        table.addCell(String.valueOf(da));
                        
                          String sa=e.get(i).getCoach();
                        table.addCell(sa);
                        String dom=e.get(i).getDate();
                        table.addCell(dom);
                      
                        String ka=e.get(i).getDesc();
                        table.addCell(ka);
                    }
                    doc.add(table);
               }catch (DocumentException ex) {
                   Logger.getLogger(CrudsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
               } catch (SQLException ex) {
                   Logger.getLogger(CrudsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
               }
               doc.close();
                }
        }) ;
      
           
          /*  ref.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               int  j = Integer.parseInt(np.getText()) ;
              Event e = new Event (Tit.getText(),L.getText(),j,ch.getText(),dte.getText(),dsc.getText());
               EventServices ps=new EventServices();
                try {
                    ps.diffuser();
                   // ps.diffuser();
                    
                        FXMLLoader loader= new  FXMLLoader(getClass().getResource("crudsFXML.fxml"));
                    Parent root;
                    try{ root=loader.load();
                         ref.getScene().setRoot(root);
                         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                         alert.setTitle("Notification");
                         alert.setContentText("Evènement enregistrée avec succés");
                         alert.showAndWait();
                    } catch (IOException ex) {
                        Logger.getLogger(CrudsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }  
                    
                } catch (SQLException ex) {
                    Logger.getLogger(CrudsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    
                }
    
            }
          
        }) ; */
                 
            
    }
      
    
        // TODO
        // TODO
     
        // TODO
    public String Combo() {
         
       return ch.getSelectionModel().getSelectedItem();
    } 
       

    @FXML
    private void make_window_drageable(MouseEvent event) {
    }
    }    
    

