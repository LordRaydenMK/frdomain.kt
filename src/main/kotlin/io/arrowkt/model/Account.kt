package io.arrowkt.model

import arrow.core.Option
import arrow.core.getOrElse
import arrow.core.some
import arrow.data.ValidatedNel
import arrow.data.invalidNel
import arrow.data.validNel
import java.time.LocalDate

typealias ValidationResult<A> = ValidatedNel<String, A>

fun validateOpenCloseDate(
    od: LocalDate,
    cd: Option<LocalDate>
): ValidationResult<Pair<Option<LocalDate>, Option<LocalDate>>> =
    cd.map { c ->
        if (c.isBefore(od)) "Close date $c cannot be earlier than open date $od".invalidNel()
        else Pair(od.some(), cd).validNel()
    }.getOrElse { Pair(od.some(), cd).validNel() }