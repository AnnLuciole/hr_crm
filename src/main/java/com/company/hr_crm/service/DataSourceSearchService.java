package com.company.hr_crm.service;

import com.company.hr_crm.entity.Candidate;
import com.company.hr_crm.entity.Vacancy;
import io.jmix.core.event.EntityChangedEvent;

public interface DataSourceSearchService {

    void searchAndAddAllCandidates(EntityChangedEvent<Vacancy> event);
    void searchVacancyForCandidate(EntityChangedEvent<Candidate> event);
}
