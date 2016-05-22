package jp.ne.hyukke.wts.hello.core.domain.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Abstract class of domain.
 *
 * @param <E> Entity class
 * @author hyukke
 */
public abstract class AbstractDomain<E> {

    @Getter
    @Setter(lombok.AccessLevel.PACKAGE)
    private E entity;
}
