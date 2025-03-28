package kg.attractor.job_search_project.exceptions;

import java.util.NoSuchElementException;

public class JobSearchException extends NoSuchElementException {
    public JobSearchException(String message) {
        super(message);
    }
}
