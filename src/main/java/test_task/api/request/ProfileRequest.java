package test_task.api.request;

import lombok.Data;

@Data
public class ProfileRequest {
    private String name;
    private String email;
    private int age;
}
