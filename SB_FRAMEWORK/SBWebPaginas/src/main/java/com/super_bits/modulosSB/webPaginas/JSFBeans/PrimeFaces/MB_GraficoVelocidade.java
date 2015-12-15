package com.super_bits.modulosSB.webPaginas.JSFBeans.PrimeFaces;

import java.io.Serializable;  
import java.util.ArrayList;
import java.util.List;
import org.primefaces.model.chart.MeterGaugeChartModel;  
  
public class MB_GraficoVelocidade implements Serializable {   
  
    private MeterGaugeChartModel meterGaugeModel;  
  
    public MB_GraficoVelocidade() {  
        createMeterGaugeModel();  
    }  
  
    private void createMeterGaugeModel() {  
  
        List<Number> intervals = new ArrayList<Number>(){{  
            add(20);  
            add(50);  
            add(120);  
            add(220);  
        }};  
  
        meterGaugeModel = new MeterGaugeChartModel(140, intervals);  
    }  
}  