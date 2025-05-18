import services.FagSystem
import services.LetterService

data class ApiContext(
    val fagSystem: FagSystem = FagSystem(),
    val letterService: LetterService = LetterService(),
)
