package wow.doge.http4sdemo

import cats.data.ValidatedNec
import eu.timepit.refined.api.Refined
import eu.timepit.refined.api.RefinedTypeOps
import eu.timepit.refined.collection._
import eu.timepit.refined.numeric._
import eu.timepit.refined.types.string.NonEmptyFiniteString

package object refinements {
  type RefinementValidation[+A] = ValidatedNec[String, A]
  type IdRefinement = Int Refined Positive
  object IdRefinement extends RefinedTypeOps[IdRefinement, Int] {
    //for use in http router dsl, which takes a string as input
    def unapply(s: String): Option[IdRefinement] =
      s.toIntOption.flatMap(unapply)
  }

  type StringRefinement = String Refined Size[Interval.Closed[5, 50]]
  object StringRefinement extends RefinedTypeOps[StringRefinement, String]

  type PaginationRefinement = Int Refined Interval.Closed[0, 50]
  object PaginationRefinement extends RefinedTypeOps[PaginationRefinement, Int]

  type SearchQuery = NonEmptyFiniteString[25]
}
