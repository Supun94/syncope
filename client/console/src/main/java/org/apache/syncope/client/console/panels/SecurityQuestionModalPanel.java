/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.syncope.client.console.panels;

import org.apache.syncope.client.console.SyncopeConsoleSession;
import org.apache.syncope.client.console.commons.Constants;
import org.apache.syncope.client.console.pages.AbstractBasePage;
import org.apache.syncope.client.console.wicket.markup.html.bootstrap.dialog.BaseModal;
import org.apache.syncope.common.lib.to.SecurityQuestionTO;
import org.apache.syncope.common.rest.api.service.SecurityQuestionService;
import org.apache.wicket.PageReference;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.form.Form;

public class SecurityQuestionModalPanel extends AbstractModalPanel {

    private static final long serialVersionUID = 4024126489500665435L;

    private final boolean createFlag;

    public SecurityQuestionModalPanel(
            final BaseModal<SecurityQuestionTO> modal,
            final PageReference pageRef,
            final boolean createFlag) {
        super(modal, pageRef);
        this.createFlag = createFlag;
        add(new SecurityQuestionDetailsPanel("securityQuestionDetailsPanel", modal));
    }

    @Override
    public void onSubmit(final AjaxRequestTarget target, final Form<?> form) {
        try {
            final SecurityQuestionTO securityQuestionTO = SecurityQuestionTO.class.cast(form.getModelObject());

            if (createFlag) {
                SyncopeConsoleSession.get().getService(SecurityQuestionService.class).create(securityQuestionTO);
            } else {
                SyncopeConsoleSession.get().getService(SecurityQuestionService.class).update(securityQuestionTO);
            }

            if (pageRef.getPage() instanceof AbstractBasePage) {
                ((AbstractBasePage) pageRef.getPage()).setModalResult(true);
            }
            modal.close(target);
        } catch (Exception e) {
            LOG.error("While creating or updating SecutiryQuestionTO", e);
            error(getString(Constants.ERROR) + ": " + e.getMessage());
            modal.getFeedbackPanel().refresh(target);
        }
    }
}