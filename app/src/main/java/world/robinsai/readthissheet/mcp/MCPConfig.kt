package world.robinsai.readthissheet.mcp

import com.modelcontextprotocol.core.MCPClient
import com.modelcontextprotocol.core.MCPContext
import com.modelcontextprotocol.core.MCPMessage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MCPConfig {
    private val client = MCPClient.Builder()
        .setApiKey(BuildConfig.MCP_API_KEY)
        .setEndpoint(BuildConfig.MCP_ENDPOINT)
        .build()

    private val _currentContext = MutableStateFlow<MCPContext?>(null)
    val currentContext: StateFlow<MCPContext?> = _currentContext

    fun createMusicAnalysisContext(
        sheetMusic: SheetMusicData,
        userSkillLevel: SkillLevel,
        learningPreferences: LearningPreferences
    ): MCPContext {
        return MCPContext.Builder()
            .addParameter("music_type", sheetMusic.type)
            .addParameter("complexity_level", sheetMusic.complexityLevel)
            .addParameter("user_skill", userSkillLevel)
            .addParameter("learning_style", learningPreferences.style)
            .addParameter("previous_interactions", learningPreferences.history)
            .build()
    }

    suspend fun getTheoryExplanation(
        context: MCPContext,
        musicalElement: MusicalElement
    ): MCPMessage {
        return client.sendMessage(
            context,
            "Explain ${musicalElement.type} in the context of ${musicalElement.context}",
            MessageType.THEORY_EXPLANATION
        )
    }

    suspend fun getPracticeRecommendations(
        context: MCPContext,
        performance: PerformanceMetrics
    ): MCPMessage {
        return client.sendMessage(
            context,
            "Suggest practice exercises based on recent performance",
            MessageType.PRACTICE_RECOMMENDATION
        )
    }

    data class SheetMusicData(
        val type: String,
        val complexityLevel: Int,
        val timeSignature: String,
        val keySignature: String
    )

    enum class SkillLevel {
        BEGINNER,
        INTERMEDIATE,
        ADVANCED,
        EXPERT
    }

    data class LearningPreferences(
        val style: String,
        val history: List<String>,
        val preferredInstruments: List<String>
    )

    data class MusicalElement(
        val type: String, // e.g., "chord progression", "key signature"
        val context: String
    )

    data class PerformanceMetrics(
        val accuracy: Float,
        val tempo: Int,
        val challengingAreas: List<String>
    )

    enum class MessageType {
        THEORY_EXPLANATION,
        PRACTICE_RECOMMENDATION,
        PERFORMANCE_FEEDBACK
    }
}
