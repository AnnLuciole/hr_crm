package com.company.hr_crm.listener;

import com.company.hr_crm.entity.Candidate;
import com.company.hr_crm.service.DataSourceSearchService;
import io.jmix.core.event.EntityChangedEvent;
import io.jmix.core.event.EntityLoadingEvent;
import io.jmix.core.event.EntitySavingEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component("hrcrm_CandidateEventListener")
public class CandidateEventListener {

    private final DataSourceSearchService dataSourceSearchService;

    @Autowired
    public CandidateEventListener(DataSourceSearchService dataSourceSearchService) {
        this.dataSourceSearchService = dataSourceSearchService;
    }

    @TransactionalEventListener
    public void onCandidateChangedAfterCommit(EntityChangedEvent<Candidate> event) {
        if (event.getType().equals(EntityChangedEvent.Type.CREATED)) {
            dataSourceSearchService.searchVacancyForCandidate(event);
        }
    }
}