package jp.ne.hyukke.wts.hello.domain.repository;

import jp.ne.hyukke.wts.hello.core.annotation.Domain;
import jp.ne.hyukke.wts.hello.domain.entity.Sample;

import lombok.Getter;
import lombok.Setter;

/**
 * Domain of Sample.
 *
 * @author hyukke
 */
@Domain
public class SampleDomain {

    @Getter
    @Setter(lombok.AccessLevel.PACKAGE)
    private Sample entity;
}
