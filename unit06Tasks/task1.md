|   | Ordering | Random Access | Key-Value Pairs | Allows Duplicates | Allows Null Values | Thread Safe | Blocking Operations |
|---|---|---|---|---|---|---|---|
| ArrayList | Yes | Yes | No | Yes | Yes | No | No |
| LinkedList | Yes | Yes | No | Yes | Yes | No | No |
| Vector | Yes | Yes | No | Yes | Yes | Yes | Yes |
| HashSet | No | No | No | No | Yes | No | No |
| LinkedHasSet  | Yes | No | No | No | Yes | No | No |
| TreeSet | Yes, if consider comparator ordering | No | No | No | Comparator defined | No | No |
| PriorityQueue | Yes, if consider comparator ordering | No | No | Yes | No | No | No |
| ArrayDequeue | Yes | No | No | Yes | No | No | No |
| HashMap | No | Yes, if accessing value by key | Yes | No, if consider keys | Yes | No | No |
| LinkedHashMap | Yes | Yes, if accessing value by key | Yes | No, if consider keys | Yes | No | No |
| TreeMap | Yes, if consider comparator ordering | Yes, if accessing value by key | Yes | No, if consider keys | Comparator defined | No | No |