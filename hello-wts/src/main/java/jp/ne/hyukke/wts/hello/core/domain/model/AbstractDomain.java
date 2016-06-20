package jp.ne.hyukke.wts.hello.core.domain.model;

/**
 * ドメインの抽象クラス.
 *
 * @param <E> エンティティクラス
 * @author hyukke
 */
public abstract class AbstractDomain<E> {

    private E entity;

    /**
     * @return エンティティ
     */
    public E getEntity() {
        return this.entity;
    }

    /**
     * @param entity エンティティ
     */
    void setEntity(E entity) {
        this.entity = entity;
    }
}
