package com.lms_user_servicess.modal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "VerificationCode")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VerificationCode {

	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
//
//	    @Column(nullable = false)
//	    private String email;
//
//	    @Column(nullable = false)
//	    private String otpHash; // BCrypt hash of the OTP — never store the raw code
//
//	    @Column(nullable = false)
//	    private LocalDateTime expiresAt;
//
//	    @Builder.Default
//	    @Column(nullable = false)
//	    private boolean used = false;
//
//	    @Builder.Default
//	    @Column(nullable = false)
//	    private int attemptCount = 0;
//
//	    @Builder.Default
//	    private LocalDateTime createdAt = LocalDateTime.now();
}