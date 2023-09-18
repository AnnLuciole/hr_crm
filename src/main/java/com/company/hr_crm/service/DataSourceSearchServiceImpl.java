package com.company.hr_crm.service;

import com.company.hr_crm.entity.Candidate;
import com.company.hr_crm.entity.Request;
import com.company.hr_crm.entity.Vacancy;
import io.jmix.core.DataManager;
import io.jmix.core.FetchPlan;
import io.jmix.core.SaveContext;
import io.jmix.core.event.EntityChangedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class DataSourceSearchServiceImpl implements DataSourceSearchService {

    private final DataManager dataManager;

    @Autowired
    public DataSourceSearchServiceImpl(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Transactional
    public void searchAndAddAllCandidates(EntityChangedEvent<Vacancy> event) {
        Request request = dataManager.create(Request.class);
        Vacancy vacancy = dataManager.getReference(event.getEntityId());
        request.setVacancy(vacancy);
        List<Candidate> allCandidates = dataManager.load(Candidate.class).all()
                .list().stream()
                .filter(x -> x.getYearsOfExperience().equals(vacancy.getYearsOfExperience()) &&
                        x.getSkills().containsAll(vacancy.getRequirements()))
                .collect(Collectors.toList());
        if(allCandidates.size() == 0) {
            request.setCandidates(allCandidates);
        }
        vacancy.setRequest(request);
        dataManager.save(request);
    }

    public void searchVacancyForCandidate(EntityChangedEvent<Candidate> event){
        Candidate candidate = dataManager.getReference(event.getEntityId());
        Vacancy desiredVacancy = candidate.getDesiredVacancy();
        List<Vacancy> allVacancies = dataManager.load(Vacancy.class).all()
                .fetchPlan(fpb -> fpb.addFetchPlan(FetchPlan.BASE).add("vacancy"))
                .list().stream()
                .filter(x -> x.getName().equals(desiredVacancy.getName()) &&
                        candidate.getSkills().containsAll(x.getRequirements()) &&
                        x.getYearsOfExperience().equals(candidate.getYearsOfExperience()))
                .collect(Collectors.toList());
        List<Request> allRequests = allVacancies.stream()
                .map(Vacancy::getRequest)
                .collect(Collectors.toList());
        allRequests.forEach(x -> x.getCandidates().add(candidate));
        dataManager.save(allRequests, allVacancies);
    }
}
