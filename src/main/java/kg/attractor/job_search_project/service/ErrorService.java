package kg.attractor.job_search_project.service;

import kg.attractor.job_search_project.exceptions.ErrorResponseBody;
import org.springframework.validation.BindingResult;

public interface ErrorService {

    ErrorResponseBody makeResponse(Exception e);

    ErrorResponseBody makeResponse(BindingResult bindingResult);
}
