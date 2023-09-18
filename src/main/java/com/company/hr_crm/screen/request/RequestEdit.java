package com.company.hr_crm.screen.request;

import io.jmix.ui.screen.*;
import com.company.hr_crm.entity.Request;

@UiController("hrcrm_Request.edit")
@UiDescriptor("request-edit.xml")
@EditedEntityContainer("requestDc")
public class RequestEdit extends StandardEditor<Request> {
}