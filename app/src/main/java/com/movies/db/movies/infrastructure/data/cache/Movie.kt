import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

open class Movie : RealmObject {

    @PrimaryKey
    var _id: ObjectId = ObjectId()
    val adult: Boolean = false
    val backdropPath: String = ""
    val genreIds: List<Long> = emptyList()
    val id: Long = 0
    val originalLanguage: String = ""
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Long
    init {
        this.checkedIn = false
    }
}