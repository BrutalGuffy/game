package dto;

import client.Topic;

public class Message {
    private Topic topic;
    private DataDto data;

    public void set_topic(Topic topic_info) {
        topic = topic_info;
    }
    public void set_data(DataDto data_info) {
        data = data_info;
    }

    public Topic getTopic() { return topic; }
    public DataDto getData() { return data; }
}
