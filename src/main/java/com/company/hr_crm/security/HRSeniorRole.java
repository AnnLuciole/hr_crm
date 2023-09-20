package com.company.hr_crm.security;

import com.company.hr_crm.entity.Candidate;
import com.company.hr_crm.entity.Request;
import com.company.hr_crm.entity.Requirement;
import com.company.hr_crm.entity.Vacancy;
import io.jmix.security.model.EntityAttributePolicyAction;
import io.jmix.security.model.EntityPolicyAction;
import io.jmix.security.role.annotation.EntityAttributePolicy;
import io.jmix.security.role.annotation.EntityPolicy;
import io.jmix.security.role.annotation.ResourceRole;
import io.jmix.securityui.role.annotation.MenuPolicy;
import io.jmix.securityui.role.annotation.ScreenPolicy;

import javax.annotation.Nonnull;

@Nonnull
@ResourceRole(name = "HR_Senior", code = "h-r_-senior", scope = "UI")
public interface HRSeniorRole {

    @ScreenPolicy(screenIds = "hrcrm_LoginScreen")
    void login();

    @ScreenPolicy(screenIds = "hrcrm_MainScreen")
    @MenuPolicy(menuIds = "hrcrm_MainScreen")
    void main();

    @EntityPolicy(
            entityClass = Candidate.class,
            actions = {EntityPolicyAction.READ})
    @EntityAttributePolicy(
            entityClass = Candidate.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    @ScreenPolicy(screenIds = "hrcrm_Candidate.browse")
    @MenuPolicy(menuIds = "hrcrm_Candidate.browse")
    void candidate();

    @EntityPolicy(
            entityClass = Request.class,
            actions = {EntityPolicyAction.READ})
    @EntityAttributePolicy(
            entityClass = Request.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    @ScreenPolicy(screenIds = "hrcrm_Request.browse")
    @MenuPolicy(menuIds = "hrcrm_Request.browse")
    void request();

    @EntityPolicy(
            entityClass = Requirement.class,
            actions = {EntityPolicyAction.READ,
                    EntityPolicyAction.CREATE,
                    EntityPolicyAction.UPDATE})
    @EntityAttributePolicy(
            entityClass = Requirement.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @ScreenPolicy(screenIds = {"hrcrm_Requirement.browse", "hrcrm_Requirement.edit"})
    @MenuPolicy(menuIds = {"hrcrm_Requirement.browse", "hrcrm_Requirement.edit"})
    void requirement();

    @EntityPolicy(entityClass = Vacancy.class,
            actions = {EntityPolicyAction.READ,
                    EntityPolicyAction.CREATE,
                    EntityPolicyAction.UPDATE})
    @EntityAttributePolicy(
            entityClass = Vacancy.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @ScreenPolicy(screenIds = {"hrcrm_Vacancy.browse", "hrcrm_Vacancy.edit"})
    @MenuPolicy(menuIds = {"hrcrm_Vacancy.browse", "hrcrm_Vacancy.edit"})
    void vacancy();
}