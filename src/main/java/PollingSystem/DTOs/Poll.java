package PollingSystem.DTOs;

import com.sun.xml.txw2.annotation.XmlCDATA;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Poll {
    String pollId;
    String question;
    Map<Integer,String> answerOptions;
    Date createdAt;

}
