/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.karaf.webconsole.core.internal;

import org.apache.karaf.webconsole.core.dashboard.DashboardPage;
import org.apache.karaf.webconsole.core.page.LoginPage;
import org.apache.karaf.webconsole.core.security.HierarchicalRoleCheckingStrategy;
import org.apache.karaf.webconsole.core.security.KarafJaasWebSession;
import org.apache.wicket.authentication.AuthenticatedWebApplication;
import org.apache.wicket.authentication.AuthenticatedWebSession;
import org.apache.wicket.authorization.strategies.role.RoleAuthorizationStrategy;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.pages.AccessDeniedPage;
import org.apache.wicket.markup.html.pages.PageExpiredErrorPage;

/**
 * Root class for wicket.
 */
public class WebConsoleApplication extends AuthenticatedWebApplication {

    public WebConsoleApplication() {
        super();
    }

    @Override
    protected void init() {
        super.init();

        mountBookmarkablePage("/login", LoginPage.class);
        mountBookmarkablePage("/error/401", AccessDeniedPage.class);
        mountBookmarkablePage("/error/404", PageExpiredErrorPage.class);

        getApplicationSettings().setAccessDeniedPage(AccessDeniedPage.class);
        getApplicationSettings().setPageExpiredErrorPage(PageExpiredErrorPage.class);

        getSecuritySettings().setAuthorizationStrategy(new RoleAuthorizationStrategy(new HierarchicalRoleCheckingStrategy()));
    }

    /**
     * @see org.apache.wicket.Application#getHomePage()
     */
    @Override
    public Class<DashboardPage> getHomePage() {
        return DashboardPage.class;
    }

    @Override
    protected Class<? extends WebPage> getSignInPageClass() {
        return LoginPage.class;
    }

    @Override
    protected Class<? extends AuthenticatedWebSession> getWebSessionClass() {
        return KarafJaasWebSession.class;
    }

}
