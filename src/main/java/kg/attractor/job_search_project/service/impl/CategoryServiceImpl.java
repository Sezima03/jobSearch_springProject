package kg.attractor.job_search_project.service.impl;

import kg.attractor.job_search_project.model.Category;
import kg.attractor.job_search_project.repository.CategoryRepository;
import kg.attractor.job_search_project.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category categoryName(Long id){
        return categoryRepository.findCategoryNameById(id);
    }
}
