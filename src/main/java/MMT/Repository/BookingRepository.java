package MMT.Repository;

import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository {

    public int fetchBookingCountById(int hotelId);
}
