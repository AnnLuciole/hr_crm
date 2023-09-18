package com.company.hr_crm.screen.requirement;

import io.jmix.ui.screen.*;
import com.company.hr_crm.entity.Requirement;

@UiController("hrcrm_Requirement.edit")
@UiDescriptor("requirement-edit.xml")
@EditedEntityContainer("requirementDc")
public class RequirementEdit extends StandardEditor<Requirement> {
}