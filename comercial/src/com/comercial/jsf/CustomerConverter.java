package com.comercial.jsf;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
 

import com.comercial.model.Customer;
import com.comercial.service.CustomerServiceImpl;
 
@FacesConverter("customerConverter")
public class CustomerConverter implements Converter {
 
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if(value != null && value.trim().length() > 0) {
            try {
            	CustomerServiceImpl service = (CustomerServiceImpl) fc.getExternalContext().getApplicationMap().get("customerService");
            	return service.get(new Customer(Integer.parseInt(value)));
            	//return service.getCustomers().get(Integer.parseInt(value));
            	
            } catch(NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid theme."));
            }
        }
        else {
            return null;
        }
    }
 
    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        if(object != null) {
            //return String.valueOf( ((Customer) object).getIdCliente() );
        	return String.valueOf( ((Customer) object).getIdCliente() );
        }
        else {
            return null;
        }
    }   
}
