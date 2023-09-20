package com.company.hr_crm.screen.candidate;

import com.company.hr_crm.entity.Request;
import com.company.hr_crm.entity.Vacancy;
import io.jmix.core.DataManager;
import io.jmix.core.FetchPlan;
import io.jmix.ui.screen.*;
import com.company.hr_crm.entity.Candidate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@UiController("hrcrm_Candidate.edit")
@UiDescriptor("candidate-edit.xml")
@EditedEntityContainer("candidateDc")
public class CandidateEdit extends StandardEditor<Candidate> {
    @Autowired
    private DataManager dataManager;

    @Subscribe
    public void onBeforeCommitChanges(BeforeCommitChangesEvent event) {
        Candidate candidate = getEditedEntity();
        List<Vacancy> allVacancies = dataManager.load(Vacancy.class).all()
                .fetchPlan(fpb -> fpb.addFetchPlan(FetchPlan.BASE).add("request"))
                .list().stream()
                .filter(x -> candidate.getSkills().containsAll(x.getRequirements()) &&
                        x.getYearsOfExperience() <= candidate.getYearsOfExperience())
                .collect(Collectors.toList());
        if (!allVacancies.isEmpty()) {
            for (Vacancy vacancy:allVacancies) {
                Request request = dataManager.load(Request.class)
                        .query("select r from hrcrm_Request r where r.id = :id")
                        .parameter("id", vacancy.getRequest().getId())
                        .fetchPlan(fpb -> fpb.addFetchPlan(FetchPlan.BASE).add("candidates"))
                        .one();
                request.getCandidates().add(candidate);
                if (candidate.getRequest() == null) {
                    candidate.setRequest(List.of(request));
                } else {
                    candidate.getRequest().add(request);
                }
                dataManager.save(vacancy);
            }
        }
        dataManager.save(candidate);
    }
}