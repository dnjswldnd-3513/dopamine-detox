package com.example.dopamine_detox.service;

import com.example.dopamine_detox.domain.AppCategory;
import com.example.dopamine_detox.dto.request.AppCategoryRequest;
import com.example.dopamine_detox.exception.DuplicateException;
import com.example.dopamine_detox.repository.AppCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AppCategoryService {

    private final AppCategoryRepository appCategoryRepository;

    @Transactional
    public AppCategory create(AppCategoryRequest request) {
        if (appCategoryRepository.existsByName(request.getName())) {
            throw new DuplicateException("Category already exists: " + request.getName());
        }
        return appCategoryRepository.save(AppCategory.builder().name(request.getName()).build());
    }

    public List<AppCategory> getAll() {
        return appCategoryRepository.findAll();
    }
}
