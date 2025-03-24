package kg.attractor.job_search_project.exceptions;

public class ResumeNotFoundException extends RuntimeException {
    public ResumeNotFoundException() {
        super("Resume not found");
    }
}
