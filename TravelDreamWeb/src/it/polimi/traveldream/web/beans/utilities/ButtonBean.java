package it.polimi.traveldream.web.beans.utilities;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named("buttonBean")
@SessionScoped
public class ButtonBean implements Serializable {
   private static final long serialVersionUID = -7367234L;
   private Integer number;

   public Integer getNumber() {
      return number;
   }

   public void setNumber(Integer number) {
      System.out.println(number);
      this.number = number;
   }
}


