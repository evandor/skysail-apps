package io.skysail.server.app.bb.goals.wc;

import java.util.List;

import io.skysail.api.links.Link;
import io.skysail.server.app.bb.AreaGoalsResource;
import io.skysail.server.app.bb.areas.Area;

public class WorkAndCareerGoalsResource extends AreaGoalsResource {
    
    public WorkAndCareerGoalsResource() {
        super(WorkAndCareerGoalResource.class, Area.WORK_AND_CAREERS);
    }
    
    @Override
    public List<Link> getLinks() {
        List<Link> result = super.getLinks(PostWorkAndCareerGoalsResource.class);
        //result.add(LinkUtils.fromResource(app, PostWorkAndCareerGoalsResource.class));
        return result;
    }


}
