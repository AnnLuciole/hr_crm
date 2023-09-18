package com.company.hr_crm.listener;

import com.company.hr_crm.entity.Candidate;
import com.company.hr_crm.entity.Request;
import com.company.hr_crm.entity.Vacancy;
import com.company.hr_crm.service.DataSourceSearchService;
import io.jmix.core.DataManager;
import io.jmix.core.FetchPlan;
import io.jmix.core.FetchPlans;
import io.jmix.core.event.EntityChangedEvent;
import io.jmix.core.event.EntityLoadingEvent;
import io.jmix.core.event.EntitySavingEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.List;
import java.util.stream.Collectors;

@Component("hrcrm_VacancyEventListener")
public class VacancyEventListener {

    private final DataSourceSearchService dataSourceSearchService;

    @Autowired
    public VacancyEventListener(DataSourceSearchService dataSourceSearchService) {
        this.dataSourceSearchService = dataSourceSearchService;
    }

    @TransactionalEventListener
    public void onVacancyChangedAfterCommit(EntityChangedEvent<Vacancy> event) {
        if (event.getType().equals(EntityChangedEvent.Type.CREATED)) {
            dataSourceSearchService.searchAndAddAllCandidates(event);
        }
    }
}