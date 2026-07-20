package com.example.dopamine_detox.controller;

import com.example.dopamine_detox.domain.AppCategory;
import com.example.dopamine_detox.dto.request.AppCategoryRequest;
import com.example.dopamine_detox.service.AppCategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class AppCategoryController {

    private final AppCategoryService appCategoryService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AppCategory> create(@Valid @RequestBody AppCategoryRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(appCategoryService.create(request));
    }

    @GetMapping
    public ResponseEntity<List<AppCategory>> getAll() {
        return ResponseEntity.ok(appCategoryService.getAll());
    }
}
