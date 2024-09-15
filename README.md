# anywhere-database
어디서든 연결 가능한 웹 전용 데이터베이스 클라이언트


```mermaid
graph TD
    subgraph Client Side
        A[Client] --> B[Input Connection Info]
        B --> C[Send Connection Request]
    end

    subgraph Server Side
        C --> D[Receive Request]
        D --> E[Connect to Database]
        E --> F[Connection Success/Failure]
        F --> G[Send Response to Client]
        G --> H[Receive Query from Client]
        H --> I[Send Query to Database]
        I --> J[Receive Query Result]
        J --> K[Send Result to Client]
    end

    subgraph Database Side
        E --> L[Database Connection]
        I --> M[Execute Query]
        M --> N[Return Result]
    end

    K --> A[Display Result]

```