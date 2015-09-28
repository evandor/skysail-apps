package io.skysail.server.app.bb.goals.wc;

import io.skysail.server.app.bb.Goal;
import io.skysail.server.app.bb.areas.Area;
import io.skysail.server.app.bb.goals.PostAreaGoalsResource;

public class PostWorkAndCareerGoalsResource extends PostAreaGoalsResource {

    @Override
    public Goal createEntityTemplate() {
        return new Goal("", Area.WORK_AND_CAREERS);
    }

}
