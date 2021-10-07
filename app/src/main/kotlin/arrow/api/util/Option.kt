package arrow.api.util

import arrow.core.Option
import arrow.core.toOption
import java.util.*

fun <A> Optional<A>.toArrowOption(): Option<A> = this.orElse(null).toOption()