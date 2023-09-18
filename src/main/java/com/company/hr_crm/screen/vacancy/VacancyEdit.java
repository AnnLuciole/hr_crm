package com.company.hr_crm.screen.vacancy;

import io.jmix.ui.screen.*;
import com.company.hr_crm.entity.Vacancy;

@UiController("hrcrm_Vacancy.edit")
@UiDescriptor("vacancy-edit.xml")
@EditedEntityContainer("vacancyDc")
public class VacancyEdit extends StandardEditor<Vacancy> {
}