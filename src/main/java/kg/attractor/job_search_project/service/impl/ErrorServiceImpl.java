package kg.attractor.job_search_project.service.impl;

import kg.attractor.job_search_project.exceptions.ErrorResponseBody;
import kg.attractor.job_search_project.service.ErrorService;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ErrorServiceImpl implements ErrorService {

    @Override
    public ErrorResponseBody makeResponse(Exception e){
        String message = e.getMessage();
        return ErrorResponseBody.builder()
                .title(message)
                .response(Map.of("errors", List.of(message)))
                .build();
    }

    @Override
    public ErrorResponseBody makeResponse(BindingResult bindingResult){
        Map<String, List<String>>  response = new HashMap<>();
        bindingResult.getFieldErrors().stream()
                .filter(err->err.getDefaultMessage()!=null)
                .forEach(err->{
                    List<String>  errors = new ArrayList<>();
                    errors.add(err.getDefaultMessage());
                    if (!response.containsKey(err.getField())) {
                        response.put(err.getField(), errors);
                    }
                });

        return ErrorResponseBody.builder()
                .title("Validation error")
                .response(response)
                .build();
    }
}
