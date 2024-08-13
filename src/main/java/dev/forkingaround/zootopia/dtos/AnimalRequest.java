package dev.forkingaround.zootopia.dtos;

import java.util.Date;
import lombok.Data;

@Data
public class AnimalRequest {
    private String name;
    private String typeName;
    private String gender;
    private Date dateOfEntry;
}

