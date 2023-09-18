package com.company.hr_crm.screen.vacancy;

import io.jmix.ui.screen.*;
import com.company.hr_crm.entity.Vacancy;

@UiController("hrcrm_Vacancy.browse")
@UiDescriptor("vacancy-browse.xml")
@LookupComponent("vacanciesTable")
public class VacancyBrowse extends StandardLookup<Vacancy> {
}