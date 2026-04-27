package com.fitlife.progress_tracking.controller;

import com.fitlife.core.response.ApiResponse;
import com.fitlife.identity.entity.User;
import com.fitlife.identity.repository.UserRepository;
import com.fitlife.progress_tracking.service.ProgressFacadeService;
import com.fitlife.progress_tracking.dto.MemberProgressSummaryResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/progress")
@RequiredArgsConstructor
@Tag(name = "Progress Tracking", description = "Báo cáo tổng hợp tiến độ tập luyện và sức khỏe cá nhân")
public class ProgressController {

    private final ProgressFacadeService progressFacadeService;
    private final UserRepository userRepository;

    @GetMapping("/my-summary")
    @PreAuthorize("hasAnyAuthority('MEMBER', 'ROLE_MEMBER')")
    @Operation(summary = "Báo cáo tiến độ cá nhân", description = "Tổng hợp thông tin hội viên, sức khỏe và tiến độ tập luyện của tài khoản hiện tại.")
    public ResponseEntity<ApiResponse<MemberProgressSummaryResponse>> getPersonalDashboard(Authentication auth) {

        User user = userRepository.findByUsername(auth.getName())
                .orElseThrow(() -> new RuntimeException("Tài khoản không hợp lệ"));

        if (user.getMember() == null) {
            throw new RuntimeException("Bạn chưa thiết lập hồ sơ hội viên!");
        }

        MemberProgressSummaryResponse report = progressFacadeService.getMyProgress(user.getMember().getId());
        return ResponseEntity.ok(ApiResponse.success(report, "Lấy báo cáo cá nhân thành công"));
    }
}