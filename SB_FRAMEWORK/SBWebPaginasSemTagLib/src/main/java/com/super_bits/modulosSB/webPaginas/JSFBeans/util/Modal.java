package com.super_bits.modulosSB.webPaginas.JSFBeans.util;

import java.util.HashMap;
import java.util.Map;


public class Modal {
	public void viewCars() {
      //  RequestContext.getCurrentInstance().openDialog("viewCars");
      //  RequestContext.getCurrentInstance().openDialog("viewCars", null, null);
    }
    
    public void viewCarsCustomized() {
        Map<String,Object> options = new HashMap<String, Object>();
        options.put("modal", true);
        options.put("draggable", false);
        options.put("resizable", false);
        options.put("contentHeight", 320);
        //hint: available options are modal, draggable, resizable, width, height, contentWidth and contentHeight
        
     //   RequestContext.getCurrentInstance().openDialog("viewCars", options, null);
    }
}
