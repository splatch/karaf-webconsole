package org.apache.karaf.webconsole.core;

import static org.easymock.EasyMock.anyObject;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.karaf.webconsole.core.brand.DefaultBrandProvider;
import org.apache.karaf.webconsole.core.internal.WebConsoleApplication;
import org.apache.karaf.webconsole.core.security.KarafJaasWebSession;
import org.apache.wicket.Page;
import org.apache.wicket.authentication.AuthenticatedWebSession;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.protocol.http.WebApplication;
import org.junit.Before;

public class WebConsoleTest {

    /**
     * Application instance.
     */
    protected WebApplication application;

    /**
     * Test injector.
     */
    protected TestInjector injector;

    public WebConsoleTest() {
        super();
    }

    @Before
    public void setUp() {
        application = new WebConsoleApplication() {
            protected java.lang.Class<? extends AuthenticatedWebSession> getWebSessionClass() {
                return WebConsoleTest.this.getWebSessionClass();
            }
        };

        // default configurations values.
        Map<String, Object> values = new HashMap<String, Object>();
        values.put("brandProvider", new DefaultBrandProvider());

        injector = new TestInjector(values);
        application.addComponentInstantiationListener(injector);
    }

    protected Class<? extends AuthenticatedWebSession> getWebSessionClass() {
        return KarafJaasWebSession.class;
    }

    // utility methods for easy mock

    /**
     * Easy mock argument matcher.
     */
    protected final static String anyString() {
        return anyObject();
    }

    /**
     * Empty list stub.
     */
    protected final static List<Link<Page>> emptyLinkList() {
        return Collections.<Link<Page>>emptyList();
    }
}