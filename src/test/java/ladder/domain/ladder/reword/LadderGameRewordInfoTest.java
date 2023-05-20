package ladder.domain.ladder.reword;

import config.BaseTest;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LadderGameRewordInfoTest extends BaseTest {

  @Test
  @DisplayName("LadderGameReword | 사다리게임 보상에서 꽝의 보상은 0으로 간주한다")
  void 사다리게임_보상에서_꽝의_보상은_0으로_간주한다() {
    // given
    List<String> 라인별_보상_목록 = List.of("꽝");

    // when
    LadderGameRewordInfo 게임_보상_정보 = new LadderGameRewordInfo(라인별_보상_목록);

    // then
    Assertions.assertThat(게임_보상_정보.getRewordAt(0))
        .isEqualTo(new LadderGameReword(0));
  }
}