package Client;

import dto.TopicDto;

public class Action {
    private TopicDto topicDto;
    private int id;

    public void setTopicDto (TopicDto action) { topicDto = action; }
    public void setId (int player_id) {id = player_id; }

    public TopicDto getTopicDto() { return topicDto; }
    public int getId() { return id; }
}
