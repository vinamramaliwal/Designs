# Functional Requirements
1. Define failure threshold
2. Three States - OPEN, HALF OPEN, CLOSE
3. Success threshold.
4. Timeout period


| State         | Description                                                                                                        |
| ------------- | ------------------------------------------------------------------------------------------------------------------ |
| **Closed**    | All requests are allowed. On failures, it tracks error count. If threshold breached → Open.                        |
| **Open**      | Requests are immediately failed/skipped. After a timeout, it transitions to Half-Open.                             |
| **Half-Open** | A limited number of requests are allowed to test if downstream is healthy. On success → Closed, on failure → Open. |
