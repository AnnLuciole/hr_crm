package com.company.hr_crm.screen.vacancy;

import com.company.hr_crm.entity.Candidate;
import com.company.hr_crm.entity.Request;
import io.jmix.core.DataManager;
import io.jmix.ui.screen.*;
import com.company.hr_crm.entity.Vacancy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@UiController("hrcrm_Vacancy.edit")
@UiDescriptor("vacancy-edit.xml")
@EditedEntityContainer("vacancyDc")
public class VacancyEdit extends StandardEditor<Vacancy> {

    @Autowired
    private DataManager dataManager;

    @Subscribe
    public void onBeforeCommitChanges(BeforeCommitChangesEvent event) {
        Vacancy vacancy = getEditedEntity();
        Request request = vacancy.getRequest();
        if(request == null) {
            request = dataManager.create(Request.class);
            vacancy.setRequest(request);
        }
        findAndAddCandidatesForVacancy(vacancy, request);
    }

    private void findAndAddCandidatesForVacancy(Vacancy vacancy, Request request){
        List<Candidate> allCandidates = dataManager.load(Candidate.class).all()
                .list().stream()
                .filter(x -> x.getYearsOfExperience() >= vacancy.getYearsOfExperience() &&
                        x.getSkills().containsAll(vacancy.getRequirements()))
                .collect(Collectors.toList());
        if (!(allCandidates.size() == 0)) {
            request.setCandidates(allCandidates);
        }
        dataManager.save(request);
        dataManager.save(vacancy);
    }
}