package jp.ne.hyukke.wts.hello.domain.repository;

import jp.ne.hyukke.wts.hello.core.domain.annotation.Domain;
import jp.ne.hyukke.wts.hello.core.domain.model.AbstractDomain;
import jp.ne.hyukke.wts.hello.domain.entity.User;

/**
 * ユーザーを扱うドメインクラス.
 *
 * @author hyukke
 */
@Domain
public class UserDomain extends AbstractDomain<User> {

}
