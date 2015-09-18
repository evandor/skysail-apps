package io.skysail.server.app.bb;

import java.util.List;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;

import io.skysail.server.db.DbRepository;
import io.skysail.server.db.DbService;
import io.skysail.server.queryfilter.Filter;
import io.skysail.server.queryfilter.pagination.Pagination;
import lombok.Setter;

@Component(immediate = true, property = "name=BodyboosterRepository")
public class Repository implements DbRepository {

    @Reference(cardinality = ReferenceCardinality.OPTIONAL)
    @Setter // for tests
    private DbService dbService;
    
    @Activate
    public void activate() { // NO_UCD
        dbService.createWithSuperClass("V", Area.class.getSimpleName());
        dbService.register(Area.class);
//        dbService.createUniqueIndex(TodoList.class, "name", "owner");
    }
    
    public List<Area> findAll(Class<?> cls, Filter filter, Pagination pagination) {
        String sql = "SELECT from " + cls.getSimpleName() + " WHERE "+filter.getPreparedStatement();//+" ORDER BY name ";
//                + limitClause(pagination);
        return dbService.findObjects(sql, filter.getParams());
    }

    public void persist(Area entity) {
        return ;//dbService.persist(entity, edges);
    }
    
    

}