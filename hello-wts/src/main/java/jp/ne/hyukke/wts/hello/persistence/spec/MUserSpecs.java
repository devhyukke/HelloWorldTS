package jp.ne.hyukke.wts.hello.persistence.spec;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import jp.ne.hyukke.wts.hello.domain.vo.UserConditionVo;
import jp.ne.hyukke.wts.hello.persistence.entity.MUser;

/**
 * {@link MUser}の仕様を表すクラス.
 *
 * @author hyukke
 */
public class MUserSpecs {

    /**
     * 指定された条件に合致する仕様を返却する.
     *
     * @param condition 条件
     * @return 仕様
     */
    public static Specification<MUser> byCondition(UserConditionVo condition) {

        return (entity, query, cb) -> {

            List<Predicate> pre = new ArrayList<>();

            Optional.ofNullable(condition.getId())
                    .ifPresent(id -> pre.add(cb.equal(entity.get("id"), id)));
            Optional.ofNullable(condition.getUsername())
                    .filter(StringUtils::hasText)
                    .ifPresent(name -> pre.add(cb.like(entity.get("username"), "%".concat(name).concat("%"))));
            Optional.ofNullable(condition.getDisplayName())
                    .filter(StringUtils::hasText)
                    .ifPresent(name -> pre.add(cb.like(entity.get("displayName"), "%".concat(name).concat("%"))));
            // TODO 結合した検索条件を追加
//            Optional.ofNullable(condition.getType())
//                    .filter(StringUtils::hasText)
//                    .ifPresent(type -> pre.add(cb.equal(entity.get("type"), type)));

            return cb.and(pre.toArray(new Predicate[pre.size()]));
        };
    }
}
