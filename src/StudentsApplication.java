import java.sql.Timestamp;
import java.util.Date;

import user.ApplicantData;
import user.ApplicationResult;

public class StudentsApplication {
    private ApplicantData applicantData;
    private ApplicationResult applicationResult;
    private Date applicationCreation;

    public StudentsApplication(ApplicantData applicantData, ApplicationResult applicationResult, Date applicationCreation) {
        this.applicantData = applicantData;
        this.applicationResult = applicationResult;
        this.applicationCreation = applicationCreation;
    }

    public ApplicantData getApplicantData() {
        return applicantData;
    }

    public ApplicationResult getApplicationResult() {
        return applicationResult;
    }

    public Date getApplicationCreation() {
        return applicationCreation;
    }
}
