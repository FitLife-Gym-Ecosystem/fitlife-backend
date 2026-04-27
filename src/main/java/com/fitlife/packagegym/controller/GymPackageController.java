package com.fitlife.packagegym.controller;

import com.fitlife.core.response.ApiResponse;
import com.fitlife.core.response.PageResponse;
import com.fitlife.packagegym.dto.GymPackageRequest;
import com.fitlife.packagegym.dto.GymPackageResponse;
import com.fitlife.packagegym.service.GymPackageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/packages")
@RequiredArgsConstructor
public class GymPackageController {

    private final GymPackageService packageService;
    @GetMapping
    @PreAuthorize("permitAll()")
    public ResponseEntity<ApiResponse<PageResponse<GymPackageResponse>>> getActivePackages(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "DESC") String sortDir,
            @RequestParam(required = false) String keyword
    ) {
        PageResponse<GymPackageResponse> result = packageService.getAllPackages(page, size, sortBy, sortDir, keyword);
        return ResponseEntity.ok(ApiResponse.success(result, "Lấy danh sách gói tập thành công"));
    }
    @GetMapping("/{id}")
    @PreAuthorize("permitAll()")
    public ResponseEntity<ApiResponse<GymPackageResponse>> getPackageById(@PathVariable Long id) {
        GymPackageResponse result = packageService.getPackageById(id);
        return ResponseEntity.ok(ApiResponse.success(result, "Lấy thông tin chi tiết gói tập thành công"));
    }

    @GetMapping("/admin")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'ROLE_ADMIN')")
    public ResponseEntity<ApiResponse<PageResponse<GymPackageResponse>>> getAllPackagesForAdmin(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "DESC") String sortDir,
            @RequestParam(required = false) String keyword
    ) {
        PageResponse<GymPackageResponse> result = packageService.getAllPackages(page, size, sortBy, sortDir, keyword);
        return ResponseEntity.ok(ApiResponse.success(result, "Lấy danh sách quản lý gói tập (Admin) thành công"));
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'ROLE_ADMIN')")
    public ResponseEntity<ApiResponse<GymPackageResponse>> createPackage(@Valid @RequestBody GymPackageRequest request) {
        GymPackageResponse result = packageService.createPackage(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.created(result, "Tạo gói tập mới thành công"));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'ROLE_ADMIN')")
    public ResponseEntity<ApiResponse<GymPackageResponse>> updatePackage(
            @PathVariable Long id,
            @Valid @RequestBody GymPackageRequest request) {

        GymPackageResponse result = packageService.updatePackage(id, request);
        return ResponseEntity.ok(ApiResponse.success(result, "Cập nhật thông tin gói tập thành công"));
    }

    @PatchMapping("/{id}/toggle-status")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'ROLE_ADMIN')")
    public ResponseEntity<ApiResponse<String>> togglePackageStatus(@PathVariable Long id) {
        packageService.togglePackageStatus(id);
        return ResponseEntity.ok(ApiResponse.success(null, "Cập nhật trạng thái gói tập thành công"));
    }
}