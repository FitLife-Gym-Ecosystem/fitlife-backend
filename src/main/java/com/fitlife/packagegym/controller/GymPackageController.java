package com.fitlife.packagegym.controller;

import com.fitlife.core.response.ApiResponse;
import com.fitlife.core.response.PageResponse;
import com.fitlife.packagegym.dto.GymPackageRequest;
import com.fitlife.packagegym.dto.GymPackageResponse;
import com.fitlife.packagegym.service.GymPackageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/packages")
@RequiredArgsConstructor
@Tag(name = "Package Management", description = "Quản lý gói tập, trạng thái và danh sách public/admin")
public class GymPackageController {

    private final GymPackageService packageService;
    @GetMapping
    @PreAuthorize("permitAll()")
    @SecurityRequirements()
    @Operation(summary = "Danh sách gói tập công khai", description = "Lấy danh sách các gói tập đang hoạt động dành cho khách truy cập.")
    public ResponseEntity<ApiResponse<PageResponse<GymPackageResponse>>> getActivePackages(
            @Parameter(description = "Trang hiện tại, bắt đầu từ 1", example = "1")
            @RequestParam(defaultValue = "1") int page,
            @Parameter(description = "Kích thước trang", example = "10")
            @RequestParam(defaultValue = "10") int size,
            @Parameter(description = "Trường sắp xếp", example = "id")
            @RequestParam(defaultValue = "id") String sortBy,
            @Parameter(description = "Chiều sắp xếp: ASC hoặc DESC", example = "DESC")
            @RequestParam(defaultValue = "DESC") String sortDir,
            @Parameter(description = "Từ khóa tìm kiếm gói tập", example = "Premium")
            @RequestParam(required = false) String keyword
    ) {
        PageResponse<GymPackageResponse> result = packageService.getAllPackages(page, size, sortBy, sortDir, keyword);
        return ResponseEntity.ok(ApiResponse.success(result, "Lấy danh sách gói tập thành công"));
    }
    @GetMapping("/{id}")
    @PreAuthorize("permitAll()")
    @SecurityRequirements()
    @Operation(summary = "Chi tiết gói tập công khai", description = "Xem thông tin chi tiết một gói tập theo ID.")
    public ResponseEntity<ApiResponse<GymPackageResponse>> getPackageById(@PathVariable Long id) {
        GymPackageResponse result = packageService.getPackageById(id);
        return ResponseEntity.ok(ApiResponse.success(result, "Lấy thông tin chi tiết gói tập thành công"));
    }

    @GetMapping("/admin")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'ROLE_ADMIN')")
    @Operation(summary = "Danh sách gói tập cho admin", description = "Lấy danh sách gói tập dành cho quản trị viên với phân trang và lọc.")
    public ResponseEntity<ApiResponse<PageResponse<GymPackageResponse>>> getAllPackagesForAdmin(
            @Parameter(description = "Trang hiện tại, bắt đầu từ 1", example = "1")
            @RequestParam(defaultValue = "1") int page,
            @Parameter(description = "Kích thước trang", example = "10")
            @RequestParam(defaultValue = "10") int size,
            @Parameter(description = "Trường sắp xếp", example = "id")
            @RequestParam(defaultValue = "id") String sortBy,
            @Parameter(description = "Chiều sắp xếp: ASC hoặc DESC", example = "DESC")
            @RequestParam(defaultValue = "DESC") String sortDir,
            @Parameter(description = "Từ khóa tìm kiếm gói tập", example = "Basic")
            @RequestParam(required = false) String keyword
    ) {
        PageResponse<GymPackageResponse> result = packageService.getAllPackages(page, size, sortBy, sortDir, keyword);
        return ResponseEntity.ok(ApiResponse.success(result, "Lấy danh sách quản lý gói tập (Admin) thành công"));
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'ROLE_ADMIN')")
    @Operation(summary = "Tạo gói tập", description = "Tạo mới một gói tập dành cho hệ thống quản trị.")
    public ResponseEntity<ApiResponse<GymPackageResponse>> createPackage(@Valid @RequestBody GymPackageRequest request) {
        GymPackageResponse result = packageService.createPackage(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.created(result, "Tạo gói tập mới thành công"));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'ROLE_ADMIN')")
    @Operation(summary = "Cập nhật gói tập", description = "Cập nhật thông tin gói tập theo ID.")
    public ResponseEntity<ApiResponse<GymPackageResponse>> updatePackage(
            @PathVariable Long id,
            @Valid @RequestBody GymPackageRequest request) {

        GymPackageResponse result = packageService.updatePackage(id, request);
        return ResponseEntity.ok(ApiResponse.success(result, "Cập nhật thông tin gói tập thành công"));
    }

    @PatchMapping("/{id}/toggle-status")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'ROLE_ADMIN')")
    @Operation(summary = "Bật/tắt trạng thái gói tập", description = "Chuyển trạng thái hoạt động của gói tập theo ID.")
    public ResponseEntity<ApiResponse<String>> togglePackageStatus(@PathVariable Long id) {
        packageService.togglePackageStatus(id);
        return ResponseEntity.ok(ApiResponse.success(null, "Cập nhật trạng thái gói tập thành công"));
    }
}