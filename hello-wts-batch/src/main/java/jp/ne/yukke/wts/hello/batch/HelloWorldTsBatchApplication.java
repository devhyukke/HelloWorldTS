package jp.ne.yukke.wts.hello.batch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * バッチアプリケーションを起動するクラス.
 * 
 * @author h.yu-suke
 */
@SpringBootApplication
public class HelloWorldTsBatchApplication {

    private static final int ERROR_CODE = 9;

    /**
     * バッチアプリケーションを起動する.
     * 
     * @param args 引数
     */
    public static void main(String[] args) {

        SpringApplication application = new SpringApplication(HelloWorldTsBatchApplication.class);
        // 内臓する Web サーバの起動をオフ
        application.setWebEnvironment(false);
        try {
            // アプリケーション（Jar）が終了せず起動したままの状態にならないように EXIT で終了
            int exitCode = SpringApplication.exit(application.run(args));
            // Shell などから起動される場合に終了コードを指定して終了
            System.exit(exitCode);
        } catch (Exception e) {
            // アプリケーション起動時にエラーの場合は、明示的にエラーコードを指定して終了
            System.exit(ERROR_CODE);
        }
	}
}
