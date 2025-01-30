# ReadThisSheet! Architecture Documentation
  2 | 
  3 | ## System Overview
  4 | ```mermaid
  5 | graph TB
  6 |     subgraph UI Layer
  7 |         MA[MainActivity]
  8 |         AR[ARSheetMusicActivity]
  9 |         Auth[AuthActivity]
 10 |     end
 11 |     
 12 |     subgraph Core Layer
 13 |         SMA[SheetMusicAnalyzer]
 14 |         MTE[MusicTheoryEngine]
 15 |         AE[AudioEngine]
 16 |         MCP[MCPConfig]
 17 |     end
 18 |     
 19 |     subgraph Services
 20 |         BM[BillingManager]
 21 |         FB[Firebase Auth]
 22 |         AR_CORE[AR Core]
 23 |         ML[ML Kit]
 24 |         MCP_SERVICE[MCP Service]
 25 |     end
 26 |     
 27 |     MA --> AR
 28 |     MA --> Auth
 29 |     AR --> SMA
 30 |     AR --> AR_CORE
 31 |     SMA --> ML
 32 |     SMA --> MTE
 33 |     MTE --> MCP
 34 |     MCP --> MCP_SERVICE
 35 |     AR --> AE
 36 |     Auth --> FB
 37 |     MA --> BM
 38 | ```
 39 | 
 40 | ## Class Diagram
 41 | ```mermaid
 42 | classDiagram
 43 |     class ReadThisSheetApp {
 44 |         +onCreate()
 45 |     }
 46 |     
 47 |     class MainActivity {
 48 |         -binding: ActivityMainBinding
 49 |         -auth: FirebaseAuth
 50 |         +onCreate()
 51 |         -setupUI()
 52 |         -checkPermissions()
 53 |     }
 54 |     
 55 |     class ARSheetMusicActivity {
 56 |         -binding: ActivityArSheetMusicBinding
 57 |         -sceneView: ArSceneView
 58 |         -sheetMusicAnalyzer: SheetMusicAnalyzer
 59 |         -musicTheoryEngine: MusicTheoryEngine
 60 |         -audioEngine: AudioEngine
 61 |         +onCreate()
 62 |         -setupAR()
 63 |         -setupAnalyzers()
 64 |         -setupTouchHandling()
 65 |     }
 66 |     
 67 |     class SheetMusicAnalyzer {
 68 |         -keySignature: StateFlow<String>
 69 |         -chordProgression: StateFlow<List<String>>
 70 |         -notes: StateFlow<List<Note>>
 71 |         +analyzeNotation(text: Text)
 72 |     }
 73 |     
 74 |     class MusicTheoryEngine {
 75 |         -noteNames: List<String>
 76 |         +identifyKeySignature()
 77 |         +analyzeChordProgression()
 78 |         +identifyCounterpoint()
 79 |     }
 80 |     
 81 |     class AudioEngine {
 82 |         -player: ExoPlayer
 83 |         -noteCache: ConcurrentHashMap
 84 |         +playNote()
 85 |         +playChord()
 86 |         +playMelody()
 87 |     }
 88 |     
 89 |     class BillingManager {
 90 |         -billingClient: BillingClient
 91 |         -subscriptionStatus: StateFlow
 92 |         +launchBillingFlow()
 93 |     }
 94 |     
 95 |     class MCPConfig {
 96 |         -mcpService: MCPService
 97 |         +createMusicAnalysisContext()
 98 |         +sendTheoryQuery()
 99 |     }
100 |     
101 |     MainActivity --|> AppCompatActivity
102 |     ARSheetMusicActivity --|> AppCompatActivity
103 |     MainActivity ..> BillingManager
104 |     ARSheetMusicActivity ..> SheetMusicAnalyzer
105 |     ARSheetMusicActivity ..> MusicTheoryEngine
106 |     ARSheetMusicActivity ..> AudioEngine
107 |     MusicTheoryEngine ..> MCPConfig
108 | ```
109 | 
110 | ## Sequence Diagram (Note Recognition Flow)
111 | ```mermaid
112 | sequenceDiagram
113 |     participant User
114 |     participant AR as ARSheetMusicActivity
115 |     participant SMA as SheetMusicAnalyzer
116 |     participant MTE as MusicTheoryEngine
117 |     participant AE as AudioEngine
118 |     
119 |     User->>AR: Points camera at sheet music
120 |     AR->>SMA: Process camera frame
121 |     SMA->>MTE: Analyze notation
122 |     MTE-->>SMA: Return music analysis
123 |     SMA-->>AR: Update UI with annotations
124 |     
125 |     User->>AR: Taps note
126 |     AR->>AE: Request note playback
127 |     AE-->>User: Plays note sound
128 | ```
129 | 
130 | ## MCP Integration
131 | ```mermaid
132 | sequenceDiagram
133 |     participant User
134 |     participant App
135 |     participant MCP
136 |     participant Theory
137 |     
138 |     User->>App: Analyzes sheet music
139 |     App->>MCP: Create music analysis context
140 |     MCP-->>App: Context established
141 |     
142 |     User->>App: Requests theory explanation
143 |     App->>MCP: Send theory query with context
144 |     MCP->>Theory: Process with context
145 |     Theory-->>MCP: Generate explanation
146 |     MCP-->>App: Contextualized explanation
147 |     App-->>User: Display explanation
148 |     
149 |     Note over App,MCP: Context includes:<br/>1. User skill level<br/>2. Learning preferences<br/>3. Previous interactions
150 | ```
151 | 
152 | ## Component Architecture
153 | ```mermaid
154 | graph TB
155 |     subgraph Client
156 |         UI[UI Components]
157 |         Core[Core Logic]
158 |         Cache[Local Cache]
159 |     end
160 |     
161 |     subgraph Cloud
162 |         Auth[Authentication]
163 |         Storage[Cloud Storage]
164 |         Analytics[Analytics]
165 |     end
166 |     
167 |     subgraph Services
168 |         Payment[Payment Processing]
169 |         ML[ML Services]
170 |         MCP[MCP Service]
171 |     end
172 |     
173 |     UI --> Core
174 |     Core --> Cache
175 |     Core --> Auth
176 |     Core --> Storage
177 |     UI --> Analytics
178 |     UI --> Payment
179 |     Core --> ML
180 |     Core --> MCP
