package jp.ne.yukke.wts.hello.batch.domain.entity;

import java.io.Serializable;

import lombok.Data;

/**
 * Sample Entity.
 * 
 * @author h.yu-suke
 */
// TODO ドメイン層に移動
@Data
public class Sample implements Serializable {

    private static final long serialVersionUID = 7133708553303041581L;

    private String id;

    private String name;
}
