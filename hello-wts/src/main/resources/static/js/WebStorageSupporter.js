/**
 * HTML5のWeb Storage機能をサポートする処理を定義する。
 */
WebStorageSupporter = new WebStorageSupporter();

/**
 * Web Storage機能に関する処理を提供するユーティリティクラス。
 *
 * @author hyukke
 */
function WebStorageSupporter() {

    this.LocalStorage = new LocalStorage();

    /**
     * LocalStorageに関する処理を提供するユーティリティクラス。
     */
    function LocalStorage() {

        /**
         * 有効であるかどうかを返却する。
         *
         * @returns true/false
         */
        this.isEnabled = function() {
            return ('localStorage' in window) && window['localStorage'] !== null;
        };

        /**
         * 無効であるかどうかを返却する。
         *
         * @return true/false
         */
        this.isDisabled = function() {
            return !this.isEnabled();
        };

        /**
         * 指定されたキーに対する値を取得する。
         *
         * @param key キー
         * @returns 値
         */
        this.getItem = function(key) {
            if (this.isDisabled()) {
                return null;
            }
            return localStorage.getItem(key);
        };

        /**
         * 指定されたキーで値を設定する。
         *
         * @param key キー
         * @param value 値
         */
        this.setItem = function(key, value) {
            if (this.isDisabled()) {
                return;
            }
            localStorage.setItem(key, value);
        };

        /**
         * 指定されたキーの値を削除する。
         *
         * @param key キー
         */
        this.removeItem = function(key) {
            if (this.isDisabled()) {
                return;
            }
            localStorage.removeItem(key);
        };

        /**
         * 指定された位置のキーを返却する。
         *
         * @param n 位置
         * @return キー
         */
        this.key = function(n) {
            if (this.isDisabled()) {
                return null;
            }
            return localStorage.key(n);
        };

        /**
         * ローカルストレージを全て消去する。
         */
        this.clear = function() {
            if (this.isDisabled()) {
                return null;
            }
            localStorage.clear();
        };

        /**
         * 指定された接頭辞に合致するキーの値を削除する。
         *
         * @param prefix 接頭辞
         */
        this.removeItems = function(prefix) {
            if (this.isDisabled()) {
                return;
            }
            if (localStorage.length === 0) {
                return;
            }
            for (var key in localStorage) {
                if (key.lastIndexOf(prefix, 0) === 0) {
                    this.removeItem(key);
                }
            }
        };
    }
}
