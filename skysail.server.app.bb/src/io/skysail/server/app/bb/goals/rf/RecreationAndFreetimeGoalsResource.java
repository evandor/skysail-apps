package io.skysail.server.app.bb.goals.rf;

import java.util.List;

import io.skysail.api.links.Link;
import io.skysail.server.app.bb.AreaGoalsResource;
import io.skysail.server.app.bb.areas.Area;
import io.skysail.server.utils.LinkUtils;

public class RecreationAndFreetimeGoalsResource extends AreaGoalsResource {
    
    public RecreationAndFreetimeGoalsResource() {
        super(RecreationAndFreetimeGoalResource.class, Area.RECREATION_AND_FREETIME);
    }
    
    @Override
    public List<Link> getLinks() {
        List<Link> result = super.getLinks(app.getMainLinks());
        result.add(LinkUtils.fromResource(app, PostRecreationAndFreetimeGoalsResource.class));
        return result;
    }
    

}
