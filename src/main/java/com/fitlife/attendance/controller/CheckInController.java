package com.fitlife.attendance.controller;

import com.fitlife.attendance.dto.CheckInResponse;
import com.fitlife.attendance.service.CheckInService;
import com.fitlife.core.response.ApiResponse;
import com.fitlife.identity.entity.User;
import com.fitlife.identity.repository.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/checkin")
@RequiredArgsConstructor
@Tag(name = "Check-in Management", description = "Xử lý check-in tại quầy hoặc tự check-in của hội viên")
public class CheckInController {

    private final CheckInService checkInService;
    private final UserRepository userRepository;

    /**
     * 1. Staff/Admin: Staff scan card/qr of member
     */
    @PostMapping("/{memberId}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_STAFF', 'ADMIN', 'STAFF')")
    @Operation(summary = "Check-in cho hội viên bởi nhân viên", description = "Nhân viên/quản lý quét hoặc nhập memberId để xác nhận check-in tại quầy.")
    public ResponseEntity<ApiResponse<CheckInResponse>> staffProcessCheckIn(
            @Parameter(description = "ID hội viên cần check-in", example = "101")
            @PathVariable Long memberId,
            Authentication authentication) {

        // Get information about the Staff performing the operation
        User staffUser = userRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new RuntimeException("Tài khoản nhân viên không hợp lệ"));

        // ANTI-FRAUD LOGIC: Staff/Admins are not allowed to check-in for themselves
        if (staffUser.getMember() != null && staffUser.getMember().getId().equals(memberId)) {
            throw new RuntimeException("LỖI GIAN LẬN: Nhân viên hoặc Quản lý không thể tự check-in cho chính mình tại quầy!");
        }

        CheckInResponse result = checkInService.processCheckIn(memberId, staffUser.getUsername());
        return ResponseEntity.ok(ApiResponse.success(result, "Check-in xử lý thành công bởi nhân viên"));
    }

    /**
     * 2. Stream Self-Service: Members automatically open the App to scan the code at the door
     */
    @PostMapping("/me")
    @PreAuthorize("hasAnyAuthority('ROLE_MEMBER', 'MEMBER')")
    @Operation(summary = "Tự check-in của hội viên", description = "Hội viên tự thực hiện check-in bằng tài khoản của mình.")
    public ResponseEntity<ApiResponse<CheckInResponse>> memberSelfCheckIn(Authentication authentication) {

        User user = userRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new RuntimeException("Tài khoản không hợp lệ"));

        if (user.getMember() == null) {
            throw new RuntimeException("Tài khoản này chưa có hồ sơ hội viên!");
        }

        CheckInResponse result = checkInService.processCheckIn(user.getMember().getId(), user.getUsername());
        return ResponseEntity.ok(ApiResponse.success(result, "Hội viên tự check-in thành công"));
    }
}