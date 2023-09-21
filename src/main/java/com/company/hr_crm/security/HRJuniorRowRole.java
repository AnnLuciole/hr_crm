package com.company.hr_crm.security;

import com.company.hr_crm.entity.Requirement;
import com.company.hr_crm.entity.Vacancy;
import io.jmix.security.model.EntityAttributePolicyAction;
import io.jmix.security.model.EntityPolicyAction;
import io.jmix.security.role.annotation.EntityAttributePolicy;
import io.jmix.security.role.annotation.EntityPolicy;
import io.jmix.security.role.annotation.JpqlRowLevelPolicy;
import io.jmix.security.role.annotation.RowLevelRole;
import io.jmix.securityui.role.annotation.MenuPolicy;
import io.jmix.securityui.role.annotation.ScreenPolicy;

import javax.annotation.Nonnull;

@Nonnull
@RowLevelRole(name = "Can see only Orders created by themselves", code = "h-r-junior-row-role")
public interface HRJuniorRowRole {

    @JpqlRowLevelPolicy(
            entityClass = Vacancy.class,
            where = "{E}.createdBy = :current_user_username")
    void vacancy();
}