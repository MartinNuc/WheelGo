package cz.nuc.wheelgo.server.api;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * Created with IntelliJ IDEA.
 * User: mist
 * Date: 10.10.13
 * Time: 3:52
 */
@ApplicationPath("/rest")
public class RestApp extends Application
{
    /*
    private Set<Object> singletons = new HashSet<Object>();
    private Set<Class<?>> empty = new HashSet<Class<?>>();
    public RestApp(){
        singletons.add(new RestService());
    }
    @Override
    public Set<Class<?>> getClasses() {
        return empty;
    }
    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }*/
}
