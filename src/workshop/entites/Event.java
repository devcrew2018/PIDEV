/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package workshop.entites;

/**
 *
 * @author takoua
 */
public class Event {
    private int ide ;
    private String titre ;
    private String lieu ;
    private int nb_p ;
    private String coach ; 
    private String date ;
    private String desc ;

    public Event() {
    }

    public Event(String titre) {
        this.titre = titre;
    }

    public Event(int ide, String titre, String lieu ,int nb_p,String coach ,String date,String desc) {
        this.ide = ide;
        this.titre = titre;
        this.lieu = lieu;
        this.nb_p=nb_p;
        this.coach=coach;
        this.date=date;
        this.desc=desc;
    }

    public Event(String titre, String lieu, int nb_p, String coach, String date, String desc) {
        this.titre = titre;
        this.lieu = lieu;
        this.nb_p = nb_p;
        this.coach = coach;
        this.date = date;
        this.desc = desc;
    }
    

    public void setNb_p(int nb_p) {
        this.nb_p = nb_p;
    }

    public int getNb_p() {
        return nb_p;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public String getCoach() {
        return coach;
    }

    public String getDate() {
        return date;
    }

    public void setCoach(String coach) {
        this.coach = coach;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getIde() {
        return ide;
    }

    public String getTitre() {
        return titre;
    }

    public String getLieu() {
        return lieu;
    }

    public void setIde(int ide) {
        this.ide = ide;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    @Override
    public String toString() {
       // return "Event{" + "ide=" + ide + ", titre=" + titre + ", lieu=" + lieu + ", coach=" + coach + ", date=" + date + ", desc=" + desc + '}';
    return titre ;
    }

    

   
    
    
    
    
    
}
