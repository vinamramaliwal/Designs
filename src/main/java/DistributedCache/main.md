Requirements

Functional
•	Store key-value pairs in memory
•	Fast access (low latency)
•	Support for expiration (TTL)
•	Support for cache invalidation
•	Distributed across multiple nodes

Non-functional
•	High availability
•	High throughput
•	Horizontal scalability
•	Consistency guarantees (eventual or strong)
•	Fault tolerance (data replication)

APIs

Core APIs for cache interaction:
GET /cache/{key}

Description: Retrieve the cached value for a given key.


Response: 200 OK with value, 404 Not Found if key doesn't exist.


POST /cache

Description: Add or update a key-value pair.


Body:
json
CopyEdit
{
"key": "user:123",
"value": "data",
"ttl": 3600
}


DELETE /cache/{key}

Description: Remove a cached entry by key.


GET /cache/stats

Description: Return stats like hit/miss ratio, eviction count, memory usage.


Optional:

GET /cache/bulk?keys=key1,key2,...
POST /cache/bulk
DELETE /cache/prefix/{prefix}

 Core Components

1. Clients
   •	Connect via SDK/HTTP/gRPC.
   •	Can use smart sharding logic.

2. Load Balancer / Proxy
   •	For routing requests to nodes.
   •	Can use consistent hashing or rendezvous hashing.

3. Cache Nodes
   •	Each stores a subset of data in memory.
   •	Handles TTL, LRU/LFU eviction locally.

4. Metadata Service / Coordinator
   •	Maintains list of nodes and hash ring.
   •	Handles node join/leave, replication, and rebalancing.

5. Replication Mechanism
   •	Each key is replicated to N nodes (e.g. master + 1 replica).
   •	Follower syncs from master periodically or in real-time.

⸻

🔄 5. Data Flow

SET(key, value):
1.	Client sends SET to load balancer.
2.	Balancer hashes key → determines node.
3.	Node stores value in memory + sets TTL if provided.
4.	If replication is enabled, forwards data to replica(s).

GET(key):
1.	Client sends GET.
2.	Balancer directs to primary node.
3.	Returns value if found, else miss.

DELETE(key):
1.	Deletes key from primary and replicas.

⸻

🧠 6. Consistent Hashing

Why?
•	Avoid rehashing all keys when adding/removing nodes.

How?
•	Nodes placed on a virtual ring.
•	Keys map to nearest node clockwise.

How to Implement TTL-Based Invalidation?
There are two main strategies:

🅰️ Lazy Expiration (on access)
Check expiration only when the key is accessed (get/put).

✅ Pros:
Simple
No extra threads/timers
Memory-efficient (you don't check unused keys)

❌ Cons:
Expired keys may occupy memory until accessed


🅱️ Active Expiration (background cleanup)
Run a background thread to periodically remove expired keys.

✅ Pros:
Frees memory proactively
Keeps cache "clean"

❌ Cons:
Extra thread overhead
May miss tight timing or overkill for large caches


🔁 Hybrid Approach (Recommended)
Use lazy expiration for correctness + active cleanup for memory control:

Only remove on get() to avoid serving stale data

Background thread removes expired keys to save memory

🧠 How TTL works in Distributed Setting
In a multi-node cache:

TTL is set locally in each node (based on wall-clock time)

Each node independently expires keys

Optional:
If replication exists → replicate TTL too

If strong consistency is needed → use a central TTL controller (rare; expensive)

🚀 Real-World Systems
System	TTL Support	Invalidation Type
Redis	Yes	Lazy + Active
Memcached	Yes	Expiry checked on access only
Hazelcast	Yes	Configurable TTL & eviction

✅ Summary
Method	Best For
Lazy expiration	Simplicity, correctness
Active cleanup	Freeing up memory proactively
Hybrid	Practical production systems

Use Case	Pick This
Unordered fast lookup table	        HashMap
Sorted access or range queries  	TreeMap
Legacy thread-safe map (avoid)  	Hashtable
Thread-safe high-perf map	    ConcurrentHashMap



