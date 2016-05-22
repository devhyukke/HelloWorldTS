package jp.ne.hyukke.wts.hello.core.domain.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

/**
 * Abstract class of repository.
 *
 * @author hyukke
 */
public abstract class AbstractRepository {

    @Autowired
    private ApplicationContext context;

    /**
     * This creates domain object by entity.
     *
     * @param entity Entity
     * @param clazz Domain Class
     * @return Domain object
     */
    protected <E, D extends AbstractDomain<E>> D createDomain(E entity, Class<D> clazz) {
        D domain = this.context.getBean(clazz);
        domain.setEntity(entity);
        return domain;
    }
}
