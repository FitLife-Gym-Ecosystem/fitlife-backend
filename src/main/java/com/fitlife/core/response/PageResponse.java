package com.fitlife.core.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "PageResponse", description = "Dữ liệu phân trang chuẩn của hệ thống FitLife")
public class PageResponse<T> {
    @Schema(description = "Trang hiện tại (bắt đầu từ 1)", example = "1")
    private int currentPage;

    @Schema(description = "Tổng số trang", example = "12")
    private int totalPages;

    @Schema(description = "Kích thước trang", example = "10")
    private int pageSize;

    @Schema(description = "Tổng số bản ghi", example = "115")
    private long totalElements;

    @Schema(description = "Danh sách dữ liệu của trang hiện tại")
    private List<T> data;
}