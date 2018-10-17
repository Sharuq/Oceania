/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oceania.controllers;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.el.ELContext;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import oceania.entities.Booking;
import oceania.entities.Users;
import oceania.search.BookingDetailsSearch;

/**
 *
 * @author Sharuq
 */
@Named(value = "detailsBean")
@RequestScoped
public class DetailsBean {

    @EJB
    private BookingDetailsSearch bookingDetailsSearch;
    
    private int bookingNo;
    private Booking booking;
    private Users users;
    private int price=0;
    private int surcharge=0;
    private float total=0;
    
    
    public DetailsBean() {
        bookingNo=Integer.valueOf(FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap()
                .get("bookingNo"));
    }
    @PostConstruct
    public void getBookingDetails() {
      
        try{
            
            booking= bookingDetailsSearch.bookingDetails(bookingNo);
            price=booking.getPackageId().getPackageCharge();
            surcharge= booking.getResortId().getSurCharge();
            total=(float)(price+((price/100)*surcharge));
            
            
            }
        catch (Exception ex) {
                 Logger.getLogger(BookingDetailsSearchBean.class.getName()).log(Level.SEVERE, null, ex);
             }
         
    }           
    
    
    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getSurcharge() {
        return surcharge;
    }

    public void setSurcharge(int surcharge) {
        this.surcharge = surcharge;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
    
    public int getBookingNo() {
        return bookingNo;
    }

    public void setBookingNo(int bookingNo) {
        this.bookingNo = bookingNo;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }
    
    
    
   
    
    
    
}
