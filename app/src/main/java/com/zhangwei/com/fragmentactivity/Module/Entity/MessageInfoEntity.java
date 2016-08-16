package com.zhangwei.com.fragmentactivity.Module.Entity;

import com.zhangwei.framelibs.Global.AbstractClass.AbstractBaseEntity;

import java.util.List;

/**
 * Created by wade on 2016/1/19.
 */
public class MessageInfoEntity extends AbstractBaseEntity {
    /**
     * object_name : MessageInfo
     * uuid : 13L9H
     * message_id : 5
     * message_content : Eum quia illo repellendus inventore deserunt tempora. Vitae nisi fugiat dolore esse in saepe et.
     * message_createdAt : 2016-01-19 11:32:00
     * message_resource : [{"messageResource_id":"9","message_id":"5","url":"http://Kerluke.info/sequi-et-qui-cumque-eaque-odio-sapiente-ut-soluta.html","type":"","created_at":"2016-01-19 11:32:00","object_name":"message_resource"}]
     * messageTag_name : 通知
     * messageUuid_id : 5
     * messageUuid_status : unread
     * message_detail_url : http://uat.box.app.xuexuecan.com
     */

    private String uuid;
    private String message_id;
    private String message_content;
    private String message_createdAt;
    private String messageTag_name;
    private String messageUuid_id;
    private String messageUuid_status;
    private String message_detail_url;
    private int today_message_unread_count;
    /**
     * messageResource_id : 9
     * message_id : 5
     * url : http://Kerluke.info/sequi-et-qui-cumque-eaque-odio-sapiente-ut-soluta.html
     * type :
     * created_at : 2016-01-19 11:32:00
     * object_name : message_resource
     */

    private List<MessageResourceEntity> message_resource;

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void setMessage_id(String message_id) {
        this.message_id = message_id;
    }

    public void setMessage_content(String message_content) {
        this.message_content = message_content;
    }

    public void setMessage_createdAt(String message_createdAt) {
        this.message_createdAt = message_createdAt;
    }

    public void setMessageTag_name(String messageTag_name) {
        this.messageTag_name = messageTag_name;
    }

    public void setMessageUuid_id(String messageUuid_id) {
        this.messageUuid_id = messageUuid_id;
    }

    public void setMessageUuid_status(String messageUuid_status) {
        this.messageUuid_status = messageUuid_status;
    }

    public void setMessage_detail_url(String message_detail_url) {
        this.message_detail_url = message_detail_url;
    }

    public void setMessage_resource(List<MessageResourceEntity> message_resource) {
        this.message_resource = message_resource;
    }

    public String getUuid() {
        return uuid;
    }

    public String getMessage_id() {
        return message_id;
    }

    public String getMessage_content() {
        return message_content;
    }

    public String getMessage_createdAt() {
        return message_createdAt;
    }

    public String getMessageTag_name() {
        return messageTag_name;
    }

    public String getMessageUuid_id() {
        return messageUuid_id;
    }

    public String getMessageUuid_status() {
        return messageUuid_status;
    }

    public String getMessage_detail_url() {
        return message_detail_url;
    }

    public int getToday_message_unread_count() {
        return today_message_unread_count;
    }

    public void setToday_message_unread_count(int today_message_unread_count) {
        this.today_message_unread_count = today_message_unread_count;
    }

    public List<MessageResourceEntity> getMessage_resource() {
        return message_resource;
    }

    public static class MessageResourceEntity {
        private String messageResource_id;
        private String message_id;
        private String url;
        private String type;
        private String created_at;

        public void setMessageResource_id(String messageResource_id) {
            this.messageResource_id = messageResource_id;
        }

        public void setMessage_id(String message_id) {
            this.message_id = message_id;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public void setType(String type) {
            this.type = type;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getMessageResource_id() {
            return messageResource_id;
        }

        public String getMessage_id() {
            return message_id;
        }

        public String getUrl() {
            return url;
        }

        public String getType() {
            return type;
        }

        public String getCreated_at() {
            return created_at;
        }
    }
}
