package com.company.hr_crm.screen.candidate;

import io.jmix.ui.screen.*;
import com.company.hr_crm.entity.Candidate;

@UiController("hrcrm_Candidate.browse")
@UiDescriptor("candidate-browse.xml")
@LookupComponent("candidatesTable")
public class CandidateBrowse extends StandardLookup<Candidate> {
}