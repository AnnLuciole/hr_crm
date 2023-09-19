package com.company.hr_crm.screen.vacancy;

import com.company.hr_crm.entity.Request;
import io.jmix.core.DataManager;
import io.jmix.ui.screen.*;
import com.company.hr_crm.entity.Vacancy;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("hrcrm_Vacancy.browse")
@UiDescriptor("vacancy-browse.xml")
@LookupComponent("vacanciesTable")
public class VacancyBrowse extends StandardLookup<Vacancy> {
}