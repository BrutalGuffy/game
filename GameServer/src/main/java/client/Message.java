package client;

import dto.MessageDataDto;

public class Message {
    private Topic topic;
    private MessageDataDto data;

    public Message(Topic topic_info, MessageDataDto data_info) {
        this.topic = topic_info;
        this.data = data_info;
    }

    public void set_topic(Topic topic_info) {
        topic = topic_info;
    }
    public void set_data(MessageDataDto data_info) {
        data = data_info;
    }

    public Topic getTopic() { return topic; }
    public MessageDataDto getData() { return data; }
}
