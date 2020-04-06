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
package org.apache.karaf.webconsole.core.dashboard;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.karaf.webconsole.core.behavior.CssBehavior;
import org.apache.karaf.webconsole.core.page.SinglePage;
import org.apache.karaf.webconsole.core.widget.WidgetProvider;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.util.ListModel;
import org.ops4j.pax.wicket.api.PaxWicketMountPoint;

/**
 * Default page shown to user after successful login or after click in logo.
 */
@PaxWicketMountPoint(mountPoint = "/dashboard")
public class DashboardPage extends SinglePage {

    private static final long serialVersionUID = 1L;

    @Inject @Named("widgets")
    private List<WidgetProvider> widgets;

    @SuppressWarnings("serial")
    public DashboardPage() {
        add(new CssBehavior(DashboardPage.class, "dashboard.css"));

        add(new Label("noWidgets", getString("widgets.empty")) {
            @Override
            public boolean isVisible() {
                return widgets.size() == 0;
            }
        });

        add(new ListView<WidgetProvider>("widgets", new ListModel<WidgetProvider>(widgets)) {
            @Override
            protected void populateItem(ListItem<WidgetProvider> item) {
                item.add(item.getModelObject().createPanel("widget"));
            }
        });
    }

}
