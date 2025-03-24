package kg.attractor.job_search_project.exceptions;

public class VacancyNotFoundException extends RuntimeException {
    public VacancyNotFoundException() {
        super("Vacancy Not Found");
    }
}
