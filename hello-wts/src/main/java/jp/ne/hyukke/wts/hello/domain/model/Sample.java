package jp.ne.hyukke.wts.hello.domain.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import jp.ne.hyukke.wts.hello.domain.constants.SampleOption;
import jp.ne.hyukke.wts.hello.domain.constants.SampleType;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

/**
 * サンプルのドメインモデルクラス.
 *
 * @author hyukke
 */
@Data
@EqualsAndHashCode(of = "id")
@RequiredArgsConstructor(staticName = "valueOf")
public class Sample implements Serializable {

    private static final long serialVersionUID = 7133708553303041581L;

    /** {@code ID} */
    private final Integer id;
    /** 名称 */
    @NotEmpty
    private String name;
    /** 種別 */
    @NotNull
    private SampleType type;
    /** メールアドレス */
    private String email;
    /** パスワード */
    private String password;
    /** チェック */
    @NotNull
    private boolean checked;
    /** オプション */
    @NotNull
    private SampleOption option;
    /** 備考 */
    private String remark;
    /** 最終更新日 */
    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private LocalDateTime lastUpdatedDate;
    /** バージョン */
    private int version;

    public Sample() {
        this.id = Integer.valueOf(0);
    }
}
