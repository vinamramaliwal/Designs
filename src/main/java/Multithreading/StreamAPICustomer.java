package Multithreading;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StreamAPICustomer {
    public String name;
    public String email;
    public List<StreamAPIAddress> allAddress;
}
