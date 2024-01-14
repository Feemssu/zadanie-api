package pl.programujodpodstaw.webjpa.post;

public class PostRequestDto {
    private String bodyPost;
    private Integer userId;

    public PostRequestDto() {
    }

    public String getBodyPost() {
        return bodyPost;
    }

    public void setBodyPost(String bodyPost) {
        this.bodyPost = bodyPost;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
