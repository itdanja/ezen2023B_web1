package example.day03.webMvc;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter@ToString
public class TodoDto {
    private int id;
    private String content;
    private String deadline;
    private boolean state;
 }
