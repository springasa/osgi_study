package freeingo.helloword;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.http.HttpContext;
import org.osgi.service.http.HttpService;

public class HelloWorldActivator implements BundleActivator {
    private HttpService service;

    @Override
    public void start(BundleContext context) throws Exception {
        System.out.println("Hello World Bundle started!");
        ServiceReference serviceRef = context.getServiceReference(HttpService.class.getName());
        service = (HttpService) context.getService(serviceRef);

        HttpContext httpContext = service.createDefaultHttpContext();
        service.registerResources("/osgi", "/web", httpContext);
        service.registerServlet("/osgi/print", new PrintNameServlet(context), null, httpContext);
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        System.out.println("Hello World Bundle stop!");
        service.unregister("/osgi/print");
        service.unregister("/osgi");
    }
}