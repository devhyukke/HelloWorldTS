package jp.ne.hyukke.wts.hello.core.domain.model;

import lombok.Getter;
import lombok.Setter;

/**
 * ドメインの抽象クラス.
 *
 * @param <E> エンティティクラス
 * @author hyukke
 */
public abstract class AbstractDomain<E> {

    @Getter
    @Setter(lombok.AccessLevel.PACKAGE)
    private E entity;
}
