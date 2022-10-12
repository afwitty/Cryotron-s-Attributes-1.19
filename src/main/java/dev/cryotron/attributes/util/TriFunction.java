package dev.cryotron.attributes.util;

import java.util.Objects;
import java.util.function.Function;

public interface TriFunction<S, T, U, R> {
  R apply(S paramS, T paramT, U paramU);
  
  default <V> TriFunction<S, T, U, V> andThen(Function<? super R, ? extends V> after) {
    Objects.requireNonNull(after);
    return (s, t, u) -> after.apply(apply((S)s, (T)t, (U)u));
  }
}
