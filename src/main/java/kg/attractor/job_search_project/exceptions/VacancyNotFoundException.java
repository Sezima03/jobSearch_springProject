package kg.attractor.job_search_project.exceptions;

import java.util.NoSuchElementException;

public class VacancyNotFoundException extends NoSuchElementException {
    public VacancyNotFoundException(String message) {
        super(message);
    }
}
