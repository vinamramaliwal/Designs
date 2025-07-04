package Meesho.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlockedEntry {

    String productId;
    String orderId;
    int blockedCount;
    LocalDateTime blockedDateTime;

}
