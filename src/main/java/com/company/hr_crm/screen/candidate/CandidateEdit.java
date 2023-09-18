package com.company.hr_crm.screen.candidate;

import io.jmix.ui.screen.*;
import com.company.hr_crm.entity.Candidate;

@UiController("hrcrm_Candidate.edit")
@UiDescriptor("candidate-edit.xml")
@EditedEntityContainer("candidateDc")
public class CandidateEdit extends StandardEditor<Candidate> {
}