package ali.ashique.usercrud.dtos;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Data
//@JsonIgnoreProperties(value = {"url","fatherName"})
@JsonFilter("userFilter")
public class UserDTO {
    @Size(min = 2)
    private String name;
    @NotEmpty
    private String caste;
    private String fatherName;
    //@JsonIgnore
    private String url;
}
