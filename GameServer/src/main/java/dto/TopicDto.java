package dto;

import geometry.Point;

public class TopicDto {
    private String topic;
    private DataDto data;

    public void set_topic(String topic_info) {
        topic = topic_info;
    }
    public void set_data(DataDto data_info) {
        data = data_info;
    }

    public String getTopic() { return topic; }
    public DataDto getData() { return data; }

}
