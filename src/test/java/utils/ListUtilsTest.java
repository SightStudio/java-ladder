package utils;

import java.util.Arrays;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ListUtilsTest {

  @Test
  @DisplayName("ListUtils.hasElementAt() | 리스트에 존재하지 않는 인덱스값으로 접근하면 false를 반환한다. ")
  void 리스트에_존재하지_않는_인덱스값으로_접근() {
    // given
    List<Integer> list = Arrays.asList(1, 2, 3);

    // when
    boolean hasElementAt = ListUtils.hasElementAt(list, 3);

    // then
    Assertions.assertThat(hasElementAt)
        .isFalse();
  }

  @Test
  @DisplayName("ListUtils.hasElementAt() | 리스트에 null이 있으면 해당 인덱스에 값이 없는것과 같다고 판단한다.")
  void 리스트에_존재하지_않는_인덱스값으로_접근_null_포함() {
    // given
    List<Integer> list = Arrays.asList(1, 2, 3, null);

    // when
    boolean hasElementAt = ListUtils.hasElementAt(list, 3);

    // then
    Assertions.assertThat(hasElementAt)
        .isFalse();
  }

  @Test
  @DisplayName("ListUtils.hasElementAt() | 리스트에 존재하는 인덱스값으로 접근하면 true 를 반환한다. ")
  void 리스트에_존재하는_인덱스값으로_접근() {
    // given
    List<Integer> list = Arrays.asList(1, 2, 3);

    // when
    boolean hasElementAt = ListUtils.hasElementAt(list, 2);

    // then
    Assertions.assertThat(hasElementAt)
        .isTrue();
  }
}