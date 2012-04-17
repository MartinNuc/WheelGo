/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package back;

import ejb.CreateReportLocal;
import java.util.Date;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author vlada
 */
@ManagedBean(name = "createReport")
@SessionScoped
public class CreateReportBean {
    @EJB
    private CreateReportLocal createReport;
    
    private int state;
    
    public String getName() {
        return createReport.getName();
    }
    
    public void setName(String name) {
        createReport.setName(name);
    }
    
    public void setDescription(String description) {
        createReport.setDescription(description);
    }
    
    public String getDescription() {
        return createReport.getDescription();
    }
    
    
    public Date getDate() {
        return createReport.getDate();
    }
    
    public void setDate(Date date) {
        createReport.setDate(date);
    }
    
    public void setState(String state) {
        if(state.equals("Problem")) {
            this.state = CreateReportLocal.TYPE_PROBLEM;
        } else {
            if(state.equals("Tip")) {
                this.state = CreateReportLocal.TYPE_TIP;
            } else {
                if(state.equals("Place")) {
                    this.state = CreateReportLocal.TYPE_PLACE;
                }
            }
        }
    }
    
    public String getState() {
        switch(createReport.getState()) {
            case CreateReportLocal.TYPE_PROBLEM: return "Problem"; 
            case CreateReportLocal.TYPE_TIP: return "Tip";
            case CreateReportLocal.TYPE_PLACE: return "Place";
        }
        return "FUuuuuuu!!!";
    }
    
    public String moveToNextStage() {
        return "createReport";
    }
    
    /** Creates a new instance of CreateReportBean */
    public CreateReportBean() {
    }
}
