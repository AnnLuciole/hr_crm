package com.company.hr_crm.screen.candidate;

import com.company.hr_crm.entity.Request;
import com.company.hr_crm.entity.Vacancy;
import io.jmix.core.DataManager;
import io.jmix.ui.screen.*;
import com.company.hr_crm.entity.Candidate;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("hrcrm_Candidate.edit")
@UiDescriptor("candidate-edit.xml")
@EditedEntityContainer("candidateDc")
public class CandidateEdit extends StandardEditor<Candidate> {
    @Autowired
    private DataManager dataManager;

    @Subscribe
    public void onBeforeCommitChanges(BeforeCommitChangesEvent event) {
        Candidate candidate = getEditedEntity();
        Vacancy desiredVacancy = candidate.getDesiredVacancy();
        Vacancy vacancy = dataManager.load(Vacancy.class).all()
                .list().stream()
                .filter(x -> x.getName().equals(desiredVacancy.getName()) &&
                        candidate.getSkills().containsAll(x.getRequirements()) &&
                        x.getYearsOfExperience() <= candidate.getYearsOfExperience())
                .findFirst().orElse(null);
        if (vacancy != null) {
            Request request = dataManager.load(Vacancy.class).query("select e from hrcrm_Vacancy e where e.id = :id")
                    .parameter("id", vacancy.getId()).one().getRequest();
            candidate.setRequest(request);
        }
        dataManager.save(candidate);
    }
}