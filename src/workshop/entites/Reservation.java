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
public class Reservation {
     private int ide ;
    private String Eve ;
    private String fullname;
    private String email ;
      private int phone_number ;

    public Reservation() {
    }

    public Reservation(String Eve) {
        this.Eve = Eve;
    }
    
    public Reservation(String Eve, String fullname, String email, int phone_number) {
        this.Eve = Eve;
        this.fullname = fullname;
        this.email = email;
        this.phone_number = phone_number;
    }
    

    public Reservation(int ide, String Eve, String fullname, String email, int phone_number) {
        this.ide = ide;
        this.Eve = Eve;
        this.fullname = fullname;
        this.email = email;
        this.phone_number = phone_number;
    }

    public int getIde() {
        return ide;
    }

    public String getEve() {
        return Eve;
    }

    public String getFullname() {
        return fullname;
    }

    public String getEmail() {
        return email;
    }

    public int getPhone_number() {
        return phone_number;
    }

    public void setIde(int ide) {
        this.ide = ide;
    }

    public void setEvent(String Event) {
        this.Eve = Eve;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone_number(int phone_number) {
        this.phone_number = phone_number;
    }

    @Override
    public String toString() {
        return  Eve ;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 61 * hash + this.ide;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Reservation other = (Reservation) obj;
        if (this.ide != other.ide) {
            return false;
        }
        return true;
    }
      
      
      
      
      
      
      
      
      
      
      
      
      
    
}
