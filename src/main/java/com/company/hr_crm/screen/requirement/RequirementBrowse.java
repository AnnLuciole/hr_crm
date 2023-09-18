package com.company.hr_crm.screen.requirement;

import io.jmix.ui.screen.*;
import com.company.hr_crm.entity.Requirement;

@UiController("hrcrm_Requirement.browse")
@UiDescriptor("requirement-browse.xml")
@LookupComponent("requirementsTable")
public class RequirementBrowse extends StandardLookup<Requirement> {
}