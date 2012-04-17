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
    private int state = CreateReportLocal.TYPE_UNSPEC;

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

    public Date getExpiration() {
        if (state == CreateReportLocal.TYPE_PROBLEM_PRE || state == CreateReportLocal.TYPE_PROBLEM) {
            return createReport.getExpiration();
        } else {
            return new Date();
        }
    }

    public void setExpiration(Date date) {
        if (state == CreateReportLocal.TYPE_PROBLEM_PRE || state == CreateReportLocal.TYPE_PROBLEM) {
            createReport.setExpiration(date);
        }
    }

    public int getAccesibility() {
        if (state == CreateReportLocal.TYPE_PLACE_PRE || state == CreateReportLocal.TYPE_PLACE) {
            return createReport.getAccesibility();
        } else {
            return -1;
        }
    }

    public void setAccesibility(int accesibility) {
        if (state == CreateReportLocal.TYPE_PLACE_PRE || state == CreateReportLocal.TYPE_PLACE) {
            createReport.setAccesibility(accesibility);
        }
    }

    public void setState(String state) {
        if (state.equals("Problem")) {
            if (this.state == CreateReportLocal.TYPE_PROBLEM_PRE) {
                this.state = CreateReportLocal.TYPE_PROBLEM;
            } else {
                if (this.state != CreateReportLocal.TYPE_PROBLEM) {
                    this.state = CreateReportLocal.TYPE_PROBLEM_PRE;
                }
            }
        } else {
            if (state.equals("Tip")) {
                this.state = CreateReportLocal.TYPE_TIP;
            } else {
                if (state.equals("Place")) {
                    if (this.state == CreateReportLocal.TYPE_PLACE_PRE) {
                        this.state = CreateReportLocal.TYPE_PLACE;
                    } else {
                        if (this.state != CreateReportLocal.TYPE_PLACE) {
                            this.state = CreateReportLocal.TYPE_PLACE_PRE;
                        }

                    }
                }
            }
        }
    }

    public String getState() {
        switch (state) {
            case CreateReportLocal.TYPE_PROBLEM_PRE:
            case CreateReportLocal.TYPE_PROBLEM:
                return "Problem";

            case CreateReportLocal.TYPE_TIP:
                return "Tip";

            case CreateReportLocal.TYPE_PLACE_PRE:
            case CreateReportLocal.TYPE_PLACE:
                return "Place";
        }
        return "";
    }

    public String moveToNextStage() {
        switch (state) {
            case CreateReportLocal.TYPE_UNSPEC:
                break;
            case CreateReportLocal.TYPE_TIP:
                createReport.createTip();
                this.state = CreateReportLocal.TYPE_UNSPEC;
                createReport.clear();
                return "index";

            case CreateReportLocal.TYPE_PROBLEM_PRE:
                createReport.preCreateProblem();
                state = CreateReportLocal.TYPE_PROBLEM;
                break;
            case CreateReportLocal.TYPE_PROBLEM:
                createReport.createProblem();
                createReport.clear();
                this.state = CreateReportLocal.TYPE_UNSPEC;
                return "index";
            case CreateReportLocal.TYPE_PLACE_PRE:
                createReport.createPlacePre();
                state = CreateReportLocal.TYPE_PLACE;
                break;
            case CreateReportLocal.TYPE_PLACE:
                createReport.createPlace();
                createReport.clear();
                this.state = CreateReportLocal.TYPE_UNSPEC;
                return "index";
        }

        return "createReport";

    }

    /** Creates a new instance of CreateReportBean */
    public CreateReportBean() {
    }
}
