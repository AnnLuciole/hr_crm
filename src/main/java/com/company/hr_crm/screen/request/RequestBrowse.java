package com.company.hr_crm.screen.request;

import io.jmix.ui.screen.*;
import com.company.hr_crm.entity.Request;

@UiController("hrcrm_Request.browse")
@UiDescriptor("request-browse.xml")
@LookupComponent("requestsTable")
public class RequestBrowse extends StandardLookup<Request> {
}