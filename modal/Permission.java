package com.lms_user_servicess.modal;


import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.lms_user_servicess.enums.ModuleType;
import com.lms_user_servicess.enums.PermissionType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "permissions")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Permission {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "permission_id")
	private Long id;

	@Enumerated(EnumType.STRING)
	@Column(name = "permission_name", nullable = false, unique = true)
	private PermissionType permissionName;

	@Enumerated(EnumType.STRING)
	@Column(name = "module_name", nullable = false)
	private ModuleType moduleName;

	@Column(length = 255)
	private String description;

	@Default
	@Column(name = "is_active")
	private Boolean active = true;

	@CreationTimestamp
	@Column(name = "created_at", updatable = false)
	private LocalDateTime createdAt;

	@UpdateTimestamp
	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

	@Default
	@ManyToMany(mappedBy = "permissions")
	private Set<Role> roles = new HashSet<>();
}