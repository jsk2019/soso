/**
 * <pre>
 *     author : 3D2Y.江圣坤
 *     e-mail : 530578697@qq.com
 *     date   : 2020/7//11
 *     description   : 用于用户操作的接口
 *     version: 2.0
 * </pre>
 */
package com.whu.soso.model;

public class ReturnMessage {
    private Integer content;

    public ReturnMessage(Integer content) {
        this.content = content;
    }

    public Integer getContent() {
        return content;
    }

    public void setContent(Integer content) {
        this.content = content;
    }
}
