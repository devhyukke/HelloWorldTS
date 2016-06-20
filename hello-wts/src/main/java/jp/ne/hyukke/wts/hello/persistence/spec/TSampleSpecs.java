package jp.ne.hyukke.wts.hello.persistence.spec;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import jp.ne.hyukke.wts.hello.domain.vo.SampleConditionVo;
import jp.ne.hyukke.wts.hello.persistence.entity.TSample;

/**
 * {@link TSample}の仕様を表すクラス.
 *
 * @author hyukke
 */
public class TSampleSpecs {

    /**
     * 指定された条件に合致する仕様を返却する.
     *
     * @param condition 条件
     * @return 仕様
     */
    public static Specification<TSample> byCondition(SampleConditionVo condition) {

        return (entity, query, cb) -> {

            List<Predicate> pre = new ArrayList<>();

            Optional.ofNullable(condition.getId())
                    .ifPresent(id -> pre.add(cb.equal(entity.get("id"), id)));
            Optional.ofNullable(condition.getName())
                    .filter(StringUtils::hasText)
                    .ifPresent(name -> pre.add(cb.like(entity.get("name"), "%".concat(name).concat("%"))));
            Optional.ofNullable(condition.getType())
                    .ifPresent(type -> pre.add(cb.equal(entity.get("type"), type)));

            return cb.and(pre.toArray(new Predicate[pre.size()]));
        };
    }
}
