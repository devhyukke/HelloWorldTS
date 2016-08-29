/**
 * HTML5のWeb Storage機能をサポートする処理を定義する.
 */
WebStorageSupporter = new WebStorageSupporter();

/**
 * Web Storage機能に関する処理を提供するユーティリティクラス.
 *
 * @author hyukke
 */
function WebStorageSupporter() {

    this.storage = localStorage;

    /**
     * @returns 有効である場合は true
     */
    this.isEnabled = function() {
        return ('localStorage' in window) && window['localStorage'] !== null;
    };

    /**
     * @return 無効である場合は true
     */
    this.isDisabled = function() {
        return !this.isEnabled();
    };

    /**
     * @param key キー
     * @returns 値
     */
    this.getItem = function(key) {
        if (this.isDisabled()) {
            return null;
        }
        return this.storage.getItem(key);
    };

    /**
     * @param key キー
     * @returns 値
     */
    this.getObject = function(key) {
    	return JSON.parse(this.getItem(key));
    }

    /**
     * @param key キー
     * @param value 値
     */
    this.setItem = function(key, value) {
        if (this.isDisabled()) {
            return;
        }
        this.storage.setItem(key, value);
    };

    /**
     * @param key キー
     * @param value 値
     */
    this.setObject = function(key, value) {
        this.setItem(key, JSON.stringify(value));
    };

    /**
     * @param key キー
     */
    this.removeItem = function(key) {
        if (this.isDisabled()) {
            return;
        }
        this.storage.removeItem(key);
    };

    /**
     * 指定された位置のキーを返却する.
     *
     * @param n 位置
     * @return キー
     */
    this.key = function(n) {
        if (this.isDisabled()) {
            return null;
        }
        return this.storage.key(n);
    };

    /**
     * ローカルストレージを全て消去する.
     */
    this.clear = function() {
        if (this.isDisabled()) {
            return null;
        }
        this.storage.clear();
    };

    /**
     * 指定された接頭辞に合致するキーの値を削除する.
     *
     * @param prefix 接頭辞
     */
    this.removeItems = function(prefix) {
        if (this.isDisabled()) {
            return;
        }
        if (this.storage.length === 0) {
            return;
        }
        for (var key in this.storage) {
            if (key.lastIndexOf(prefix, 0) === 0) {
                this.removeItem(key);
            }
        }
    };

    return this;
}

WebStorageSupporter.prototype.setPersistent = function(persistent) {
	this.persistent = persistent;
}
