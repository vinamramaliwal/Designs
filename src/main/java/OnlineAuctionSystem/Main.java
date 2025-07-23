package OnlineAuctionSystem;

public class Main {

    /*
    Online Auction System - Requirements
1.User Accounts
    The system should allow users to register and log in to their accounts securely.

2.Auction Listings
    Users should be able to create new auction listings with the following details:
    Item name
    Description
    Starting price
    Auction duration

3.Search & Browse
    Users should be able to search and browse auction listings based on criteria such as:
    1.Item name
    2.Category
    3.Price range
    4.Bidding

4. Users should be able to place bids on active auction listings.

5.Highest Bid Management
    The system should automatically update and maintain the current highest bid.
    Bidders should be notified when their bid is no longer the highest.

6.Auction Conclusion
    The auction should automatically end when the specified duration is reached.
    The highest bidder at the end should be declared the winner.

7.Concurrency & Data Consistency
    The system should handle concurrent access to auction listings.
    It must ensure consistency of bidding data and auction states.

8.Extensibility
    The system should be designed to accommodate future features and enhancements easily.
     */
}

/* Entities-
1.User
    String UserId
    String userName;
    String phoneNumber
    String email;

2. AuctionListing
    String listingId
    String ItemName
    String Description
    String category;
    Double price
    Date startDate
    Date EndDate
    Bid highestBid;
    AuctionStatus status; // ACTIVE, ENDED, etc.

3. Bid
    String bidId
    String userId
    String listingId
    Double price
    Long timestamp;

UserManagement
    List<User> listUsers;

    addUser();
    removeUser();
    List<User> fetchUsers(){}

BidManagement
    NotificationService notificationService
    List<AuctionListing> auctionListing;
    Map<String, List<Bid>> listingBidsMap = new HashMap<>();
    Map<String, ReentrantLock> listingLock = new ConcurrentHashMap<>();

    List<AuctionListing> search(string ItemName, String category, Int startingPrice, int endingPrice, int currentBidLessThan){

    //I can iterate on this auctionListing Map and filter search.

    }


     void createBid(String userId, String listingId, double price) {
        ReentrantLock lock = listingLocks.computeIfAbsent(listingId, id -> new ReentrantLock());
        lock.lock();
        try {
            AuctionListing listing = fetchExistingListing(listingId);
            if (listing == null || listing.getStatus() != AuctionStatus.ACTIVE) return;

            Bid currentHighest = listing.getHighestBid();
            if (currentHighest == null || price > currentHighest.getPrice()) {
                if (currentHighest != null) {
                    notificationService.notifyUser(currentHighest.getUserId(), "You have been outbid.");
                }

                Bid newBid = new Bid(UUID.randomUUID().toString(), userId, listingId, price, new Date());
                listing.setHighestBid(newBid);
                listingBidsMap.computeIfAbsent(listingId, k -> new ArrayList<>()).add(newBid);
            }
        } finally {
            lock.unlock();
        }
        }

    AuctionListing fetchExistingListing(String listingId){

    }


    @Scheduled()
    void concludeBidding(){
    //schduler which will check if any listing has ended and conclude the bidding process.

    }


NotificationService
    notifyUser()




 */
