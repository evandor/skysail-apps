package io.skysail.server.app.bb;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.restlet.resource.ResourceException;

import io.skysail.server.queryfilter.Filter;
import io.skysail.server.queryfilter.pagination.Pagination;
import io.skysail.server.restlet.resources.ListServerResource;

public class AreasResource extends ListServerResource<Area> {

    private BodyboosterApplication app;

    @Override
    protected void doInit() throws ResourceException {
        super.doInit();
        app = (BodyboosterApplication)getApplication();
    }

    @Override
    public List<Area> getEntity() {
        Filter filter = new Filter(getRequest());
        filter.add("owner", SecurityUtils.getSubject().getPrincipal().toString());
        Pagination pagination = new Pagination(getRequest(), getResponse(), 10);
        return app.getRepository().findAll(Area.class, filter, pagination);
    }

}