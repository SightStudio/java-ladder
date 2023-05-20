package ladder.domain.ladder.ladder;

import exception.ExceptionCode;
import exception.LadderGameException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import ladder.domain.ladder.line.HorizontalLadderLine;
import ladder.domain.ladder.line.point.LinePoint;
import ladder.domain.random.DefaultRandomBooleanGenerator;
import ladder.domain.random.RandomBooleanGenerator;

public class DefaultLadderGenerator implements LadderGenerator {

  private final RandomBooleanGenerator randomBooleanGenerator;
  private static final int MIN_LADDER_HEIGHT_SIZE = 1;

  public DefaultLadderGenerator() {
    this.randomBooleanGenerator = new DefaultRandomBooleanGenerator();
  }

  @Override
  public Ladder generateLadderLines(int playerCnt, int ladderHeight) {
    throwIfHeightNotValid(ladderHeight);
    final List<HorizontalLadderLine> lines = IntStream.range(0, ladderHeight)
        .mapToObj(i -> new HorizontalLadderLine(playerCnt))
        .map(this::connectPointInLineIfPossible)
        .collect(Collectors.toList());

    return new Ladder(lines);
  }

  private void throwIfHeightNotValid(int ladderHeight) {
    if(MIN_LADDER_HEIGHT_SIZE > ladderHeight) {
      throw new LadderGameException(ExceptionCode.MIN_LADDER_HEIGHT_REQUIRED);
    }
  }

  /**
   * 라인을 연결할 수 있는 경우애서 랜덤으로 연결한다.
   */
  private HorizontalLadderLine connectPointInLineIfPossible(HorizontalLadderLine line) {
    int pointSize = line.getPointSize();
    for (int i = 0; i < pointSize; i++) {
      connectPointIfPossible(line, i);
    }

    return line;
  }

  private void connectPointIfPossible(HorizontalLadderLine line, int linePointIdx) {
    final boolean allowedToConnect = randomBooleanGenerator.getBoolean();
    if (!allowedToConnect) {
      return;
    }

    final LinePoint point = line.getPointAt(linePointIdx);
    if (point.isEndPoint()) {
      return;
    }

    final LinePoint nextPoint = line.getPointAt(linePointIdx + 1);
    if (point.isStartPoint()) {
      point.connect(nextPoint);
      return;
    }

    // 이전 라인이 이어져있으면 넘어감
    if (line.isPreviousPointConnected(point)) {
      return;
    }

    point.connect(nextPoint);
  }
}