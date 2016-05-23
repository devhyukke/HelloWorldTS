package jp.ne.hyukke.wts.hello.core.domain.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

/**
 * リポジトリの抽象クラス.
 *
 * @author hyukke
 */
public abstract class AbstractRepository {

    @Autowired
    private ApplicationContext context;

    /**
     * 指定されたエンティティで、ドメインを生成する.
     *
     * @param entity エンティティ
     * @param clazz ドメインクラス
     * @return ドメイン
     */
    protected <E, D extends AbstractDomain<E>> D createDomain(E entity, Class<D> clazz) {
        D domain = this.context.getBean(clazz);
        domain.setEntity(entity);
        return domain;
    }
}
