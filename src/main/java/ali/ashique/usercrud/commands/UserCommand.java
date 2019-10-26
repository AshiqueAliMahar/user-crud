package ali.ashique.usercrud.commands;

import lombok.Data;

@Data
public class UserCommand {
    private long id;
    private String name;
    private String caste;
    private String fatherName;
}
